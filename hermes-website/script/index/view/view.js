var view = {
	responseMessage: function (msg) {
		document.getElementById("loader").innerHTML = msg;


	},
	responseData: function (data, response) {
		document.getElementById("main_div").innerHTML = response;
		if (data === "login_page") {
			document.getElementById("login_submit").onclick = controller.login;

		} else if (data === "signup_page") {
			document.getElementById("signup_submit").onclick = controller.signupValidation;
		} else {
			document.getElementById("loader").innerHTML = "<h2 class=\"warning\">Failed To load Data </h2>";

		}
	},


	loader: function ()

	{
		var main_div = document.getElementById("main_div");
		var loader = document.createElement("p");
		var img = document.createElement("img");
		img.setAttribute("src", "images/loading.gif");
		img.setAttribute("alt", "loader");
		loader.setAttribute("id", "loader");

		loader.appendChild(img);
		main_div.appendChild(loader);

	},


	callLoginValidation: function () {

		var login_heading = document.getElementById("login_heading");
		var username_li = document.getElementById("username_li");
		var password_li = document.getElementById("password_li");

		var warning1 = document.createElement("p");
		var warningText1 = document.createTextNode("Invalid Entry of Username or Password ");
		warning1.setAttribute("class", "warning");
		warning1.appendChild(warningText1);
		login_heading.appendChild(warning1);

		var warning2 = document.createElement("p");
		var warningText2 = document.createTextNode("username must be without special charcter and 8<=length<=20 ");
		warning2.setAttribute("class", "warning");
		warning2.appendChild(warningText2);
		username_li.appendChild(warning2);


		var warning3 = document.createElement("p");
		var warningText3 = document.createTextNode("allowed special charcters are @$#.,* and 8<=length<=20 ");
		warning3.setAttribute("class", "warning");
		warning3.appendChild(warningText3);
		password_li.appendChild(warning3);

	},
	warning_nessage: function (id, msg) {
		var obj = document.getElementById(id);
		var warning = null;
		var p = obj.getElementsByTagName("p");

		if (p.length === 0) {
			warning = document.createElement("p");
			var warningText = document.createTextNode(msg);
			warning.setAttribute("class", "warning");
			warning.appendChild(warningText);
			obj.appendChild(warning);
		}

	}
}
