<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/PrintTmpl.js"></script>

<div class="grid">

	<div class="toolbar">
	
		<ul>
			<li><a href="javascript:;" id="revertBtn">还原</a></li>
			<li><a href="javascript:;" id="cleanBtn">清除</a></li>
		</ul>
		
		<div style="clear:both"></div>
	</div>
<form method="POST" >
<grid:grid  from="trash">

	<grid:header>
	<grid:cell width="50px"><input type="checkbox" id="toggleChk" /></grid:cell>
	<grid:cell width="250px">模板名称</grid:cell> 
	<grid:cell sort="url">是否启用</grid:cell> 
	<grid:cell  width="100px">操作</grid:cell> 
	</grid:header>

  <grid:body item="tmpl">
  		<grid:cell><input type="checkbox" name="id" value="${tmpl.prt_tmpl_id }" />${tmpl.prt_tmpl_id } </grid:cell>
        <grid:cell>${tmpl.prt_tmpl_title } </grid:cell>
        <grid:cell><c:if test="${tmpl.shortcut=='true' }">是</c:if><c:if test="${tmpl.shortcut=='false' }">否</c:if></grid:cell> 
        <grid:cell> <a href="printTmpl!edit.do?prt_tmpl_id=${tmpl.prt_tmpl_id }" ><img class="modify" src="images/transparent.gif"/></a> </grid:cell> 
  </grid:body>  
  
</grid:grid>  
</form>	
<div style="clear:both;padding-top:5px;"></div>
</div>

