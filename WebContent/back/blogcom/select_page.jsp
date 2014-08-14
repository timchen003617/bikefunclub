<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>IBM Blogcom: Home</title></head>
<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td><h3>IBM Blogcom: Home</h3>	<font color=red>( MVC )</font></td></tr></table>

<p>This is the Home page for IBM Blogcom: Home</p>

<h3>網誌留言查詢:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
<ul>
  <li><a href='<%=request.getContextPath() %>/back/blogcom/listAllBlogcom.jsp'>List</a> all Blogcom. </li> <br>
</ul>
<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/BlogcomServlet" >
        <b>輸入網誌留言編號 :</b>
        <input type="text" name="bgcomno">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="blogcomSvc" scope="page" class="com.bikefunclub.blogcom.model.BlogcomService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/BlogcomServlet" >
       <b>選擇網誌留言:</b>
       <select size="1" name="bgcomno">
         <c:forEach var="blogcomVO" items="${blogcomSvc.all}" > 
          <option value="${blogcomVO.bgcomno}">${blogcomVO.bgcomno}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
</ul>




<h3>網誌留言管理</h3>

<ul>
  <li><a href='<%=request.getContextPath() %>/back/blogcom/addBlogcom.jsp'>Add</a> a new Blogcom.</li>
</ul>

</body>

</html>
