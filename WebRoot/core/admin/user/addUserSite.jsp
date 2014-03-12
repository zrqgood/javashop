<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/commonlibs.jsp"%>
<div class="input">
<form action="userSite!addSave.do" method="post" name="theForm"
	id="theForm" enctype="multipart/form-data">
<table  class="form-table">
	<tr>
		<th><label class="text">网站名称：</label></th>
		<td><input type="text" name="eopSite.sitename" 
			dataType="string" required="true" /></td>
	</tr>
	<tr>
       <th><label class="text">网站LOGO：</label></th>
       <td><input type="file" name="cologo" id="cologo" size="45"/>
           <span class="notice-span"  id="warn_brandlogo">请上传图片，做为网站的LOGO</span></td>
     </tr>
     <tr>
       <th><label class="text">网站ICO文件：</label></th>
       <td><input type="file" name="ico" id="ico" size="45"/>
           <span class="notice-span"  id="warn_brandlogo">请上传网站的ico文件</span></td>
     </tr>
     <tr>
       <th><label class="text">指定站点域名：</label></th>
       <td><input type="text" name="sitedomain" id="sitedomain"  /><span class="notice-span"  id="warn_brandlogo">必须为站点指定域名</span>
           </td>
     </tr>
	<tr>
		<td colspan=2><input type="reset" value="重置" class="btn"/>&nbsp;<input
			type="submit" value="新增" class="btn"/></td>
	</tr>
</table>
</form>
</div>