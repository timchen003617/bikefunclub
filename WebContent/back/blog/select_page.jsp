<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM Blog: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Blog: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM Blog: Home</p>

<h3>資料查詢:</h3>
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
  <li><a href='<%=request.getContextPath() %>/back/blog/listAllBlog.jsp'>List</a> all Blogs. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/BlogServlet" >
        <b>網誌編號 :</b>
        <input type="text" name="blogno">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
   
<jsp:useBean id="blogSvc" scope="page" class="com.bikefunclub.blog.model.BlogService" />
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/BlogServlet" >
       <b>選擇網誌編號:</b>
       <select size="1" name="blogno">
         <c:forEach var="blogVO" items="${blogSvc.all}" > 
          <option value="${blogVO.blogno}">${blogVO.blogno}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/BlogServlet" >
       <b>選擇網誌分類編號:</b>
       <select size="1" name="blogno">
         <c:forEach var="blogVO" items="${blogSvc.all}" > 
          <option value="${blogVO.blogno}">${blogVO.blogclsno}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>網誌新增</h3>

<ul>
  <li><a href='<%=request.getContextPath() %>/back/blog/addBlog.jsp'>Add</a> a new Blog.</li>
</ul>

</body>

</html>
