$(document).ready(function() {
	$("#friname").keypress(function(e) {
		if (e.keyCode == 13 && !e.shiftKey) {//按下enter
			e.preventDefault();
			var request = $.ajax({
				type : "post",
				url : "/bikefunclub/FriendListServlet",
				data : fricreatQueryString(),
				dataType : "html",

			});
			request.done(function(msg) {
				$(".tab_content#panel1").html(msg);
			});
			request.fail(function(jqXHR, textStatus) {
				alert("Request failed: " + textStatus);
			});
		}

	});
	$("#memname").keypress(function(e) {
		if (e.keyCode == 13 && !e.shiftKey) {
			e.preventDefault();
			var request = $.ajax({
				type : "post",
				url : "/bikefunclub/FriendListServlet",
				data : memcreatQueryString(),
				dataType : "html",

			});
			request.done(function(msg) {
				$(".tab_content#panel2").html(msg);
			});
			request.fail(function(jqXHR, textStatus) {
				alert("Request failed: " + textStatus);
			});
		}
	});
});

function fricreatQueryString() {
	var friname = $("#friname").val();
	var action = $("#actionfri").val();
	var queryString = {
		friname : friname,
		action : action
	};
	return queryString;
}
function memcreatQueryString() {
	var memname = $("#memname").val();
	var action = $("#actionmem").val();
	var queryString = {
		memname : memname,
		action : action
	};
	return queryString;
}