
$(document).ready(function() {		
	    $detailWin = $("#previewrotmap").dialog({
	        autoOpen: false,
	        width: 700,
	        height: 600,
	        show: "fade",
	        close: "fade",
	        open: setmap
	    });

	    $("#rotmap").on('click', function() {
	        $detailWin.dialog("open");
	    });
	    
		var directionsDisplay;
		var directionsService = new google.maps.DirectionsService();
		var map;

		function setmap() {
			$.ajax({
				type : "GET",
				url : "/bikefunclub/Rot.do",
				data : {
					"action" : "getSelected_rotinfo",
					"rotno" : $("#rotno").val()
				},
				dataType : "json",
				success : function(dataobj) {
					showmap(dataobj, 'previewrotmap');
				},
				error : function() {
					alert("error");
				}
			});
		}	
		function showmap(obj,map){
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
					}
				});
			} else {
				var request = {
						origin : start,
						destination : end,
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
						} 
			
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
			map = new google.maps.Map(document.getElementById("previewrotmap"),
					mapOptions);

				directionsDisplay.setMap(map);
		}				
	});

