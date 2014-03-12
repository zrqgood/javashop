<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<div class="input">
 <form class="validate" method="post" action="friendsLink!addSave.do" name="theForm" id="theForm" enctype="multipart/form-data">
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
     <tr>
       <td  class="label"><label class="text">友情链接名称：</label></td>
       <td  ><input type="text" name="friendsLink.name" maxlength="60"  dataType="string" required="true" />       </td>
      </tr>
      <tr>
       <td  class="label"><label class="text">友情链接地址：</label></td>
       <td  ><input type="text" name="friendsLink.url" maxlength="60"  dataType="string" required="true" /></td>
      </tr>
      <tr>
       <td  class="label"><label class="text">排序：</label></td>
       <td  ><input type="text" name="friendsLink.sort" maxlength="60" dataType="int"  /></td>
      </tr>
      <tr>
       <td  class="label"><label class="text">友情链接图片：</label></td>
       <td><input type="file" name="pic" id="pic" size="45"/>
           <span class="notice-span"  id="warn_pic">请上传图片</span></td>
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