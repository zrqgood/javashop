<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../../adminthemes/default/css/grid.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../../statics/js/common/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../../../statics/js/admin/Grid.js"></script>
<title>Grid异步分页js测试</title>
</head>
<body>

<%
    String pageNo  = request.getParameter("page");
	if(pageNo==null) pageNo="1";
	
	 String name  = request.getParameter("name");
	 name=name==null?"":name;
	 
%>
<div class="grid">
<div>toolbar</div>
<!-- 异步分页要抓取的区域 -->
<div class="gridbody" gridid="1293600828708" >
<table >
  <tbody>
  	<tr><td>这是第<%=pageNo %>页<%=name %></td></tr>
  </tbody>
</table><script>$(function(){$(".gridbody[gridid='1293600828708']>.page").gridAjaxPager('main.jsp?name=pp');});</script>
<div class="page"><span class="info">共11条记录</span>
<span class="info">1/6</span>
<ul><li><a class="selected">1</a></li>
<li><a pageno="2" href="javascript:;">2</a></li>
<li><a pageno="3" href="javascript:;">3</a></li>
<li><a pageno="4" href="javascript:;">4</a></li>
<li><a pageno="5" href="javascript:;">5</a></li>
<li><a pageno="6" href="javascript:;">6</a></li>
<li><a pageno="2" href="javascript:;">&gt;&gt;</a></li>
<li><a pageno="6" href="javascript:;">&gt;|</a></li>
</ul></div>
</div>
<!-- 异步分页要抓取的区域 结束-->
这里是乱七八糟的其它内容，请不要显示重复
</div>


</body>
</html>