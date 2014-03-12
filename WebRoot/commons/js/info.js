
function loadPoss(url) {
	var obj = document.getElementsByName("ids");
	var len = obj.length;
	re = false;
	for (var i = 0; i < len; i++) {
		if (obj[i].checked == true) {
			re = true;
		}
	}
	if (re) {
		document.getElementById("InfoDeleteForm").action = url;
		document.getElementById("InfoDeleteForm").submit();
	} else {
		alert("\u8bf7\u9009\u62e9\u5bf9\u8c61\uff01");
	}
}

