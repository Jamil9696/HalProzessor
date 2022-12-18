
receive(){
     if  [ -s data.txt ]; then
        echo "" | cat data.txt
        succeedMessage
        args=$(cat data.txt)

        echo "works with $args"

        truncate -s 0 data.txt
     fi
}


succeedMessage(){
  readData=$(cat data.txt)
  echo "Server sends back: $readData" | timeout 1 nc -n 127.0.0.1 55555

}

connectionToServer(){
  nc -l -p 54321 -v > registerClient.txt &
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
#succeedMessage

while true
do
    receive
done