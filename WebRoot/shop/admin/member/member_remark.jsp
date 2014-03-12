<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<form id="editRemarkForm">
<div id="Member_Form_Point_" class="division">
<table cellspacing="0" cellpadding="0" border="0" width="100%">
	<tbody>
		<tr>
			<th>会员备注:</th>
			<td>
				<textarea name="modify_memo" style="width: 750px; height: 200px;" >${member.remark }</textarea>
			</td>
		</tr>
	</tbody>
</table>
<input type="hidden" value="${member_id }" name="member_id"></div>

<div class="submitlist" align="center">
<table>
	<tr>
		<td><input name="button" type="button" value=" 确    定   "
			class="submitBtn" id="editRemarkBtn"/></td>
	</tr>
</table>
</div>

</form>
<script>
$(function(){

	$("#editRemarkBtn").click(function(){
		$.Loading.show('正在更新数据，请稍侯...');
		var that =this;
		var options = {
			url : "member!remarkSave.do?ajax=yes",
			type : "POST",
			dataType : 'json',
			success : function(result) {	
				$.Loading.hide();
				if(result.result==0){
					alert(result.message);
					MemberDetail.showRemark();
				}else{
					alert(result.message);
				}
			},
			error : function(e) {
				$.Loading.hide();
				alert("出现错误 ，请重试");
			}
		};
		$('#editRemarkForm').ajaxSubmit(options);
		
	});
});
</script>