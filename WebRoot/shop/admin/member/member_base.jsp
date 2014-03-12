<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<div class="division clearfix">
<table class="form-table">
	<tr>
		<th>用户姓名：</th><td>${member.name }</td>
		<th>当前积分：</th><td>${member.point }</td>
		<th>当前预存款：</td><td><fmt:formatNumber value="${member.advance }" type="currency" /></td>
	</tr>
	<tr>
		<th>地区：</th><td>${member.province }-${member.city }-${member.region }</td>
		<th>E-mail：</th><td>${member.email }</td>
		<th>固定电话：</td><td>${member.tel }</td>
	</tr>
	<tr>
		<th>手机：</th><td>${member.mobile }</td>
		<th>邮编：</th><td>${member.zip }</td>
		<th>性别：</th><td><c:if test="${member.sex == 0 }">女</c:if><c:if test="${member.sex == 1 }">男</c:if></td>
	</tr>
	<tr>
		<th>出生日期：</td><td><html:dateformat pattern="yyyy-MM-dd HH:mm:ss" time="${member.birthday }"/></td>
		<th>注册日期：</td><td><html:dateformat pattern="yyyy-MM-dd HH:mm:ss" time="${member.regtime }"/></td>
		<th>QQ：</th><td>${member.qq }</td>
	</tr>
	<tr>
		<th>Msn：</th><td>${member.msn }</td>
		<th>地址：</th><td colspan="3">${member.address }</td>
	</tr>
	<tr>
		<th>备注：</th><td colspan="5">${member.remark }</td>
	</tr>
</table>

</div>
