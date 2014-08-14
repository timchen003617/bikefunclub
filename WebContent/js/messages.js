$(document).ready(function() {
	$(".text-right li:last").click(
	function() {
		$("#messagebox-inner").slideToggle(200);
		$("#messagebox-inner").css({"display":"block"});
	}
	);
});
