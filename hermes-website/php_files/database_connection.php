<?php

$user="id865672_admin";
$pass="Admin";
//$user = "root";
//$pass = "";
try {
	$pdo= new PDO('mysql:host=localhost;dbname=id865672_hermesdb', $user, $pass);  

	//$pdo = new PDO( 'mysql:host=localhost;dbname=hermes1.2', $user, $pass );
} catch ( PDOException $e ) {
	print "Error!:" . $e->getMessage() . "<br/>";
	die();
}

?>