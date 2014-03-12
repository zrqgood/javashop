<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/commonlibs.jsp"%>
<script type="text/javascript">
$(function(){
	$("#btn").click(function(){
		Eop.Dialog.init({ width:500,height:400,resizable:false });
		Eop.Dialog.open('/core/admin/user/baseInfo.do');
	});
});
 
</script>
<div class="grid">
	<grid:grid from="webPage">
		<grid:header>
			<grid:cell width="100px">名称</grid:cell>
			<grid:cell width="150px">描述</grid:cell>
			<grid:cell  width="100px">作者</grid:cell>
			<grid:cell >操作</grid:cell>
		</grid:header>
		<grid:body item="app">
			<grid:cell>
			${ app.app_name }
			</grid:cell>
			<grid:cell>&nbsp;${app.descript } </grid:cell>
			<grid:cell>&nbsp;${app.author }</grid:cell>
			<grid:cell>&nbsp; <c:if test="${app.installedsiteid != defaultsiteid }"><a href="app!install.do?appid=${app.appid}">安装</a></c:if></grid:cell>
		</grid:body>
	</grid:grid>
</div>

	
<input type="button" id="btn" value="open" />