$(function() {
	$("#blog_insert,#blog_update").validate({
		rules : {
			bgtitle : {
				required : true,
				maxlength : 20
			}
		},// end of rules
		messages : {
			bgtitle : {
				required : "請輸網誌標題",
				maxlength :"網誌標題不能超過20個字"
			
			}
		
		}
	});
});