<?php

try
		{
			$pdo=new PDO("mysql:dbname=hermes;host=localhost","root","");
			
		}
		catch(PDOException $e)
		{
		echo $e->getMessage();	
		}
		
		
		?>