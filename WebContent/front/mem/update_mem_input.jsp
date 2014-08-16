<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<!-- 	MemberServlet forward 過來的request key:memVO -->
<%
	String path = request.getContextPath();
%>
<div class="container body-content">
	<div class="row">
		<form class="form-memmodify" action="<%=path%>/MemberServlet"
			method="post" enctype="multipart/form-data" name="update_mem_input"
			id="update_mem_input">
			<fieldset>
				<legend>個人資料管理</legend>
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
					<input type="text" name="name" id="name" value="${memVO.memname}"
						placeholder="姓名" />
				</div>
				<div>
					<label for="nickname" class="label">綽號</label> <input type="text"
						name="nickname" id="nickname" value="${memVO.memnickname}"
						maxlength="6" placeholder="綽號" />
				</div>
				<div>
					<label for="sex" class="label"><span class="red">*性別</span></label>
					<c:choose>
						<c:when test="${memVO.memsex=='F'}">
							<input type="radio" name="sex" id="sex" value="M" />男性
					<input type="radio" name="sex" value="F" checked
								style="margin-left: 5px;" />女性 
					</c:when>
						<c:otherwise>
							<input type="radio" name="sex" id="sex" value="M" checked />男性	
					<input type="radio" name="sex" value="F" style="margin-left: 5px;" />女性
					</c:otherwise>
					</c:choose>
				</div>
				<div>
					<label for="birthday" class="label"><span class="red">*生日</span></label>
					<input type="text" id="birthday" value="${memVO.membirth}"
						name="birthday" readonly placeholder="西元年月日" />
				</div>
				<div>
					<label for="id" class="label">身分證字號</label> <input type="text"
						name="id" id="id" value="${memVO.memid}" placeholder="A123456789" />
				</div>
				<div>
					<label for="telm" class="label">行動電話</label> <input type="text"
						name="telm" id="telm" value="${memVO.memtelm}" maxlength="15"
						placeholder="0939123456" />
				</div>
				<div>
					<label for="email" class="label">E-MAIL</label> <input
						style="background-color: transparent;" type="text" name="email"
						id="email" value="${memVO.mememail}" readonly>
				</div>
				<div>
					<label for="password" class="label"><span class="red">*新密碼</span></label>
					<input type="password" name="password" id="password"
						value="${memVO.mempw}" size="20" maxlength="20"
						placeholder="密碼長度至少為8碼" /> <sub></sub>
				</div>
				<div>
					<label for="checkpassword" class="label"><span class="red">*確認密碼</span></label>
					<input type="password" name="checkpassword" id="checkpassword"
						value="${memVO.mempw}" size="20" maxlength="20"
						placeholder="請再輸入一次密碼" /> <sub></sub>
				</div>
				<div>
					<p>
						<label for="zip" class="label">郵遞區號</label> <input type="text"
							name="zip" id="zip" value="${memVO.memzip}" size="5"
							placeholder="324" />
					</p>
					<p>
						<label for="addr" class="label">地址</label> <input type="text"
							name="addr" id="addr" value="${memVO.memaddr}" size="60"
							placeholder="桃園縣平鎮市中央路xxx" />
					</p>
				</div>
				<!--預覽圖片顯示區域 -->
				<div id="preview">
					<c:if test="${empty memVO.memfile}">
						<img id="imgheadmd"
							src="<%=path%>/img/photo.jpg">
					</c:if>
					<c:if test="${not empty memVO.memfile}">
						<img id="imgheadmd"
							src="<%=path%>/MemreadimgServlet?memno=${memVO.memno}">
					</c:if>
				</div>
				<div>
					<label for="file" class="label">大頭照</label>
					<p style="margin-left: 180px;">${memVO.memfilename}.${memVO.memextname}</p>
					<p style="margin-left: 180px;">
						<input type="file" name="file" id="file"
							value="${memVO.memfilename}.${memVO.memextname}"
							onchange="previewImage(this,'imgheadmd')" />
					</p>
				</div>
			</fieldset>
			<br>
			<div>
				<input type="hidden" name="action" value="update"> <input
					type="hidden" name="memno" value="${memVO.memno}"> <input
					class="btn btn-primary btn-lg" type="submit" value="確定修改">
			</div>
		</form>
	</div>
</div>