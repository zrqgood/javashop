<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="input" >
<form class="validate" method="post" action="ask!adminReply.do" >
<input type="hidden" name="askid" value="${ask.askid }" />
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table" >
     <tr> 
       <th >标题：</th>
       <td> ${ask.title } 
	   </td>
     </tr>
      <tr> 
       <th >内容：</th>
       <td>${ask.content } 
	   </td>
     </tr>
      <tr> 
       <th >提问人：</th>
       <td>${ask.username } 
	   </td>
     </tr>
     <tr> 
       <th >提问时间：</th>
       <td><html:dateformat pattern="yyyy-MM-dd hh:mm:ss" time="${ask.dateline*1000}"></html:dateformat>    
	   </td>
     </tr>     
   </table>
   
   <c:forEach items="${ask.replyList}" var="reply">
   <hr></hr>
   
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table" >
     <tr> 
       <th >${reply.username}：</th>
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
       <th >回答：</th>
       <td><textarea name="content" id="content"></textarea>
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
<script type="text/javascript">
$(function(){
	$('#content' ).ckeditor( );	
});
</script>