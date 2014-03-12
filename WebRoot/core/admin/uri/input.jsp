<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="addcontent">
<div class="input">
<form >
<input type="hidden" name="themeUri.id" value="${ themeUri.id}" />
<table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	<tr>
		<th><label class="text">uri：</label></th>
		<td>
		<input type="text" name="themeUri.uri" value="${ themeUri.uri}"  dataType="string" required="true" />
		</td>
	</tr>
	<tr>
		<th><label class="text">path：</label></th>
		<td><input type="text" name="themeUri.path" value="${themeUri.path }" dataType="string" required="true"/> </td>
	</tr>
	<tr>
		<th><label class="text">页面名称：</label></th>
		<td><input type="text" name="themeUri.pagename"  value="${ themeUri.pagename}"  dataType="string" required="true"/> 
		</td>
	</tr>
	<tr>
		<th><label class="text">积分</label></th>
		<td><input type="text" name="themeUri.point" value="${themeUri.point }"  dataType="string" required="int" value="0"/> </td>
	</tr>
	<tr>
		<th><label class="text">关键字：</label></th>
		<td><input type="text" name="themeUri.keywords" value="${ themeUri.keywords}"   dataType="string" required="true"/> 
		</td>
	</tr>
	<tr>
		<th><label class="text">描述：</label></th>
		<td> 
			<textarea name="themeUri.description"  style="width:250px;height:100px"  >${themeUri.description }</textarea>
		</td>
	</tr>	
</table>
</form>
</div>

</div>

<div class="footContent" >
<div style="width: 200px; height: 40px; margin: 0pt auto;"
	class="mainFoot">
<table style="margin: 0pt auto; width: auto;">
	<tbody>
		<tr>
			<td><b class="save">
			<button class="submitBtn" >保存</button>
			</b></td>
		</tr>
	</tbody>
</table>
</div>
</div> 
