$(function(){
	setTabNum($("#tabNum").val());
});
function setTabNum(num){
	$("input[type='hidden'][name='tabNum']").val(num);//抓到現在是幾號頁籤，設定給input為hidden的值
	$("ul").each(function(){
		if($(this).hasClass("pagination pagination-sm")){
			$(this).find("a").each(function(){
				var urlStr = $(this).attr("href");
				var urlStrAry = urlStr.split("&");
				if(urlStrAry.length > 0){
					$(this).attr("href",urlStrAry[0]+"&tabNum="+num);
				}else{
					$(this).attr("href",urlStr+"&tabNum="+num);
				}
			});
		}
	});
}