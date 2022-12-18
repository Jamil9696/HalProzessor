# ================== non repeating functions ==========
sendToClient(){
  echo "$args" | timeout 1 nc -n 127.0.0.1 55555
}
listenToClient(){
  args=$(nc -l -p 54321 -v &)
}

# ================== register client ===================

saveUser(){
  listenToClient
  grepUser
  if [ -z "$hasValue" ] ; then
      echo $args >> saveUser.txt
      args="User: $firstArg has been registered"
      sendToClient
  else
      args="User: $firstArg is already registered"
      sendToClient
  fi
}

# ================== login client =====================

grepUser(){
  firstArg=$(echo -n "$args" | awk '{print $1}')
  secondArg=$(echo -n "$args" | awk '{print $2}')
  thirdArg=$(echo -n "$args" | awk '{print $3}')
  hasValue=$(cat saveUser.txt | grep "$firstArg")
  echo "Log: find User: $hasValue"

}

lookForUser(){
  listenToClient
  grepUser

  if [ -z "$hasValue" ] ; then
    args="User is not registered"
    sendToClient
    return
  else
    args="$thirdArg"
    sendToClient
  fi

}

#================= program start ========================

echo "Server is running"
while true
do
   listenToClient
   case $args in
      r)
         saveUser
         ;;
      l)
         lookForUser
         ;;
   esac

done