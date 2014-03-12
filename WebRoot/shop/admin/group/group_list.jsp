<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/Brand.js"></script>

<div class="grid">

	<div class="toolbar">
		<ul>
			<li><a href="groupBuy!add.do">添加</a></li>
		</ul>
		<div style="clear:both"></div>
	</div>
<form method="POST" >
<grid:grid  from="webpage">

	<grid:header>
	<grid:cell width="50px">id</grid:cell>
	<grid:cell width="250px">团购标题</grid:cell> 
	<grid:cell  width="100px">购买人数</grid:cell>
	<grid:cell  width="250px">首页显示</grid:cell>
	<grid:cell >操作</grid:cell> 
	</grid:header>

  <grid:body item="group">
  		<grid:cell>${group.groupid } </grid:cell>
        <grid:cell>${group.title } </grid:cell>
         <grid:cell>${group.buy_count}</grid:cell> 
         
       <grid:cell>
  		
  		<c:if test="${group.is_index ==1}" > 
  			是
  		</c:if> 
  		<c:if test="${group.is_index ==0}" > 
  			否
  		</c:if>
  		</grid:cell>
  		
  		
        <grid:cell> <a  href="groupBuy!edit.do?groupid=${group.groupid }" ><img class="modify" src="images/transparent.gif" ></a>  
        &nbsp;&nbsp; <a onclick="javascript:return confirm('确认删除此团购吗');"  href="groupBuy!delete.do?groupid=${group.groupid }" ><img class="delete" src="images/transparent.gif" ></a> </grid:cell>
  </grid:body>  
  
</grid:grid>  
</form>	
<div style="clear:both;padding-top:5px;"></div>
</div>