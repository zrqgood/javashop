<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
 <script type="text/javascript" src="js/Comments.js"></script>
<div class="grid">
	<div class="toolbar">
		
		<ul>
			<li><a href="javascript:;" id="revertBtn">还原</a></li>
			<li><a href="javascript:;" id="cleanBtn">清除</a></li>
			<li><a href="comments!bglist.do?object_type=${object_type }" >返回列表</a></li>
		</ul>
		
	<div style="clear:both"></div>
	</div>

<form method="POST">
<grid:grid  from="webpage">

	<grid:header>
	<grid:cell width="50px"><input type="checkbox" id="toggleChk" /></grid:cell>
	<grid:cell width="250px">标题</grid:cell> 
	<grid:cell >商品ID</grid:cell>
	<grid:cell >评论人</grid:cell> 
	<grid:cell >评论时间</grid:cell>
	<grid:cell  width="100px">操作</grid:cell> 
	</grid:header>

  <grid:body item="comments">
  		<grid:cell><input type="checkbox" name="id" value="${comments.comment_id }" />${comments.comment_id } </grid:cell>
        <grid:cell>${comments.title } </grid:cell>
        <grid:cell>${comments.object_id} </grid:cell>
        <grid:cell>${comments.author }</grid:cell>  
        <grid:cell><html:dateformat pattern="yyyy-MM-dd" time="${comments.time }"></html:dateformat></grid:cell>
        <grid:cell><a href="comments!detail.do?comment_id=${comments.comment_id}"  ><img class="modify" src="images/transparent.gif" ></a> </grid:cell> 
  </grid:body>  
  
</grid:grid>  
</form>	
</div>
 
