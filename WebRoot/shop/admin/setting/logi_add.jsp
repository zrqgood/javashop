<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<div class="input">
 <form method="post" action="logi!saveAdd.do" name="theForm" id="theForm"  >
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
     <tr>
       <td width="15%" class="label">物流公司名称</td>
       <td width="27%"><input type="text" name="name" id="name" maxlength="60" value="" />       </td>
       <td width="58%" align="left"><div id="nameTip" style="width:80px;"></div></td>
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
 
