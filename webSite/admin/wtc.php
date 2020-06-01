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
  width:200px;
  height:180px;
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
    
      <li class="nav-item">
        <a class="nav-link" href="home.php">Home</a>
      </li>

      <li class="nav-item active">
      <a class="nav-link" href="wtc.php">Gestione WTC / WTO<span class="sr-only">(current)</span></a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link" href="logout.php">Logout</a>
      </li>
  </div>
</nav>


<br><br>
<div class="container-fluid">
<div class="row justify-content-start">
<div class="col-sm-12">
<div class="form-group">
<h4>Seleziona un file WTC da caricare nel server.</h4>
   <!-- <label for="exampleFormControlFile1">Seleziona un file WTC</label> -->
    <input type="file" accept=".wtc" class="form-control-file" id="exampleFormControlFile1">
  </div>
<div>
</div>
<br>
<br>
<div class="row justify-content-start">
<div class="col-sm-12">
<div class="form-group">
<h4>Seleziona un file WTO da caricare nel server.</h4>
   <!-- <label for="exampleFormControlFile1">Seleziona un file WTC</label> -->
    <input type="file" accept=".wto"  class="form-control-file" id="exampleFormControlFile1">
  </div>
<div>
</div>

  </div>

</body>
</html>