<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/Brand.js"></script>
<div class="input">
<form method="post" action="brand!save.do" class="validate"
	id="brandForm" enctype="multipart/form-data">
<table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	<tr>
		<th><label class="text">品牌名称：</label></th>
		<td><input type="text" name="brand.name" id="name" maxlength="60"
			value="" dataType="string" required="true" /></td>
	</tr>
	<tr>
		<th><label class="text">品牌网址：</label></th>
		<td><input type="text" name="brand.url" id="url" maxlength="60"
			size="40" value="" dataType="url" /> 以http://开头</td>
	</tr>
	<tr>
		<th><label class="text">品牌LOGO：</label></th>
		<td><input type="file" name="logo" id="logo" size="45" /> <span
			class="notice-span" id="warn_brandlogo">请上传图片，做为品牌的LOGO</span></td>
	</tr>
	<tr>
		<th><label class="text">详细说明</label></th>
		<td><textarea id="brief" name="brand.brief"></textarea></td>
	</tr>

</table>
<div class="submitlist" align="center">
<table>
	<tr>
		<td><input type="button" value=" 确    定   " class="submitBtn" />
		</td>
	</tr>
</table>
</div>
</form>
</div>

<script type="text/javascript">
	$(function() {
		$("form.validate").validate();
		BrandInput.init();
		$('#brief').ckeditor();
		
	});
</script>