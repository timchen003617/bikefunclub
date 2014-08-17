<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.blog.model.*"%>
<%@ page import="com.bikefunclub.blogcls.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>

<%


	String path = request.getContextPath();
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	Integer memno = memVO.getMemno();
	
   
	
	
%>

<div class="container body-content">
	<div class="row">
		<form class="form-blog" action="<%=path%>/BlogServlet" method="post"
			id="blog_insert" name="insert">
			<fieldset>
			<div class="text-right">
					<input class="btn btn-warning" type="button" value="上一頁"
						onClick="history.back();return true;" name="button">
				</div>
				<legend>網誌新增</legend>
				
				<p class="red">*為必填欄位</p>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<div>
						<p class="red">請修正以下錯誤:</p>
						<ul class="red">
							<c:forEach var="message" items="${errorMsgs}">
								<li>${message}</li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
				
			
				
				<jsp:useBean id="blogclsSvc" scope="page"
					class="com.bikefunclub.blogcls.model.BlogclsService" />
				<div>
					<label for="blogclsno" class="label"><span class="red">*網誌分類</span></label>
					<select size="1" name="blogclsno">
						<c:forEach var="blogclsVO" items="${blogclsSvc.all}">
							<option value="${blogclsVO.blogclsno}"
								${(blogVO.blogclsno==blogclsVO.blogclsno)?'selected':'' }>${blogclsVO.blogclsname}
						</c:forEach>
					</select>
				</div>
				<div>
					<label for="bgtitle" class="label"><span class="red">*網誌標題</span></label>
					<input type="text" name="bgtitle" id="bgtitle"
						value="${blogVO.bgtitle}" placeholder="請輸入網誌標題" />
				</div>
				<div>
					<label for="bgtext" class="label"><span class="red"></span></label>
					<div class="ckeditor">
						<textarea class="ckeditor" name="bgtext" id="bgtext"
							placeholder="請輸入網誌內容">${blogVO.bgtext}</textarea>
					</div>
				</div>
				
			</fieldset>
			<br>
			<div>
			    <input type="hidden" name="memno" value="${memVO.memno}">
			    <input  type="hidden" name="blogno" value="${blogVO.blogno}">
				<!--authname先寫死為公開 -->
				<input type="hidden" name="authname" value="PUBLIC"> <input
					type="hidden" name="action" value="insert"><input
					id="insertblog" class="btn btn-primary btn-lg" type="submit"
					value="確定發文">
			</div>
		</form>
	</div>
</div>
<ckeditor:replace replace="bgtext" basePath="/bikefunclub/ckeditor/" />