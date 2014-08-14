$(function() {
	var showTab;

	showTab = ($("#tabNum").val() == null)?"0":$("#tabNum").val();// 預設顯示第1個 Tab

	var $defaultLi = $(".tabs li").eq(showTab).addClass("active");
	$('.tab_content').eq($defaultLi.index()).siblings().hide();

	// 當 li 頁籤被點擊時...
	// 若要改成滑鼠移到 li 頁籤就切換時, 把 click 改成 mouseover
	$("ul.tabs li").click(
			function() {
				// 找出 li 中的超連結 href(#id)
				var $this = $(this), _index = $this.index();
				// 把目前點擊到的 li 頁籤加上 .active
				// 並把兄弟元素中有 .active 的都移除 class
				$this.addClass("active").siblings(".active").removeClass(
						"active");
				// 淡入相對應的內容並隱藏兄弟元素
				$(".tab_content").eq(_index).stop(false, true).fadeIn()
						.siblings().hide();

				return false;
			}).find('a').focus(function() {
		this.blur();
	});
});
