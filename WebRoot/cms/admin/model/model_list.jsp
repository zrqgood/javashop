<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/datamodel.js"></script>

<div class="grid">
	<div class="toolbar">
		<ul>
			<li><a href="model!add.do">添加</a></li>
		</ul>
		<div style="clear:both"></div>
	</div>
<form method="POST" >
<grid:grid  from="modelList">

	<grid:header>
	<grid:cell width="50px">id</grid:cell>
	<grid:cell  width="250px">模型名称</grid:cell> 
	<grid:cell > 表名</grid:cell> 
	<grid:cell  width="100px">修改</grid:cell> 
	<grid:cell  width="100px">删除</grid:cell> 
	</grid:header>

  <grid:body item="model">
  		<grid:cell>${model.model_id } </grid:cell>
        <grid:cell>${model.china_name} </grid:cell>
        <grid:cell>${model.english_name} </grid:cell> 
        <grid:cell> <a  href="model!edit.do?modelid=${model.model_id }" ><img class="modify" src="images/transparent.gif" ></a> </grid:cell> 
 		<grid:cell> <a  href="javascript:;" modelid="${model.model_id }"><img class="delete" src="images/transparent.gif" ></a> </grid:cell>         
  </grid:body>  
  
</grid:grid>  
</form>	
<div style="clear:both;padding-top:5px;"></div>
</div>
<script>
$(function(){
	DataModel.init();
});
</script>