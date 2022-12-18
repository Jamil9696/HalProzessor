# ============================ non repeating functions =========
sendToServer(){
  echo  "$args" | timeout 1 nc -n 127.0.0.1 54321
}

listenToServer(){
  args=$(nc -l -p 55555 -v &)
}

hashPw(){
   n=10
   i=0
   while [ "$i" -le "$((n - c))" ]; do
      newHash=$(echo -n "$newHash" | sha1sum | awk '{print $1}')
      i=$(( i + 1 ))
   done
}

sendCredentials(){
  hashPw
  echo "send credentials: $username $newHash $n"
  args="$username $newHash $n"
  sendToServer
}
sendUsername(){
  args="$username"
  echo "send username: $args"
  sendToServer
}

sendHashPW(){
  args="$hashPw"
  echo "send hashPw: $args"
  sendToServer
}



# ============================= Register Client ===============
enterUserNamePwd(){
  echo "username: "
  read username
  echo "Enter new password"
  read newHash
  c=0
}

register(){
  echo "----- R E G I S T E R -----"
  enterUserNamePwd
  sendCredentials
  listenToServer
  echo "$args"
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

}

#======================== Program start ================
while true
do
   echo "======  Lab: Shell Programming (BS)  ======"
   echo "    r    Register"
   echo "    l    Login"
   echo "    q    Quit"
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
      ;;
   esac
done