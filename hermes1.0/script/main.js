// JavaScript Document
window.onload=function()
{
	document.getElementById("login_page").onclick=login_signup;
	document.getElementById("signup_page").onclick=login_signup;

};


function login_signup(event)
{
	
	event.preventDefault();
	var target= event.target;
	var id =target.id;
	pageCall(id);
		
}
function loader()

{
var main_div=document.getElementById("main_div");
var loader=document.createElement("p");
var img=document.createElement("img");
	img.setAttribute("src","images/loading.gif");
	img.setAttribute("alt","loader");
	loader.setAttribute("id","loader");

	loader.appendChild(img);
	main_div.appendChild(loader);
		
}

function pageCall(data)
{
	loader();

	
	var xhr=new XMLHttpRequest();
	//var loginData="loginData="+data;

	xhr.open("POST","php_files/"+data+".php",true);
	xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
	xhr.send(null);
	xhr.onreadystatechange=function()
	{
		if(xhr.readyState===4)
			{
				if((xhr.status>=200 && xhr.status<300)|| xhr.status===304)
					{
						var response=xhr.responseText;
						
							document.getElementById("main_div").innerHTML=response;	
						
								if(data==="login_page")
								{
								document.getElementById("login_submit").onclick=login;

								}
								else if(data==="signup_page")
								{
							document.getElementById("signup_submit").onclick=signupValidation;
								}
								
							}
						else
							{
								document.getElementById("loader").innerHTML="<h2 class=\"warning\">Failed To load Data </h2>";
								
							}
						
						
						
					}
				
				
			};
		
		
		
	}


	
