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
<div class="main">
		<div class="logo"><span>http://www.enation.cn</span></div>
		<div class="content">
			<form>
				<table>
					<tr>
						<td colspan="2"><div class="images"></div></td>
					</tr>
					<tr>
						<td>用户名：</td>
						<td><input type="text" id="username" name="username" /></td>
					</tr>
					<tr>
						<td>密&nbsp;&nbsp;码：</td>
						<td><input type="password" name="password" /></td>
					</tr>
					<tr>
						<td>验证码：</td>
						<td><input type="input" id="valid_code" style="width:50px" name="valid_code" /> <img id="code_img" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="button" name="login_btn" id="login_btn" value="确定" /></td>
					</tr>
				</table>
			</form>
		</div>
	 </div>
	 <div id="log"></div>
</body>
</html>