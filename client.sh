
receive(){
  nc -l -p 4444
}

connectToServer(){
   nc localhost 54321 &
}
send(){
  echo "Your input: $choice"
  echo $choice | nc localhost 54321 &
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




done