<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/ExCoupons.js"></script>
<div class="grid">

	<div class="toolbar">
	
		<ul>
			<li><a href="coupon!addExchange.do">添加兑换规则</a></li>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
		</ul>
		
		<div style="clear:both"></div>
	</div>
<form method="POST">
<grid:grid  from="webpage">

	<grid:header>
	<grid:cell width="50px"><input type="checkbox" id="toggleChk" /></grid:cell>
	<grid:cell sort="cpns_name" width="250px">优惠券名称</grid:cell> 
	<grid:cell>优惠券号码</grid:cell>
	<grid:cell sort="cpns_prefix">兑换优惠券积分</grid:cell>
	<grid:cell  width="100px">操作</grid:cell> 
	</grid:header>

  <grid:body item="coupon">
  		<grid:cell><input type="checkbox" name="id" value="${coupon.cpns_id }" />${coupon.cpns_id } </grid:cell>
        <grid:cell>${coupon.cpns_name } </grid:cell>
        <grid:cell>${coupon.cpns_prefix } </grid:cell>
        <grid:cell>${coupon.cpns_point} </grid:cell>
        <grid:cell> <a href="coupon!editExchange.do?cpnsid=${coupon.cpns_id }" ><img class="modify" src="images/transparent.gif" ></a> </grid:cell> 
  </grid:body>  
  
</grid:grid>  
</form>	
</div>