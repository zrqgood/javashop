<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/commonlibs.jsp"%>
<div class="input">
<form action="multiSite!editSave.do" method="post" name="theForm" id="theForm"  class="validate"  >
<table  class="form-table">
	<tr>
		<th><label class="text">网站名称：</label></th>
		<td><input type="text" name="site.name" value="${site.name }" style="width:250px" dataType="string" required="true" /><input type="hidden" name="site.siteid" value="${site.siteid }"/></td>
	</tr>
	<tr>
       <th><label class="text">父站点：</label></th>
       <td>${parentname }<input type="hidden" name="site.parentid" value="${site.parentid }"/></td>
     </tr>
     <tr>
       <th><label class="text">站点域名：</label></th>
       <td><input type="text" name="site.domain" id="sitedomain"  value="${site.domain }"  dataType="string" required="true"  style="width:250px"/></td>
     </tr>
      
</table>


<div class="submitlist" align="center">
 <table>
 <tr><td >
           <input  type="submit" name="submit"  value=" 确定 " class="submitBtn" id="saveAddBtn"/>
           <input name="reset" type="reset"  value=" 重置 " class="submitBtn"/>
   </td>
   </tr>
 </table>
</div>  
</form>
<script>
$(function(){
	$("form.validate").validate();
});
</script>

</div>
