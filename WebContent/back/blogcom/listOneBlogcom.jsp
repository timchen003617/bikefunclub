<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.bikefunclub.blogcom.model.*"%>
<%
    BlogcomVO blogcomVO = (BlogcomVO) request.getAttribute("blogcomVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>網誌留言資料 - listOneBlogcom.jsp</title>
</head>


	<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>留言編號</th>
		<th>網誌編號</th>
		<th>會員編號</th>
		<th>留言內容</th>
		<th>發布時間</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=blogcomVO.getBgcomno()%></td>
		<td><%=blogcomVO.getBlogno()%></td>
		<td><%=blogcomVO.getMemno()%></td>
		<td><%=blogcomVO.getBgcomtext()%></td>
		<td><%=blogcomVO.getBgcomtime()%></td>
	</tr>
</table>

</body>
</html>
