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


var rendererOptions = {
		  draggable: true
		};
		var directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);;
		var directionsService = new google.maps.DirectionsService();
		var map;
		

	function set_map(markers,targetMap) {
		var start = markers[0];
		//alert(start);
		var end = markers[markers.length-1];
		//alert(end);
		var waypts = [];
		var wayptsArray = markers.slice(1,markers.length-1);
		for (var i = 0; i < wayptsArray.length; i++) {
			waypts.push({
				location : wayptsArray[i],
				stopover : true
			});
		}

		var request = {
			origin : start,
			destination : end,
			waypoints : waypts,
			optimizeWaypoints : true,
			avoidHighways:true,
			avoidTolls:true,
			travelMode : google.maps.TravelMode.DRIVING
		};
		directionsService.route(request, function(response, status) {
		    if (status == google.maps.DirectionsStatus.OK) {
			        directionsDisplay.setDirections(response);
			}
		});

	}
	
	function HomeControl(controlDiv, map) {

		controlDiv.style.padding = '5px';
		var controlUI = document.createElement('div');
		controlUI.style.backgroundColor = 'white';
		controlUI.style.borderStyle = 'solid';
		controlUI.style.borderColor = '#FFAC55';
		controlUI.style.borderWidth = '2px';
		controlUI.style.cursor = 'pointer';
		controlUI.style.textAlign = 'center';
		controlUI.title = '到下一步編輯路線資料';
		controlDiv.appendChild(controlUI);

		// Set CSS for the control interior
		var controlText = document.createElement('div');
		controlText.style.fontFamily = 'cursive';
		controlText.style.color = '#FFAC55';
		controlText.style.fontSize = '18px';
		controlText.style.paddingLeft = '20px';
		controlText.style.paddingRight = '20px';
		controlText.innerHTML = '<b>下一步</b>';
		controlUI.appendChild(controlText);

		// Setup the click event listeners: simply set the map to
		google.maps.event.addDomListener(controlUI, 'click', function() {
			if(markers.length>1){
		    for(var i=0;i<markers.length;i++){
		    	markers[i]=latLngToString(markers[i]);
		    }
		    if(markers.length>2){
				$("#formhidden").find("#rotloc").attr("value","["+markers.slice(1,markers.length-1)+"]");
				//alert(markers.slice(1,markers.length-1));
		    }else{
		    	$("#formhidden").find("#rotloc").attr("value","");
		    }// new google.maps.LatLng(座標);
			
			$("#formhidden").find("#rotstart").attr("value",markers[0]);
			//alert(markers[0]);
			$("#formhidden").find("#rotend").attr("value",markers[markers.length-1]);			
			//alert(markers[markers.length-1]);						
			$("#formhidden").submit();
			}
		});

	}
	function latLngToString(latlng){
		var str = latlng.toString();
		str = str.replace("(", "");
		str = str.replace(")", "");
		str = str.replace(" ", "");
		arr = str.split(",");
		arr[0]="{\"lat\":"+arr[0];
		//alert(arr[0]);
		arr[1]="\"lng\":"+arr[1]+"}";
		//alert(arr[1]);
		//alert(arr);
		return arr;
	}
	var startflag=1;
	//var arr = new Array();
	var markers =[];
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
		directionsDisplay.setPanel(document.getElementById('selected_rots'));
		var homeControlDiv = document.createElement('div');
		var homeControl = new HomeControl(homeControlDiv, map);

		homeControlDiv.index = 1;
		map.controls[google.maps.ControlPosition.TOP_RIGHT]
				.push(homeControlDiv);
		
		google.maps.event.addListener(directionsDisplay, 'directions_changed', function() {
			    computeTotalDistance(directionsDisplay.getDirections());
			  });
		set_map(markers,'map-container');
		startedit=document.getElementById("startedit");
		endedit=document.getElementById("endedit");
/* 		clearedit=document.getElementById("clearedit"); */
		home=document.getElementById("home");
		
		startedit.addEventListener('click',clickstartedit,false);
		endedit.addEventListener('click',clickendedit,false);
/* 		clearedit.addEventListener('click',clickclearedit,false); */
		home.addEventListener('click',clickhome,false);	

	}

	google.maps.event.addDomListener(window, 'load', init_map);
	
	function computeTotalDistance(result) {
		  var total = 0;
		  var myroute = result.routes[0];
		  for (var i = 0; i < myroute.legs.length; i++) {
		    total += myroute.legs[i].distance.value;
		  }
		  total = total / 1000.0;
		  document.getElementById('total').innerHTML = total + ' km';
		}
	
	
	
	function clickstartedit(){
		if(startflag==1){
			startedit.disabled='disabled';
			endedit.disabled='';
/* 			clearedit.disabled=''; */
			home.disabled='';
			startflag=0;
	        google.maps.event.addListener(map, 'click', function(e) {
	    	    /* placeMarker(e.latLng, map); */
	        	 //map.setCenter(e.latLng);
	        	 markers.push(e.latLng);
	        	 if(markers.length>=1){
	        		 set_map(markers,'map-container');
	        	 }
	    	  });
		}
	}

	function clickendedit() {
		if (startflag == 0) {
			startedit.disabled = '';
			endedit.disabled = 'disabled';
/* 			clearedit.disabled = 'disabled'; */
			home.disabled = 'disabled';
			startflag = 1;
			google.maps.event.clearListeners(map, 'click');
		}
	}

	function clickhome() {
		if (markers.length > 0) {
			map.setCenter(markers[0]);
		}
	}
/* 	// Sets the map on all markers in the array.
	function setAllMap(map) {
		for (var i = 0; i < markers.length; i++) {
			markers[i].setMap(map);
		}
	}

	// Removes the markers from the map, but keeps them in the array.
	function clearMarkers() {
		setAllMap(null);
	}
	function clickclearedit() {
 		clearMarkers();
		markers = [];
	} */
</script>
<div class="container body-content">
	<h1 class="page-header">新增推薦路線</h1>

	<div class="row">
		<div class="col-md-3">
			<div class="panel panel-warning">
				<div class="panel-heading">
					<h3 class="panel-title">新增推薦路線</h3>
				</div>
  		 	    <p>Total Distance: <span id="total"></span></p>
				<div class="panel-body" id="selected_rots"></div>
				
			</div>
		</div>
		<div class="btn-group">
			<button type="button" id="startedit" class="btn btn-warning">開始編輯</button>
			<button type="button" id="endedit" disabled="disabled" class="btn btn-warning">結束編輯</button>
<!-- 			<button type="button" id="clearedit" disabled="disabled" class="btn btn-default">刪除全部節點</button>
 -->			<button type="button" id="home" disabled="disabled" class="btn btn-warning">返回起點</button>
		</div>


		<div id="map-container" class="col-md-8"></div>
		<FORM id="formhidden" METHOD="post" ACTION="<%=path%>/back/rot/page_insert_sgrot_2.jsp">
			<input type="hidden" id="rotloc" name="rotloc"> <input
				type="hidden" id="rotstart" name="rotstart"> <input
				type="hidden" id="rotend" name="rotend">
		</FORM>
	</div>
</div>
