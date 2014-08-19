<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div id="backmain" class="col-md-10">
	<h1 class="page-header">相簿分類管理</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">相簿分類管理</h3>
		</div>
		<div class="panel-body">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<p class="red">請修正以下錯誤:</p>
				<ul class="red">
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</c:if>
<ul>
  <li><a href='<%=request.getContextPath() %>/back/albcls/page_listAllAlbcls.jsp'>List</a> all Albcls. </li> <br>
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
  </div>
  </div>
</ul>
</div>





<h3>相簿分類管理</h3>

<ul>
  <li><a href='<%=request.getContextPath() %>/back/albcls/page_addAlbcls.jsp'>Add</a> a new Albcls.</li>
</ul>


