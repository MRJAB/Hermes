<?php
session_start();
$_SESSION = array();
setcookie( session_name(), "", 1, "/" );
session_destroy();
header( "location:../" );
?>