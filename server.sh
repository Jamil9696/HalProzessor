receive(){

     if  [ -s data.txt ]; then
        echo "" | cat data.txt
        succeedMessage
        truncate -s 0 data.txt
     fi

}

succeedMessage(){
  readData=$(cat data.txt)
  echo "Server sends back: $readData" | timeout 1 nc -n 127.0.0.1 55555

}

connectionToServer(){
  nc -l -p 54321 -v > data.txt &
  echo "Server is running"
}

connectionToServer
while true
do
    receive
done








