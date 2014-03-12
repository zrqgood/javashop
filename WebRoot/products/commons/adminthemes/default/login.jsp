<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${title }</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="image/x-icon" href="${ico}" rel="icon" />
<link type="image/x-icon" href="${ico}" rel="bookmark" />
<script type="text/javascript" src="${staticserver }/js/common/jquery-1.3.2.js"></script>
<SCRIPT  src="${staticserver }/js/common/jquery-form-2.33.js" type="text/javascript"></SCRIPT>
<script type="text/javascript" src="${staticserver }/js/admin/Eop.SSO.js"></script>
<link href="${context}/css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="main">
		<div class="logo"></div>
		<div class="content">
			<form>
				<table>
					<tr>
						<td width="50" align="right">用户名：</td>
						<td><input type="text" id="username" name="username" class="inputstyle" value="${username}"/></td>
					</tr>
					<tr>
						<td align="right">密码：</td>
						<td><input type="password" name="password" class="inputstyle"  /></td>
					</tr>
					<tr>
						<td align="right">验证码：</td>
						<td><input type="input" id="valid_code" name="valid_code" class="inputstyle" /> <img id="code_img" class="code_img" /></td>
					</tr>
					<tr>
						<td colspan="2">记住用户名：<input type="checkbox" value="1" checked="" name="remember_login_name"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="button" name="login_btn" id="login_btn" value="登录后台" class="loginbtnfocus" /></td>
					</tr>
				</table>
			</form>
		</div>
	 </div>
	 <div id="log"></div>
<script>
$(function(){
	var bkloginpicfile = '${bkloginpicfile}';
	if(bkloginpicfile!=''){
		$(".logo").css("background","transparent url(${bkloginpicfile}) no-repeat scroll 0 0");
	}
});
</script>
</body>
</html>
