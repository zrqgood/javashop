<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="division">
<div class="grid">
<grid:grid from="listPointHistory">
	<grid:header>
		<grid:cell>日期</grid:cell>
		<grid:cell>摘要</grid:cell>
		<grid:cell>积分</grid:cell>
	</grid:header>
	<grid:body item="log">
		<grid:cell><html:dateformat pattern="yyyy-MM-dd HH:mm:ss" time="${log.time }"/></grid:cell>
		<grid:cell>${log.cnreason}</grid:cell>
		<grid:cell>${log.point}</grid:cell>
	</grid:body>
</grid:grid>
</div>
</div>