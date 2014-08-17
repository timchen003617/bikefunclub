<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.rot.model.*"%>
<%@ page import="com.bikefunclub.memrot.model.*"%>
<%@ page import="com.bikefunclub.rotcls.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>

<%
	String path = request.getContextPath();
	String servletpath = request.getServletPath();
	RotService rotSvc = new RotService();
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	Integer memno = memVO.getMemno();
	pageContext.setAttribute("memno",memno);
    List<RotVO> list_rot = rotSvc.getRotsBymemno(memno);
    pageContext.setAttribute("list_rot",list_rot);
    
    List<RotVO> list_memrot = rotSvc.getrotsBymemnoFromMemrot(memno);
    pageContext.setAttribute("list_memrot",list_memrot);
    
// 	RotVO rotVO = (RotVO) request.getAttribute("rotVO");
// 	//String rotno = (String)request.getAttribute("rotno");
// 	pageContext.setAttribute("rotno", rotVO.getRotno());
// 	MemrotService memrotSvc = new MemrotService();
// 	Integer memno = 1;
// 	MemrotVO memrotVO = memrotSvc.findByPrimaryKey(memno,
// 			rotVO.getRotno());
%>

<div class="container body-content">
	<div class="col-md-13">
        <div class="panel panel-default">
            <div class="panel-heading">
            <h3 class="panel-title">自訂路線</h3></div>
            <div class="panel-body">
			<%@ include file="pages/page1.file"%>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>路線編號</th>
							<th>路線名稱</th>
							<th>路線分類</th>													
							<th>刪除</th>
							<th>詳細資料</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="rotVO" items="${list_rot}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td>${rotVO.rotno}</td>
								<td>${rotVO.rotname}</td>
								<jsp:useBean id="rotclsSvc" scope="page"
								 class="com.bikefunclub.rotcls.model.RotclsService" />
								<td><c:forEach var="rotclsVO" items="${rotclsSvc.all}">
								<c:if test="${rotVO.rotclsno==rotclsVO.rotclsno}">
	                       				    【<font color=orange>${rotclsVO.rotclsname}</font>】
                    			</c:if>
								</c:forEach></td>
								<td>
									<FORM METHOD="post" ACTION="<%=path%>/Rot.do">
										<input class="btn btn-success" type="submit" value="刪除"> 
										<input type="hidden" name="rotno" value="${rotVO.rotno}"> 
										<input type="hidden" name="requestURL" value="<%=servletpath%>">
										<!--送出本網頁的路徑給Controller-->
										<input type="hidden" name="whichPage" value="<%=whichPage%>">
										<!--送出當前是第幾頁給Controller-->
										<input type="hidden" name="action" value="delete_fromrotsmanage_bymemno">
									</FORM>
								</td>
								<td>
									<FORM METHOD="post" ACTION="<%=path%>/Rot.do">
										<input class="btn btn-success" type="submit" value="詳細資料"> 
										<input type="hidden" name="rotno" value="${rotVO.rotno}"> 
									    <input type="hidden" name="requestURL" value="<%=servletpath%>">
										<!--送出本網頁的路徑給Controller-->
										<input type="hidden" name="whichPage" value="<%=whichPage%>">
										<!--送出當前是第幾頁給Controller-->
										<input type="hidden" name="action" value="getRot_info">
									</FORM>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<%@ include file="pages/page3.file"%>
            </div>
        </div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">收藏路線</h3>
			</div>
			  <div class="panel-body">
			<%@ include file="pages/page1_memrot.file"%>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>路線編號</th>
							<th>路線名稱</th>
							<th>路線分類</th>													
							<th>取消收藏</th>
							<th>詳細資料</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="rotVO2" items="${list_memrot}" begin="<%=pageIndex1%>"
							end="<%=pageIndex1+rowsPerPage1-1%>">
							<tr>
								<td>${rotVO2.rotno}</td>
								<td>${rotVO2.rotname}</td>
								<jsp:useBean id="rotclsSvc2" scope="page"
								 class="com.bikefunclub.rotcls.model.RotclsService" />
								<td><c:forEach var="rotclsVO" items="${rotclsSvc2.all}">
								<c:if test="${rotVO2.rotclsno==rotclsVO.rotclsno}">
	                       				    【<font color=orange>${rotclsVO.rotclsname}</font>】
                    			</c:if>
								</c:forEach></td>
								<td>
									<FORM METHOD="post" ACTION="<%=path%>/Rot.do">
										<input class="btn btn-success" type="submit" value="取消收藏"> 
										<input type="hidden" name="rotno" value="${rotVO2.rotno}"> 
										<input type="hidden" name="memno" value="${memno}">
										<input type="hidden" name="requestURL" value="<%=servletpath%>">
										<!--送出本網頁的路徑給Controller-->
										<input type="hidden" name="whichPage" value="<%=whichPage1%>">
										<!--送出當前是第幾頁給Controller-->
										<input type="hidden" name="action" value="delete_fromrotmanage_bymemrot">
									</FORM>
								</td>
								<td>
									<FORM METHOD="post" ACTION="<%=path%>/Rot.do">
										<input class="btn btn-success" type="submit" value="詳細資料"> 
										<input type="hidden" name="rotno" value="${rotVO2.rotno}"> 
									    <input type="hidden" name="requestURL" value="<%=servletpath%>">
										<!--送出本網頁的路徑給Controller-->
										<input type="hidden" name="whichPage" value="<%=whichPage1%>">
										<!--送出當前是第幾頁給Controller-->
										<input type="hidden" name="action" value="getRot_info">
									</FORM>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<%@ include file="pages/page3_memrot.file"%>
            </div>
            </div>
		</div>
	</div>
</div>