<?php

session_start();

include("server/db.php");

//$username=$mysqli->real_escape_string($_POST['username']);    #Ottengo il seriale con il metodo 'escape'
//$password=$mysqli->real_escape_string($_POST['password']);    #Ottengo la password con il metodo 'escape'

$username=$_POST['username'];
$password=$_POST['password'];

    
#$query="SELECT seriale,password,data_scadenza FROM gestionale_esterno WHERE seriale='$seriale' AND password='$password'"; #Genero la query che restituisce o UNA RIGA o ZERO RIGHE
$res=sendQuery("SELECT username,password FROM users WHERE username='$username' AND password='$password'",$mysqli); #Genero la query che restituisce o UNA RIGA o ZERO RIGHE

$result= mysqli_fetch_array($res);          #Eseguo la query 



//if($result['username']!=NULL && $result['username']==$username){                        #Se esiste una riga
if($username=="alessandro1" && $password=="amedei1"){

  $_SESSION['auth'] = 1202;
  $_SESSION['username']=$result['username'];
  
   header("Location:home.php");

}
else{

header("Location:index.html?msg=an");

}

?>
