$(document).ready(
		function() {
			// 設定中文語系
			$.datepicker.regional['zh-TW'] = {
				dayNames : [ "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" ],
				dayNamesMin : [ "日", "一", "二", "三", "四", "五", "六" ],
				monthNames : [ "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月",
						"九月", "十月", "十一月", "十二月" ],
				monthNamesShort : [ "一月", "二月", "三月", "四月", "五月", "六月", "七月",
						"八月", "九月", "十月", "十一月", "十二月" ],
				prevText : "上月",
				nextText : "次月",
				weekHeader : "週"
			};
			// 將預設語系設定為中文
			$.datepicker.setDefaults($.datepicker.regional["zh-TW"]);
			$('input[name$="Date"]')
					.datepicker(
							{
								dateFormat : 'yy/mm/dd',
								changeMonth : true,
								changeYear : true,
								yearRange : "2004:2024",
								beforeShow : function() {
									if ($(this).attr('maxDate')) {
										var dateItem = $('#'
												+ $(this).attr('maxDate'));
										if (dateItem.val() !== "") {
											$(this).datepicker('option',
													'maxDate', dateItem.val());
										}
									}

									if ($(this).attr('minDate')) {
										var dateItem = $('#'
												+ $(this).attr('minDate'));
										if (dateItem.val() !== "") {
											$(this).datepicker('option',
													'minDate', dateItem.val());
										}
									}
								}
							});
			$('input[name$="date"]')
					.datepicker(
							{
								dateFormat : 'yy/mm/dd',
								changeMonth : true,
								changeYear : true,
								yearRange : "2004:2024",
								minDate : new Date(),// 不能選今天以前日期
								beforeShow : function() {
									if ($(this).attr('maxDate')) {
										var dateItem = $('#'
												+ $(this).attr('maxDate'));
										if (dateItem.val() !== "") {
											$(this).datepicker('option',
													'maxDate', dateItem.val());
										}
									}

									if ($(this).attr('minDate')) {
										var dateItem = $('#'
												+ $(this).attr('minDate'));
										if (dateItem.val() !== "") {
											$(this).datepicker('option',
													'minDate', dateItem.val());
										}
									}
								}
							});
			// 生日
			$("#birthday").datepicker({
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true,
				yearRange : "1884:2014",
				maxDate : new Date(),// 不能選今天以後的日期
			});
	});
