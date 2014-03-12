<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/Regions.js"></script>
<style>
.imgTree{
cursor:pointer;
}
</style>
<div class="grid">
<div class="toolbar">
		<ul>
			<li><a href="region!add.do?parentid=0&regiongrade=1">添加</a></li>
			<li><a href="javascript:;" id="resetBtn" >初始化</a></li>
		</ul>
		
		<div style="clear:both"></div>
	</div>
<table>
    
<col class="Colauto">
<col class="Coloption_1b">
<col class="Coloption_1b">
<col class="Coloption_1b">
<col>
	<thead>
		<tr>
		<th>地区名称</th>
		<th>添加子地区</th>
		<th>编辑</th>
		<th>删除</th>
		<th></th>
		</tr>
	</thead>
	<tbody id="regionMain">
		
	</tbody>
</table>

</div>