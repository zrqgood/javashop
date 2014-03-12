<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/GoodsType.js"></script>
<script type="text/javascript">$(function(){GoodsType.init();});</script>
<div class="grid">

	<div class="toolbar">
	<ul>
		<li><a href="javascript:;" id="revertBtn">还原</a></li>
		<li><a href="javascript:;" id="cleanBtn">清除</a></li>
	</ul>
	<div style="clear:both"></div>
	</div>
<form>
<grid:grid  from="webpage" >

	<grid:header>
		<grid:cell  width="250px"><input type="checkbox" id="toggleChk" />&nbsp;类型名称</grid:cell> 
		<grid:cell sort="have_prop">拥有属性</grid:cell> 
		<grid:cell sort="have_parm">拥有参数</grid:cell> 
	</grid:header>

  <grid:body item="type">
        <grid:cell><input type="checkbox" name="id" value="${type.type_id }" />${type.name } </grid:cell>
        
        <grid:cell>
        <c:if test="${type.have_prop==1 }">有</c:if>
        <c:if test="${type.have_prop==0 }">否</c:if>
        </grid:cell> 
         
         
        <grid:cell>        
        <c:if test="${type.have_parm ==1 }">有</c:if>
        <c:if test="${type.have_parm ==0 }">否</c:if>
        </grid:cell> 
         
  </grid:body>  
  
</grid:grid>  
</form>
</div>


