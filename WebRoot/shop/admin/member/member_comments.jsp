<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="grid">
<grid:grid  from="listComments">

	<grid:header>
	<grid:cell width="150px">标题</grid:cell> 
	<grid:cell width="400px">内容</grid:cell> 
	<grid:cell >发表时间</grid:cell>
	<grid:cell >显示状态</grid:cell> 
	</grid:header>

  <grid:body item="comments">
        <grid:cell>${comments.title } </grid:cell>
        <grid:cell>${comments.comment }</grid:cell>  
        <grid:cell><html:dateformat pattern="yyyy-MM-dd" time="${comments.time }"></html:dateformat></grid:cell>
        <grid:cell > 
        	<c:if test="${comments.display == 'true' }">已显示</c:if>
        	<c:if test="${comments.display == 'false' }">已隐藏</c:if>
        </grid:cell>
  </grid:body>  
  
</grid:grid>
</div>