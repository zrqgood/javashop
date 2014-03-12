<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<script type="text/javascript" src="js/Brand.js"></script>
<div class="input">
	 <form method="post" action="siteTheme!save.do" class="validate" >
	   <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	     <tr>
	       <th><label class="text">模板名称：</label></th>
	       <td><input type="text" name="theme.themename" id="name"   dataType="string" required="true" /></td>
	      </tr>
	     <tr>
	       <th><label class="text">模板ID：</label></th>
	       <td><input type="text" name="theme.path"      required="true" dataType="string"/></td>
	      </tr>
	     <tr>
	       <th><label class="text">作者：</label></th>
	       <td><input type="text" name="theme.author" id="author"  required="true" dataType="string"/>
	       </td>
	     </tr>
 
	 
	   </table>
<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input type="submit"  value=" 确    定   " class="submitBtn" />
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