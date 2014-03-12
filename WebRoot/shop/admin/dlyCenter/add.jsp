<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="fck"%>
<div class="input">
<div class="main-div">
<form method="post"
	action="dlyCenter!saveAdd.do"
	class="validate">
<h4>添加发货地址信息</h4>
<div class="division">
<table cellspacing="0" cellpadding="0" border="0" class="form-table">
	<tbody>
		<tr>
			<td>
			<table cellspacing="0" cellpadding="0" border="0" class="form-table">
				<tbody>
					<tr>
						<th>发货点名称：</th>
						<td colspan="3"><input style="width: 145px;" size="10"
							 name="dlyCenter.name"></td>
					</tr>
					<tr>
						<th>发货人姓名：</th>
						<td><input style="width: 80px;" size="10"
							 name="dlyCenter.uname"></td>
						<th>性别：</td>
						<td><label> <input type="radio" checked="checked"
							value="male" name="dlyCenter.sex">先生</label> <label> <input
							type="radio" value="famale" name="dlyCenter.sex">女士</label></th>
					</tr>
					<tr>
						<th>地区：</th>
						<td>&nbsp; 
		<select name="dlyCenter.province_id"	id="address_province_id"></select>
		&nbsp; <input type="hidden"	name="dlyCenter.province" id="address_province" value="${dlyCenter.province }"/> 
		<select	name="dlyCenter.city_id" id="address_city_id"></select>
		&nbsp; <input type="hidden" name="dlyCenter.city" id="address_city" value="${dlyCenter.city }"/> 
		<select	name="dlyCenter.region_id" id="address_region_id"></select>
		&nbsp; <input type="hidden" name="dlyCenter.region" id="address_region" value="${dlyCenter.region }"/>
		</td>
						<th>邮编：</th>
						<td><input name="dlyCenter.zip" size="6" style="width: 90px;">
						</td>
					</tr>
					<tr>
						<th>地址：</th>
						<td colspan="3"><input type="text" style="width: 400px;" size="50"
							name="dlyCenter.address"></td>
					</tr>
					<tr>
						<th>手机：</th>
						<td><input style="width: 145px;" name="dlyCenter.cellphone"
							size="36"></td>
						<th>电话：</th>
						<td><input style="width: 145px;" name="dlyCenter.phone" size="36"></td>
					</tr>

					<tr>
						<th>&nbsp;</th>						<td colspan="3"><input type="checkbox" checked="checked"
							id="is_default_dc" name="is_default"> <label
							for="is_default_dc">设置为默认地址</label></th>
					</tr>
					<tr>
						<th>备注：</th>
						<td colspan="3"><textarea rows="5" cols="45"
							style="width: 395px;" name="dlyCenter.memo"></textarea></td>
					</tr>
				</tbody>
			</table>
			</td>
		</tr>
	</tbody>
</table>
</div>
<div class="submitlist" align="center">
<table>
	<tr>
		<td><input name="submit" type="submit" value=" 确    定   "
			class="submitBtn" /></td>
	</tr>
</table>
</div>
</form>
</div>
<script>
function initCity(){
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
	$("form.validate").validate();
	initCity();
});
</script>
</div>
