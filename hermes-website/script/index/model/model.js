var model = {
	pageCall: function (data) {
		view.loader();
		var xhr = new XMLHttpRequest();
		xhr.open("POST", "php_files/" + data + ".php", true);
		xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
		xhr.send(null);
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4) {
				if ((xhr.status >= 200 && xhr.status < 300) || xhr.status === 304) {
					var response = xhr.responseText;
					view.responseData(data, response)

				}
			}
		};
	},
	callAjax: function (data) {

		view.loader();

		//alert(data);
		var xhr = new XMLHttpRequest();
		xhr.open("POST", "php_files/verification.php", true);
		xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
		xhr.send(data);
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4) {
				if ((xhr.status >= 200 && xhr.status < 300) || xhr.status === 304) {
					var response = xhr.responseText;
					console.log(response);

					if (response === "login_success") {
						console.log(response);

						window.location = "php_files/home.php";
					} else if (response === "signup_success") {

						view.responseMessage("<h2 class=\"success\">Signup Success Click to Login</h2>");

					} else if (response === "signup_failed") {
						view.responseMessage("<h2 class=\"warning\">Username Already Exists </h2>");
					} else {
						view.responseMessage("<h2 class=\"warning\">Incorrect Username or Password </h2>");

					}
				}
			}

		};
	}
};
