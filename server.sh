# ================== non repeating functions ==========
sendToClient(){
  echo "$args" | timeout 1 nc -n 127.0.0.1 55555
}
listenToClient(){
  args=$(nc -l -p 54321 &)
}
compareHashValues(){

  # e^n == e(p)
  newHash=$(echo -n "$args" | sha1sum | awk '{print $1}')
  firstArg=$(echo -n "$hasValue" | awk '{print $1}')
  secondArg=$(echo -n "$hasValue" | awk '{print $2}')
  thirdArg=$(echo -n "$hasValue" | awk '{print $3}')
  fourthArg=$(echo -n "$hasValue" | awk '{print $4}')

  echo " new index2:: $(( fourthArg + 1 ))"
   if [ "$newHash" = "$secondArg" ] ; then
        sed -i "s/$hasValue/$firstArg $args $thirdArg $(( fourthArg + 1 ))/" saveUser.txt
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
      echo $args "1" >> saveUser.txt
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
  fourthArg=$(echo -n "$hasValue" | awk '{print $4}')

  args=$fourthArg
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