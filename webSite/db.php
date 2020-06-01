<?php
$mysqli = new mysqli("127.0.0.1","root","","my_ame97software");  //Connessione al database , out the function to get the mysqli last id..
function sendQuery($query,$mysqli){
$res= $mysqli->query($query);
return $res;
}
?>