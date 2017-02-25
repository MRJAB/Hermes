<?php
session_start();
if(empty($_SESSION['username'])&& empty($_SESSION['name']))
   {
header("location:../");
}
?>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>hermes</title>
<link rel="stylesheet" type="text/css" href="../css/main.css"  />
<link rel="stylesheet" type="text/css" href="../css/home.css" />
	<script src="../script/chat.js"></script>
</head>

<body>
<div id="header">
            <div id="chatLogo">
            <a href="../index.php"><img src="../images/chat.png" id="logo" alt="image"/></a>
            </div>
	<h1 id="title">Welcome to Hermes <span id="userName"><?php echo $_SESSION['name'] ;?> !</span></h1>
           
            <h6 id="subtitle">Your Favourite Chat System</h6>
	<h2> <span id="userInfo" hidden="<?php echo $_SESSION['user_id']; ?>" title="username"><?php echo $_SESSION['username']?></span>  </h2>
   
           <
           </div>
            
            
<div id="subheader_div">
		<div id="profile_picture"></div>
                  <div id="search_div">
<form action="#" method="post">
<input type="search" id="search_box" name="search_box" placeholder="Search by username or email to start a new chat" />
<input type="submit" id="search" name="search" style="display:none" /> 
</form>
					  <ul id="search_result"></ul>

</div>
                   <div id="menu_div">
								<ul id="menu" >
									<li id="submenu_li">
											<ul id="submenu">
												<li>New Chat</li>
												<li>Profile</li>
												<li>Setting</li>
												<li>Logout</li>

											</ul>

									</li>


								   </ul>

					
					</div>
</div>



<div id="chat_div">
	<ul id="contacts">
<!--
		<li><?php //echo $_SESSION['name']; ?> Connections!</li>
-->
		<?php
		
		
		require ("database_connection.php");

$query="select user_id,name, login,lastseen from users where user_id 
in(select friend_id from connections where user_id=".$_SESSION['user_id'].")";

if($result=$pdo->query($query))
		{
			while($row=$result->fetch(PDO::FETCH_ASSOC))
				{
					$arr[]=$row;
				echo "<li id='".$row['user_id']."'><span>".$row['name']."</span><span class='activity ".
					(!empty($row['login'])?"online":"lastseen")."'></spn></li>";

				}
				$jsonstr=json_encode($arr);
			
				
		}
		else
		{
		var_dump($pdo->errorInfo());	
			
		}

unset($pdo);



	?>
	</ul>
	<div id="friend_info">
			<div id="friend_profile_picture"></div>
		<div id="friend_name">Start Chat</div>
	
	
	
	
	
	</div>
	<ul id="chat">
		<li><img src="../images/Chat-PNG-HD.png" /></li>
		
	</ul>
	<div id="message_div">
		<form action="#" method="post">
<input type="text" id="message_box" name="message_box" placeholder="Write here......" />
<!--			<textarea rows="3" cols="130"></textarea>-->
<input type="submit" id="message" name="message" style="display:none" /> 
</form>
	</div>
	

</div>

</body>
</html>
