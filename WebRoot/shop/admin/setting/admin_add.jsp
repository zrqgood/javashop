<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="fck" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>管理中心 - 添加管理员 </title>
<meta name="robots" content="noindex, nofollow" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="../style/general.css" rel="stylesheet" type="text/css" />
<link href="../style/main.css" rel="stylesheet" type="text/css" />
<link href="../style/validator.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../../js/jquery.js"></script>
<script language="JavaScript" src="../../js/formValidator_min.js"></script>
<script language="JavaScript" src="../../js/formValidatorRegex.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.formValidator.initConfig({formid:"theForm",onerror:function(msg){alert(msg)}});
	$("#uname").formValidator({onshow:"请输入用户名",onfocus:"用户名不能为空",oncorrect:"用户名合法"}).inputValidator({min:1,onerror:"用户名不能为空,请确认"});
$("#name").formValidator({onshow:"请输入姓名",onfocus:"姓名不能为空",oncorrect:"姓名合法"}).inputValidator({min:1,onerror:"姓名不能为空,请确认"});
$("#password").formValidator({onshow:"请输入密码",onfocus:"密码不能为空",oncorrect:"密码合法"}).inputValidator({min:1,onerror:"密码不能为空,请确认"});
	$("#repwd").formValidator({onshow:"请输入重复密码",onfocus:"两次密码必须一致哦",oncorrect:"密码一致"}).inputValidator({min:1,onerror:"重复密码不能为空,请确认"}).compareValidator({desid:"password",operateor:"=",onerror:"2次密码不一致,请确认"});
	});
	</script>
</head>
<body>
<h1>
<span class="action-span"><a href="admin_list.jsp">管理员列表</a></span>
<span class="action-span1"><a href="../home.jsp">管理中心</a>  - 添加管理员 </span>
<div style="clear:both"></div>
</h1><!-- start add new category form -->
<div class="main-div">
 <form method="post" action="../admin!saveAdd.do" name="theForm" id="theForm"  >
   <table cellspacing="1" cellpadding="3" width="100%">
     <tr>
       <td width="28%" class="label">用户名</td>
       <td width="17%"><input type="text" name="admin.uname"  maxlength="60" value="" id="uname"/>       </td>
       <td width="55%" align="left"><div id="unameTip" style="width:250px"></div></td>
     </tr>
     <tr>
       <td width="28%" class="label">姓名</td>
       <td width="17%"><input type="text" name="admin.name"  maxlength="60" value="" id="name"/>       </td>
       <td width="55%" align="left"><div id="nameTip" style="width:250px"></div></td>
     </tr>
     <tr>
       <td width="28%" class="label">密码</td>
       <td width="17%"><input type="password" id="password" name="admin.pwd"  maxlength="60" value="" />       </td>
       <td width="55%" align="left"><div id="passwordTip" style="width:250px"></div></td>
     </tr>
     <tr>
       <td width="28%" class="label">确认密码</td>
       <td width="17%"><input type="password" name="repwd" id="repwd"  maxlength="60" value="" />       </td>
       <td width="55%" align="left"><div id="repwdTip" style="width:250px"></div></td>
     </tr>	 

     <tr>
       <td width="28%" class="label">状态</td>
       <td width="17%">&nbsp;<input type="radio" name="admin.status" value="1" checked />启用&nbsp;&nbsp;<input type="radio" name="admin.status" value="0" />禁用 </td>
       <td width="55%" align="left">&nbsp;</td>
     </tr>	
	 	 	 	 
     <tr>
       <td colspan="3" align="center"><br />
           <input name="submit" type="submit" class="button" value=" 确定 " class="btn"/>
           <input name="reset" type="reset" class="button" value=" 重置 " class="btn"/>       </td>
     </tr>
   </table>
 </form>
</div>

<jsp:include page="../footer.jsp"/>
</body>
</html>
