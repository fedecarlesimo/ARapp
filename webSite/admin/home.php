<?php
require("security.php")
?>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Home</title>
  <meta charset="utf-8">
  <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style>
td, th{
  text-align:center;
}
.column{
  margin-top: 12px;
}
.image{
  max-width:250px;
  max-height:400px;
}
.audioplayer{
  width:150px;
}
</style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
<a class="navbar-brand" href="#">Admin</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    
      <li class="nav-item active">
        <a class="nav-link" href="#">Home<span class="sr-only">(current)</span></a>
      </li>

      <li class="nav-item">
      <a class="nav-link" href="wtc.php">Gestione WTC / WTO</a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link" href="logout.php">Logout</a>
      </li>
  </div>
</nav>

<br>

<div class="container-fluid">
<div class="row justify-content-center">
<div class="col-md-4 col-md-4">
<!--<label for="search_text"><b>Ricerca un'opera per nome</b></label>-->
<input class="form-control" type="text" id="search_text" placeholder="Cerca un'opera d'arte"/>
</div>
<div class="col-md-3 col-md-3">
<button value="Cerca" class="form-control btn-primary" onclick="search()" id="search_button">Cerca</button>
</div>
</div>
<br>
<div class="row justify-content-center">
<div class="col-md-3 col-md-3">
<!--<label for="search_text"><b>Ricerca un'opera per nome</b></label>-->
<button class="form-control btn-primary" onclick="openDialog()" id="newOpera_button">Crea una nuova opera</button>
</div>
</div>

</div>

<br>

<table style="width:90%; margin:auto;" id="table" class="table">
  <thead>
    <tr style="background-color:#ff7514;">
    <th scope="col">Immagine</th>
      <th scope="col">Nome</th>
      <th scope="col">Autore</th>
      <th scope="col">Anno</th>
      <th scope="col">Luogo</th>
      <th scope="col">Descrizione</th>
      <th scope="col">Storia</th>
      <th scope="col">Audio</th>
      <th scope="col">Video</th>
      <th scope="col">Operazioni</th>

    </tr>
  </thead>
  <tbody id="tbody">
    
  </tbody>
</table>

<div style="width:90%; height:90%;" id="dialog" title="Inserisci una nuova opera ">
<div class="container-fluid">
<div class="row justify-content-around">
<div class="col-md-4">
<label for="Nome"><b>Nome</b></label>
<input class="form-control" placeholder="" type="text" id="nome">
</div>
<div class="col-md-4">
<label for="autore"><b>Autore</b></label>
<input class="form-control" placeholder="" type="text" id="autore">
</div>
<div class="col-md-4">
<label for="anno"><b>Anno</b></label>
<input class="form-control" placeholder="" type="text" id="anno">
</div>
</div>

<div class="row justify-content-around">
<div class="col-md-12">
<label for="descrizione"><b>Descrizione</b></label>
<textarea style="width: 100%; height: 100%;" class="form-control" id="descrizione"></textarea>
</div>
</div>
<br><br>
<div class="row justify-content-around">
<div class="col-md-12">
<label for="storia"><b>Storia</b></label>
<textarea style="width: 100%; height: 100%;" class="form-control" id="storia"></textarea>
</div>
</div>
<br><br>
<div class="row justify-content-around">
<div class="col-md-4">
<label for="luogo"><b>Luogo</b></label>
<input class="form-control" placeholder="" type="text" id="luogo">
</div>
<div class="col-md-4">
<label for="id_app"><b>ID APP</b></label>
<input class="form-control" placeholder="" type="text" id="id_app">
</div>
<div class="col-md-4">
</div>
</div>
<br>
<div class="row justify-content-around">
<div class="col-md-12">
<label for="url_immagine"><b>Url Immagine</b></label>
<textarea style="width: 100%; height: 100%;" class="form-control" id="url_immagine"></textarea>
</div>
</div>
<br><br>
<div class="row justify-content-around">
<div class="col-md-12">
<label for="url_audio"><b>Url audio</b></label>
<textarea style="width: 100%; height: 100%;" class="form-control" id="url_audio"></textarea>
</div>
</div>
<br><br>
<div class="row justify-content-around">
<div class="col-md-12">
<label for="url_video"><b>Url video</b></label>
<textarea style="width: 100%; height: 100%;" class="form-control" id="url_video"></textarea>
</div>
</div>
<br><br><br>
<div class="row justify-content-end">
<div class="col-md-4">
<button  class="form-control btn-primary"  onclick="insertOpera('insert');" id="button">Inserisci</button>
</div>
</div>
<br>
</div>

<script>

$(document).ready(function() {
    var w=  $(window).width()* 0.8;
    var h =  $(window).height() * 0.8;
    $("#dialog").dialog({width: w,
                    height: h,
                    resizable: false,
                    autoOpen:false,
                    modal: true
                  });


loadAll();

});

function insertOpera(action,id){

 var nome = $("#nome").val();
 var autore = $("#autore").val();
 var anno_creazione = $("#anno").val();

 var descrizione = $("#descrizione").val();
 var storia = $("#storia").val();

 var luogo = $("#luogo").val();
 var id_app = $("#id_app").val();

 var url_immagine = $("#url_immagine").val();
 var url_audio = $("#url_audio").val();
 var url_video = $("#url_video").val();

 $.post( "server/api.php", {id:id,action:action,nome:nome,autore:autore,anno_creazione:anno_creazione,descrizione:descrizione,storia:storia,luogo:luogo,id_app:id_app,url_image:url_immagine,url_audio:url_audio,url_video:url_video} ,function( data ) {
   $("#dialog").dialog("close");
   loadAll();
});


}


function openDialog(){
  $('#button').removeAttr('onclick');
$('#button').attr('onClick', 'insertOpera(\'insert\')');
$('#button').html("Inserisci");
  resetDialog();
  $("#dialog").dialog("open");
}

function search(){
  var nome = $("#search_text").val();
  $("#tbody").empty();
  $.post( "server/api.php", {action:'load',nome:nome} ,function( json ) {
    fillTable(json.results);
},'json');
}

function loadAll(){
  $("#tbody").empty();
  $.post( "server/api.php", {action:'load'} ,function( json ) {
      fillTable(json.results);
},'json');
}

function fillTable(jsonResult){
  $.each(jsonResult, function(i, res) {
    $('#table > tbody:last-child').append('<tr> <td><image class="image" src=\''+res.url_image+'\'/></td>  <td>'+res.nome+'</td> <td>'+res.autore+'</td> <td>'+res.anno+'</td> <td>'+res.luogo+'</td><td>'+res.descrizione+'</td> <td>'+res.storia+'</td> <td> <audio class="audioplayer" src="'+res.url_audio+'" preload="auto" controls></audio></td>  <td> <a href="'+res.url_video+'">Qui</a> </td> <td><image title="Elimina" style="width: 22px; height:22px;" src="images/minus.png" onclick="deleteOpera('+res.id+')"/><image style="width: 22px; height:22px;" title="Modifica" src="images/modify.png" onclick="updateOpera('+res.id+')"/></td>   </tr>');
});
}

function deleteOpera(id){
if (confirm('Sei sicuro? Tutti i dati relativi verranno cancellati')) {
  $.post("server/api.php", {action:'delete',id:id},function ( data ) {
  loadAll();
});
}
}

function updateOpera(id){
$.post("server/api.php",{action:'load',id:id},function(json){

$('#button').removeAttr('onclick');
$('#button').attr('onClick', 'insertOpera(\'update\','+id+')');
$('#button').html("Aggiorna");

$("#dialog").dialog("open");
var res = json.results[0];
$("#nome").val(res.nome);
$("#autore").val(res.autore);
$("#anno").val(res.anno);
$("#descrizione").val(res.descrizione);
$("#storia").val(res.storia);
$("#luogo").val(res.luogo);
$("#id_app").val(res.id_app);
$("#url_immagine").val(res.url_image);
$("#url_audio").val(res.url_audio);
$("#url_video").val(res.url_video);

},'json');
}

function resetDialog(){
  $("#nome").val("");
$("#autore").val("");
$("#anno").val("");
$("#descrizione").val("");
$("#storia").val("");
$("#luogo").val("");
$("#id_app").val("");
$("#url_immagine").val("");
$("#url_audio").val("");
$("#url_video").val("");
}


</script>

</body>
</html>