<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.enation.eop.sdk.context.EopSetting" %>
<%@ include file="header.jsp" %>
 
<h5>系统安装成功!以下是您的商店系统管理员帐户信息：</h5>
<table width="300px;">
<tbody><tr>
    <th width="60">用户名:</th>
    <td width="228">${uname }</td>
  </tr>
  <tr>
    <th>密&nbsp;&nbsp;&nbsp;&nbsp;码:</th>
    <td>${pwd }</td>
  </tr>
</tbody></table>
<br>
<%if( ("2").equals(EopSetting.RUNMODE) ) {%>
<a target="_blank" href="http://${domain }:<%=request.getServerPort()%><%=request.getContextPath() %>/index.html">点此查看网站前台</a>
<br>
<br>
<a target="_blank" href="http://${domain }:<%=request.getServerPort()%><%=request.getContextPath() %>/admin">点此登录商店系统后台管理</a>
<%}else{ %>
<a target="_blank" href="../index.html">点此查看网站前台</a>
<br>
<br>
<a target="_blank" href="../admin">点此登录商店系统后台管理</a>
<%} %>
<br> 
<jsp:include page="footer.jsp"></jsp:include>