receive(){

     if  [ -s data.txt ]; then
        echo "received" | cat data.txt
        echo "received done"
        truncate -s 0 data.txt
     fi

}

startServer(){
  echo "startServer"
  nc -l -p 54321 -v > data.txt &
  echo "startServer done"
}
startServer
while true
do
    receive
done








