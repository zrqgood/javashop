<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<div    class='success'>
	<c:forEach var="msg" items="${msgs}">
	  <h4>${msg }</h4>
	</c:forEach>     
  <div style="clear:both;"> 
  <c:forEach var="url" items="${urls}">
        <p>
        
        <c:if test="${target!=null}">
    <a href="${url.value}" target="${target }" >
    	返回${url.key}  </a>
    </c:if>
    	
    <c:if test="${target==null}">
    <a href="${url.value}" target="_self" >
    	返回${url.key}  </a>
    </c:if>
    </p>
    </c:forEach>
   </div>
</div>
