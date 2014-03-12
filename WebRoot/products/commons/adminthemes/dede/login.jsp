<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${staticserver }/js/common/jquery-1.3.2.js"></script>
<SCRIPT eop_type="widget_tool" src="${staticserver }/js/common/jquery-form-2.33.js" type="text/javascript"></SCRIPT>
<script type="text/javascript" src="${staticserver }/js/admin/Eop.SSO.js"></script>
<link href="${context}/css/login.css" rel="stylesheet" type="text/css" />

<title>登陆</title>
</head>
<body>
	<div id="head">
		<div class="top">
			<div class="logo"><a href="#"><img src="${context}/images/logo.gif"/></a></div>
			<div id="top_link">
				<ul>
					<li class="welcome">用户您好，您可以访问您的:</li>
					<li><a href="#">网站主页</a></li>
					<li><a href="#">会员中心</a></li>
					<li><a href="#">登录官方网站</a>或<a href="#">论坛</a></li>
					
				</ul>
			</div>
		</div>
		<div class="topnav">
			<div class="menuact">
					<a href="#">版本号：DEDECMS V55_UTF8</a>
			</div>
		</div>
	</div>
	<div class="login">
		<form>
			<table>
				<tr>
					<td>用户名：</td>
					<td><input type="text" id="username" name="username" /></td>
				</tr>
				<tr>
					<td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td>验证码：</td>
					<td><input type="input" id="valid_code" style="width:50px" name="valid_code" /> <img id="code_img" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="button" name="login_btn" id="login_btn" value="确定" /></td>
				</tr>
			</table>
		</form>
	</div>
	 <div id="log"></div>
	 
</body>
</html>