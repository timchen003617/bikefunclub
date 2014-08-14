$(function() {
	$("#register,#update_mem_input").validate({
		rules : {
			name : {
				required : true,
				maxlength : 10
			},
			email : {
				required : true,
				email : true,
				maxlength : 30
			},
			telm : {
				number : true,
				maxlength : 15
			},
			sex : {
				required : true,
			},
			birthday : {
				required : true,
				date : true
			},
			id : {
				rangelength : [ 10, 15 ]
			},
			zip : {
				number : true,
				rangelength : [ 3, 5 ]
			},
			password : {
				required : true,
				rangelength : [ 8, 16 ]
			},
			checkpassword : {
				required : true,
				equalTo : '#password'
			},
		},// end of rules
		messages : {
			name : {
				required : "請輸入姓名",
				maxlength : "姓名不能超過10個字"
			},
			email : {
				required : "請輸入電子郵件地址",
				email : "email格式錯誤",
				maxlength : "email不能超過30個字"
			},
			telm : {
				number : "行動電話必須為數字格式",
				maxlength : "行動電話不能超過15個字"
			},
			sex : {
				required : "請選擇性別"
			},
			birthday : {
				required : "請選擇生日",
				date : "生日必須為日期格式"
			},
			id : {
				rangelength : "身分證範圍為10-15個字",
			},
			zip : {
				number : "郵遞區號請輸入數字",
				rangelength : "郵遞區號範圍為3-5個字"
			},
			password : {
				required : "請輸入密碼",
				rangelength : "密碼長度為8-16個字",
			},
			checkpassword : {
				required : "請輸入確認密碼",
				equalTo : "確認密碼必須和密碼相同"
			}
		},// end of messages
		errorPlacement : function(error, element) {
			if (element.is(":radio") || element.is(":checkbox")) {
				error.appendTo(element.parent());
			} else {
				error.insertAfter(element);
			}
		}
	});
});