<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
 
<div class="input">
 <form method="post" action="logi!saveEdit.do" name="theForm" id="theForm"  >
 <input type="hidden" name="cid" value="${logi.id }"/>
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
     <tr>
       <td width="29%" class="label">物流公司名称</td>
       <td width="13%"><input type="text" name="name" id="name" maxlength="60" value="${logi.name }" />       </td>
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
 