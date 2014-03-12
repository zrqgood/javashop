<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<div class="grid">

	<div class="toolbar">
	
		<ul>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
		</ul>
		
		<div style="clear:both"></div>
	</div>
<form method="POST" >
<grid:grid  from="webpage">

	<grid:header>
	<grid:cell width="50px"><input type="checkbox" id="toggleChk" /></grid:cell>
	<grid:cell  width="250px">标题</grid:cell> 
	<grid:cell >用户名</grid:cell> 
	<grid:cell >ip</grid:cell> 
	<grid:cell >地区</grid:cell> 
	<grid:cell  width="100px">操作</grid:cell> 
	</grid:header>

  <grid:body item="subject">
  		<grid:cell><input type="checkbox" name="idArray" value="${subject.id }" />${subject.id } </grid:cell>
        <grid:cell>
        ${subject.title }
        </grid:cell>
        <grid:cell>${subject.username}</grid:cell> 
        <grid:cell>${subject.ip}</grid:cell> 
        <grid:cell>${subject.area}</grid:cell> 
        <grid:cell> <a  href="guestBook!detail.do?id=${subject.id }" >查看/回复</a> </grid:cell> 
  </grid:body>  
  
</grid:grid>  
</form>	
<div style="clear:both;padding-top:5px;"></div>
</div>
<script>
var GuestBook=$.extend({},Eop.Grid,{
	init:function(){
		var self =this;
		$("#delBtn").click(function(){self.doDelete();});
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);		
	},	
	doDelete:function(){
		if(!this.checkIdSeled()){
			alert("请选择要删除的留言");
			return ;
		}
	 
		if(!confirm("确认要将这些留言删除吗？删除后将不可恢复")){	
			return ;
		}
		this.deletePost("guestBook!delete.do");
	}	
});
$(function(){
	GuestBook.opation('idChkName',"idArray" );
	GuestBook.init();
});
</script>
