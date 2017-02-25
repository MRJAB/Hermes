window.onload=function()
{
		var contacts=document.getElementById("contacts");
		var contactList=contacts.getElementsByTagName("li");
	for(var i=0; i<contactList.length;i++)
		{
		contactList.item(i).onclick=loadChat;
		}
document.getElementById("search_box").onkeyup=searchUser;
document.getElementById("search_box").onblur=removeBox;
	
	
};

function removeBox()
{
	var p=document.getElementById("search_result");
		p.style.display="none";
}

function searchUser()
{
	var val=this.value;
	var data="search_term="+val;
	loadData(data,"searchUser");
	}

function loadChat()
{
	var friendId=this.getAttribute("id");
	 document.getElementById("friend_name").innerHTML=this.innerHTML;
	var data="friendInfo="+friendId;
	loadData(data,"loadChat");
}

function chatLoader()

{
var main_div=document.getElementById("chat");
var loader=document.createElement("p");
var img=document.createElement("img");
	img.setAttribute("src","images/loading.gif");
	img.setAttribute("alt","loader");
	loader.setAttribute("id","loader");

	loader.appendChild(img);
	main_div.appendChild(loader);
		
}

function loadData(data,fun)
{
	//chatLoader();
	var xhr= new XMLHttpRequest();
	xhr.open("POST","../php_files/load_chat.php",true);
	xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
	xhr.send(data);
	xhr.onreadystatechange=function()
	{
		if(xhr.readyState===4)
		{
			if((xhr.status>=200 && xhr.status<300)||(xhr.status===304))
			{
				if(xhr.responseText)
				{
							var arr=[];			
					console.log(xhr.responseText);
						if(xhr.response==="empty")
							{
							arr.push({"username":"no record found"})	;
							}
						else
							{
						arr=JSON.parse(xhr.responseText);
							}
					if(fun==="searchUser")
							{
						searchBox(arr);
							}
						else if(fun==="loadChat")
							{
								
							chat(arr);
							}	
						
		
				}
			}
		}
	};
	
}

function searchBox(arr)
{
			var searchResult=document.getElementById("search_result");
					searchResult.innerHTML="";
				searchResult.style.display="block";	
				for(var i=0;i<arr.length;i++)
						{
							var userId=Number(arr[i].user_id);
							var username=arr[i].username;
					
					var li= document.createElement("li");
					var a= document.createElement("a");
						a.setAttribute("id",userId);
							//a.setAttribute("href","#");
					var info=document.createTextNode(username);
						a.appendChild(info);
						li.appendChild(a);
						searchResult.appendChild(li);
						}
	
	
		}
	


function chat(arr)
{
					var chat= document.getElementById("chat");
										chat.innerHTML="";

					var userInfo=document.getElementById("userInfo");
					var currentUser=Number(userInfo.getAttribute("hidden"));
				
					
					//console.log(xhr.response);
					for(var i=0;i<arr.length;i++)
						{
							var message=arr[i].msg;
							var user_id=Number(arr[i].user_id);
							var friend_id=Number(arr[i].friend_id);
					var li= document.createElement("li");
					var p= document.createElement("p");
					var msg=document.createTextNode(message);
						p.appendChild(msg);
						p.className=(user_id===currentUser)?"user":"friend";
						li.appendChild(p);
						chat.appendChild(li);
						}
	
	
	
}


/*
// JavaScript Document
	


var messagetime=1;
	var msg= document.getElementById("msg");
	msg.onfocus=function()
	{
		loader=setInterval(loadChat,1000);
	}
	msg.onblur=function()
	{
				setTimeout(closeConn,10000,loader)
	}
	function closeConn(loader)
	{
		clearInterval(loader);
		
	}


function ajax(type,msg)
{
	try
	{
	xhr= new XMLHttpRequest();

	xhr.open("POST","../php/savechat.php",true);
	xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
		var str=type+"="+msg;
		var send=xhr.send(str);	
	}
catch(failed)
{
	clearInterval(loader);
	
	

}
xhr.onreadystatechange=function()
	{
		if(xhr.readyState==4)
		{
			if((xhr.status>=200&& xhr.status<300)||(xhr.status==305))
			{
				if(xhr.responseText)
				{
			console.log(xhr.responseText);

					var arr=JSON.parse(xhr.responseText);
					for(var a in arr)
					{
					showMessage(arr[a].msg);	
					messagetime=arr[a].messagetime;
					}
					
				}
			}
					
		}
			
	}
}



function showMessage(response)
{
				var ul=document.getElementById("chatLog");
				
				var li=document.createElement("li");
				li.className="send";
				li.appendChild(document.createTextNode(response));
				ul.appendChild(li);
				form1.elements.item(0).value="";
			ul.scrollTop = ul.scrollHeight - ul.clientHeight;

								
}
function loadChat()
{
	ajax("load_data",messagetime);

}

chatstart=function(event)
{
	event.preventDefault();
	var msg=form1.elements.item(0).value;
		time=new Date().getTime();
		if(msg)
		{

	var json_msg={msg:escape(msg),time:time,user_id:1,friend_id:2};
	json_data=JSON.stringify(json_msg);
	ajax("insert_data",json_data);
		}
		else
		{
		alert("please enter something");	
		}
}

document.forms.item(0).onsubmit=chatstart;
*/
