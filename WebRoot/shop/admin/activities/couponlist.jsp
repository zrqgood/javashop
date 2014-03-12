<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/Coupons.js"></script>
<div class="grid">

	<div class="toolbar">
	
		<ul>
			<li><a href="coupon!add.do">添加</a></li>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
		</ul>
		
		<div style="clear:both"></div>
	</div>
<form method="POST">
<grid:grid  from="webpage">

	<grid:header>
	<grid:cell width="50px"><input type="checkbox" id="toggleChk" /></grid:cell>
	<grid:cell sort="cpns_name" width="250px">优惠券名称</grid:cell> 
	<grid:cell sort="cpns_prefix">优惠券号码</grid:cell>
	<grid:cell sort="cpns_type">类型</grid:cell>
	<grid:cell sort="cpns_gen_quantity">已发数量</grid:cell>
	<grid:cell sort="cpns_point">兑换积分</grid:cell>
	<grid:cell sort="cpns_status">状态</grid:cell>
	<grid:cell sort="pmt_time_begin">起始时间</grid:cell> 
	<grid:cell sort="pmt_time_end">终止时间</grid:cell>
	<grid:cell  width="100px">操作</grid:cell> 
	</grid:header>

  <grid:body item="coupon">
  		<grid:cell><input type="checkbox" name="id" value="${coupon.cpns_id }"/>${coupon.cpns_id }<input type="hidden" name="pmtIdArray" value="${coupon.pmt_id }" disabled="disabled"/></grid:cell>
        <grid:cell>${coupon.cpns_name } </grid:cell>
        <grid:cell>${coupon.cpns_prefix } </grid:cell>
        <grid:cell><c:if test="${coupon.cpns_type == 0 }">一张可无限使用</c:if><c:if test="${coupon.cpns_type == 1 }">多张只适用一次</c:if> </grid:cell>
        <grid:cell>${coupon.cpns_gen_quantity} </grid:cell>
        <grid:cell>${coupon.cpns_point} </grid:cell>
        <grid:cell><c:if test="${coupon.cpns_status == 1 }">启用</c:if><c:if test="${coupon.cpns_status == 0 }">禁用</c:if> </grid:cell>
        <grid:cell><html:dateformat pattern="yyyy-MM-dd" time="${coupon.pmt_time_begin }"></html:dateformat></grid:cell>  
        <grid:cell><html:dateformat pattern="yyyy-MM-dd" time="${coupon.pmt_time_end }"></html:dateformat></grid:cell>
        <grid:cell> 
        <a  href="coupon!edit.do?cpnsid=${coupon.cpns_id }" >
       	 <img class="modify" src="images/transparent.gif" >
        </a>
         </grid:cell> 
  </grid:body>  
  
</grid:grid>  
</form>	
</div>