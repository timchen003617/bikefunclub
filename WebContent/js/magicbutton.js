$(function() {
	//會員註冊
	$("#magic_register").click(function(){
		$("#name").val("小明");
		$("#nickname").val("Ming");
		$("#birthday").val("1991-08-22");
		$("#email").val("yachun.tseng427@gmail.com");
		$("#password").val("12345678");
		$("#checkpassword").val("12345678");
	});
	//發起揪團
	$("#magic_insertgp").click(function(){
		$("#gpclsno").val("2");
		$("#gptitle").val("中央大學單車團");
		CKEDITOR.instances.gpdesc.setData("中央大學是個練習的好地方，大家一起來練習騎單車吧。");
		$("#gpmaxnum").val("10");
		$("#gpnote").val("保持愉快的心情");
	});
	//路線儲存
	$("#magic_insertrot").click(function(){
		$("#rotname").val("中央大學");
		$("#rottag").val("中央大學週邊道路");
		$("#rotdesc").val("中央大學週邊道路");
	});
});