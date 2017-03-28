var view = {
	removeBox: function () {
		var search_result = document.getElementById("search_result");
		search_result.style.visibility = "hidden";
		
	},

	searchBox: function (arr) {
		var searchResult = document.getElementById("search_result");
		searchResult.innerHTML = "";
		if (arr) {
			for (var i = 0; i < arr.length; i++) {
				var userId = Number(arr[i].UserID);
				var username = arr[i].Username;
				var name = arr[i].Name;
				var li = document.createElement("li");
				li.setAttribute("id", userId);
				var a1 = document.createElement("a");
				var info = document.createTextNode(username);
				li.setAttribute("title", name);
				a1.appendChild(info);

				li.appendChild(a1);
				searchResult.appendChild(li);
				a1.onclick = view.chatInitiate;

			}
		} else {
			var li1 = document.createElement("li");
			var a11 = document.createElement("a");
			var info1 = document.createTextNode("no record found");
			a11.appendChild(info1);
			li1.appendChild(a11);
			searchResult.appendChild(li1);

		}


	},
	removeList: function (li) {
		var ul = document.getElementById("contacts");
		var lis = ul.getElementsByTagName("li");
		if(lis.length>1)
		ul.removeChild(li);
		
	},
	latestChat: function (arr) {
		var ul = document.getElementById("contacts");
		if (!arr) {
			return;
		}
		for (var i = 0; i < arr.length; i++) {
			//echo "<li id='" . $arr[ $i ][ 'ChatID' ] . "' title=''><span>FRENDID:" . $arr[ $i ][ 'Name' ] . " </span></li>";
			var li = document.createElement("li");
			var span1 = document.createElement("span");
			var span2 = document.createElement("span");
			var val1= document.createTextNode(arr[i].Name);
			var val2= document.createTextNode("Delete");

			span2.setAttribute("class","deleteButton");
			li.setAttribute("id", arr[i].ChatID);
			
			span1.appendChild(val1);
			span2.appendChild(val2);
			li.onclick = controller.startChat;
			span2.onclick=controller.deleteChat;
			li.appendChild(span1);
			li.appendChild(span2)
			ul.appendChild(li);
		}
	},
	deleteChat:function(arr)
	{
		//<img src="../images/live-chat.png"/>
		console.log(arr);
		var chat = document.getElementById("chat");
		chat.innerHTML = "";
		var fName= document.getElementById("friend_name");
		var ul=document.getElementById("contacts");
		fName.innerHTML="Start Chat";
		var li =document.getElementById(arr[0]);
		ul.removeChild(li);
		clearTimeout(timeout);	
	},
	chat: function (arr) {

		if (arr) {

			var chat = document.getElementById("chat");
			chat.innerHTML = "";


			var userInfo = document.getElementById("userInfo");
			var currentUser = Number(userInfo.getAttribute("hidden"));
			for (var i = 0; i < arr.length; i++) {
				var message = arr[i].Content;
				var user_id = Number(arr[i].fk_SenderUserID);
				//var friend_id = Number(arr[i].fk_ChatID);
				var msg_id = Number(arr[i].MessageID);
				var time = arr[i].RecieveDateTime;

				var li = document.createElement("li");
				li.setAttribute("id", msg_id);
				var p = document.createElement("p");
				var span = document.createElement("span");
				span.className = "time";

				var msg = document.createTextNode(message);
				var msgtime = document.createTextNode(time);

				p.appendChild(msg);
				span.appendChild(msgtime);
				p.appendChild(span);

				p.className = (user_id === currentUser) ? "user" : "friend";
				li.appendChild(p);
				chat.appendChild(li);
			}

			chat.scrollTop = chat.scrollHeight - chat.clientHeight;

		}

		var fn = document.getElementById("friend_name");
		var chatId = fn.getAttribute("value");

		var str = JSON.stringify({
			"chatId": chatId
		});
		var data = "loadChat=" + str;
		timeout = setTimeout(function () {
			model.loadData(data, "loadChat", "load_data.php");

		}, 2000);

	},

	chatInitiate: function () {

		var search_result = document.getElementById("search_result");
		var id = this.parentNode.id;
		var username = this.parentNode.getElementsByTagName('a')[0].innerHTML;
		var contacts = document.getElementById("contacts");
		/*if (contacts.getElementsByTagName("li")[0].id === "empty_list") {
			contacts.innerHTML = "";
		}*/
		var li = document.createElement("li");
		var span = document.createElement("span");
		var info = document.createTextNode("NEWCHAT:" + username);
		li.setAttribute("id", "0");
		li.setAttribute("name", id);
		span.appendChild(info);
		li.appendChild(span);
		var fc = contacts.firstChild;
		contacts.insertBefore(li, fc);
	setTimeout(view.removeList, 10000,li);


		view.removeBox();
		li.onclick = controller.startChat;

	},
	updateChatList: function (arr) {
		console.log(arr[0].chatID);
		var chatId = arr[0].chatID;
		var fn = document.getElementById("friend_name");
		fn.setAttribute("value", chatId);



	}
};
