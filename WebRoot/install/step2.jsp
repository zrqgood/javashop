<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="com.enation.eop.sdk.context.EopSetting" %>
<%
String runmode = EopSetting.RUNMODE;
%>
<%@ include file="header.jsp" %>
<style>
input.txt,select{width:180px}
</style>

<h5 style="color: rgb(255, 164, 61); padding-left: 5%; line-height: 150%;">请确认您的tomcat已经禁用reload功能！</h5>
<form id="db_setting" action="install!step3.${ext }" method="post" onsubmit="return checkInput();">
	<table border="0" width="100%">
		<tbody>
			<tr>
				<th align="right" scope="row"><label>数据库类型:</label></th>
				<td>
					<select id="dbtype" name="dbtype">
						<option value="mysql">MySQL</option>
						<option value="oracle">Oracle</option>
						<option value="sqlserver">SQLServer</option>
					</select>
				</td>
			</tr>
			<tr>
				<th align="right" width="22%"><label>数据库主机:</label></th>
				<td width="30%">
					<input type="text" value="localhost:3306" size="25" name="dbhost" id="db_host" class="txt" />
				</td>
				<td width="48%">如果数据库服务器与WEBSERVER不在同一台主机上,请设置为数据库服务器的地址。</td>
			</tr>
			<tr>
				<th align="right" scope="row"><label>数据库用户名:</label></th>
				<td><input type="text" value="" size="25" name="uname" class="txt" id="db_uname" /></td>
				<td style="display: none;" id="db_check_result" rowspan="2"><img src="images/db_succ.gif"></td>
			</tr>
			<tr>
				<th align="right" scope="row"><label>数据库密码:</label></th>
				<td><input type="password" value="" size="25" name="pwd" class="txt" id="db_passwd"></td>
			</tr>
			<tr>
				<th align="right" scope="row"><label>数据库名:</label></th>
				<td id="db_selector">
					<input type="text" value="" size="25" name="dbname" id="db_name" style="width: 120px;" class="txt" />
					<!--   <span  id="btn_check_db"><span style="text-decoration: underline; cursor: pointer; color: rgb(0, 0, 255);">测试连接»</span></span>-->
				</td>
				<td>警告！如果您指定的数据库名称已存在，此安装有可能会破坏原有库中的数据！</td>
			</tr>
			<% if( runmode.equals("2") ){ %>
			<tr>
				<th align="right" scope="row"><label>域名:</label></th>
				<td><input type="text" value="" size="25" name="domain"  id="domain" class="txt" /></td>
				<td width="48%">全域名如:www.enationsoft.com，不要带端口号</td>
			</tr>
			<tr>
				<th align="right" scope="row"><label>解决方案磁盘目录:</label></th>
				<td><input type="text" value="" size="25" id="solutionpath" name="solutionpath" class="txt" /></td>
				<td>解决方案在磁盘的存储目录</td>
			</tr>    
			<%} %>
			<tr>
				<th align="right" scope="row"><label>静态资源分离:</label></th>
				<td>
					<input type="radio" name="resourcemode" id="res_no" value="2" checked="true" />否&nbsp;&nbsp;
					<input type="radio" name="resourcemode" id="res_yes" value="1" />是
				</td>
			</tr>
			<tr class="res_tr" style="display:none">
				<th align="right" scope="row"><label >静态服务磁盘目录:</label></th>
				<td><input type="text" value="" size="25" name="staticpath" id="staticpath" class="txt" /></td><td>默认静态资源将被安装至此目录</td>
			</tr>
			<tr class="res_tr" style="display:none">
				<th align="right" scope="row"><label>静态服务域名:</label></th>
				<td><input type="text" value="" size="25" name="staticdomain" id="staticdomain" class="txt" /></td>
				<td>使此域名可访问上述的"静态服务磁盘目录"</td>
			</tr>
		</tbody>
	</table>
	<center>
    	<input type="button" id="submitBtn" value="下一步：创建配置文件»" style="margin: 10px;" />
	</center>    
</form> 
<script type="text/javascript">
(function(a){function c(){var b=a("#loading");if(b.size()==0)b=a('<div id="loading" class="loading" style=\'z-index:3000\' />').appendTo(a("body")).hide();return b}a.Loading=a.Loading||{};a.Loading.show=function(b){var d=c();b&&d.html(b);a('<div style="height: 100%; width: 100%; position: fixed; left: 0pt; top: 0pt; z-index: 2999; opacity: 0.4;" class="jqmOverlay"></div>').appendTo(a("body"));d.show()};a.Loading.hide=function(){c().hide();a(".jqmOverlay").remove()}})(jQuery);
function checkInput(){
	if($.trim( $("#db_host").val())=='' ){
		
		alert("请输入数据库主机");
		$("#db_host")[0].focus();
		return false;
	}

	if($.trim( $("#db_uname").val() )=='' ){
		alert("请输入数据库用户名");
		$("#db_uname")[0].focus();
		return false;
	}
 
	if($.trim( $("#db_passwd").val() )=='' ){
		alert("请输入数据库密码");
		$("#db_passwd")[0].focus();
		return false;
	}

	if($.trim( $("#db_name").val() )=='' ){
		alert("请输入数据库名");
		$("#db_name")[0].focus();
		return false;
	}
	
	<% if( runmode.equals("2") ){ %>
	if($.trim( $("#domain").val() )=='' ){
		alert("请输入域名");
		$("#domain")[0].focus();
		return false;
	}

	if($.trim( $("#solutionpath").val() )=='' ){
		alert("请输入解决方案磁盘目录");
		$("#solutionpath")[0].focus();
		return false;
	}
	<%}%>
	
	if( $("#res_yes").attr("checked") ){
		if($.trim( $("#staticpath").val() )=='' ){
			alert("请输入静态资源磁盘目录");
			$("#staticpath")[0].focus();
			return false;
		}
	
		if($.trim( $("#staticdomain").val() )=='' ){
			alert("请输入入静态资源域名");
			$("#staticdomain")[0].focus();
			return false;
		}	
	}
}

function checkAndSubmit(){
	$.Loading.show('正在检测数据库连接，请稍候...');
	var options = {
		url :"install!testConnection.${ext}",
		cache:false,
		type : "POST",
		dataType : 'json',
		success : function(result) {
			$.Loading.hide();
			if(result.result==1){
				$.Loading.show('数据库连接成功，转入下一步...');
				$("#db_setting")[0].submit();
			}else{
				alert("数据库连接失败，请检查您输入的用户名或密码。");
			}
		},
		error : function(e) {
			alert("出错啦:(");
		}
	};
	
	$("#db_setting").ajaxSubmit(options);	
}

 //db_setting
$(function(){
	alert("请确认已经禁用tomcat自动reload功能再进行安装!");
	$("#res_no").attr("checked",true) 
	$("#submitBtn").click(function(){
		checkAndSubmit();
	});
	$("#dbtype").change(function(){
		if($("#dbtype").val()=="mysql")
			$("#db_host").val("localhost:3306");
		else if($("#dbtype").val()=="oracle")
			$("#db_host").val("localhost:1521");
		else if($("#dbtype").val()=="sqlserver")
			$("#db_host").val("localhost:1433");
	});
	$("input[name=resourcemode]").click(function(){
		if( $("#res_yes").attr("checked") ){
			$(".res_tr").show();
		}
		if( $("#res_no").attr("checked") ){
			$(".res_tr").hide();
		}
	}); 
});
</script>
<jsp:include page="footer.jsp"></jsp:include>