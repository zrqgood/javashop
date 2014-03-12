<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/FriendsLink.js"></script>

<div class="grid">
	<div class="toolbar">
		<ul>
			<li><a href="friendsLink!add.do">添加</a></li>
		</ul>
		<div style="clear:both"></div>
	</div>
<form method="POST">
<grid:grid from="listLink">
	<grid:header>
	<grid:cell width="50px"><input type="checkbox" id="toggleChk" /></grid:cell>
	<grid:cell width="250px">友情链接名称</grid:cell> 
	<grid:cell>友情链接地址</grid:cell>
	<grid:cell>排序</grid:cell> 
	<grid:cell width="100px">操作</grid:cell> 
	</grid:header>

  <grid:body item="link">
  		<grid:cell><input type="checkbox" name="id" value="${link.link_id }" />${link.link_id } </grid:cell>
        <grid:cell>${link.name } </grid:cell>
        <grid:cell>${link.url} </grid:cell>
        <grid:cell>${link.sort }</grid:cell>  
        <grid:cell>
			<a href="friendsLink!edit.do?link_id=${link.link_id }" ><img class="modify" src="images/transparent.gif" ></a> 
			<a href="friendsLink!delete.do?link_id=${link.link_id }" linkid="${link.link_id}"><img class="delete" src="images/transparent.gif" ></a>
 		</grid:cell>					
  </grid:body>  
  
</grid:grid>  
</form>	
</div>
<script type="text/javascript">
$(function(){
	$(".delete").parent().click(function(){	
		if( !confirm("确定删除此链接吗?") ){
			return false;
		}
		var id = $(this).attr("linkid");
		$.Loading.show('正在删除，请稍侯...');
		$.ajax({
			url:'friendsLink!delete.do?id='+id+'&ajax=yes',
			type:'get',
			dataType:'json',
			success:function(result){
				$.Loading.hide();
				if(result.result==0){
					alert("删除成功");
					location.reload();
				}else{
					alert(result.message);
				}						
			},
			error:function(){
				alert("删除出现错误");
			}	
		});
		return false;
	});
});
</script>

