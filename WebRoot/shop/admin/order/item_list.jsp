<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="grid">
      <table cellspacing="0" cellpadding="0" border="0" width="100%" class="finderInform">
      <colgroup class="Colgoodsid"></colgroup>
      <colgroup class="textleft"></colgroup>     
      <colgroup class="Colcategory"></colgroup>          
      <colgroup span="3" class="Colamount"></colgroup>
        <thead>
        <tr>
          <th>货号</th>
          <th>商品名称</th>
          
          <th>价格</th>
          <th>购买量</th>
          <th>已发货量</th> 
          
          </tr>
      </thead>
      <tbody>
      
      	<c:forEach items="${itemList}" var="item">
          <tr>
          <td>${item.sn }</td>
          <td class="textleft">
          <a target="_blank" href="../goods-${item.goods_id }.html">${item.name }</a>
   		 </td>
         
          <td class="Colamount"><fmt:formatNumber value="${item.price}" type="currency" pattern="￥.00"/></td>
          <td class="Colamount">${item.num}</td>
          <td class="Colamount">${item.ship_num}</td>
        </tr>
        </c:forEach>
              </tbody>
      </table>
      <c:if test="${giftCount>0 }">
      <div>赠品</div>
      <grid:grid from="orderGiftList">
      	<grid:header>
      		<grid:cell>赠品名称</grid:cell>
      	</grid:header>
      	<grid:body item="gift">
      		<grid:cell>${gift.gift_name}</grid:cell>
      	</grid:body>
      </grid:grid>
      </c:if>
    </div>