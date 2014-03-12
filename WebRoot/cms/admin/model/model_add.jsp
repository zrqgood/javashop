<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="input">
	 <form method="post" action="model!saveAdd.do" class="validate" name="theForm" id="theForm" >
	   <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	     <tr>
	       <th><label class="text">模型名称：</label></th>
	       <td><input type="text" name="dataModel.china_name" maxlength="60" value=""  dataType="string" required="true" /></td>
	      </tr>
	     <tr>
	       <th><label class="text">表名：</label></th>
	       <td><input type="text" name="dataModel.english_name"  maxlength="60" size="40" value=""  required="true"  dataType="string"/></td>
	      </tr>
	     <tr>
	       <th><label class="text">备注</label></th>
	       <td><textarea name="dataModel.brief" style="height:50px;width;100px"></textarea>
		  </td>
	     </tr>
	 
	   </table>
	<div class="submitlist" align="center">
	 <table>
	 <tr><td >
	  <input name="submit" type="submit"	  value=" 确    定   " class="submitBtn" />
	   </td>
	   </tr>
	 </table>
	</div>
	 </form>
</div>
<script type="text/javascript">
$(function(){
	$("form.validate").validate();
});
</script>