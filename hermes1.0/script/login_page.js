// JavaScript Document

function login(event)
{
	
	event.preventDefault();
	var loginUsername=document.getElementById("login_username").value;
		var loginPassword=document.getElementById("login_password").value;
		
	if(loginUsername && loginPassword)
		{
			
			var regExUsername=/^\w{6,20}$/;	
			var regExPassword=/^[@$#.,*a-zA-z0-9]{8,20}$/;
			if(loginUsername.match(regExUsername) && loginPassword.match(regExPassword))
			{

				var data="loginData="+loginUsername+"|"+loginPassword;
				callAjax(data);
				console.log(loginUsername.match(regExUsername)+" "+ loginPassword.match(regExPassword));
			}
			else
				{
					var main_div=document.getElementById("login_form").getElementsByTagName("p");
					return (main_div.length===0)?callLoginValidation():false;
				}
			
				
	
		}

}
function callLoginValidation()
{
	
	var login_heading=document.getElementById("login_heading");
	var username_li=document.getElementById("username_li");
	var password_li=document.getElementById("password_li");
	
	var warning1=document.createElement("p");
	var warningText1=document.createTextNode("Invalid Entry of Username or Password ");
		warning1.setAttribute("class","warning");
		warning1.appendChild(warningText1);
		login_heading.appendChild(warning1);
	
	var warning2=document.createElement("p");
	var warningText2=document.createTextNode("username must be without special charcter and 8<=length<=20 ");
		warning2.setAttribute("class","warning");
		warning2.appendChild(warningText2);
		username_li.appendChild(warning2);

	
	var warning3=document.createElement("p");
	var warningText3=document.createTextNode("allowed special charcters are @$#.,* and 8<=length<=20 ");
		warning3.setAttribute("class","warning");
		warning3.appendChild(warningText3);
		password_li.appendChild(warning3);

}
function callAjax(data)
{
	
loader();
	
	
	var xhr=new XMLHttpRequest();
	xhr.open("POST","php_files/validation.php",true);
	xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
	xhr.send(data);
	xhr.onreadystatechange=function()
	{
		if(xhr.readyState===4)
			{
				if((xhr.status>=200 && xhr.status<300)|| xhr.status===304)
					{
						var response=xhr.responseText;
						if(response==="login_success")
							{
								console.log(response);

								window.location="php_files/home.php";
							}
						else if(response=="signup_success")
							{
								
								
								
							}
							
						else
							{
							console.log(response);

								document.getElementById("loader").innerHTML="<h2 class=\"warning\">Filed to Proceed</h2>";
								
							}
						
						
						
					}
				
				
			}
		
		
		
	};


	
}