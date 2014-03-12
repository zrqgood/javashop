<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/commons/taglibs.jsp"%>
<div class="grid">

<table>

	<thead><tr>
		<th >报表</th> 
 
	</tr></thead>

  <tbody>
  <c:forEach items="${linkList}" var="link">
  <tr>
  
        <td><a href="${link.link}" target="_blank">${link.text }</a> </td>
 
  </tr>
  </c:forEach>
  </tbody>  
  </table>


<div style="clear:both;padding-top:5px;"></div>
</div>