<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="fck" %>

<div class="input">
 <form class="validate" method="post" action="freeOfferCategory!save.do" name="theForm" id="theForm">
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
     <tr>
       <th><label class="text">分类名称：</label></th>
       <td  ><input type="text" name="freeOfferCategory.cat_name" maxlength="60" value=""  dataType="string" required="true" />       </td>
      </tr>
	<tr>
		<th><label class="text">是否发布：</label></th>
		<td><input type="radio" name="freeOfferCategory.publishable" value="0"
			checked /> 是&nbsp;&nbsp; <input type="radio"
			name="freeOfferCategory.publishable" value="1" /> 否</td>
	</tr>
	
      <tr>
       <th><label class="text">排序：</label></th>
       <td  ><input type="text" name="freeOfferCategory.sorder" maxlength="60" value=""  dataType="int" required="true" /></td>
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
 <script type="text/javascript">
$("form.validate").validate();
</script>
 </div>
