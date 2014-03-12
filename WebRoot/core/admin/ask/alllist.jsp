<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="grid">
<form action="ask!listAll.do" method="get">
	<div class="toolbar">
		<ul>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
		</ul>
		<div>
		             关键字:<input type="text" name="keyword" value="${keyword }"/>&nbsp;&nbsp;
		             日期：由<input type="text" name="startTime" readonly class="dateinput" value="${startTime }"/>
		                            至<input type="text" name="endTime" readonly class="dateinput" value="${endTime }"/><input type="submit" value="搜索" name="submit" />
		 </div>
		<div style="clear:both"></div>
	</div>
</form>	
<form method="POST" >
<grid:grid  from="webpage">

	<grid:header>
		<grid:cell width="50">id<input type="checkbox" id="toggleChk" /></grid:cell> 
		<grid:cell width="200">问题</grid:cell> 
		<grid:cell width="150">提问人</grid:cell> 
		<grid:cell width="150">站点</grid:cell>
		<grid:cell width="200">提问时间</grid:cell>
		 <grid:cell>操作</grid:cell>
	</grid:header>

  <grid:body item="ask">
        <grid:cell>${ask.askid}<input type="checkbox" value="${ask.askid }"  name="id" /> </grid:cell>
        <grid:cell><c:if test="${ask.isreply==0}" ><b>${ask.title}</b></c:if> <c:if test="${ask.isreply==1}" >${ask.title}</c:if> </grid:cell>
        <grid:cell>${ask.username} </grid:cell> 
        <grid:cell>${ask.sitename} </grid:cell> 
        <grid:cell><html:dateformat pattern="yyyy-MM-dd hh:mm:ss" time="${ask.dateline*1000}"></html:dateformat></grid:cell>
        <grid:cell><a href="ask!adminview.do?askid=${ask.askid }">查看/回复</a></grid:cell>
  </grid:body> 
  
</grid:grid>  
</form>	 
<div style="clear:both;padding-top:5px;"></div>
</div>
<script>
var Ask =$.extend({},Eop.Grid,{
	init:function(){
		var self = this;
		$("#delBtn").click(function(){
			self.doDelete();
		});

		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);		
	},	
	doDelete:function(){
		if(!this.checkIdSeled()){
			alert("请选择要删除的问题");
			return ;
		}
	 
		if(!confirm("确认要将这些问题彻底删除吗？删除后将不可恢复")){	
			return ;
		}
		this.deletePost("ask!delete.do");
	}	
});

$(function(){
	Ask.init();
});
</script>