$(function() {
	var w = $("#slider_content").width();
	$('#slider_content').css('height', ($(document).height()) + 'px'); // 將區塊自動撐滿畫面高度
	$("#fb_tab").click(function() {// 按下滑鼠時
		if ($("#slider_scroll").css('right') == '-' + w + 'px') {
			$("#slider_scroll").animate({
				right : '0px'
			}, 600, 'swing');
		}
	});

	$("#slider_content .hideslider").click(function() {// 點選隱藏邊欄後
		$("#slider_scroll").animate({
			right : '-' + w + 'px'
		}, 600, 'swing');
	});
	// 打開聊天室窗
	$("#openchatbox").click(function() {
		$("#chatbox").show();
	});
	// 關閉聊天室窗
	$("#chatbox").on("click", "#closechatbox", (function() {
		$("#chatbox").hide();
	}));

	$( "#chatbox" ).draggable();

});
// 打開聊天室窗for 1對1聊天
// function openchatbox(sendermemno,receivermemno,i){
// $("#chatbox"+ i).html(
// "<div id='closechatbox'"+ i +">"+receivermemno+
// "<span class='glyphicon glyphicon-remove'></span>"+
// "</div><div id='chatbox-inner'>"+
// "<div id='chattext'><ul id='chatlog'></ul>"+
// "</div></div><div id='chatbox-input'><hr>"+
// "<input type='hidden' id='sendermemno'"+i+ "value="+sendermemno+">"+
// "<input type='hidden' id='receivermemno'"+i+"value="+receivermemno+">"+
// "<input id='text'"+i+"onkeypress='dwr.util.onReturn(event, sendMessage)'/>"+
// "<input type='button' value='發送' onclick='sendMessage("+i+")'/>"+
// "</div>"
// );
// $("#chatbox"+ i).css({
// "position":"fixed",
// "z-index": "105",
// "right": "200px",
// "bottom": "20px",
// "right": 200*i+"px",
// "width": "200px",
// "background-color": "#e9eaed",
// "display": "block",
// "border": "1px solid #3c5a98"
// });
// $("#chatbox-inner").css({
// "height":"200px",
// "overflow-x":"hidden",
// "overflow-y": "auto"
// });
// $("#chatbox hr").css({
// "border-top":"1px solid #bbb",
// "margin":"5px 0px",
// });
// $("#closechatbox"+ i).css({
// "margin-left":"180px",
// "cursor":"pointer",
// });
// $("#chattext").css({
// "min-height":"150px",
// });
// $("#chattext ul li").css({
// "display":"block",
// "list-style-type":"none",
// "word-break":"break-all"/* 強制文字斷行 */
// });
// $("#chatlog").css({
// "margin":"0px",
// "padding":"0px 10px"
// });
// $("#chatbox-input").css({
// "margin":"5px 0px",
// "padding":"0px 5px"
// });
// $("#chatbox-input input").css({
// "height":"30px",
// "padding":"0px 5px"
// });
// };
