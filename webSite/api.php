<?php
require("db.php");

if(isset($_GET['id_app']) && isset($_GET['token'])){


$id_app = $_GET['id_app'];
$token = $_GET['token'];

$res=sendQuery("SELECT * FROM wikiArt WHERE id_app='".$mysqli->real_escape_string($id_app)."'",$mysqli);
$row = mysqli_fetch_array($res);

echo json_encode(array('nome'=>$row['nome'],'autore'=>$row['autore'],'descrizione'=>$row['descrizione'],'storia'=>$row['storia'],'luogo'=>$row['luogo'],'anno_creazione'=>$row['anno_creazione'],'url_video'=>$row['url_video'],'url_audio'=>$row['url_audio'],'url_image'=>$row['url_image']));

}
else if(isset($_GET['search']) && isset($_GET['token'])){

$search = $_GET['search'];
$token = $_GET['token'];
    
$res=sendQuery("SELECT * FROM wikiArt WHERE nome LIKE '%".$search."%'",$mysqli);

while($row = mysqli_fetch_array($res)){
	echo json_encode(array('nome'=>$row['nome'],'autore'=>$row['autore'],'descrizione'=>$row['descrizione'],'storia'=>$row['storia'],'luogo'=>$row['luogo'],'anno_creazione'=>$row['anno_creazione'],'url_video'=>$row['url_video'],'url_audio'=>$row['url_audio'],'url_image'=>$row['url_image']));
	echo "#;@";
	}
}



?>