<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<div class="grid">
	<div class="toolbar">
		<ul>
			<li><a href="limitBuy!add.do">添加</a></li>
		</ul>
		<div style="clear:both"></div>
	</div>
<form method="POST" >
<grid:grid  from="webpage">

	<grid:header>
	<grid:cell width="50px">id</grid:cell>
	<grid:cell width="250px">标题</grid:cell> 
	<grid:cell width="250px">首页显示</grid:cell> 
	<grid:cell >操作</grid:cell> 
	</grid:header>

  <grid:body item="limitBuy">
  		<grid:cell>${limitBuy.id} </grid:cell>
  		<grid:cell>${limitBuy.name } </grid:cell>
  		<grid:cell>
  		
  		<c:if test="${limitBuy.is_index ==1}" > 
  			是
  		</c:if> 
  		<c:if test="${limitBuy.is_index ==0}" > 
  			否
  		</c:if>
  		</grid:cell>
        <grid:cell> <a  href="limitBuy!edit.do?id=${limitBuy.id }" ><img class="modify" src="images/transparent.gif" ></a>  
        &nbsp;&nbsp; <a onclick="javascript:return confirm('确认删除此限时抢购吗');"  href="limitBuy!delete.do?id=${limitBuy.id }" ><img class="delete" src="images/transparent.gif" ></a> </grid:cell>
  </grid:body>  
  
</grid:grid>  
</form>	
<div style="clear:both;padding-top:5px;"></div>
</div>