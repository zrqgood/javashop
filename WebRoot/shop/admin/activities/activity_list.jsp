<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/Activity.js"></script>

<div class="grid">

	<div class="toolbar">
	
		<ul>
			<li><a href="activity!add.do">添加</a></li>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
		</ul>
		
		<div style="clear:both"></div>
	</div>
<form method="POST">
<grid:grid  from="webpage">

	<grid:header>
	<grid:cell width="50px"><input type="checkbox" id="toggleChk" /></grid:cell>
	<grid:cell sort="name" width="250px">活动名称</grid:cell> 
	<grid:cell sort="enable">是否开启</grid:cell>
	<grid:cell sort="begin_time">起始时间</grid:cell> 
	<grid:cell sort="end_time">终止时间</grid:cell>
	<grid:cell  width="200px">操作</grid:cell> 
	</grid:header>

  <grid:body item="activity">
  		<grid:cell><input type="checkbox" name="id" value="${activity.id }" />${activity.id } </grid:cell>
        <grid:cell>${activity.name } </grid:cell>
        <grid:cell><c:if test="${activity.enable == 0}">否</c:if><c:if test="${activity.enable == 1}">是</c:if> </grid:cell>
        <grid:cell><html:dateformat pattern="yyyy-MM-dd" time="${activity.begin_time }"></html:dateformat></grid:cell>  
        <grid:cell><html:dateformat pattern="yyyy-MM-dd" time="${activity.end_time }"></html:dateformat></grid:cell>
        <grid:cell> 
        <a href="activity!edit.do?activity_id=${activity.id }" >
       		 <img class="modify" src="images/transparent.gif" > 
        </a>
        <a href="promotion!list.do?activityid=${activity.id }"  >规则管理</a>
        </grid:cell> 
  </grid:body> 
  
</grid:grid>  
</form>	
</div>


