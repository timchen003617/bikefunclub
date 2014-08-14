<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>IBM Albcls: Home</title></head>
<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td><h3>IBM Albcls: Home</h3>	<font color=red>( MVC )</font></td></tr></table>

<p>This is the Home page for IBM Albcls: Home</p>

<h3>相簿分類查詢:</h3>
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
  <li><a href='<%=request.getContextPath() %>/back/albcls/listAllAlbcls.jsp'>List</a> all Albcls. </li> <br>
</ul>
<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/AlbclsServlet" >
        <b>輸入相簿分類編號 :</b>
        <input type="text" name="albclsno">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="albclsSvc" scope="page" class="com.bikefunclub.albcls.model.AlbclsService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/AlbclsServlet" >
       <b>選擇相簿分類編號:</b>
       <select size="1" name="albclsno">
         <c:forEach var="albclsVO" items="${albclsSvc.all}" > 
          <option value="${albclsVO.albclsno}">${albclsVO.albclsno}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
</ul>




<h3>相簿分類管理</h3>

<ul>
  <li><a href='<%=request.getContextPath() %>/back/albcls/addAlbcls.jsp'>Add</a> a new Albcls.</li>
</ul>

</body>

</html>
