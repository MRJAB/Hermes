window.onload = function () {
	document.getElementById("login_page").onclick = controller.main_buttons;
	document.getElementById("signup_page").onclick = controller.main_buttons;
	document.getElementById("get_started_button").onclick = controller.main_buttons;
};
var controller = {
	main_buttons: function (event) {

		event.preventDefault();
		var target = event.target;
		var id = target.id;
		model.pageCall(id);
	},
	login: function (event) {

		event.preventDefault();
		var loginUsername = document.getElementById("login_username").value;
		var loginPassword = document.getElementById("login_password").value;

		if (loginUsername && loginPassword) {

			var regExUsername = /^\w{6,20}$/;
			var regExPassword = /^[@$#.,*a-zA-z0-9]{8,20}$/;
			if (loginUsername.match(regExUsername) && loginPassword.match(regExPassword)) {

				var data = "loginData=" + loginUsername + "|" + loginPassword;

				model.callAjax(data);
			} else {
				var main_div = document.getElementById("login_form").getElementsByTagName("p");
				return (main_div.length === 0) ? view.callLoginValidation() : false;
			}



		}

	},

	signupValidation: function (event) {

		event.preventDefault();

		var signupName = document.getElementById("signup_name").value;
		var signupUsername = document.getElementById("signup_username").value;
		var signupPassword = document.getElementById("signup_password").value;
		var signupCpassword = document.getElementById("signup_cpassword").value;
		var signupEmail = document.getElementById("signup_email").value;
		var signupCemail = document.getElementById("signup_cemail").value;

		var errorMsgs = {
			"nameError": "Name must be Alphabetic ",
			"usernameError": "username must be without special charcter and 8<=length<=20 ",
			"passwordError": "Allowed special characters in password are @$#.,* and 8<=length<=20 ",
			"cpassowrdError1": "password and confirm password did not match",
			"cpassowrdError2": "Allowed special characters in confirm password are @$#.,* and 8<=length<=20 ",
			"emailError": "Invalid email format",
			"cemailError1": "email and confirm email did not match",
			"cemailError2": "Invalid confirm email format",
		};

		if (signupName && signupUsername && signupPassword && signupCpassword && signupEmail && signupCemail) {
			var nameFlag = true,
				usernameFlag = true,
				passwordFlag = true,
				cpasswordFlag1 = true;
			var cpasswordFlag2 = true,
				emailFlag = true,
				cemailFlag1 = true,
				cemailFlag2 = true;

			var regExUsername = /^\w{6,20}$/;
			var regExName = /^[a-z\sA-Z]{3,20}$/;
			var regExPassword = /^[@$#.,*a-zA-z0-9]{8,20}$/;
			var regExEmail = /^[\w_\.\-\+]+@[\w-]+(\.\w{2,4})$/;
			if (signupPassword!== signupCpassword) {
				view.warning_nessage("cpassword_li", errorMsgs.cpassowrdError1);
				cpasswordFlag1 = false;
				console.log("hiiii");
				return;
			}
			if (signupEmail!== signupCemail) {
				view.warning_nessage("cemail_li", errorMsgs.cemailError1);
				cemailFlag2  = false;
				console.log("hellooooo");
				return;
			}
			if (!signupName.match(regExName)) {
				view.warning_nessage("name_li", errorMsgs.nameError);
				nameFlag = false;
			}
			if (!signupUsername.match(regExUsername)) {
				view.warning_nessage("username_li", errorMsgs.usernameError);
				usernameFlag = false;
			}
			if (!signupPassword.match(regExPassword)) {
				view.warning_nessage("password_li", errorMsgs.passwordError);
				passwordFlag = false;
			}
			if (!signupCpassword.match(regExPassword)) {
				view.warning_nessage("cpassword_li", errorMsgs.cpassowrdError2);
				cpasswordFlag2 = false;
			}
			if (!signupEmail.match(regExEmail)) {
				view.warning_nessage("email_li", errorMsgs.emailError);
				emailFlag = false;

			}
			if (!signupCemail.match(regExEmail)) {
				view.warning_nessage("cemail_li", errorMsgs.cemailError2);
				cemailFlag2 = false;

			}
			if (nameFlag && usernameFlag && passwordFlag && cpasswordFlag1 && cpasswordFlag2 && cemailFlag1 && cemailFlag2) {
				var data = "signupData=" + JSON.stringify({
					"username": signupUsername,
					"name": signupName,
					"password": signupPassword,
					"email": signupEmail
				});
				model.callAjax(data);
			} else {

				return false;
			}



		}

	}
};
