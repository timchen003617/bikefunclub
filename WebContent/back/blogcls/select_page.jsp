<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>IBM Blogcls: Home</title></head>
<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td><h3>IBM Blogcls: Home</h3>	<font color=red>( MVC )</font></td></tr></table>

<p>This is the Home page for IBM Blogcls: Home</p>

<h3>相簿查詢:</h3>
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
  <li><a href='<%=request.getContextPath() %>/back/blogcls/listAllBlogcls.jsp'>List</a> all Blogcls. </li> <br><br>

<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/BlogclsServlet" >
        <b>輸入網誌分類編號 :</b>
        <input type="text" name="blogclsno">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="blogclsSvc" scope="page" class="com.bikefunclub.blogcls.model.BlogclsService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/BlogclsServlet" >
       <b>選擇網誌分類編號:</b>
       <select size="1" name="blogclsno">
         <c:forEach var="blogclsVO" items="${blogclsSvc.all}" > 
          <option value="${blogclsVO.blogclsno}">${blogclsVO.blogclsno}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
</ul>


<h3>相簿管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back/blogcls/addBlogcls.jsp'>Add</a> a new Blogcls.</li>
</ul>

</body>

</html>
