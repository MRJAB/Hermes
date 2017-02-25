v<?php
//sleep(1);
if(!empty($_POST['loginData']))
{

$loginData= htmlentities(trim($_POST['loginData']));
$data=explode("|",$loginData);
$username=htmlentities(trim($data[0]));
$password=htmlentities(trim($data[1]));

$p=hash("Sha256",$password);
require("database_connection.php");

$query="select user_id,name from users where username='$username'and password='$p'";
if($result=$pdo->query($query))
{
	$arr=array();
	while($row=$result->fetch(PDO::FETCH_ASSOC))
	{
	array_push($arr,$row);	
	
	}
	if(count($arr)===1)
	{
		session_start();

		$_SESSION['username']=$username;
		$_SESSION['name']=    $arr[0]['name'];
		$_SESSION['user_id']= $arr[0]['user_id'];
				
		echo "login_success";

		
		
	}
	else{
		echo "false";
	}
	unset($result);
	
	
	
}
else
{
	print_r($pdo->errorInfo());
	
}

}
else if($_POST['signupData'])
{
	
	$signupData= trim($_POST['signupData']);
	$data =json_decode($signupData);
	
	$username=$data->username;
	$password=$data->password;
	$email=$data->email;
	$name=$data->name;

//$u=hash("Sha256",$username);
$p=hash("Sha256",$password);
require("database_connection.php");
	
	$query="insert into users values(null,'$username','$p','$email','$name')";
if($result=$pdo->exec($query))
{
	echo "true";		
}
else{
		print_r($pdo->errorInfo());
	}
	
	
	
}
else
{
		exit("Javascript disabled");
}
	
unset($pdo);

?>