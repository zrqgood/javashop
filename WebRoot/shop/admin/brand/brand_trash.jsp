<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
 <script type="text/javascript" src="js/Brand.js"></script>
<div class="grid">
	<div class="toolbar">
		
		<ul>
			<li><a href="javascript:;" id="revertBtn">还原</a></li>
			<li><a href="javascript:;" id="cleanBtn">清除</a></li>
		</ul>
		
	<div style="clear:both"></div>
	</div>

<form method="post">
	
<grid:grid  from="webpage">

	<grid:header>
	<grid:cell width="50px"><input type="checkbox"  id="toggleChk"  /></grid:cell> 
	<grid:cell sort="name" width="250px">品牌名称</grid:cell> 
	<grid:cell sort="url">品牌网址</grid:cell> 
	</grid:header>

  <grid:body item="brand">
  	<grid:cell><input type="checkbox" name="id" value="${brand.brand_id }" />${brand.brand_id} </grid:cell> 
    <grid:cell>${brand.name } </grid:cell>
    <grid:cell>${brand.url} </grid:cell> 
  </grid:body>  
  
</grid:grid>  
</form>	
<div style="clear:both;padding-top:5px;"></div>
</div>
 
