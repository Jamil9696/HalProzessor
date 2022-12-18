connectionToClient(){
  nc -l -p 55555 -v &
}

send(){
  read choice
  echo "Your input: $choice"
  echo  $choice | timeout 0.5 nc -n 127.0.0.1 54321

}

sendCredentials(){
  echo "send credentials: $username $newHash $n"
  echo "$username $newHash $n" | timeout 1 nc -n 127.0.0.1 54321
}

# generate hash iterations

initNewPassword(){
  echo "Enter new password"
  read newHash

  c=0
  hashPw
}

hashPw(){
   n=10
   i=0
   while [ "$i" -le "$((n - c))" ]; do
      newHash=$(echo -n "$newHash" | sha1sum | awk '{print $1}')
      echo "pwd $i: $newHash"
      i=$(( i + 1 ))
   done
}
register(){
  echo "----- R E G I S T E R -----"
  echo "username: "
  read username
  initNewPassword


}

login(){
  echo "----- L O G I N -----"
    echo "username: "
    read username
    echo "password: "
    read newHash

    # server sendet c ->
    c=0
    hashPw
    sendCredentials
}


connectionToClient

while true
do
   echo "======  Lab: Shell Programming (BS)  ======"
   echo "    r    Register"
   echo "    l    Login"
   echo "    q    Quit"
   read choice

   case $choice in
   r)
      register
      ;;
   l)
      login
      ;;
   q)
      echo "quit"
      ;;
   esac
done