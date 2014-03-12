<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="division">
<table cellspacing="0" cellpadding="0" border="0" width="100%">
	<tbody>
		<tr>
			<th>当前预存款:</th>
			<td><fmt:formatNumber type="currency" value="${member.advance}" /></td>
			<th></th>
			<td></td>
		</tr>
	</tbody>
</table>
</div>

<div id="Member_Form_Deposit_4" class="division">
<form id="advanceForm" class="validate">
<input type="hidden" value="${member_id }" name="member_id">
<h5 style="padding-left: 22px;">预存款充值</h5>
<table cellspacing="0" cellpadding="0" border="0" width="100%">
	<tbody>
		<tr>
			<th>充值金额:</th>
			<td><input type="text" name="modify_advance" size="20" dataType="float"><span><img
				border="none"
				style="width: 14px; height: 14px; background-image: url(&quot;images/ImageBundle.gif&quot;); background-repeat: no-repeat; background-position: 0pt -1577px;"
				src="images/transparent.gif" title="此处可输入负值，负值表示减预存款"></span></td>
			<th>备注:</th>
			<td><input type="text" name="modify_memo" size="30"></td>
		</tr>
		<tr>
			<td colspan="4">
			<div class="submitlist" align="center">
			<table>
				<tr>
					<td><input name="button" type="button" value=" 确    定   "
						class="submitBtn" id="addAdvanceBtn"/></td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
	</tbody>
</table>
</form>
</div>

<div class="division">
<div class="grid"><grid:grid from="listAdvanceLogs">
	<grid:header>
		<grid:cell>交易时间</grid:cell>
		<grid:cell>业务摘要</grid:cell>
		<grid:cell style="width:75px">存入金额</grid:cell>
		<grid:cell style="width:75px">支出金额</grid:cell>
		<grid:cell style="width:75px">当前余额</grid:cell>
		<grid:cell style="width:75px">支付方式</grid:cell>
		<grid:cell>支付单号</grid:cell>
		<grid:cell>订单号</grid:cell>
		<grid:cell style="width:300px">管理备注</grid:cell>
	</grid:header>
	<grid:body item="advance">
		<grid:cell>
			<html:dateformat pattern="yyyy-MM-dd HH:mm" time="${advance.mtime }" />
		</grid:cell>
		<grid:cell>${advance.memo}</grid:cell>
		<grid:cell>
		<c:if test="${advance.import_money != 0 }">
			<fmt:formatNumber type="currency" value="${advance.import_money}" />
		</c:if>
		</grid:cell>
		<grid:cell>
			<c:if test="${advance.explode_money != 0 }">
			<fmt:formatNumber type="currency" value="${advance.explode_money}" />
			</c:if>
		</grid:cell>
		<grid:cell>
			<fmt:formatNumber type="currency" value="${advance.member_advance}" />
		</grid:cell>
		<grid:cell>${advance.paymethod}</grid:cell>
		<grid:cell><c:if test="${advance.payment_id != 0 }">${advance.payment_id}</c:if></grid:cell>
		<grid:cell><c:if test="${advance.order_id != 0 }">${advance.order_id}</c:if></grid:cell>
		<grid:cell>${advance.message}</grid:cell>
	</grid:body>
</grid:grid></div>
<script>
$(function(){
	$("form.validate").validate();
	$("#addAdvanceBtn").click(function(){
		$.Loading.show('正在更新数据，请稍侯...');
		var that =this;
		var options = {
			url : "member!editSaveAdvance.do?ajax=yes",
			type : "POST",
			dataType : 'json',
			success : function(result) {	
				$.Loading.hide();
				if(result.result==0){
					alert(result.message);
					MemberDetail.showAdvance();
				}else{
					alert(result.message);
				}
			},
			error : function(e) {
				$.Loading.hide();
				alert("出现错误 ，请重试");
			}
		};
		$('#advanceForm').ajaxSubmit(options);
		
	});
});
</script>

</div>