<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<tr >
	<td colspan="6">
		<table style="margin-left:10px">
		 <c:forEach items="${accessList}" var="access">
		  <tr>
		   <td width="100">
  		 <c:if test="${access.membername!=null}">
  		 	${access.membername}
  		 </c:if>
  		 <c:if test="${access.membername==null}">
  		 	访客/未登录
  		 </c:if>  		 
  		 </td>
	   		  <td width="120">
		          <span style="margin-left:15px">${access.ip}</span> 
		       </td>
			  <td width="220"><html:dateformat pattern="yyyy-MM-dd HH:mm:ss" time="${access.access_time*1000}"></html:dateformat> </td> 
		      		       
		        <td  width="150">${access.area} </td> 
		        <td width="150">${access.page} </td> 
		        <td  width="100"><html:dateformat pattern="HH:mm:ss" time="${access.stay_time *1000-8*60*60*1000}"></html:dateformat></td> 
		      
		      <c:if test="${runmodel==2}">
		     	 <td>${access.point}</td>
		      </c:if>
		      
		  </tr>
		  </c:forEach> 
		 </table>
	</td>
</tr>