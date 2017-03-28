<img src="images/5e03471b065980b00f251567776984.png" id="login_image" alt="login_image"/>
<form action="#" method="post">
	<ul id="login_form">
		<li id="login_heading">
			<div>Authentication !</div>
		</li>
		<li id="username_li"><label for="username">UserName:</label><input type="text" required placeholder="Username" id="login_username" class="inputs" maxlength="20" name="login_username"/>
		</li>
		<li id="password_li"><label for="passowrd">Password:</label>
			<input type="password" required id="login_password" maxlength="20" placeholder="Password" class="inputs" name="login_password"/>
		</li>
		<li><input type="checkbox" id="remember_me" name="remember_me" style="width:30px; height:40px "/>
			<label for="remember_me">Remember Me</label>
			<input type="submit" id="login_submit" name="login_submit" class="link" value="Submit"/>
		</li>
		<li><a id="forgot_password" href="#" class="">Forgot Password ?</a>
		</li>
	</ul>
</form>