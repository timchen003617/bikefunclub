$(document).ready(function() {
	$("#btnallfrilist").click(function() {
		var request = $.ajax({
			type : "post",
			url : "/Bikefunclub/FriendListServlet",
			data : fricreatQueryString(),
			dataType : "html",

		});
		request.done(function(msg) {
			$(".tab_content#panel1").html(msg);
		});
		request.fail(function(jqXHR, textStatus) {
			alert("Request failed: " + textStatus);
		});

	});
	$("#btnnotfrimemlist").click(function() {
		var request = $.ajax({
			type : "post",
			url : "/Bikefunclub/FriendListServlet",
			data : memcreatQueryString(),
			dataType : "html",

		});
		request.done(function(msg) {
			$(".tab_content#panel2").html(msg);
		});
		request.fail(function(jqXHR, textStatus) {
			alert("Request failed: " + textStatus);
		});

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