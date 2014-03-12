<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/Promotion.js"></script>
<div class="grid">
	<div class="toolbar">
	
		<ul>
			<li><a href="promotion!select_plugin.do?activityid=${activityid }">添加</a></li>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
		</ul>
		
		<div style="clear:both"></div>
	</div>
<form method="POST" >
<grid:grid  from="pmtList" pager="no">

	<grid:header>
	<grid:cell width="50px"><input type="checkbox" id="toggleChk" /></grid:cell>
	<grid:cell   width="150px">超始时间</grid:cell> 
	<grid:cell   width="150px">截止时间</grid:cell> 
	<grid:cell  >规则描述</grid:cell> 
	<grid:cell  width="100px">操作</grid:cell> 
	</grid:header>

  <grid:body item="pmt">
  		<grid:cell><input type="checkbox" name="pmtidArray" value="${pmt.pmt_id }" />${pmt.pmt_id} </grid:cell>
        <grid:cell><html:dateformat pattern="yyyy-MM-dd" time="${pmt.pmt_time_begin }"></html:dateformat> </grid:cell>
        <grid:cell><html:dateformat pattern="yyyy-MM-dd" time="${pmt.pmt_time_end }"></html:dateformat></grid:cell>
        <grid:cell>${pmt.pmt_describe}</grid:cell>  
        <grid:cell><a href="promotion!select_plugin.do?activityid=${activityid }&pmtid=${pmt.pmt_id }"><img class="modify"  src="images/transparent.gif"></a> </grid:cell> 
  </grid:body>
  
</grid:grid>  
</form>	
<div style="clear:both;padding-top:5px;"></div>
</div>
<script type="text/javascript">
$(function(){
	Promotion.opation("idChkName","pmtidArray");
	Promotion.init();
});
</script>
