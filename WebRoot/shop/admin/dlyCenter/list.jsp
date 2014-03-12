<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/DlyCenter.js"></script>

<div class="grid">

	<div class="toolbar">
	
		<ul>
			<li><a href="dlyCenter!add.do">添加</a></li>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
		</ul>
		
		<div style="clear:both"></div>
	</div>
<form method="POST" >
<grid:grid  from="list">

	<grid:header>
	<grid:cell width="50px"><input type="checkbox" id="toggleChk" /></grid:cell>
	<grid:cell sort="name" width="250px">发货点名称</grid:cell> 
	<grid:cell >地区</grid:cell> 
	<grid:cell >地址</grid:cell> 
	<grid:cell >邮编</grid:cell> 
	<grid:cell >电话</grid:cell> 
	<grid:cell >发货人姓名</grid:cell>
	<grid:cell  width="100px">操作</grid:cell> 
	</grid:header>

  <grid:body item="dlyCenter">
  		<grid:cell><input type="checkbox" name="id" value="${dlyCenter.dly_center_id }" />${dlyCenter.dly_center_id } </grid:cell>
        <grid:cell>${dlyCenter.name } </grid:cell>
        <grid:cell>${dlyCenter.province}-${dlyCenter.city}-${dlyCenter.region}</grid:cell> 
        <grid:cell>${dlyCenter.address}</grid:cell> 
        <grid:cell>${dlyCenter.zip}</grid:cell> 
        <grid:cell>${dlyCenter.cellphone}</grid:cell> 
        <grid:cell>${dlyCenter.uname}</grid:cell> 
        <grid:cell> <a  href="dlyCenter!edit.do?dlyCenterId=${dlyCenter.dly_center_id }" ><img class="modify" src="images/transparent.gif" ></a> </grid:cell> 
  </grid:body>  
  
</grid:grid>  
</form>	
<div style="clear:both;padding-top:5px;"></div>
</div>

