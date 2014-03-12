<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${goods.name }7</title>
</head>
<style>
body {
	margin: 0;
}
</style>
<body>

<div id="show-pic">
<object>

	<embed src="album/pic-view.swf" quality="high" id="picview"
		flashvars="xml=${albumXml }"
		pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="100%"
		height="100%"></embed>
						
</div> 

<script> 
	function windowClose() {
		if (window.confirm("您是否关闭当前窗口")) {
			window.close();
		}

	} 
</script>
</body>
</html>
