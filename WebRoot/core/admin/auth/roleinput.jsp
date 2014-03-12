<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/jquery.checktree.js"></script>
<script type="text/javascript" src="../../admin/menu.do"></script>
<script type="text/javascript" src="js/Auth.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="auth/checktree.css" />
<div class="input">
<style>
#new_action{
cursor:pointer;
text-indent:-9999px; 
display:block;
}
#actionDlg th{
	width:60px;
	padding:0px;
}
#menubox{
width:250px;
height:300px;
overflow:auto;
}
#actbox {width:300px}
#actbox li{
	margin-top:5px;
	background-color:#F4F9FF; 
}
</style> 
<c:if test="${isEdit==1}">
<form method="post" action="role!saveEdit.do" class="validate"   id="brandForm" >
<input type="hidden" name="role.roleid" value="${role.roleid }" />

</c:if> 
<c:if test="${isEdit==0}">
<form method="post" action="role!saveAdd.do" class="validate"   id="brandForm" >
</c:if>

<table cellspacing="1" cellpadding="3" width="100%" class="form-table">
     <tr>
       <th><label class="text">角色名称：</label></th>
       <td><input type="text" name="role.rolename" id="rolename" maxlength="60" value="${role.rolename }"  dataType="string" required="true" /></td>
      </tr>
     <tr>
       <th><label class="text">描述：</label></th>
       <td> 
    	  <textarea name="role.rolememo" style="width:300px;height:150px;">${role.rolememo}</textarea>
       </td>
      </tr>
</table>
   
   
<table cellspacing="1" cellpadding="3" width="100%" class="form-table">
    <tr>
      <th><label class="text">权限选择：</label></th>
      <td>
		<div id="actbox">
			<div id="opbar"><span id="new_action" class="add">新增</span></div>
			<ul>
			<c:forEach items="${authList}" var="act">
				<li id="li_${act.actid }"><input type="checkbox" name="acts" value="${act.actid }" /><span>${act.name }</span>
					 <img class="modify" src="images/transparent.gif" authid="${act.actid }">&nbsp; <img authid="${act.actid }" class="delete" src="images/transparent.gif" >  
       
				</li>
			</c:forEach>
			</ul>
		</div>
	  </td>
     </tr>
</table>

   
<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input type="submit" name="submit"  value=" 确    定   " class="submitBtn" />
   </td>
   </tr>
 </table>
</div>
</form>
</div>
<div id="actionDlg"></div>
<script>

$(function(){
	$("form.validate").validate();
	<c:if test="${isEdit==1}">
		<c:forEach items="${role.actids}" var="actid">
			$("#actbox input[value=${actid}]").attr("checked",true);
		</c:forEach>
	</c:if>
	AuthAction.init();
	
});
</script>
