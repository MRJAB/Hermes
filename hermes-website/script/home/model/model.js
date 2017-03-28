var model = {

	loadData: function (data, fun, page) {
		var xhr = new XMLHttpRequest();
		xhr.open("POST", "../php_files/" + page, true);
		xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
		xhr.send(data);
		xhr.onreadystatechange = function () {

			if (xhr.readyState === 4) {
				if ((xhr.status >= 200 && xhr.status < 300) || (xhr.status === 304)) {
					if (xhr.responseText) {
						var arr = null;
						console.log(xhr.responseText);

						if (xhr.response !== "empty") {
							//console.log(xhr.response);

							arr = JSON.parse(xhr.responseText);

						}
						model.findFunction(arr, fun);

					}
				}
			}
		};
	},

	findFunction: function (arr, fun) {

		if (fun === "searchUser") {
			view.searchBox(arr);
		} else if (fun === "loadChat") {

			view.chat(arr);
		} else if (fun === "createRequestBox") {
			view.createRequestBox(arr);
		} else if (fun === "updateChatList") {
			view.updateChatList(arr);
		} else if (fun === "latestChat") {
			view.latestChat(arr);
		} else if (fun === "deletetChat") {
			view.deleteChat(arr);

		}
	}
};
