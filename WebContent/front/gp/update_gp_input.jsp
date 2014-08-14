<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.gp.model.*"%>
<%@ page import="com.bikefunclub.rot.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%
	String path = request.getContextPath();
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	Integer memno = memVO.getMemno();
	RotService rotSvc = new RotService();
	//會員收藏的路線
	List<RotVO> listrotbymem = rotSvc.getrotsBymemnoFromMemrot(memno);
	pageContext.setAttribute("listrotbymem", listrotbymem);
%>
<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script src="<%=path%>/js/googlemap_dialog.js"></script>
<div class="container body-content">
	<div class="row">
		<form class="form-gp" action="<%=path%>/Gp.do" method="post"
			id="launchgp_input" name="launchgp_input">
			<fieldset>
				<legend>發起揪團修改</legend>
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
				<jsp:useBean id="gpclsSvc" scope="page"
					class="com.bikefunclub.gpcls.model.GpclsService" />
				<div>
					<label for="gpclsno" class="label"><span class="red">*揪團分類</span></label>
					<select size="1" name="gpclsno">
						<c:forEach var="gpclsVO" items="${gpclsSvc.all}">
							<option value="${gpclsVO.gpclsno}"
								${(gpVO.gpclsno==gpclsVO.gpclsno)?'selected':'' }>${gpclsVO.gpclsname}</option>
						</c:forEach>
					</select>
				</div>
				<div>
					<label for="gptitle" class="label"><span class="red">*揪團標題</span></label>
					<input type="text" name="gptitle" id="gptitle"
						value="${gpVO.gptitle}" placeholder="揪團標題" />
				</div>
				<div>
					<label for="gpdesc" class="label"><span class="red">*揪團描述</span></label>
					<div class="ckeditor">
						<textarea class="ckeditor" name="gpdesc" id="gpdesc"
							placeholder="揪團描述">${gpVO.gpdesc}</textarea>
					</div>
				</div>
				<div>
					<label for="rotno" class="label"><span class="red">*騎乘路線</span></label>
					<select size="1" name="rotno" id="selected_rot">
						<c:forEach var="rotVO2" items="${listrotbymem}">
							<option value="${rotVO2.rotno}"
								${(rotVO2.rotno==gpVO.rotno)?'selected':'' }>${rotVO2.rotname}</option>
						</c:forEach>
					</select>  <input style="margin-left: 10px;" type="button" id="rotmap"
						class="btn btn-primary" value="地圖預覽" /> <span class="red"
						style="float: left; margin-left: 10px;">(自己的收藏路線)</span>
							<div id="previewrotmap" title="路線地圖"
								style="display: none"></div>
				</div>
				<div>
					<label for="gpnote" class="label"><span>注意事項</span></label>
					<textarea cols="80" rows="7" name="gpnote" id="gpnote"
						placeholder="注意事項">${gpVO.gpnote}</textarea>
				</div>
				<div>
					<label for="gpmaxnum" class="label"><span>參加人數上限</span></label> <input
						type="text" name="gpmaxnum" id="gpmaxnum" value="${gpVO.gpmaxnum}"
						placeholder="參加人數上限" /> <span class="red"
						style="float: left; margin-left: 10px;">(未填寫則預設上限為15人)</span>
				</div>
				<div>
					<label for="joinstartdatetime" class="label"><span
						class="red">*報名開始時間-結束時間</span></label> <input type="text"
						id="joinstartdatetime" value="${gpVO.joinstartdate}"
						name="joinstartdatetime" readonly placeholder="報名開始時間" /><span
						class="dash">-</span> <input type="text" id="joinenddatetime"
						value="${gpVO.joinenddate}" name="joinenddatetime" readonly
						placeholder="報名結束時間" />
				</div>
				<div>
					<label for="gpstartdatetime" class="label"><span
						class="red">*揪團開始時間-結束時間</span></label> <input type="text"
						id="gpstartdatetime" value="${gpVO.gpstartdate}"
						name="gpstartdatetime" readonly placeholder="揪團開始時間" /><span
						class="dash">-</span><input type="text" id="gpenddatetime"
						value="${gpVO.gpenddate}" name="gpenddatetime" readonly
						placeholder="揪團結束時間" />
				</div>
			</fieldset>
			<br>
			<div>
				<input type="hidden" name="memno" value="${memVO.memno}"> <input
					type="hidden" name="gpno" value="${gpVO.gpno}">
				<!-- 				gpauth先寫死為公開 -->
				<input type="hidden" name="gpauth" value="PUBLIC"> <input
					type="hidden" name="action" value="update_launchgp"><input
					type="hidden" name="requestURL"
					value="<%=request.getAttribute("requestURL")%>">
				<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
				<input type="hidden" name="whichPage"
					value="<%=request.getAttribute("whichPage")%>"><input
					id="insertgp" class="btn btn-primary btn-lg" type="submit"
					value="確定修改">
			</div>
		</form>
	</div>
</div>
<ckeditor:replace replace="gpdesc" basePath="/Bikefunclub/ckeditor/" />