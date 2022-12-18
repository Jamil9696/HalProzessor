# ================== non repeating functions ==========
sendToClient(){
  echo "$args" | timeout 1 nc -n 127.0.0.1 55555
}
listenToClient(){
  args=$(nc -l -p 54321 -v &)
}

compareHashValues(){

  # e^n == e(p)
  echo "e(hash): $newHash"
  newHash=$(echo -n "$args" | sha1sum)
  secondArg=$(echo -n "$hasValue" | awk '{print $2}')
   if [ "$newHash" = "$secondArg" ] ; then

        args="true"
   else
        args="Credentials are wrong"
   fi
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

  # when user is not registered
  if [ -z "$hasValue" ] ; then
    args="false"
    sendToClient
    return
  fi

  # send c value to client
  thirdArg=$(echo -n "$hasValue" | awk '{print $3}')
  echo "thirdArg: $thirdArg"

  args=1
  echo "send c: 1"
  sendToClient
  listenToClient

  # compare hash values
  compareHashValues
  sendToClient


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