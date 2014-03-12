<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
 <%@ include file="/commons/taglibs.jsp"%>
<tr>
	<td width="10%" style="">
	<input type="hidden" name="specvalues" specid="${spec.spec_id }" specvalue="${spec.spec_value }" specvid="${spec.spec_value_id }" spectype="${spec.spec_type }" specimage="${spec.spec_image }" /> 
	<c:if test="${spec.spec_type==0}" >
	
	<span class="spec_span" >${spec.spec_value }</span>
	</c:if>
	
	<c:if test="${spec.spec_type==1}">
	<div class="spec_box" ><center><img height="20" width="20" alt="${ spec.spec_value}" src="${spec.spec_image}"></center></div>
	</c:if>
	</td>

	<td><span ></span><a href="javascript:;" class="join">关联</a></td>
	<td><a href="javascript:;" ><img src="images/transparent.gif" class="delete"/></a></td>
</tr>  