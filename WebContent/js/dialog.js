$(function() {
	// 會員修改成功訊息
	$("#memmdok").dialog({
		autoOpen : false,
	});
	// 信箱認證成功訊息
	$("#mailok").dialog({
		autoOpen : false,
	});
	// 是否刪除好友提示
	var id = null;
	delfirconfirm = $("#delfri-confirm").dialog({
		autoOpen : false,
		resizable : false,
		modal : true,
		buttons : {
			"確定刪除好友" : function() {
				// off 取消之前的事件綁定
				$("#"+id).off("click","#"+id).submit();
				$(this).dialog("close");
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		}
	});
	// 為動態新增的Elements綁定事件jQuery .on()
	$("form.del #delfri").on("click",this, function(e) {
		id = "del"+$(this).attr("name");
		delfirconfirm.dialog("open");
		e.preventDefault();// 事件綁定，無法submit到FriendListServlet
	});
});
