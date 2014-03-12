<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<div class="input">
<form class="validate" id="editMemberForm">
<input type="hidden" name="member.member_id" value="${member_id }">
<div class="main-div">
<table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	<tr>
		<td class="label" style="text-align: right"><label class="text">用户名：</label>
		</td>
		<td valign="middle">${member.uname}</td>
	</tr>
	<tr>
		<td class="label" style="text-align: right"><label class="text">姓名：</label>
		</td>
		<td valign="middle">&nbsp; <input type="text" name="member.name"
			value="${member.name}" /></td>
	</tr>
	<tr>
		<td class="label" style="text-align: right"><label class="text">性别：</label>
		</td>
		<td valign="middle">&nbsp; <select name="member.sex">
			<option value="-1">&nbsp;</option>
			<option value="0" <c:if test="${member.sex==0 }">selected</c:if>>
			女</option>
			<option value="1" <c:if test="${member.sex==1 }">selected</c:if>>
			男</option>

		</select></td>
	</tr>
	<tr>
		<td class="label" style="text-align: right"><label class="text">出生日期：</label>
		</td>
		<td valign="middle">&nbsp; <input type="text" name="birthday" value="<html:dateformat pattern="yyyy-MM-dd" time="${member.birthday }"></html:dateformat>" maxlength="60" dataType="date" required="true" class="dateinput"/></td>
	</tr>
	<tr>
		<td class="label" style="text-align: right"><label class="text">Email：</label>
		</td>
		<td valign="middle">&nbsp; <input type="text" name="member.email"
			value="${member.email}" id="email" dataType="email" />
		</td>
	</tr>
	<tr>
		<td class="label" style="text-align: right"><label class="text">固定电话：</label>
		</td>
		<td valign="middle">&nbsp; <input type="text" name="member.tel"
			value="${member.tel}" /></td>
	</tr>
	<tr>
		<td class="label" style="text-align: right"><label class="text">移动电话：</label>
		</td>
		<td valign="middle">&nbsp; <input type="text"
			name="member.mobile" value="${member.mobile}" /></td>
	</tr>
	<tr>
		<td class="label" style="text-align: right"><label class="text">会员等级：</label>
		</td>
		<td valign="middle">&nbsp; <select name="member.lv_id">
			<option value="0">--选择等级--</option>
			<c:forEach items="${lvlist }" var="lv">
				<option value="${lv.lv_id }"
					<c:if test="${ lv.lv_id == member.lv_id  }">selected</c:if>>
				${lv.name }</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td class="label" style="text-align: right"><label class="text">邮编：</label>
		</td>
		<td valign="middle">&nbsp; <input type="text" name="member.zip"
			id="zip" value="${member.zip}" style="width: 70px" dataType="post_code"/></td>
	</tr>
	<tr>
		<td class="label" style="text-align: right"><label class="text">地区：</label>
		</td>
		<td>&nbsp; 
		<select name="member.province_id"	id="address_province_id"></select>
		&nbsp; <input type="hidden"	name="member.province" id="address_province" value="${member.province }"/> 
		<select	name="member.city_id" id="address_city_id"></select>
		&nbsp; <input type="hidden" name="member.city" id="address_city" value="${member.city }"/> 
		<select	name="member.region_id" id="address_region_id"></select>
		&nbsp; <input type="hidden" name="member.region" id="address_region" value="${member.region }"/>
		</td>
	</tr>
	<tr>
		<td class="label" style="text-align: right"><label class="text">地址：</label>
		</td>
		<td valign="middle">&nbsp; <input type="text"
			name="member.address" value="${member.address}" style="width: 150px" />
		</td>
	</tr>
	<tr>
		<td class="label" style="text-align: right"><label class="text">QQ：</label>
		</td>
		<td valign="middle">&nbsp; <input type="text" name="member.qq"
			id="qq" value="${member.qq}" style="width: 70px" /></td>
	</tr>
	<tr>
		<td class="label" style="text-align: right"><label class="text">Msn：</label>
		</td>
		<td valign="middle">&nbsp; <input type="text" name="member.msn"
			id="msn" value="${member.msn}" style="width: 70px" /></td>
	</tr>
	<tr>
		<td class="label" style="text-align: right"><label class="text">安全问题：</label>
		</td>
		<td valign="middle">&nbsp; <input type="text" id="pw_question"
			name="member.pw_question" value="${member.pw_question}"
			style="width: 150px" /></td>
	</tr>
	<tr>
		<td class="label" style="text-align: right"><label class="text">回答：</label>
		</td>
		<td valign="middle">&nbsp; <input type="text" id="pw_answer"
			name="member.pw_answer" value="${member.pw_answer}"
			style="width: 150px" /></td>
	</tr>
</table>
</div>

<div class="submitlist" align="center">
<table>
	<tr>
		<td><input name="button" type="button" value=" 确    定   "
			class="submitBtn" id="editMemberBtn"/></td>
	</tr>
</table>
</div>

</form>
<script>
function initCity(){
	//$("#address_city_id").hide();
	//$("#address_region_id").hide();
	$("#address_province_id").empty();
	$("<option value='-1'>请选择...</option>").appendTo($("#address_province_id"));
	
	<c:forEach items="${provinceList}" var="province" >
		$("<option value='${(province.region_id)}' >${province.local_name}</option>").appendTo($("#address_province_id"));
	</c:forEach>

	<c:forEach items="${cityList}" var="city" >
		$("<option value='${(city.region_id)}' >${city.local_name}</option>").appendTo($("#address_city_id"));
	</c:forEach>

	<c:forEach items="${regionList}" var="region" >
		$("<option value='${(region.region_id)}' >${region.local_name}</option>").appendTo($("#address_region_id"));
	</c:forEach>
	
	$("#address_province_id option[value=${member.province_id}]").attr("selected", "selected");
	$("#address_city_id option[value=${member.city_id}]").attr("selected", "selected");
	$("#address_region_id option[value=${member.region_id}]").attr("selected", "selected");
	
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
	$("form.validate").validate();
	initCity();
	$("#editMemberBtn").click(function(){
		$.Loading.show('正在更新数据，请稍侯...');
		var that =this;
		var options = {
			url : "member!saveEditMember.do?ajax=yes",
			type : "POST",
			dataType : 'json',
			success : function(result) {	
				$.Loading.hide();
				if(result.result==0){
					alert(result.message);
					MemberDetail.showEdit();
				}else{
					alert(result.message);
				}
			},
			error : function(e) {
				$.Loading.hide();
				alert("出现错误 ，请重试");
			}
		};
		$('#editMemberForm').ajaxSubmit(options);
		
	});
});
</script>
</div>
