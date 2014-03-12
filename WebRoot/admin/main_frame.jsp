<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

String path= request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
   "http://www.w3.org/TR/html4/strict.dtd">

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>eop</title>
<style type="text/css">
body{
	margin:0px;
	font-family:Arial,"宋体",Verdana,sans-serif;
	font-size:12px;
}
html,body {padding: 0;margin: 0; overflow-x:hidden; overflow-y:auto;}
dl,dd{margin:0px}
ul{
	list-style-type:none;
	margin:0px;
	padding:0px;
}
</style>

<script type="text/javascript">
var app_path="<%=path%>";
var mainpage=false;
</script>
<script type="text/javascript" src="${staticserver }/js/common/common.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/eop-min.js"></script>
<script type="text/javascript" src="${staticserver }/js/common/SelectTree.js"></script>
<script type="text/javascript" src="<%=path %>/editor/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=path %>/editor/ckeditor/adapters/jquery.js"></script>
<link href="${context}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/input.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/grid.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/validate.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/dialog.css" rel="stylesheet" type="text/css" />
</head>
<body>
${content }
</body>
</html>