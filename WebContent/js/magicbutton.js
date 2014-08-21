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
	//網誌新增
	$("#magic_inserblog").click(function(){
		$("#bgtitle").val("櫻木花道");
		CKEDITOR.instances.bgtext.setData("春雷震起出舊墉嚴寒與陰雨始終束縛著人們，冬季的北台灣一直處於半冬眠狀態，直到二月開春後，蟄伏已久的靈魂終於破繭而出，著名的賞櫻地點都是綿延數公里的車潮。其實淡水、三芝還有多處賞櫻秘境，找個非假日，踩上單車，" +
				         "沿著大屯山西麓的小徑騎一趟，享受高品質的櫻花旅行。");
	});
	//後端員工新增
	$("#magic_inseremp").click(function(){
		$("#empacc").val("Daniel_ZENG");
		$("#empname").val("謝瑞榮");
		$("#empemail").val("yachun.tseng427@gmail.com");
		$("#empaddr").val("新北市淡水區新民街21號7樓");
		$("#empid").val("G127172987");
		$("#emptel").val("02-26224077");
		
		
	});
});
