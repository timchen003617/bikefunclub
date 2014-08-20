$(function() {
	$("#accordion").accordion({
	    heightStyle: "content",//自動伸縮符合內容高度
	});
	//收合速度100毫秒
	$(".ui-accordion").accordion({
		animate : 100
	});
});