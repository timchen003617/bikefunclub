var messageold = new Array();// 舊的訊息記錄
var sendermemno;// 登入者的會員編號
var memname;// 會員姓名
var datetime;
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
Date.prototype.format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};
// 取系統時間
function getdatetime() {
	datetime = "最近一次發送訊息時間:" + new Date().format("yyyy-MM-dd hh:mm:ss");
}
$(function() {
	// 按下開啟公開聊天後自動發送,每一秒自動檢查有無新訊息
	$("#openchatbox").click(function() {
		dwr.engine.setActiveReverseAjax(true);// 啟動反轉
		setTimeout("sendMessage()", 1000);
	});
});
function sendMessage() {
	var text = "";
	text = dwr.util.getValue("text");
	// 清空DOM id 為text的值
	dwr.util.setValue("text", "");
	sendermemno = $("#sendermemno").val();// 發訊人的會員編號
	/*
	 * callback寫法,呼叫Server端的Chat.addMessage方法，當server端messages物件傳回後，
	 * 會呼叫JavaScript的gotMessages函式，傳入messages物件
	 */
	Chat.addMessage(sendermemno, text, gotMessages);

}
// 訊息寫入到id為chatlog的dom
function gotMessages(messages) {
	// 收到新訊息，則跳出聊天視窗chatbox
	if (messageold.length != messages.length) {
		$("#chatbox").show();// 跳出聊天視窗
		messageold.push(messages[messages.length]);// 新訊息記錄覆蓋舊的訊息記錄
	}
	var chatlog = "";

	for ( var data in messages) {
		if (messages[data].senderno == sendermemno) {// 登入者自己
			chatlog += "<li class='text-right'>"
					+ dwr.util.escapeHtml(messages[data].chatdate
							.toLocaleDateString()
							+ " "+ messages[data].chatdate.toLocaleTimeString())
					+ "</li>";
			chatlog += "<li class='text-right'><strong>"
					+ dwr.util.escapeHtml(messages[data].chattext)
					+ "</strong></li>";
		} else {// 登入者好友
			chatlog += "<li class='text-left'>"
				+ dwr.util.escapeHtml(messages[data].chatdate
						.toLocaleDateString()
						+ " "+messages[data].chatdate.toLocaleTimeString())
				+ "</li>";
		chatlog += "<li class='text-left'><strong>"
				+ dwr.util.escapeHtml(messages[data].chattext)
				+ "</strong></li>";
		}
	}
	dwr.util.setValue("chatlog", chatlog, {
		escapeHtml : false
	});
	setTimeout("queryMessage()", 1000);
}
// 取得server端list存的訊息紀錄
function queryMessage() {
	// callback寫法,呼叫Server端的Chat.getMessages方法，當結果傳回後，會呼叫JavaScript的gotMessages函式
	Chat.getMessages(gotMessages);
};
