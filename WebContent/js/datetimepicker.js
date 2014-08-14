$(document).ready(
		function() {
			// 時間選擇器
			$.timepicker.regional['zh-TW'] = {
				dayNames : [ "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" ],
				dayNamesMin : [ "日", "一", "二", "三", "四", "五", "六" ],
				monthNames : [ "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月",
						"九月", "十月", "十一月", "十二月" ],
				monthNamesShort : [ "一月", "二月", "三月", "四月", "五月", "六月", "七月",
						"八月", "九月", "十月", "十一月", "十二月" ],
				prevText : "上月",
				nextText : "次月",
				weekHeader : "週",
				timeOnlyTitle : "選擇時分秒",
				timeText : "時間",
				hourText : "時",
				minuteText : "分",
				secondText : "秒",
				millisecText : "毫秒",
				timezoneText : "時區",
				currentText : "現在時間",
				closeText : "確定",
				amNames : [ "上午", "AM", "A" ],
				pmNames : [ "下午", "PM", "P" ]
			};
			$.timepicker.setDefaults($.timepicker.regional["zh-TW"]);
			// 時間選擇器區間
			var joinstartdatetime = $('#joinstartdatetime');
			var joinenddatetime = $('#joinenddatetime');
			joinstartdatetime.datetimepicker({
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true,
				yearRange : "2014:2024",
				showSecond : true,
				timeFormat : 'HH:mm:ss',
				minDate : new Date(),
				onClose : function(dateText, inst) {
					if (joinenddatetime.val() != '') {
						var testStartDate = joinstartdatetime
								.datetimepicker('getDate');
						var testEndDate = joinenddatetime
								.datetimepicker('getDate');
						if (testStartDate > testEndDate)
							joinenddatetime.datetimepicker('setDate',
									testStartDate);
					} else {
						joinenddatetime.val(dateText);
					}
				},
				onSelect : function(selectedDateTime) {
					joinenddatetime.datetimepicker('option', 'minDate',
							joinstartdatetime.datetimepicker('getDate'));
				}
			});
			joinenddatetime.datetimepicker({
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true,
				yearRange : "2014:2024",
				showSecond : true,
				timeFormat : 'HH:mm:ss',
				minDate : new Date(),
				onClose : function(dateText, inst) {
					if (joinstartdatetime.val() != '') {
						var testStartDate = joinstartdatetime
								.datetimepicker('getDate');
						var testEndDate = joinenddatetime
								.datetimepicker('getDate');
						if (testStartDate > testEndDate)
							joinstartdatetime.datetimepicker('setDate',
									testEndDate);
					} else {
						joinstartdatetime.val(dateText);
					}
				},
				onSelect : function(selectedDateTime) {
					joinstartdatetime.datetimepicker('option', 'maxDate',
							joinenddatetime.datetimepicker('getDate'));
				}
			});
			var gpstartdatetime = $('#gpstartdatetime');
			var gpenddatetime = $('#gpenddatetime');
			gpstartdatetime.datetimepicker({
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true,
				yearRange : "2014:2024",
				showSecond : true,
				timeFormat : 'HH:mm:ss',
				minDate : new Date(),
				onClose : function(dateText, inst) {
					if (gpenddatetime.val() != '') {
						var testStartDate = gpstartdatetime
								.datetimepicker('getDate');
						var testEndDate = gpenddatetime
								.datetimepicker('getDate');
						if (testStartDate > testEndDate)
							gpenddatetime.datetimepicker('setDate',
									testStartDate);
					} else {
						gpenddatetime.val(dateText);
					}
				},
				onSelect : function(selectedDateTime) {
					gpenddatetime.datetimepicker('option', 'minDate',
							gpstartdatetime.datetimepicker('getDate'));
				}
			});
			gpenddatetime.datetimepicker({
				dateFormat : 'yy-mm-dd',
				changeMonth : true,
				changeYear : true,
				yearRange : "2014:2024",
				showSecond : true,
				timeFormat : 'HH:mm:ss',
				minDate : new Date(),
				onClose : function(dateText, inst) {
					if (gpstartdatetime.val() != '') {
						var testStartDate = gpstartdatetime
								.datetimepicker('getDate');
						var testEndDate = gpenddatetime
								.datetimepicker('getDate');
						if (testStartDate > testEndDate)
							gpstartdatetime.datetimepicker('setDate',
									testEndDate);
					} else {
						gpstartdatetime.val(dateText);
					}
				},
				onSelect : function(selectedDateTime) {
					gpstartdatetime.datetimepicker('option', 'maxDate',
							gpenddatetime.datetimepicker('getDate'));
				}
			});			
			
			
		});