<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<div class="grid">

	<div class="toolbar">
	<form method="GET">
		<ul>
			<li>订单日期从：<input type="text" name="beginTime" value="${beginTime }" maxlength="60" dataType="date" class="dateinput"/></li>
			<li>到：<input type="text" name="endTime" value="${endTime }" maxlength="60" dataType="date" class="dateinput"/></li>
			<li><input type="submit" value="查询" /></li>
		</ul>
	</form>		
		<div style="clear:both"></div>
	</div>

<grid:grid  from="list" pager="no">
	<grid:header>
	<grid:cell width="500px">商品名称</grid:cell>
	<grid:cell sort="num">销售量</grid:cell>
	<grid:cell sort="amount">销售额</grid:cell> 
	</grid:header>
  <grid:body item="rank">
        <grid:cell>${rank.name } </grid:cell>
        <grid:cell>${rank.num } </grid:cell>
        <grid:cell><fmt:formatNumber value="${rank.amount }" type="currency" pattern="￥.00"/></grid:cell>
  </grid:body>  
</grid:grid>  
</div>


