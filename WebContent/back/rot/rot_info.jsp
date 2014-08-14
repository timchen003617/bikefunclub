<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.rot.model.*"%>
<%@ page import="com.bikefunclub.memrot.model.*"%>
<%@ page import="com.bikefunclub.rotcls.model.*"%>

<%
	String path = request.getContextPath();
	RotVO rotVO = (RotVO) request.getAttribute("rotVO");
	//String rotno = (String)request.getAttribute("rotno");
	pageContext.setAttribute("rotno",rotVO.getRotno());
	MemrotService memrotSvc = new MemrotService();
	Integer memno = 1;
// 	MemrotVO memrotVO = memrotSvc.findByPrimaryKey(memno,
// 			rotVO.getRotno());
%>

<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			type : "GET",
			url : "/Bikefunclub/Rot.do",
			data : {
				"action" : "back_getSelected_rotinfo",
				"rotno" :  '${rotno}'
			},
			dataType : "json",
			success : function(dataobj) {
				setmap(dataobj, 'map-container');
			},
			error : function() {
				alert("error");
			}
		});
		
	});

	var directionsDisplay;
// 	var flightPath;
// 	var flightPlanCoordinates;
	var directionsService = new google.maps.DirectionsService();
	var map;
	var readtype = 1;//0:polyline,1:directionsService
	function setmap(obj, targetMap1) {
		var start = obj.rotstart.lat + ',' + obj.rotstart.lng;
		var end = obj.rotend.lat + ',' + obj.rotend.lng;

		var waypts = [];
		var wayptsArray = obj.rotloc;
		if (wayptsArray != undefined) {// && (wayptsArray.length < 9)
			for (var i = 0; i < wayptsArray.length; i++) {
				waypts.push({
					location : wayptsArray[i].lat + ',' + wayptsArray[i].lng,
					stopover : true
				});
			}
			var request = {
				origin : start,
				destination : end,
				waypoints : waypts,
				optimizeWaypoints : true,
				avoidHighways : true,
				avoidTolls : true,
				travelMode : google.maps.TravelMode.DRIVING
			};
			directionsService.route(request, function(response, status) {
				if (status == google.maps.DirectionsStatus.OK) {
					directionsDisplay.setDirections(response);
					computeTotalDistance(directionsDisplay.getDirections());
				}
			});
		} /*else {
						readtype = 0;
						var lng=parseFloat(obj.rotstart.lng);
						var lat=parseFloat(obj.rotstart.lat);
						waypts.push(new google.maps.LatLng(lng,lat));
						alert(lng);
						alert(lat);
						for (var i = 0; i < wayptsArray.length; i++) {
							var lng=parseFloat(wayptsArray[i].lng);
							var lat=parseFloat(wayptsArray[i].lat);
							waypts.push(new google.maps.LatLng(lng,lat));
						}
						var lng=parseFloat(obj.rotstart.lng);
						var lat=parseFloat(obj.rotstart.lat);			
						waypts.push(new google.maps.LatLng(lng,lat));
					}
					    flightPlanCoordinates =waypts;*/
	}

	function init_map() {
		directionsDisplay = new google.maps.DirectionsRenderer();
		var myLocation = new google.maps.LatLng(24.967881, 121.1917015);

		var mapOptions = {
			center : myLocation,
			zoom : 15,
			panControl : true,
			zoomControl : true,
			mapTypeControl : true,
			scaleControl : true,
			streetViewControl : true,
			overviewMapControl : true,
			rotateControl : true,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};

		map = new google.maps.Map(document.getElementById("map-container"),
				mapOptions);
		if (readtype == 1) {
			directionsDisplay.setMap(map);
			directionsDisplay
					.setPanel(document.getElementById('selected_rots'));

		}
// 		 else if (readtype == 0) {		    
// 						              flightPath = new google.maps.Polyline({
// 						              path: flightPlanCoordinates,
// 						              geodesic: true,
// 						              strokeColor: '#FF0000',
// 						              strokeOpacity: 1.0,
// 						              strokeWeight: 2
// 						            });
// 									flightPath.setMap(map);
// 								} 

	}

	function computeTotalDistance(result) {
		var total = 0;
		var myroute = result.routes[0];
		for (var i = 0; i < myroute.legs.length; i++) {
			total += myroute.legs[i].distance.value;
		}
		total = total / 1000.0;
		document.getElementById('total').innerHTML = total + ' km';
	}
	
	google.maps.event.addDomListener(window, 'load', init_map);
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
		<div class="col-md-3">
			<div class="panel panel-warning">
				<div class="panel-heading">
					<h3 class="panel-title">路線資訊</h3>
				</div>

				<p>
					Total Distance: <span id="total"></span>
				</p>
				<div class="panel-body" id="selected_rots"></div>
			</div>
		</div>
		<div id="map-container" class="col-md-9"></div>
	</div>
	<div class="col-md-13">
		<div class="panel panel-warning">
			<div class="panel-heading">
				<h3 class="panel-title">路線描述</h3>
			</div>
			<div class="panel-body" id="rot_desc">${rotVO.rotdesc}</div>
		</div>
		<div class="panel panel-warning">
			<div class="panel-heading">
				<h3 class="panel-title">經過地區</h3>
			</div>
			<div class="panel-body" id="rot_tag">${rotVO.rottag}</div>
		</div>
	</div>

</div>