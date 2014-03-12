<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%> 
<table  border="0" cellspacing="0" cellpadding="2"  >
  <tr><td height="30" width="200">
     <select id="tag_id" name="tag_id" eop_type="widget_params" >
		<c:forEach items="${tagList}" var="tag">
			<option value="${tag.tag_id }" selected>${tag.tag_name }</option>
		</c:forEach>
    </select>
 </td>
 </tr>
</table>