<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" >
loadScript("js/Spec.js");
</script>

<div class="grid">
 
	<div class="toolbar">
	
		<ul>
			<li><a href="spec!add.do">添加</a></li>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
			<li>&nbsp;&nbsp;<span class="help_icon" helpid="spec"></span></li>
		</ul>
		
		<div style="clear:both"></div>
	</div>
<form method="POST" >
<grid:grid  from="specList" pager="no">

	<grid:header>
	<grid:cell width="50px"><input type="checkbox" id="toggleChk" /></grid:cell>
	<grid:cell  width="250px">规格名称</grid:cell> 
	<grid:cell>类型</grid:cell> 
	<grid:cell  width="100px">操作</grid:cell> 
	</grid:header>

  <grid:body item="spec">
  		<grid:cell><input type="checkbox" name="id" value="${spec.spec_id }" />${spec.spec_id } </grid:cell>
        <grid:cell>${spec.spec_name }<c:if test="${spec.spec_memo!=null && spec.spec_memo!=''}" >[${spec.spec_memo }]</c:if></grid:cell>
        <grid:cell> <c:if test="${spec.spec_type==0}">文字</c:if> <c:if test="${spec.spec_type==1}">图片</c:if></grid:cell> 
        <grid:cell> <a   href="spec!edit.do?spec_id=${spec.spec_id }" ><img class="modify" src="${staticserver }/images/transparent.gif" ></a> </grid:cell> 
  </grid:body>  
  
</grid:grid>  
</form>	
<div style="clear:both;padding-top:5px;"></div>
</div>
