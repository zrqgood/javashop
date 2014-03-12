<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<form id="addPointForm" class="validate">
<div id="Member_Form_Point_" class="division">
<table cellspacing="0" cellpadding="0" border="0" width="100%">
	<tbody>
		<tr>
			<th>当前积分:</th>
			<td>${member.point }</td>
		</tr>
		<tr>
			<th>调整积分（增/减）:</th>
			<td><input type="text" name="point" size="20" dataType="float">输入负值即可减少积分；</td>
		</tr>
	</tbody>
</table>
<input type="hidden" value="${member_id }" name="member_id"></div>

<div class="submitlist" align="center">
<table>
	<tr>
		<td><input name="button" type="button" value=" 确    定   "
			class="submitBtn" id="addPointBtn"/></td>
	</tr>
</table>
</div>

</form>
<script>
$(function(){
	$("form.validate").validate();
	$("#addPointBtn").click(function(){
		$.Loading.show('正在更新数据，请稍侯...');
		var that =this;
		var options = {
			url : "member!editSavePoint.do?ajax=yes",
			type : "POST",
			dataType : 'json',
			success : function(result) {	
				$.Loading.hide();
				if(result.result==0){
					alert(result.message);
					MemberDetail.showEditPoint();
				}else{
					alert(result.message);
				}
			},
			error : function(e) {
				$.Loading.hide();
				alert("出现错误 ，请重试");
			}
		};
		$('#addPointForm').ajaxSubmit(options);
		
	});
});
</script>