<?php
require("database_connetcion.php");
if(isset($_POST['insert_data']))
{
	$json=trim($_POST['insert_data']);
		$obj=json_decode($json);
		


$query="insert into chatbox(user_id,friend_id,msg,messagetime) values
	(".$obj->user_id.",".$obj->friend_id.",'".$obj->msg."',now())";
	$pdo->exec($query);
}
if(isset($_POST['load_data']))
{
	
		//$obj=json_decode($json);

			$arr=array();
$query2="select msg,messagetime from chatbox where ((user_id=1 and friend_id=2)or(user_id=2 and friend_id=1))
		 and messagetime >'".$_POST['load_data']."' order by messagetime ";
		
		if($result=$pdo->query($query2))
		{
			while($row=$result->fetch(PDO::FETCH_ASSOC))
				{
					$arr[]=$row;
					
				}
				$jsonstr=json_encode($arr);
				echo $jsonstr;
				
		}
		else
		{
		//echo "failed2";
		var_dump($pdo->errorInfo());	
			
		}
}
unset($pdo);
?>