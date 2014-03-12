<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<script type="text/javascript" src="js/Tag.js"></script>
<div class="input">
 <form class="validate" method="post" action="tag!saveAdd.do"   >
   <table cellspacing="1" cellpadding="3" width="100%">
     <tr>
       <th><label class="text">标签名称</label></th>
       <td ><input type="text" name="tag.tag_name" id="name" maxlength="60" value="" dataType="string" required="true"/>       </td>
       <td  align="left"><div id="nameTip" style="width:80px;"></div></td>
     </tr>
     
   </table>
   <div class="submitlist" align="center">
 <table>
 <tr><td >
  <input  type="button"	  value=" 确    定   " class="submitBtn" />
   </td>
   </tr>
 </table>
</div>
 </form>
</div>
<script type="text/javascript">
$(function(){
	$("form.validate").validate();
	Tag.intChkNameEvent();
});
</script>