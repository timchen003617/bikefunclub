<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%
	String path = request.getContextPath();

%>
<!DOCTYPE html>
<html>
<head>
<title>會員註冊</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="<%=path%>/img/favicon.ico" rel="icon" media="screen">
<link href="<%=path%>/css/style.css" rel="stylesheet" media="screen">
<link href="<%=path%>/css/jquery-ui.css" rel="stylesheet" media="screen">
<script src="<%=path%>/js/jquery-1.11.1.min.js"></script>
<script src="<%=path%>/js/jquery-ui.js"></script>
<script src="<%=path%>/js/jquery.validate.min.js"></script>
<script src="<%=path%>/js/datepicker.js"></script>
<script src="<%=path%>/js/validate.js"></script>
<script src="<%=path%>/js/previewimage.js"></script>
</head>
<body>
	<h1 class="loginlogo">
		<a href="<%=path%>/front/home/index.jsp">BikeFunclub</a>
	</h1>
	<form class="form-register" action="<%=path%>/MemberServlet"
		method="post" name="register" id="register" enctype="multipart/form-data">
		<fieldset>
			<legend>會員註冊</legend>
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
			<div>
				<label for="name" class="label"><span class="red">*姓名</span></label>
				<input type="text" name="name" id="name" value="${memVO.memname}" placeholder="姓名" />
			</div>
			<div>
				<label for="nickname" class="label">綽號</label> <input type="text"
					name="nickname" id="nickname" value="${memVO.memnickname}" maxlength="6" placeholder="綽號" />
			</div>
			<div>
				<label for="sex" class="label"><span class="red">*性別</span></label>
				<input type="radio" name="sex" value="M" checked/>男性 <input
					type="radio" name="sex" value="F" style="margin-left: 5px;" />女性
			</div>
			<div>
				<label for="birthday" class="label"><span class="red">*生日</span></label>
				<input type="text" id="birthday" name="birthday" value="${memVO.membirth}" readonly
					placeholder="西元年月日" />
			</div>
			<div>
				<label for="id" class="label">身分證字號</label> <input type="text"
					name="id" id="id" placeholder="A123456789" />
			</div>
			<div>
				<label for="telm" class="label">行動電話</label> <input type="text"
					name="telm" id="telm" maxlength="15" placeholder="0939123456" />
			</div>
			<div>
				<label for="email" class="label"><span class="red">*E-MAIL</span></label>
				<input type="text" name="email" id="email" maxlength="50"
					placeholder="此email,當作登入帳號" />
			</div>
			<div>
				<label for="password" class="label"><span class="red">*密碼</span></label>
				<input type="password" name="password" id="password" size="20"
					maxlength="20" placeholder="密碼長度至少為8碼" /> <sub></sub>
			</div>
			<div>
				<label for="checkpassword" class="label"><span class="red">*確認密碼</span></label>
				<input type="password" name="checkpassword" id="checkpassword"
					size="20" maxlength="20" placeholder="請再輸入一次密碼" /> <sub></sub>
			</div>
			<div>
				<p>
					<label for="zip" class="label">郵遞區號</label> <input type="text"
						name="zip" id="zip" size="5" placeholder="324" />
				</p>
				<p>
					<label for="addr" class="label">地址</label> <input type="text"
						name="addr" id="addr" size="60"
						placeholder="桃園縣平鎮市中央路xxx" />
				</p>
			</div>
			<!--預覽圖片顯示區域 -->
			<div id="preview">
				<img id="imghead" src="#">
			</div>
			<div>
				<label for="file" class="label">大頭照</label>
				<p style="margin-left: 180px;">
					<input type="file" name="file" id="file" value=""
						onchange="previewImage(this,'imghead')" />
				</p>
			</div>
		</fieldset>
		<br>
		<div>
			<input type="hidden" name="action" value="insert"> <input
				class="btn btn-primary btn-lg" type="submit" value="建立帳號">
		</div>
	</form>
</body>
</html>