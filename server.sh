
receive(){
     if  [ -s registerClient.txt  ]; then
        echo "" | cat registerClient.txt
        succeedMessage
        args=$(cat registerClient.txt)
        echo "works with $args"

        truncate -s 0 registerClient.txt
     fi
}
receiveCredentials(){

  if [ -z "$args" ] ; then
     return
  else
       echo "works with $args"
       succeedMessage
  fi
}


succeedMessage(){
  #readData=$(cat registerClient.txt)
  echo "Server sends back: $args" | timeout 1 nc -n 127.0.0.1 55555
  args=noArgs
}

connectionToServer(){

  args=$(nc -l -p 54321 -v &)
  echo "Server is running"
}

lamportAuthorisation(){
  c=1
  while [ "$c" -le "$n" ]; do
      if  [ "$P" == "$(echo -n "$newHash" | sha1sum | awk '{print $1}')" ]; then
          i=$(( i + 1 ))
          P = $( (Pc) )
      fi
  done
}

connectionToServer
while true
do
    receiveCredentials
done