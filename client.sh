


send(){
  read choice
  echo "Your input: $choice"
  echo  $choice | timeout 0.5 nc -n 127.0.0.1 54321

}

while true
do
   echo "======  Lab: Shell Programming (BS)  ======"
   echo "    r    Register"
   echo "    l    Login"
   echo "    q    Quit"

   send




done