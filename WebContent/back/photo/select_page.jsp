<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>IBM Photo: Home</title></head>
<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td><h3>IBM Photo: Home</h3>	<font color=red>( MVC )</font></td></tr></table>

<p>This is the Home page for IBM Photo: Home</p>

<h3>相片查詢:</h3>
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
  <li><a href='<%=request.getContextPath() %>/back/photo/listAllPhoto.jsp'>List</a> all Photo. </li> <br><br>

<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/PhotoServlet" >
        <b>輸入相片編號 :</b>
        <input type="text" name="photono">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="photoSvc" scope="page" class="com.bikefunclub.photo.model.PhotoService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/PhotoServlet">
       <b>選擇相片編號:</b>
       <select size="1" name="photono">
         <c:forEach var="photoVO" items="${photoSvc.all}" > 
          <option value="${photoVO.photono}">${photoVO.photono}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/AlbumServlet" >
        <b>輸入相簿編號 :</b>
        <input type="text" name="albno">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  
  <jsp:useBean id="albumSvc" scope="page" class="com.bikefunclub.album.model.AlbumService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/AlbumServlet">
       <b>選擇相簿編號:</b>
       <select size="1" name="albno">
         <c:forEach var="albumVO" items="${albumSvc.all}" > 
          <option value="${albumVO.albno}">${albumVO.albno}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
</ul>


<h3>相片管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back/photo/addPhoto.jsp'>Add</a> a new Photo.</li>
</ul>

</body>

</html>
