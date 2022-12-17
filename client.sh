connectionToClient(){
  nc -l -p 55555 -v &
}

send(){
  read choice
  echo "Your input: $choice"
  echo  $choice | timeout 0.5 nc -n 127.0.0.1 54321

}
connectionToClient

while true
do
   echo "======  Lab: Shell Programming (BS)  ======"
   echo "    r    Register"
   echo "    l    Login"
   echo "    q    Quit"
   send

done