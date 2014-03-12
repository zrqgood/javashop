<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/Comments.js"></script>

<div class="grid">

	<div class="toolbar">
	
		<ul>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
			<li><a href="comments!trashlist.do?object_type=${object_type }">回收站</a></li>
		</ul>
		
		<div style="clear:both"></div>
	</div>
<form method="POST">
<grid:grid  from="webpage">

	<grid:header>
	<grid:cell width="50px"><input type="checkbox" id="toggleChk" /></grid:cell>
	<grid:cell width="250px">标题</grid:cell> 
	<grid:cell >对象名称</grid:cell>
	<grid:cell ><c:if test="${object_type == 'discuss'}">评论</c:if><c:if test="${object_type == 'ask'}">咨询</c:if><c:if test="${object_type == 'leavewords'}">留言</c:if>人</grid:cell> 
	<grid:cell >发表时间</grid:cell>
	<grid:cell >显示状态</grid:cell> 
	<grid:cell  width="200px">操作</grid:cell>
	</grid:header>

  <grid:body item="comments">
  		<grid:cell><input type="checkbox" name="id" value="${comments.comment_id }" />${comments.comment_id } </grid:cell>
        <grid:cell>${comments.title } </grid:cell>
        <grid:cell ><c:if test="${object_type != 'leavewords'}"><a href="../../${comments.commenttype }-${comments.object_id }.html" target="_blank">${comments.name}</a></c:if><c:if test="${object_type == 'leavewords'}">客户留言</c:if> </grid:cell>
        <grid:cell>${comments.author }</grid:cell>  
        <grid:cell><html:dateformat pattern="yyyy-MM-dd" time="${comments.time }"></html:dateformat></grid:cell>
        <grid:cell > 
        	<c:if test="${comments.display == 'true' }">已显示&nbsp;<input type="button" class="chide" comment_id="${comments.comment_id }" value="隐藏" /></c:if>
        	<c:if test="${comments.display == 'false' }">已隐藏&nbsp;<input type="button" class="cshow" comment_id="${comments.comment_id }" value="显示" /></c:if>
        </grid:cell>
        <grid:cell><a href="comments!detail.do?comment_id=${comments.comment_id}"   ><img class="modify" src="images/transparent.gif" ></a></grid:cell> 
  </grid:body>  
  
</grid:grid>  
</form>	
</div>


