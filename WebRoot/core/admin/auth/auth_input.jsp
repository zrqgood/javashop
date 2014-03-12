<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="input" >
<form id="authForm" method="post">
<input type="hidden" name="isEdit" id="isEdit" value="${isEdit }" />
<input type="hidden" name="authid" value="${auth.actid}" />
<input type="hidden" id="objvalue" value="${auth.objvalue }" />
<table cellspacing="1" cellpadding="3" width="100%" class="form-table">
     <tr>
       <th><label class="text">权限名：</label></th>
       <td><input type="text" name="name" id="authname" maxlength="60" value="${auth.name}"  ></td>
      </tr>
     <tr>
       <th><label class="text">菜单：</label></th>
       <td> 
<div  id="menubox">
<ul class="checktree" >
</ul>
</div>

       </td>
      </tr>
</table>
<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input type="button" id="authBtn" value=" 确    定   " class="submitBtn" />
   </td>
   </tr>
 </table>
</div>
</form>
</div>