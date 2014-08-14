<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<div class="navbar-wrapper">
	<div class="navbar">
		<div class="container">
			<ul id="navigation">
				<li><a href="<%=path%>/front/ann/page_listAllann.jsp">公告</a></li>
				<li><a href="#">會員專區</a>
					<ul>
						<li><a href="<%=path%>/front/mem/page_update_mem_input.jsp">個人資料管理</a></li>
					</ul></li>
				<li><a href="#">社群</a>
					<ul>
						<li><a href="<%=path%>/front/friendlist/friendlist.jsp">好友管理</a></li>
						<li><a href="#">相簿</a></li>
						
					</ul></li>
					<li><a href="#">網誌</a>
					<ul>
						<li><a href="#">瀏覽網誌</a></li>
						<li><a href="#">網誌管理</a></li>
					</ul></li>
					<li><a href="#">揪團</a>
					<ul>
						<li><a href="<%=path%>/front/gp/page_listAllgp.jsp">參加揪團</a></li>
						<li><a href="<%=path%>/front/gp/page_insert_gp.jsp">發起揪團</a></li>
						<li><a href="<%=path%>/front/gp/page_manage_gp.jsp">揪團管理</a></li>
					</ul></li>
				<li><a href="#">路線</a>
					<ul>
						<li><a href="<%=path%>/front/rot/page_listAllrots.jsp">瀏覽路線</a></li>
						<li><a href="<%=path%>/front/rot/page_insert_memrot_1.jsp">規劃路線</a></li>
						<li><a href="<%=path%>/front/rot/page_rots_manage.jsp">路線管理</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</div>