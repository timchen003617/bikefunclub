<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta charset="UTF-8">
<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<title>Draggable directions</title>
<style>
html,body,#map-container {
	height: 80%;
	width: 80%;
	margin: 0px;
	padding: 0px
}
</style>
<script type="text/javascript">
	//將座標處理傳送至google map 導航回傳資料
	function set_map(markers) {
		//座標處理
		var start = markers[0];
		var end = markers[markers.length - 1];
		var waypts = [];
		var wayptsArray = markers.slice(1, markers.length - 1);
		for (var i = 0; i < wayptsArray.length; i++) {
			waypts.push({
				location : wayptsArray[i],
				stopover : true
			});
		}
		//開車避開收費道路導航設定
		// 		var request = {
		// 			origin : start,
		// 			destination : end,
		// 			waypoints : waypts,
		// 			optimizeWaypoints : true,
		// 			avoidHighways:true,
		// 			avoidTolls:true,
		// 			travelMode : google.maps.TravelMode.DRIVING
		// 		};

		//步行路線導航設定
		var request = {
			origin : start,
			destination : end,
			waypoints : waypts,
			optimizeWaypoints : true,
			travelMode : google.maps.TravelMode.WALKING
		};
		//送出導航請求並傳回對應資料
		directionsService.route(request, function(response, status) {
			if (status == google.maps.DirectionsStatus.OK) {
				directionsDisplay.setDirections(response);
			}
		});

	}

	
	//宣告導航物件
	var rendererOptions = {
		draggable : true
	};
	var directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
	;
	var directionsService = new google.maps.DirectionsService();
	
	var map;
	//判斷按鈕狀態
	var startflag = 1;
	//座標陣列
	var markers = [];
	
	function init_map() {
		directionsDisplay = new google.maps.DirectionsRenderer();
		
		//初始地圖設定
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
		//設定地圖物件
		map = new google.maps.Map(document.getElementById("map-container"),
				mapOptions);
		directionsDisplay.setMap(map);

		startedit = document.getElementById("startedit");
		endedit = document.getElementById("endedit");
		clearedit = document.getElementById("clearedit");
		deleteedit = document.getElementById("deleteedit");
		home = document.getElementById("home");

		startedit.addEventListener('click', clickstartedit, false);
		endedit.addEventListener('click', clickendedit, false);
		clearedit.addEventListener('click', clickclearedit, false);
		deleteedit.addEventListener('click', clickdeleteedit, false);
		home.addEventListener('click', clickhome, false);

	}

	google.maps.event.addDomListener(window, 'load', init_map);

	function clickstartedit() {
		if (startflag == 1) {
			startedit.disabled = 'disabled';
			endedit.disabled = '';
			clearedit.disabled = '';
			deleteedit.disabled = '';
			home.disabled = '';
			startflag = 0;
			//google map 點擊事件
			google.maps.event.addListener(map, 'click', function(e) {
				if (markers) {
					//將導航地圖疊加層放到map
					directionsDisplay.setMap(map);
				}
				//在map點擊的座標存到陣列
				markers.push(e.latLng);
				if (markers.length >= 1) {
					set_map(markers);
				}
			});
		}
	}

	function clickendedit() {
		if (startflag == 0) {
			startedit.disabled = '';
			endedit.disabled = 'disabled';
			clearedit.disabled = 'disabled';
			deleteedit.disabled = 'disabled';
			home.disabled = 'disabled';
			startflag = 1;
			//關閉聆聽者事件
 			google.maps.event.clearListeners(map, 'click');
		}
	}

	function clickhome() {
		if (markers.length > 0) {
			map.setCenter(markers[0]);
		}
	}

	function clickdeleteedit() {
		markers.pop();
		if (markers.length >= 1) {
			set_map(markers);
		} else {
			//清空路線疊加層
			directionsDisplay.setMap(null);
		}

	}
	function clickclearedit() {
		//清除所有點
		while (markers.length > 0) {
			markers.pop();
		}
		//地圖設定初始化
		map.setZoom(15);
		map.setCenter(new google.maps.LatLng(24.967881, 121.1917015));
		//清空路線疊加層
		directionsDisplay.setMap(null);
	}
</script>
</head>
<body>

	<button type="button" id="startedit">開始編輯</button>
	<button type="button" id="endedit" disabled="disabled">結束編輯</button>
	<button type="button" id="deleteedit" disabled="disabled">刪除最後節點</button>
	<button type="button" id="clearedit" disabled="disabled">刪除全部節點</button>
	<button type="button" id="home" disabled="disabled">返回起點</button>



	<div id="map-container"></div>

</body>
</html>