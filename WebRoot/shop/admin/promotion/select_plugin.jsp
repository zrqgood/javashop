<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="input">
	 <form method="post" action="promotion!select_condition.do" id="pmtform">
	   <input type="hidden" name="activityid" value="${activityid }"/>
	   <input type="hidden" name="pmtid" value="${pmtid }" />
	   <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	   	<c:forEach items="${pluginList}" var="plugin">
	      <tr>
	       <th width="50px"><input type="radio" name="pluginid" value="${plugin.id }" <c:if test="${pluginid==plugin.id }" >checked="checked"</c:if> /></td>
	       <td>${plugin.name }</td>
	      </tr>
	     </c:forEach> 
	   </table>
	   
	   
		<div class="submitlist" align="center">
		 <table>
		 <tr><td >
		  <input name="submit" type="submit" value=" 下一步   " class="submitBtn" />
		   </td>
		   </tr>
		 </table>
		</div>

	   
	 </form>
	 
</div>
<script>
$(function(){
	$("#pmtform").submit(function(){
		var result =false;
		$("#pmtform input[type=radio]").each(function(){
			if(this.checked) result=true;
		});
		if(!result)
		alert("请选择一种优惠方案");
		return result;
	});
});
</script>