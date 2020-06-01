<?php

	/* setta il formato di risposta, json */
	header('Content-Type: text/json');
	require_once("configuration.php");

	$action = $_POST['action'];

	/* prende la query effettuata sul databse */
	$query_string = "";

	/* fa una azione diversa in base al tipo(aggiungi, togli, ecc) */
	switch($action) {

		case "load" :
			loadData();
		break;
		case "insert" :
			//echo($action);
			insertData();
		break;
		case "update" :
	   		updateData();
		break;
		case "delete" :
			deleteData();
		break;
	}

	function loadData() {

		$mysqli = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);



		if(isset($_POST['id_app']))
			$query_string = "SELECT * FROM wikiArt WHERE id_app='".$mysqli->real_escape_string($_POST['id_app'])."'";
		else if(isset($_POST['nome'])){
			$query_string = "SELECT * FROM wikiArt WHERE nome LIKE '%".$mysqli->real_escape_string($_POST['nome'])."%'";
            }
		else if(isset($_POST['id']))
			$query_string = "SELECT * FROM wikiArt WHERE id='".$mysqli->real_escape_string($_POST['id'])."'";
		else
			$query_string = "SELECT * FROM wikiArt";

		

    	// esegui la query
		$res = $mysqli->query($query_string);

    	$results = array();

    	// cicla sul risultato
		while ($row = $res->fetch_array(MYSQLI_ASSOC)) {  //fetch_array allows you to access data stored in the result returned from a successful mysql_query
			$result = array('id'=>$row['id'],'nome'=>$row['nome'],'autore'=>$row['autore'],'descrizione'=>$row['descrizione'],'storia'=>$row['storia'],'luogo'=>$row['luogo'],'anno'=>$row['anno_creazione'],'url_video'=>$row['url_video'],'url_audio'=>$row['url_audio'],'url_image'=>$row['url_image'],'id_app'=>$row['id_app']);
			array_push($results, $result);
		}

    	$response = array('results' => $results, 'type' => 'load');
		// trasformi l'array in formato JSON
		echo json_encode($response);
}

	function insertData() {

/*		if (isset($_POST['text'])) {  //$_POST invia i dati ad una applicazione PHP tramite i form HTML, isset guarda se è NULL
			$to_do_text = $_POST['text'];
		} else {
			echo "you didn't specify a text";
			return;
		}

*/

		$query_string = "INSERT INTO `wikiArt`(`nome`, `autore`, `descrizione`, `storia`, `luogo`, `anno_creazione`, `id_app`, `url_video`, `url_audio`, `url_image`) 
		VALUES ('".$_POST['nome']."','".$_POST['autore']."','".$_POST['descrizione']."','".$_POST['storia']."','".$_POST['luogo']."','".$_POST['anno_creazione']."','".$_POST['id_app']."','".$_POST['url_video']."','".$_POST['url_audio']."','".$_POST['url_image']."')";
		$mysqli = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

    	// esegui la query per inserire il to do nel db
		$result = $mysqli->query($query_string);


    	$query_string = 'SELECT * FROM wikiArt WHERE id=' . $mysqli->insert_id;  // . concatena le stringhe

		//$mysqli = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

    	// esegui la query per rileggere il record inserito
		$res = $mysqli->query($query_string);

    	$results = array();

    	// cicla sul risultato
		while ($row = $res->fetch_array(MYSQLI_ASSOC)) {  //fetch_array allows you to access data stored in the result returned from a successful mysql_query
			$result = array('nome'=>$row['nome'],'autore'=>$row['autore'],'descrizione'=>$row['descrizione'],'storia'=>$row['storia'],'luogo'=>$row['luogo'],'anno_creazione'=>$row['anno_creazione'],'url_video'=>$row['url_video'],'url_audio'=>$row['url_audio'],'url_image'=>$row['url_image']);
			array_push($results, $result);
		}

    	$response = array('results' => $results, 'type' => 'insert');

		// encodo l'array in JSON
		echo json_encode($response);

	}

	function updateData() {
		if (isset($_POST['id'])) $id = $_POST['id'];
		$query_string = "UPDATE `wikiArt` SET `nome`='".$_POST['nome']."',`autore`='".$_POST['autore']."',`descrizione`='".$_POST['descrizione']."',`storia`='".$_POST['storia']."',`luogo`='".$_POST['luogo']."',`anno_creazione`='".$_POST['anno_creazione']."',`id_app`='".$_POST['id_app']."',`url_video`='".$_POST['url_video']."',`url_audio`='".$_POST['url_audio']."',`url_image`='".$_POST['url_image']."' WHERE id=".$id;

		$mysqli = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

    	// esegui la query
		$result = $mysqli->query($query_string);

		//echo $query_string;

    	if($mysqli->affected_rows > 0) {   //affected_rows get the number of affected rows by the last INSERT, UPDATE, REPLACE or DELETE query
		// encodo l'array in JSON
	  		$response = array('updated' => true, 'id' => $id, 'type' => 'update');
		} else {
	  		$response = array('updated' => false, 'id' => $id, 'type' => 'update');
		}
	echo json_encode($response);
}


	function deleteData() {

		if (isset($_POST['id'])) $id = $_POST['id'];

			$query_string = "DELETE FROM wikiArt WHERE id=".$_POST['id'];

			$mysqli = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

    		// esegui la query
			$result = $mysqli->query($query_string);

    		if($mysqli->affected_rows > 0) {
	  			$response = array('deleted' => true, 'id' => $id, 'type' => 'delete');
			} else {
	  			$response = array('deleted' => false, 'id' => $id, 'type' => 'delete');
	  		}

			echo json_encode($response);
	}

?>