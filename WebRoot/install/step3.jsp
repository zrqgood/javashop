<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="com.enation.eop.sdk.context.EopSetting" %>
<%
String runmode = EopSetting.RUNMODE;
%>
<%@ include file="header.jsp" %>
<script>
(function(a){function c(){var b=a("#loading");if(b.size()==0)b=a('<div id="loading" class="loading" style=\'z-index:3000\' />').appendTo(a("body")).hide();return b}a.Loading=a.Loading||{};a.Loading.show=function(b){var d=c();b&&d.html(b);a('<div style="height: 100%; width: 100%; position: fixed; left: 0pt; top: 0pt; z-index: 2999; opacity: 0.4;" class="jqmOverlay"></div>').appendTo(a("body"));d.show()};a.Loading.hide=function(){c().hide();a(".jqmOverlay").remove()}})(jQuery);
function do_install(str){
	if($.trim($('#ipt_uname').val())==''){
		alert('管理员用户名不能为空。');
		return false;
	}
	if($.trim($('#ipt_passwd').val())==''){
		alert('管理员密码不能为空。');
		return false;
	}
	if($('#ipt_passwd').val()!=$('#ipt_re_passwd').val()){
		alert('两次输入密码不一致。');
		return false;
	}
	return true;
}

function bindEvent(){
 $("#installBtn").click(function(){
	if(!do_install(""))
		return false;	 
	 $.Loading.show('正在安装，可能要花费很长时间，请稍候...');
		var options = {
			url :"install!doInstall.${ext}",
			cache:false,
			type : "POST",
			dataType : 'json',
			success : function(result) {				
				 if(result.result==1){
						 $("#installFrm").submit();
					 }else{
						 alert("安装失败");
	 				 }
				$.Loading.hide();
			},
			error : function(e) {
				 alert("出错啦:(");
			}
		};

	$("#installFrm").ajaxSubmit(options);		 
 });	
}

function checkReady(){
	$.ajax({
		url :"install!testReady.${ext}",
			cache:false,
			type : "POST",
			dataType : 'json',
			success : function(result) {				
				 if(result.result==1){
					 bindEvent();	  
				 }else{
					alert("初始化失败，不能继续，可尝试重起web容器后重试.");	 
	 			}
				 $.Loading.hide();
			},error:function(){
				alert("初始化失败，不能继续，可尝试重起web容器后重试.");	 
				 $.Loading.hide();
			}
	});	
}

$(function(){
	$.Loading.show('正初始化，请稍后...');
	setTimeout('checkReady()',1000);
		  
});
  
</script>
<center><h5 class="success">数据库已配置并连接成功 </h5></center>

<div style="width: 590px; padding: 10px; margin: 10px; border-width: 1px; border-color: rgb(102, 102, 102); border-style: solid none;">

<table width="100%">
<tbody><tr style="background: none repeat scroll 0% 0% rgb(224, 234, 242);">
  <td width="60%">操作系统</td><td>${osVersion }</td>
</tr>
<tr>
  <td width="60%">Java的运行环境</td><td>${javaVersion }</td>
</tr>

</tbody></table>
</div>
<div style="padding-left: 45px;"><h5 class="success"><br>请在下面建立商店管理员帐户：</h5></div>
<div id="show">
<form  action="install!installSuccess.${ext }" method="post" id="installFrm">

<table>
  <tbody>
   <% if( runmode.equals("2") ){ %>
 
   <input type="hidden" name="productid" value="eopsaler"  >
   <input type="hidden" name="domain" value="${domain }"  >
   
   	
   <%}else{ %>
  <tr>
    <th align="right" width="150px" scope="row"><label for="ipt_uname">解决方案：</label></th>
    <td width="200px">
		<select name="productid">
			<option value="neiyi">网络商店示例</option>
			<option value="company">企业网站示例</option>
			<option value="digital">新潮电子网站示例</option>
		</select>  	
	</td>
    <th align="right"> </th>
    <td>
    </td>
  </tr> 
    <%} %>
  <tr>
    <th align="right" width="150px" scope="row"><label for="ipt_uname">管理员用户名：</label></th>
    <td width="200px"><input type="text" tabindex="2" value="admin" id="ipt_uname" name="uname"></td>
    <th align="right"> </th>
    <td>
    </td>
  </tr>
  <tr>
    <th align="right" scope="row"><label for="ipt_passwd">管理员密码：</label></th>
    <td><input type="password" tabindex="3" id="ipt_passwd" name="pwd"></td>
    <th align="right"> </th>
    <td>
    </td>
  </tr>
  <tr>
    <th align="right" scope="row"><label for="ipt_re_passwd">再输入一次密码：</label></th>
    <td colspan="3"><input type="password" tabindex="4" id="ipt_re_passwd" name="re_passwd"></td>
  </tr>

</tbody></table>
</form></div>
<div class="button"><input id="installBtn" type="image" tabindex="5" src="images/btn-install.gif"></div>
<jsp:include page="footer.jsp"></jsp:include>
 