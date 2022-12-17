
receive(){
     if  [ -s data.txt ]; then
        echo "received " | cat data.txt
        echo "received done"
        truncate -s 0 data.txt
     fi

}

send(){

    echo "received" | nc localhost 4444 > data.txt
}

startServer(){
  nc -lvp 4447 > data.txt &
}
startServer
echo "connection done"
while (true)
do
    receive
done








