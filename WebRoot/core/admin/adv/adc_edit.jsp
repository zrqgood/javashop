<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<div class="input">
 <form class="validate" method="post" action="adColumn!editSave.do" name="theForm" id="theForm">
 <div class="toolbar">
	
		<ul>
			<li>广告位置编辑</li>
		</ul>
		
		<div style="clear: both;"></div>
	</div>
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
     <tr>
       <td  class="label"><label class="text">位置名称：<input type="hidden" name="adColumn.acid" value="${acid}" /></label></td>
       <td  ><input type="text" name="adColumn.cname" maxlength="60" value="${adColumn.cname }"  dataType="string" required="true" />&nbsp;&nbsp;<span class="help_icon" helpid="adcolumn_name"></span>       </td>
      </tr>
	 <tr>
       <td  class="label"><label class="text">广告类型：</label></td>
       <td  ><select name="adColumn.atype">
       			<option value=0 <c:if test="${adColumn.atype == 0 }">selected</c:if>>图片</option>
       			<option value=1 <c:if test="${adColumn.atype == 1 }">selected</c:if>>Flash</option> 
             </select>      
       </td>
      </tr>
      <tr>
       <td  class="label"><label class="text">宽度：</label></td>
       <td  ><input type="text" name="adColumn.width" maxlength="60" value="${adColumn.width }"  dataType="string" required="true" />&nbsp;&nbsp;<span class="help_icon" helpid="adcolumn_width"></span></td>
      </tr>
      <tr>
       <td  class="label"><label class="text">高度：</label></td>
       <td  ><input type="text" name="adColumn.height" maxlength="60" value="${adColumn.height }"  dataType="string" required="true" />&nbsp;&nbsp;<span class="help_icon" helpid="adcolumn_height"></span></td>
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
