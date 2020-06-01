<?php
session_start();

if(!isset($_SESSION['auth']) && !$_SESSION['auth']==1202)
	header('location: index.html');

$inactive = 2000;

if (isset($_SESSION["timeout"])) {
	
    $sessionTTL = time() - $_SESSION["timeout"];
    if ($sessionTTL > $inactive) {
        header("Location:logout.php?msg=sc");
        die;
    }
}

$_SESSION["timeout"] = time();

?>