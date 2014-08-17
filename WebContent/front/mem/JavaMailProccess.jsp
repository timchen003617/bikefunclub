<%@ page language="java" import="java.util.*,java.io.*"
	contentType="text/html;charset=UTF-8"%>
<%@ page import="javax.mail.*"%>
<%@ page import="javax.mail.internet.*"%>
<%@ page import="javax.activation.*"%>
<!-- 會員註冊發送認證信 ，點選連結完成認證-->
<%!InternetAddress[] address = null;%>

<%

	String memname = (String) request.getAttribute("memname"); 
	String getmailurl = (String)request.getAttribute("getmailurl");
	
	String mailserver = "140.115.236.9";
	String From = "Bikefunclub@test.com";
	String to = (String) request.getAttribute("mememail"); //String to = request.getParameter("email");
	String Subject = "Bikefunclub會員註冊認證信";
    
	MimeBodyPart textPart = new MimeBodyPart();
	StringBuffer messageText = new StringBuffer();
	messageText.append("<h3>Hello!"+memname+"你好，歡迎加入Bikefunclub</h3>");
	messageText.append("<h3>請點選此連結，完成信箱認證:</h3>");
	messageText.append("<a href='"+getmailurl+"'>"+getmailurl+"</a>");
    textPart.setContent(messageText.toString(), "text/html; charset=UTF-8");
	
	
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

		// 設定傳送信的內文
		Multipart email = new MimeMultipart();
        email.addBodyPart(textPart);
        msg.setContent(email);
		// 送信
		Transport.send(msg);

		System.out.println("傳送成功!");
	} catch (MessagingException mex) {
		System.out.println("傳送失敗!");

	}
%>