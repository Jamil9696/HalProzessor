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
  echo "send credentials: $username $newHash $n"
  args="$username $newHash $n"
  sendToServer
}


# ============================= Register Client ===============
initNewPassword(){
  echo "Enter new password"
  read newHash
  c=0
  hashPw
}

register(){
  echo "----- R E G I S T E R -----"
  echo "username: "
  read username
  initNewPassword
  sendCredentials
  listenToServer
  echo "$args"

}

# ============================  Login Client ==================
login(){
  echo "----- L O G I N -----"
    echo "username: "
    read username
    echo "password: "
    read newHash

    # server sendet c ->
    c=0
    hashPw
    sendCredentials
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