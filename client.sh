# ============================ non repeating functions =========
sendToServer(){
  echo  "$args" | timeout 1 nc -n 127.0.0.1 54321
}

listenToServer(){
  args=$(nc -l -p 55555 &)
}

hashPw(){
   n=10
   i=0
   newHash="$password"
   while [ "$i" -le "$((n - c))" ]; do
      newHash=$(echo -n "$newHash" | sha1sum | awk '{print $1}')
      i=$(( i + 1 ))
   done
}

sendUsername(){
  args="$username"
  sendToServer
}
sendHashPW(){
  args="$newHash"
  sendToServer
}
enterUserNamePwd(){
  echo "username: "
  read username
  echo "Enter new password"
  read password
  c=0
}
dataFetch(){
  echo "file name: "
  read path
  dir="/etc/hosts"
  #Look for it
  if [ "$path" = "/etc/hosts" ] ; then
      echo "file exists"
      args=$dir
      sendToServer
      listenToServer
      echo "$args"
  else
      echo "Path invalid"
  fi
}
# ============================= Register Client ===============
register(){
  echo "----- R E G I S T E R -----"
  enterUserNamePwd
  sendCredentials
  listenToServer
  echo "$args"
}
sendCredentials(){
  hashPw
  args="$username $newHash $n"
  sendToServer
}
# ============================  Login Client ==================
login(){
  echo "----- L O G I N -----"
  #send Credentials to server
  enterUserNamePwd
  sendUsername
  listenToServer

  # if( user doesn't exists )
  if [ "$args" = "false" ] ; then
      echo "credentials are wrong"
      return
  fi
  # else {server has sent c to client}
  c=$args
  echo "c-value: $c"
  hashPw
  sendHashPW
  # wait for response
  listenToServer
  echo "$args"
  dataFetch
}
#======================== Program start ================
while true
do
   echo "======  Lab: Shell Programming (BS)  ======"
   echo "    r    Register"
   echo "    l    Login"
   echo "    q    Quit"
   echo "select option: "
   read args
   case $args in
   r)
      sendToServer
      register

      ;;
   l)
      sendToServer
      login
      ;;
   q)
      echo "quit"
      exit
      ;;
   esac
done