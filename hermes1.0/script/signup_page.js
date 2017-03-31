// JavaScript Document
function warning_nessage(id,msg)
{
				var obj=document.getElementById(id);
				var warning=null;
					var p=obj.getElementsByTagName("p");
	
					if(p.length===0)
						{
				warning=document.createElement("p");
				var warningText=document.createTextNode(msg);
				warning.setAttribute("class","warning");
				warning.appendChild(warningText);
				obj.appendChild(warning);
						}
					
				
	
	
	
}
function signupValidation(event)
{
	
		event.preventDefault();
		
		var signupName=document.getElementById("signup_name").value;
		var signupUsername=document.getElementById("signup_username").value;
		var signupPassword=document.getElementById("signup_password").value;
		var signupCpassword=document.getElementById("signup_cpassword").value;
		var signupEmail=document.getElementById("signup_email").value;
		var signupCemail=document.getElementById("signup_cemail").value;
	
	var errorMsgs={ "nameError"       :"Name must be Alphabetic ",
			 	    "usernameError"   :"username must be without special charcter and 8<=length<=20 ",
				    "passwordError"   :"Allowed special characters in password are @$#.,* and 8<=length<=20 ",
				    "cpassowrdError1" :"password and confirm password did not match",
				   	"cpassowrdError2" :"Allowed special characters in confirm password are @$#.,* and 8<=length<=20 ",
				   	"emailError"      :"Invalid email format",
				  	"cemailError1"    :"email and confirm email did not match",
				   "cemailError2"     :"Invalid confirm email format",
				  };

		
		
		
		
	
	
	if( signupName&& signupUsername && signupPassword && signupCpassword &&signupEmail && signupCemail)
		{
		var nameFlag=true,usernameFlag=true,passwordFlag=true,cpasswordFlag1=true;
		var cpasswordFlag2=true,emailFlag=true,cemailFlag1=true,cemailFlag2=true;
			
				
			
			var regExUsername=/^\w{6,20}$/;	
			var regExName=/^[a-z\sA-Z]{3,20}$/;	
			var regExPassword=/^[@$#.,*a-zA-z0-9]{8,20}$/;
			var regExEmail=/^[\w_\.\-\+]+@[\w-]+(\.\w{2,4})$/;
			if(!signupName.match(regExName))
				{
				warning_nessage("name_li",errorMsgs.nameError);
								nameFlag= false;
				}
			if(!signupUsername.match(regExUsername))
				{
				warning_nessage("username_li",errorMsgs.usernameError);
										usernameFlag= false;
				}
				if(!signupPassword.match(regExPassword))
				{
				warning_nessage("password_li",errorMsgs.passwordError);
										passwordFlag= false;
				}
			if(!signupCpassword.match(regExPassword))
				 {
				warning_nessage("cpassword_li",errorMsgs.cpassowrdError2);
					 					cpasswordFlag2= false;
	 			 }
			if(!signupEmail.match(regExEmail))
				{
			warning_nessage("email_li",errorMsgs.emailError);
										emailFlag= false;

				}
			if(!signupCemail.match(regExEmail))
				{
		warning_nessage("cemail_li",errorMsgs.cemailError2);
										cemailFlag2= false;

				}
			if(nameFlag && usernameFlag && passwordFlag && cpasswordFlag1 && cpasswordFlag2 && cemailFlag1 && cemailFlag2)
				{
	var data="signupData="+JSON.stringify({"username":signupUsername,"name":signupName,"password":signupPassword,"email":signupEmail});
				callAjax(data);
				}
			else
				{
					
					return false;
				}
			
			
			
	}

}



		
