<?php
session_start();
if ( empty( $_SESSION[ 'username' ] ) && empty( $_SESSION[ 'name' ] ) ) {
	header( "location:../" );
}
?>
<!doctype html>
<html>

<head>
	<meta charset="utf-8">
	<title>hermes</title>
	<link rel="stylesheet" type="text/css" href="../css/main.css"/>
	<link rel="stylesheet" type="text/css" href="../css/home.css"/>
	<script src="../script/home/view/view.js"></script>
	<script src="../script/home/model/model.js"></script>
	<script src="../script/home/controller/controller.js"></script>
</head>

<body id="body1">
	<div id="header">
		<div id="chatLogo">
			<a href="home.php"><img src="../images/chat.png" id="logo" alt="image"/></a>
		</div>
		<h1 id="title">Welcome to Hermes <span id="userName"><?php echo $_SESSION['name'] ;?> !</span></h1>

		<h6 id="subtitle">Your Favourite Chat System</h6>
		<h2> <span id="userInfo" hidden="<?php echo $_SESSION['user_id']; ?>" title="username">
           			<?php echo $_SESSION['username']?></span>  </h2>
	</div>
	<div id="subheader_div">
		<div id="profile_picture"></div>
		<div id="search_div">

			<input type="text" autocomplete="off" id="search_box" name="search_box" placeholder="Search Username..."/>

			<ul id="search_result"></ul>

		</div>
		<div id="friend_request_div" title="Friend Request">
			<div id="count_request_div" class="count_div">0</div>
		</div>
		<div id="notification_div" title="Notifications">
			<div id="count_notification_div" class="count_div">0</div>

		</div>
		<div id="menu_div">
			<ul id="menu">
				<li id="submenu_li">
					<ul id="submenu">
						<li>Profile</li>
						<li>Setting</li>
						<li><a href="logout.php">Logout</a>
						</li>

					</ul>

				</li>


			</ul>
		</div>

	</div>

	<div id="chat_div">
				<div id ="contact_tilte">Contact List</div>
				<ul id="contacts"><?php require("load_contacts.php"); ?></ul>
		<div id="friend_info">
			<div id="friend_profile_picture"></div>
			<div id="friend_name">Start Chat</div>
		</div>
		<ul id="chat">
			<li><img src="../images/live-chat.png"/>
			</li>

		</ul>
		<div id="message_div">
			<input type="text" id="message_box" name="message_box" placeholder="Write here......"/>
		</div>


	</div>
	<div id="profile_view"></div>
	<div id="footer">Distributed Chat System 2017. All Rights Reserved. Developed By Hermes</div>

</body>

</html>