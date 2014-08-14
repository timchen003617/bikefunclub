function previewImage(file, imgId) {
	if (file.files && file.files[0]) {
		var img = document.getElementById(imgId);
		// 使用JavaScript的FileReader对象来读取本地数据，并且将数据结果赋值给image的src
		var reader = new FileReader();
		reader.onload = function(evt) {
			img.src = evt.target.result;
			$("#" + imgId).show();
		};
		reader.readAsDataURL(file.files[0]);
	} else {// 如果是IE浏览器，采用滤镜效果，进行显示，但特别注意的是该滤镜效果使用的对象是div对象，并非img对象，因此我们需要将原有的img对象remove同时生成新的div对象，并且赋值相应的class和id
		// 采用滤镜效果生成图片预览
		file.select();
		div.focus();
		var src = document.selection.createRange().text;
		$("#" + imgId).attr("src", src);
		$("#" + imgId).show();
	}
}
