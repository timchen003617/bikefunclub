$(document).ready(function() {
	$("input#friname").autocomplete({
		delay : 100,
		minLength : 0,
		source : function(request, response) {
			$.ajax({
				type: "post",
				url : "/bikefunclub/Friautocomplete",
				dataType : "json",
				data: request,
				success : function(data, textStatus, jqXHR) {
//					console.log(data);
					var items = data;
					response(items);
				},
				error : function(jqXHR, textStatus, errorThrown) {
//					console.log(textStatus);
				}
			});
		}

	});
	$("input#memname").autocomplete({
		delay : 100,
		minLength : 0,
		source : function(request, response) {
			$.ajax({
				type: "post",
				url : "/bikefunclub/Memautocomplete",
				dataType : "json",
				data: request,
				success : function(data, textStatus, jqXHR) {
//					console.log(data);
					var items = data;
					response(items);
				},
				error : function(jqXHR, textStatus, errorThrown) {
//					console.log(textStatus);
				}
			});
		}

	});	
});