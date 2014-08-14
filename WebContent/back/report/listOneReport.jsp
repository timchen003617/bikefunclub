<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.bikefunclub.report.model.*"%>
<%
	ReportVO repVO = (ReportVO) request.getAttribute("repVO");
%>
<div id="backmain" class="col-md-10">
	<table border='1'>
		<tr>
			<th>檢舉編號</th>
			<th>檢舉人姓名</th>
			<th>被檢舉人姓名</th>
			<th>檢舉分類</th>
			<th>編號直</th>
			<th>檢舉內容</th>
			<th>處理日期</th>
			<th>回覆日期</th>
			<th>處理進度</th>
			<th>處理結果</th>
		</tr>
		<tr align='center' valign='middle'>
			<td><%=repVO.getRepno()%></td>
			<td><%=repVO.getMemno()%></td>
			<td><%=repVO.getByrepno()%></td>
			<td><%=repVO.getRepcls()%></td>
			<td><%=repVO.getRepclsno()%></td>
			<td><%=repVO.getReptext()%></td>
			<td><%=repVO.getRepdate()%></td>
			<td><%=repVO.getReplydate()%></td>
			<td><%=repVO.getRepprogress()%></td>
			<td><%=repVO.getRepresult()%></td>
		</tr>
	</table>
</div>