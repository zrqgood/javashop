<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="grid">
      <table cellspacing="0" cellpadding="0" border="0" width="100%"  >
        <thead>
        <tr>
          <th>订单商品 (购买量)</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${itemList}" var="item">
         <tr>
          <td style="white-space: normal; text-align: left;">
          <a target="_blank" href="../goods-${item.goods_id }.html">${item.name }</a><!-- 李改：去了两点 -->
          <span class="fontcolorOrange fontbold">×(${item.num })</span></td>
        </tr>
		</c:forEach>
         </tbody>
      </table>
</div>
    
<div class="tableform">
<table cellspacing="0" cellpadding="0" border="0" style="width: auto;" class="orderdetails_basic">
  <tbody>
  <tr>
    <td style="vertical-align: top;">
    
	<h5>商品价格</h5>
<div class="division">
    <table cellspacing="0" cellpadding="0" border="0">
      
      <tbody><tr>
        <th style="width: 80px;">商品总额：</th>
        <td><fmt:formatNumber value="${ord.goods_amount }" type="currency" pattern="￥.00"/></td>
      </tr>
      <tr>
        <th style="width: 80px;">配送费用：</th>
        <td><fmt:formatNumber value="${ord.shipping_amount }" type="currency" pattern="￥.00"/></td>
      </tr>
                      
       <tr>
        <th style="width: 80px;">订单总额：</th>
        <td style="font-size: 16px; color: rgb(255, 153, 0); font-weight: 700;">
        <span id="order_price_span"><fmt:formatNumber value="${ord.order_amount }" type="currency" pattern="￥.00"/></span>
        <a href="javascript:;" id="editPriceBtn" style="font-size:12px">修改</a>
        </td>
      </tr>
      <tr>
        <th style="width: 80px;">已支付金额：</th>
        <td><fmt:formatNumber value="${ord.paymoney }" type="currency" pattern="￥.00"/></td>
      </tr>
      </tbody>
      </table>
</div>

    </td>
    
    <td style="vertical-align: top;">
<h5>订单其他信息</h5>
<div class="division">
    <table cellspacing="0" cellpadding="0" border="0">
      
      <tbody><tr>
        <th style="width: 80px;">配送方式：</th>
        <td>${ord.shipping_type }</td>
      </tr>
      <tr>
        <th style="width: 80px;">配送保价：</th>
        <td>
        <c:if test="${ord.is_protect==1}">是</c:if>
		<c:if test="${ord.is_protect==0}">否</c:if>
		</td>
      </tr>
      <tr>
        <th style="width: 80px;">商品重量：</th>
        <td>${ord.weight } g</td>
      </tr>
      <tr>
        <th style="width: 80px;">支付方式：</th>
        <td>${ord.payment_name }</td>
      </tr>

     <tr>
        <th style="width: 80px;">可得积分：</th>
        <td>${ord.gainedpoint }</td>
      </tr>
    </tbody></table>
</div>
    </td>
    <td style="vertical-align: top;">
    <h5>购买人信息</h5>
    <div class="division">
    <table cellspacing="0" cellpadding="0" border="0">
      
            <tbody><tr>
        <th style="width: 80px;">用户名：</th>
        <td>${member.uname }
        </td>
      </tr>
      <tr>
        <th style="width: 80px;">姓名：</th>
        <td>${member.name }</td>
      </tr>
      <tr>
        <th style="width: 80px;">电话：</th>
        <td>${member.tel }</td>
      </tr>
      <tr>
        <th style="width: 80px;">地区：</th>
        <td>${member.province }-${member.city}-${member.region }</td>
      </tr>
      <!--            <tr>
              <td>Email：</td><td>demo@demo.com
            </td></tr>
            <tr>
              <td>省份：</td><td>
            </td></tr>
            <tr>
              <td>邮编：</td><td>
            </td></tr> -->
      <!--<tr>
        <th style="width:80px;" >地址：</th>
        <td></td>
      </tr>-->
      <tr>
        <th style="width: 80px;">Email：</th>
        <td><a href="mailto:demo@demo.com" target="_self">${member.email }</a></td>
      </tr>
      
          </tbody></table>
</div>
    </td>
    <td style="vertical-align: top;">
            <h5 colspan="2">收货人信息 
			</h5>
        <div class="division">
        <table cellspacing="0" cellpadding="0" border="0">
          
          <tbody><tr>
            <th style="width: 80px;">发货日期：</th>
            <td>${ord.ship_day }${ord.ship_time }</td>
          </tr>
          <tr>
            <th style="width: 80px;">姓名：</th>
            <td>${ord.ship_name }</td>
          </tr>
          <tr>
            <th style="width: 80px;">电话：</th>
            <td>${ord.ship_tel}</td>
          </tr>
          <tr>
            <th style="width: 80px;">手机：</th>
            <td>${ord.ship_mobile}</td>
          </tr>
          <tr>
            <th style="width: 80px;">地区：</th>
            <td>${ord.shipping_area}</td>
          </tr>
          <tr>
            <th style="width: 80px;">地址：</th>
            <td style="white-space: normal; line-height: 18px;">${ord.ship_addr}</td>
          </tr>
          <tr>
            <th style="width: 80px;">邮编：</th>
            <td>${ord.ship_zip}</td>
          </tr>
           </tbody>
           </table>
 </div>
          </td>
  </tr>
</tbody></table></div>