<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<form id="serchform">

<div style="clear:both;padding-top:5px;"></div>
<c:forEach items="${tagList }" var="tag">
<input type="checkbox" value="${tag.tag_id }" name="tagid"/>${tag.tag_name }&nbsp;&nbsp;
</c:forEach>

<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input name="btn" type="button" value=" 确    定   "  class="submitBtn" />
   </td>
   </tr>
 </table>
</div>	

</form>
