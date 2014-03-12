<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<div class="input">
<form id="newFileFrm" action="themeFile!addFile.do" method="post" class="validate">
<input type="hidden" name="themeid" value="${themeid }" />
<input type="hidden" name="folderName" value="${folderName }" />
<input type="hidden" name="type" value="${type }" />
<table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	<tr>
		<th><label class="text">名称：</label></th>
		<td>
		<input type="text" name="name"  dataType="string" required="true" />
		</td>
	</tr>
	<tr>
		<th><label class="text">内容：</label></th>
		<td>
		<textarea style="width:95%;height:500px" id="content" name="content"></textarea>
		</td> 
	</tr>
</table>

<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input type="submit" name="submit"  value=" 确    定   " class="submitBtn" />
   </td>
   </tr>
 </table>
</div>

</form>
</div>
<script type="text/javascript">
$(function(){
	$("form.validate").validate();
	$('#content' ).ckeditor( );	
});
</script>