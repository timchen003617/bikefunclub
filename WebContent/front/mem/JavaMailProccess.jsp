<%@ page language="java" import="java.util.*,java.io.*"
	contentType="text/html;charset=UTF-8"%>
<%@ page import="javax.mail.*"%>
<%@ page import="javax.mail.internet.*"%>
<%@ page import="javax.activation.*"%>

<!-- 會員註冊發送認證信 -->
<%!InternetAddress[] address = null;%>

<%
	String ch_name = request.getParameter("memacc");
	; //String ch_name = request.getParameter("ch_name");
	String passRandom = (String) request.getAttribute("mempw"); //String passRandom = request.getParameter("passRandom");

	String mailserver = "140.115.236.9";
	String From = "Bikefunclub@test.com";
	String to = request.getParameter("mememail"); //String to = request.getParameter("email");
	String Subject = "您的密碼";
	String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom
			+ "\n" + " (已經啟用)";

	boolean sessionDebug = false;

	try {

		// 設定所要用的Mail 伺服器和所使用的傳送協定
		java.util.Properties props = System.getProperties();
		props.put("mail.host", mailserver);
		props.put("mail.transport.protocol", "smtp");

		// 產生新的Session 服務
		javax.mail.Session mailSession = javax.mail.Session
				.getDefaultInstance(props, null);
		mailSession.setDebug(sessionDebug);

		Message msg = new MimeMessage(mailSession);

		// 設定傳送郵件的發信人
		msg.setFrom(new InternetAddress(From));

		// 設定傳送郵件至收信人的信箱
		address = InternetAddress.parse(to, false);
		msg.setRecipients(Message.RecipientType.TO, address);

		// 設定信中的主題 
		msg.setSubject(Subject);
		// 設定送信的時間
		msg.setSentDate(new Date());

		// 設定傳送信的MIME Type
		msg.setText(messageText);

		// 送信
		Transport.send(msg);

		System.out.println("傳送成功!");
	} catch (MessagingException mex) {
		System.out.println("傳送失敗!");

	}
%>