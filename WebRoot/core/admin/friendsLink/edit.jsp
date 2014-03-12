<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<div class="input">
 <form class="validate" method="post" action="friendsLink!editSave.do" name="theForm" id="theForm" enctype="multipart/form-data">
 <input type="hidden" name="friendsLink.link_id" value="${friendsLink.link_id }" />
  <input type="hidden" name="oldpic" value="${friendsLink.logo }" />
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
     <tr>
       <th><label class="text">友情链接名称：</label></th>
       <td  ><input type="text" name="friendsLink.name" maxlength="60" value="${friendsLink.name }"  dataType="string" required="true" />       </td>
      </tr>
      <tr>
       <th><label class="text">友情链接地址：</label></th>
       <td  ><input type="text" name="friendsLink.url" maxlength="60" value="${friendsLink.url }"  dataType="string" required="true" /></td>
      </tr>
      <tr>
       <th><label class="text">排序：</label></th>
       <td  ><input type="text" name="friendsLink.sort" maxlength="60" value="${friendsLink.sort }"  dataType="int" /></td>
      </tr>
      <tr>
       <th><label class="text">友情链接图片：</label></th>
       <td><input type="file" name="pic" id="pic" size="45"/>
           <span class="notice-span"  id="warn_pic">请上传图片</span></td>
      </tr>
      <c:if test="${friendsLink.logo!=null }">
	     <tr>
	       <th>&nbsp;</td>
	       <td> 
	       <img src="${friendsLink.logo }" />	       </td>
	     </tr> 
     </c:if>
 
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