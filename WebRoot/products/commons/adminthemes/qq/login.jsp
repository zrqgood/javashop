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
  <center>
<div class="wd">
	<img src="${context }/images/logo.gif" class="qqmaillogo" alt="QQ邮箱">
	<div class="top">
				
		<a href="#" target="_blank">域名邮箱</a>
		<a href="#" target="_blank">反馈意见</a>
		<a href="#" target="_blank">帮助中心</a>
		<a href="#" style="border: medium none ;" target="_blank">腾讯客服</a>
	</div>
</div>
 
<div id="returnMsg">
	</div>
<div class="wd main">
	<form  >
 
			<div class="loginplan_bg">
	<div class="loginplan">
		<div class="loginname">登录您的后台管理中心</div>
		<div style="margin: 5px 0pt; padding: 4px 0pt 0pt 2px;">
			<div id="msgContainer"></div> 
		</div>

				<div style="height: 25px;">
			<label for="uin" class="column">用户名：</label><input id="username" name="username" value=""  class="text" type="text"> 
		</div>
		    <div id="chen2" >推荐使用邮箱帐号，如：chen@qq.com</div>
			<div class="pass">
			   <label for="pp" class="column">密　码：</label><input name="password" value="" class="text"  type="password">
			</div>
			 <div class="vcode">
			 <label for="uin" class="column">验证码：</label><input id="valid_code"  style="width:80px;" class="text" type="text" name="valid_code" /><img id="code_img" />
		     </div>		 
 
					<div class="btn_div"><input class="btn" value="登 录" id="login_btn" name="login_btn" type="button"></div>
					<div class="login_msg">
						<img src="${context }/images/lock.jpg" alt=" https 安全方式登录" width="12" height="14"  align="absmiddle"/>正在使用https方式登录
					</div>
				<div class="login_msg_a"> 
				<span><a href="#" target="_blank" tabindex="6">忘记密码？</a></span>
				<em><a href="#" tabindex="7">立即注册</a></em>
				<em> <a href="#">普通登录</a> </em>
		        </div>
	</div>
	</div>
   </form>
	<div class="adplan">
				<dl class="panintro" id="LeftContainer">
				<dt><img src="${context }/images/tg-smile.gif" alt="QQ邮箱，常联系！" width="146" height="168" class="adimg"  border="0"/></dt>
			<dd style="margin-top: 30px; padding-top: 10px;">
				&nbsp;<a href="#" target="_blank">QQ邮箱，常联系！</a>
				<div style="margin: 10px 0pt 0pt 5px; font-size: 13px; line-height: 26px;">1982年，第一张电脑笑脸诞生<br>
				  今天，人们已经习惯用它来表达心情<br>
现在，您也可以在邮件里用:-)来say hi！<br>

<a href="#" target="_blank" style="font-family: normal; font-weight: normal; font-size: 12px; ">了解更多表情符号</a>				</div>
		  </dd>
		  		</dl>
		<ul style="line-height: 170%; color: rgb(134, 134, 134); margin-left: 19px; padding-left: 0pt; padding-top: 12px ! important; clear: left;">
			<li>您可以用您的QQ号和密码直接登录QQ邮箱。</li>
			<li id="chen1">您还可以 <a href="#">注册</a> 一个邮箱帐号（例如：chen@qq.com），并以此登录。</li>
			<li>
				手机登录 m.mail.qq.com 随时随地收发邮件。
			</li>
		</ul>
		
	</div>
</div>
<div class="wd" style="border: 1px solid rgb(255, 255, 255); margin-top: -5px;">
	<div class="navPageBottom">
		<a href="#" target="_blank">关于腾讯</a>
		<a href="#" target="_blank">About Tencent</a>
		<a href="#" target="_blank">服务条款</a>		
		<a href="#" target="_blank" class="end">客服中心</a>
	</div>
	<div class="copyright cLight">
		&#169; 1998 - 2010 Tencent Inc. All Rights Reserved&nbsp;&nbsp; 
	</div>
</div>
 
</center>
</body>
</html>