<?php
require("database_connection.php");
if(isset($_POST['friendInfo']))
{
session_start();
$friendInfo=$_POST['friendInfo'];
 $userInfo= $_SESSION['user_id'];
$query="select user_id,friend_id ,msg from chatbox where (user_id=$userInfo and friend_id=$friendInfo) 
or (user_id=$friendInfo and friend_id=$userInfo) order by messagetime" ;
}
elseif(!empty($_POST['search_term']))
{
	
$search_term=$_POST['search_term'];
	
	$query="select user_id,username from users where username like '$search_term%' limit 10";

}
	   else
	   {
	die("empty");
}


if($result=$pdo->query($query))
		{
			$arr=array();
			while($row=$result->fetch(PDO::FETCH_ASSOC))
				{
					array_push($arr,$row);
				}
			if(!empty($arr))
			   {
			$jsonstr=json_encode($arr);
			echo $jsonstr;
			}
			   else{
				echo "empty";
			}
				
		}
		else
		{
		var_dump($pdo->errorInfo());	
			
		}

?>