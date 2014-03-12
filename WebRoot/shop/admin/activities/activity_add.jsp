<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<div class="input">
 <form class="validate" method="post" action="activity!saveAdd.do" name="theForm" id="theForm">
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
     <tr>
       <th><label class="text">活动名称：</label></th>
       <td><input type="text" name="activity.name" maxlength="60" value=""  dataType="string" required="true"  />       </td>
      </tr>
      <tr>
       <th><label class="text">是否开启：</label></th>
       <td>
       <input type="radio" name="activity.enable" value="0"
			checked /> 否&nbsp;&nbsp; <input type="radio"
			name="activity.enable" value="1" /> 是
       </td>
      </tr>
	
	<tr>
       <th><label class="text">起始时间：</label></th>
       <td  ><input type="text" name="begin_time" maxlength="60" value=""  dataType="date" required="true" class="dateinput"/></td>
      </tr>
      <tr>
       <th><label class="text">终止时间：</label></th>
       <td  ><input type="text" name="end_time" maxlength="60" value=""  dataType="date" required="true" class="dateinput"/></td>
      </tr>
	<tr>
       <th><label class="text">促销活动描述</label></th>
       <td><textarea rows="5" cols="30" name="activity.brief"></textarea> </td>
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