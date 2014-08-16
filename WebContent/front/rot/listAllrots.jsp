<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.rot.model.*"%>
<%@ page import="com.bikefunclub.rotcls.model.*"%>
<%
	String path = request.getContextPath();
%>
<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#select_rotclsno').change(function() {
			$.ajax({
				type : "GET",
				url : "/bikefunclub/Rot.do",
				data : {
					"action" : "getSelected_rots",
					"rotclsno" : $(this).val()
				},
				dataType : "json",
				success : function(data) {
					selectCreate(data, 'selected_rots');
				},
				error : function() {
					alert("error");
				}
			});
		});
	});

	function selectCreate(oJson, targetId) {
		$('#' + targetId).empty();
		var i = 0;
		$('#' + targetId).append("<ul class='nav nav-tabs nav-stacked'>");

		$.each(oJson, function() {
			if ('PUBLIC' == oJson[i].rotauth) {
				$('#' + targetId).append(
						"<li class='col-md-12'><a href='#"+oJson[i].rotno+"'>"
								+ oJson[i].rotname + "</a></li>");
			}
			i++;
		});

		$('#' + targetId).append("</ul>");

		$('#' + targetId).find("a").click(function() {
			strAry = $(this).attr("href").split("#");
			$.ajax({
				type : "GET",
				url : "/bikefunclub/Rot.do",
				data : {
					"action" : "getSelected_rotinfo",
					"rotno" : strAry[1]
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

	}
	var directionsDisplay;
	var directionsService = new google.maps.DirectionsService();
	var map;

	function setmap(obj, targetMap) {
		var start = obj.rotstart.lat + ',' + obj.rotstart.lng;
		var end = obj.rotend.lat + ',' + obj.rotend.lng;

		var waypts = [];
		var wayptsArray = obj.rotloc;
		if (wayptsArray != undefined) {
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
				}
			});
		}
	}
	function HomeControl(controlDiv, map) {

		controlDiv.style.padding = '5px';
		var controlUI = document.createElement('div');
		controlUI.style.backgroundColor = 'white';
		controlUI.style.borderStyle = 'solid';
		controlUI.style.borderColor = 'green';
		controlUI.style.borderWidth = '2px';
		controlUI.style.cursor = 'pointer';
		controlUI.style.textAlign = 'center';
		controlUI.title = '查看詳細路線資料';
		controlDiv.appendChild(controlUI);

		// Set CSS for the control interior
		var controlText = document.createElement('div');
		controlText.style.fontFamily = 'cursive';
		controlText.style.color = 'green';
		controlText.style.fontSize = '18px';
		controlText.style.paddingLeft = '20px';
		controlText.style.paddingRight = '20px';
		controlText.innerHTML = '<b>詳細路線資料</b>';
		controlUI.appendChild(controlText);

		// Setup the click event listeners: simply set the map to
		google.maps.event.addDomListener(controlUI, 'click', function() {
			$("#formhidden").find("#rotno").attr("value", strAry[1]);
			//alert($("#formhidden").find("#rotno").attr("value"));
			$("#formhidden").submit();
		});

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

			directionsDisplay.setMap(map);

		var homeControlDiv = document.createElement('div');
		var homeControl = new HomeControl(homeControlDiv, map);

		homeControlDiv.index = 1;
		map.controls[google.maps.ControlPosition.TOP_RIGHT]
				.push(homeControlDiv);

	}

	google.maps.event.addDomListener(window, 'load', init_map);
</script>
<div class="container body-content">
	<div class="row">
		<div class="col-md-3">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<jsp:useBean id="rotclsSvc" scope="page"
							class="com.bikefunclub.rotcls.model.RotclsService" />
						選擇路線分類: <select size="1" name="rotclsno" id="select_rotclsno">
							<option value="0">請選擇</option>
								<c:forEach var="rotclsVO" items="${rotclsSvc.all}">
									<option value="${rotclsVO.rotclsno}"
										<c:if test="${not empty rotclsno}">
								 ${(rotclsno==rotclsVO.rotclsno)?'selected':'' }		
								</c:if>>${rotclsVO.rotclsname}</option>
								</c:forEach>
						</select>
					</h3>
				</div>
				<div class="panel-body" id="selected_rots"></div>
			</div>
		</div>
		<div id="map-container" class="col-md-8"></div>
		<FORM id="formhidden" METHOD="post" ACTION="<%=path%>/Rot.do">
			<input type="hidden" id="rotno" name="rotno"> <input
				type="hidden" name="action" value="getRot_info">
		</FORM>
	</div>
</div>