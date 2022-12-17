connectionToClient(){
  nc -l -p 55555 -v &
}

send(){
  read choice
  echo "Your input: $choice"
  echo  $choice | timeout 0.5 nc -n 127.0.0.1 54321

}

# generate hash iterations
initNewPassword(){
   echo "Enter new password"
   read newHash
   echo "Enter password strength (integer Value)"
   read n

   i=0
   while [ "$i" -le "$n" ]; do
       newHash=$(openssl passwd -1 -salt 12 "$newHash")
       echo "pwd $i: $newHash"
       i=$(( i + 1 ))
   done
   echo "your final hash is: $newHash"
}

connectionToClient
initNewPassword

while true
do
   echo "======  Lab: Shell Programming (BS)  ======"
   echo "    r    Register"
   echo "    l    Login"
   echo "    q    Quit"
   send
done