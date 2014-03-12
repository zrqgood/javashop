<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<div class="grid">

<grid:grid  from="list" pager="no">
	<grid:header>
	<grid:cell width="300px">商品名称</grid:cell>
	<grid:cell sort="view_count">访问次数</grid:cell>
	<grid:cell sort="buy_count">购买次数</grid:cell>
	<grid:cell>访问购买率</grid:cell> 
	</grid:header>
  <grid:body item="rank">
        <grid:cell>${rank.name } </grid:cell>
        <grid:cell>${rank.view_count } </grid:cell>
        <grid:cell>${rank.buy_count } </grid:cell>
        <grid:cell>${rank.per }%</grid:cell>
  </grid:body>  
</grid:grid>  
</div>


