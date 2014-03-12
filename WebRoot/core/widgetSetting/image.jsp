<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<p><br/></p>
<p>
	<label>链接地址：</label>
	<input type="text" id="url" name="url" eop_type="widget_params"/>
</p>
<p>
	<label>图像大小：</label>
	<input type="text" id="width" name="width" style="width:40px;" eop_type="widget_params"/>
	<span style="margin:auto 5px">宽</span>
	<input type="text" id="height" name="height" style="width:40px;" eop_type="widget_params"/>
	<span style="margin:auto 5px">高</span>
</p>
<%

String act  = request.getParameter("act");
String id = request.getParameter("id");

out.print("act is " + act +"<br/> + id is " + id );

%>