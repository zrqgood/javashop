<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/grid.tld" prefix="grid" %>
<div class="grid">
	<div id="addDialog" style="display:none">我的对话框内容</div>
	<div class="toolbar">
		<ul>
			<li><a id="btnAdd" href="#">添加</a></li>
			<li><a id="btnDel" href="#">修改</a></li>
			<li><a href="#">删除</a></li>
			<li>
				<form action="example.do" method="post">
					<label style="float:left;line-height:20px;">关键字：</label>
					<input name="search" type="text" style="float:left;"></input>
					<input type="submit" value="查询"/>
					<select name="area">
						<option value="0">测试弹出对话框用</option>
						<option value="1">北京</option>
						<option value="2">天津</option>
					</select>
				</form>
			</li>
		</ul>
	</div>
	<grid:grid from="webPage">
		<grid:header>
			<grid:cell sort="goods_id" width="100px">id</grid:cell>
			<grid:cell sort="name" width="300px">用户名</grid:cell>
			<grid:cell>地址</grid:cell>
		</grid:header>
		<grid:body item="goods">
			<grid:cell><input type="checkbox" name="id" value="${goods.goods_id }" />${goods.goods_id }</grid:cell>
			<grid:cell>&nbsp;${goods.name } </grid:cell>
			<grid:cell>&nbsp;${goods.sn } <a href="javascript:deleteUser();" >delete</a></grid:cell>
		</grid:body>
	</grid:grid>
</div>
<script type="text/javascript">
$(function(){
	$("#btnAdd").click(function(){
		enation.control.dialog('dlgAdd',
			{
				title:'新增域名',
				url:'/eop_test2/admin/core/user/baseInfo.do',
				resizable:true,
				bgiframe:true,
				width:800,
				height:600,
				modal:true
			}
		);
	});
	
	$("#btnDel").click(function(){
		enation.control.dialog('dlgDel',
			{
				title:'删除',
				url:'/eop_test2/admin/core/user/userDetail.do',
				width:500,
				height:500
			}
		);
	});
});
</script>