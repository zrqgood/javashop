<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/commonlibs.jsp"%>
<script type="text/javascript" src="${ctx }/js/jquery.pstrength-min.1.2.js"></script>
<script type="text/javascript">
	
	function checkPassword(val) {
		if (val == $("#password").attr("value"))
			return true;
		else
			return "两次输入密码不一致";
	}
	$.Validator.options = {
		lang : {
			required : '此项不能为空!'
		}
	};
	$(function() {
		$('.password').pstrength();
		 
		}
	);
</script>
<style type="text/css">
.password {
	font-size: 12px;
	border: 1px solid #cc9933;
	font-family: arial, sans-serif;
}

.pstrength-minchar {
	font-size: 10px;
}
</style>
<div class="input"><%@ include file="account_header.jsp"%>
<%@ include file="info_header.jsp"%>
<form method="post" action="userAdmin!changePassword.do" name="theForm"
	id="theForm" class="validate">
<table  class="form-table">
	<tr>
		<th><label class="text">请输入原密码：</label></th>
		<td>
		<input type="password" name="oldpassword" id="oldpassword"  dataType="string" required="true" style="width:260px;" />
		</td>
	</tr>
	<tr height="30px">
		<th><label class="text">请输入新密码：</label></th>
		<td><input type="password" name="password" id="password" class="password" style="width:260px;"/></td>
	</tr>
	<tr>
		<th><label class="text">确认新密码：</label></th>
		<td>
			<input type="password" name="newpassword" id="newpassword" dataType="string" required="true" fun="checkPassword" style="width:260px;" />
		</td>
	</tr>
 
</table>
<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input name="submit" type="submit"	  value=" 确    定   " class="submitBtn" />
   </td>
   </tr>
 </table>
</div> 
</form>
</div>