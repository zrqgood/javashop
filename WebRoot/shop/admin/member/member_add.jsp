<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<div class="input">
<form class="validate" action="member!saveMember.do" method="post" id="theForm"
	name="theForm">
<div class="main-div">
<table cellspacing="1" cellpadding="3" width="100%"  class="form-table">
	<tr>
		<td class="label" style="text-align:right"><label class="text">用户名：</label></td>
		<td valign="middle">&nbsp;<input type="text" name="member.uname"
			id="uname" value="" dataType="string" required="true" /></td>
	</tr>
	<tr>
		<td class="label" style="text-align:right"><label class="text">密码：</label></td>
		<td valign="middle">&nbsp; <input type="text"
			name="member.password" id="password" value="" dataType="string"
			required="true" /></td>
	</tr>
	<tr>
		<td class="label" style="text-align:right"><label class="text">
		姓名：</label></td>
		<td valign="middle">&nbsp; <input type="text" name="member.name"
			  required="true" dataType="string"/></td>
	</tr>
	<tr>
		<td class="label" style="text-align:right"><label class="text">性别：</label></td>
		<td valign="middle">&nbsp; <select name="member.sex">
			<option value="1">男</option>
			<option value="0">女</option>
		</select></td>
	</tr>
	<tr>
		<td class="label" style="text-align:right"><label class="text">出生日期：</label></td>
		<td valign="middle">&nbsp; <input required="true" dataType="date" name="birthday" id="birthday" value="1980-01-01" readonly="true"  class="dateinput" /> </td>
	</tr>
	<tr>
		<td class="label" style="text-align:right"><label class="text">Email：</label></td>
		<td valign="middle">&nbsp; <input type="text" name="member.email"
			value="" id="email" dataType="email"  required="true"/></td>
	</tr>
	<tr>
		<td class="label" style="text-align:right"><label class="text">固定电话：</label></td>
		<td valign="middle">&nbsp; <input type="text" name="member.tel"
			value="" /></td>
	</tr>
	<tr>
		<td class="label" style="text-align:right"><label class="text">移动电话：</label></td>
		<td valign="middle">&nbsp; <input type="text"
			name="member.mobile" value="" /></td>
	</tr>
	<tr>
		<td class="label" style="text-align:right"><label class="text">会员等级：</label></td>
		<td valign="middle">&nbsp; <select name="member.lv_id">
			<option value="0">--请选择等级--</option>
			<c:forEach items="${lvlist }" var="lv">
				<option value="${lv.lv_id }">${lv.name }</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td class="label" style="text-align:right"><label class="text">邮编：</label></td>
		<td valign="middle">&nbsp; <input type="text" name="member.zip"
			value="" style="width: 70px"   /></td>
	</tr>
	<tr>
		<td class="label" style="text-align:right"><label class="text">地区：</label></td>
		<td valign="middle">
		<select name="member.province_id"	id="address_province_id"></select>
		&nbsp; <input type="hidden"	name="member.province" id="address_province" /> 
		<select	name="member.city_id" id="address_city_id"></select>
		&nbsp; <input type="hidden" name="member.city" id="address_city" /> 
		<select	name="member.region_id" id="address_region_id"></select>
		&nbsp; <input type="hidden" name="member.region" id="address_region" />
		</td>
	</tr>
	
	<tr>
		<td class="label" style="text-align:right"><label class="text">地址：</label></td>
		<td valign="middle">&nbsp; <input type="text"
			name="member.address" value="" style="width: 150px" /></td>
	</tr>
	<tr>
		<td class="label" style="text-align:right"><label class="text">安全问题：</label></td>
		<td valign="middle">&nbsp; <input type="text" id="pw_question"
			name="member.pw_question" value="我的宠物名字是？" style="width: 150px" /></td>
	</tr>
	<tr>
		<td class="label" style="text-align:right"><label class="text">回答：</label></td>
		<td valign="middle">&nbsp; <input type="text" id="pw_answer"
			name="member.pw_answer" style="width: 150px" /></td>
	</tr>
</table>
</div>

<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input name="submit" type="submit"	  value=" 确    定   " class="submitBtn" />
   </td>
   </tr>
 </table>
</div>

</form>
<script type="text/javascript">
$("form.validate").validate();
</script>
<script>
function initCity(){
	$("#address_city_id").hide();
	$("#address_region_id").hide();
	$("#address_province_id").empty();
	$("<option value='-1'>请选择...</option>").appendTo($("#address_province_id"));
	
	<c:forEach items="${provinceList}" var="province" >
		$("<option value='${(province.region_id)}' >${province.local_name}</option>").appendTo($("#address_province_id"));
	</c:forEach>

	$("#address_province_id").change(function(){
		$("#address_province").val($("#address_province_id option:selected").text());
		$("#address_city_id").empty();
		$("#address_city_id").hide();
		$("#address_region_id").empty();
		$("#address_region_id").hide();
		$.ajax({
			method:"get",
			url:"../area!list_city.do?province_id=" + $("#address_province_id").attr("value"),
			dataType:"html",
			success:function(result){
				$("#address_city_id").show();
				$(result).appendTo($("#address_city_id"));
			},
			error:function(){
				alert("异步失败");
			}
		});
	});
	$("#address_city_id").change(function(){
		$("#address_city").val($("#address_city_id option:selected").text());
		$("#address_region_id").empty();
		$("#address_region_id").hide();
		$.ajax({
			method:"get",
			url:"../area!list_region.do?city_id=" + $("#address_city_id").attr("value"),
			dataType:"html",
			success:function(result){
				$("#address_region_id").show();
				$(result).appendTo($("#address_region_id"));
			},
			error:function(){
				alert("异步失败");
			}
		});
	});
	$("#address_region_id").change(function(){
		$("#address_region").val($("#address_region_id option:selected").text());
	});
}
$(function(){
	initCity();
});
</script>
</div>

