<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../commons/taglibs.jsp"%>

<param:append name="id" type="array" >  
</param:append>  

<shop:datasource type="detail" id="admin">
 <sql:append >
 	select count(0) as count from js_administrator
 </sql:append>
</shop:datasource>

<c:if test="${admin.count>1 }">
<sql:execute json="{'result':1,'message':'删除成功'}">
  <sql:append >
  delete  from js_administrator  where id in (${id })
 </sql:append> 
</sql:execute> 
</c:if>
<c:if test="${admin.count==1 }">{'result':2,'message':'不能删除此管理员'}</c:if>