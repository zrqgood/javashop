 <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>

 <form method="post" action="area!saveAdd.do" name="theForm" id="theForm"  >
 <div class="main-div">
   <table cellspacing="1" cellpadding="3" width="100%">
     <tr>
       <td width="15%" class="label">地区名称</td>
       <td width="27%"><input type="text" name="name" id="name" maxlength="60" value="" />       </td>
       <td width="58%" align="left"><div id="nameTip" style="width:80px;"></div></td>
     </tr>
     <tr>
       <td colspan="3" align="center"><br />
           <input name="submit" type="submit" class="button" value=" 确定 " class="btn"/>
           <input name="reset" type="reset" class="button" value=" 重置 " class="btn"/>       </td>
     </tr>
   </table>
   </div>
 </form>

 