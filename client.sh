
receive(){
  nc -l -p 4444
}

connectToServer(){
   nc -v localhost 4447 &
   ping localhost 4447

}
send(){
  echo $choice | nc localhost 4447
}
connectToServer

while (true)
do
   echo "======  Lab: Shell Programming (BS)  ======"
   echo "    r    Register"
   echo "    l    Login"
   echo "    q    Quit"
   read choice
   send
   receive




done