$(function() {
	// bind submit to update textarea from ckeditor
	$('#launchgp_input').submit(
			function() {
				$("textarea.ckeditor").each(
						function() {
							var $textarea = $(this);
							$textarea.val(CKEDITOR.instances[$textarea
									.attr("name")].getData());
						});
			});
	$("#launchgp_input").validate({
		rules : {
			gptitle : {
				required : true,
				maxlength : 100
			},
			gpdesc : {
				required : true
			},
			rotno:{
				required : true
			},
			gpmaxnum : {
				number : true,
				maxlength : 2
			},
			joinstartdatetime : {
				required : true,
			},
			joinenddatetime : {
				required : true,
			},
			gpstartdatetime : {
				required : true,
			},
			gpenddatetime : {
				required : true,
			}
		},
		messages : {
			gptitle : {
				required : "請輸入揪團標題",
				maxlength : "標題不能超過100個字"
			},
			gpdesc : {
				required : "----------請輸入揪團描述----------",
			},
			rotno:{
				required : "請選擇路線"
			},
			gpmaxnum : {
				number : "人數上限請輸入數字",
				maxlength: "人數不能超過十位數"
			},
			joinstartdatetime : {
				required : "請輸入報名開始時間",
			},
			joinenddatetime : {
				required : "請輸入報名結束時間",
			},
			gpstartdatetime : {
				required : "請輸入揪團開始時間",
			},
			gpenddatetime : {
				required : "請輸入揪團結束時間",
			}
		},
		ignore : '',
		errorPlacement : function(error, element) {
			if (element.attr("name") == "gpdesc") {
				error.insertAfter($(element).parent().children().last());
			} else {
				error.insertAfter(element);
			}
		}
	});
});