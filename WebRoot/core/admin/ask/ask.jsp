<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<div class="input">
	 <form method="post" action="ask!ask.do" class="validate"   id="brandForm"  enctype="multipart/form-data">
	   <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	     <tr>
	       <th><label class="text">标题：</label></th>
	       <td><input type="text" name="title" id="title"  style="width:250px;"  dataType="string" required="true" /></td>
	      </tr>
	     <tr>
	       <th><label class="text">内容：</label></th>
	       <td>
		   <textarea id="content" name="content"></textarea>
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