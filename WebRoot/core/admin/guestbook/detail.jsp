<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<style>
.input table td {
	font-weight:normal;
}
</style>
<div class="input" >
<form class="validate" method="post" action="guestBook!reply.do" >
<input type="hidden" name="parentid" value="${book.id }" />
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table" >
     <tr> 
       <th >标题：</th>
       <td> ${book.title } 
	   </td>
     </tr>
      <tr> 
       <th >内容：</th>
       <td>${book.content } 
	   </td>
     </tr>
      <tr> 
       <th >用户名：</th>
       <td>${book.username } 
	   </td>
     </tr>
      <tr> 
       <th >ip：</th>
       <td>${book.ip} 
	   </td>
     </tr>
      <tr> 
       <th >地区：</th>
       <td>${book.area } 
	   </td>
     </tr>          
     <tr> 
       <th >提问时间：</th>
       <td><html:dateformat pattern="yyyy-MM-dd hh:mm:ss" time="${book.dateline*1000}"></html:dateformat>    
	   </td>
     </tr>     
   </table>
   
   <c:forEach items="${book.replyList}" var="reply">
   <hr></hr>
   
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table" >
     <tr> 
       <th >管理员：</th>
       <td> ${reply.content } 
	   </td>
     </tr>
      <tr> 
       <th ></th>
       <td><html:dateformat pattern="yyyy-MM-dd hh:mm:ss" time="${reply.dateline*1000}"></html:dateformat> 
	   </td>
     </tr>
    
   </table>
   </c:forEach>
   
    <table cellspacing="1" cellpadding="3" width="100%" class="form-table" >
     <tr> 
       <th >回复：</th>
       <td><textarea name="content" id="content" style="width:400px;height:150px"></textarea>
	   </td>
     </tr>  
   </table>  
   
	<div class="submitlist" align="center">
	 <table>
	 <tr><td >
	       <input name="submit" type="submit"  value="确定" class="submitBtn"/>
	   </td>
	   </tr>
	 </table>
	</div>   
	
   </form>
</div>