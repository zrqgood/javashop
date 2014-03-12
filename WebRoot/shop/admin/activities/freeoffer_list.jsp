<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/FreeOffer.js"></script>

<div class="grid">

	<div class="toolbar">
	
		<ul>
			<li><a href="freeOffer!add.do">添加</a></li>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
			<li><a href="freeOffer!trash_list.do">回收站</a></li>
		</ul>
		
		<div style="clear:both"></div>
	</div>
<form method="POST">
<grid:grid  from="webpage">

	<grid:header>
	<grid:cell width="50px"><input type="checkbox" id="toggleChk" /></grid:cell>
	<grid:cell sort="fo_name" width="250px">赠品名称</grid:cell> 
	<grid:cell sort="cat_name">赠品分类</grid:cell>
	<grid:cell sort="startdate">起始时间</grid:cell> 
	<grid:cell sort="enddate">终止时间</grid:cell>
	<grid:cell  width="100px">操作</grid:cell> 
	</grid:header>

  <grid:body item="freeOffer">
  		<grid:cell><input type="checkbox" name="id" value="${freeOffer.fo_id }" />${freeOffer.fo_id } </grid:cell>
        <grid:cell>${freeOffer.fo_name } </grid:cell>
        <grid:cell>${freeOffer.cat_name} </grid:cell>
        <grid:cell><html:dateformat pattern="yyyy-MM-dd" time="${freeOffer.startdate }"></html:dateformat></grid:cell>  
        <grid:cell><html:dateformat pattern="yyyy-MM-dd" time="${freeOffer.enddate }"></html:dateformat></grid:cell>
        <grid:cell> <a href="freeOffer!edit.do?fo_id=${freeOffer.fo_id }" ><img class="modify" src="images/transparent.gif" ></a> </grid:cell> 
  </grid:body>  
  
</grid:grid>  
</form>	
</div>


