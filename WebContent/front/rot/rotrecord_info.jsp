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
	RotVO rotVO = (RotVO) request.getAttribute("rotVO");
	//String rotno = (String)request.getAttribute("rotno");
	pageContext.setAttribute("rotno", rotVO.getRotno());
	MemrotService memrotSvc = new MemrotService();
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	Integer memno = memVO.getMemno();
	pageContext.setAttribute("memno", memno);
	MemrotVO memrotVO = memrotSvc.findByPrimaryKey(memno,
			rotVO.getRotno());
%>

<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript">
	$(document).ready(function() {

	 	var flightPath;
	 	var flightPlanCoordinates;
		var myLocation = new google.maps.LatLng(24.967881, 121.1917015);

		var mapOptions = {
			center : myLocation,
			zoom : 14,
			panControl : true,
			zoomControl : true,
			mapTypeControl : true,
			scaleControl : true,
			streetViewControl : true,
			overviewMapControl : true,
			rotateControl : true,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};

		  var map = new google.maps.Map(document.getElementById('map-container'),
			      mapOptions);
		$.ajax({
			type : "GET",
			url : "/bikefunclub/Rot.do",
			data : {
				"action" : "getSelected_rotinfo",
				"rotno" :  '${rotno}'
			},
			dataType : "json",
			success : function(obj) {
				var start = new google.maps.LatLng(obj.rotstart.lat,obj.rotstart.lng);
				var end = new google.maps.LatLng(obj.rotend.lat,obj.rotend.lng);

				var waypts = [];
				var wayptsArray = obj.rotloc;


				  flightPlanCoordinates = [];
				  flightPlanCoordinates.push(start);
					for (var i = 0; i < wayptsArray.length; i++) {
						var str=new google.maps.LatLng(wayptsArray[i].lat,wayptsArray[i].lng);
						 flightPlanCoordinates.push(str);
					}
				  flightPlanCoordinates.push(end);
				  flightPath = new google.maps.Polyline({
					    path: flightPlanCoordinates,
					    geodesic: true,
					    strokeColor: '#FF0000',
					    strokeOpacity: 0.7,
					    strokeWeight: 5
					  });
				      map.panTo(start);
					  flightPath.setMap(map);
			},
			error : function() {
				alert("error");
			}
		});
		
	});

</script>
<div class="container body-content">
	<jsp:useBean id="rotclsSvc" scope="page"
		class="com.bikefunclub.rotcls.model.RotclsService" />
	<h1>
		路線-${rotVO.rotname}
		<c:forEach var="rotclsVO" items="${rotclsSvc.all}">
			<c:if test="${rotVO.rotclsno==rotclsVO.rotclsno}">	
	【<font color=orange>${rotclsVO.rotclsname}</font>】
	                    </c:if>
		</c:forEach>
	</h1>

	<div class="row">
		<div id="map-container" class="col-md-13"></div>
	</div>

</div>