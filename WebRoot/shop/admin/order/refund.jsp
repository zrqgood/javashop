<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="division">
<table width="100%">
  <tbody><tr>
    <th>订单号：</th>
    <td>${ord.sn } 【${ord.payStatus}】</td>
    <th>下单日期：</th>
    <td><html:dateformat pattern="yy-MM-dd hh:mm:ss" time="${ord.create_time}"></html:dateformat>:</td>
      </tr>
  <tr>
    <th>订单总金额：</th>
    <td><fmt:formatNumber value="${ord.order_amount}" type="currency" pattern="￥.00"/></td>
    <th>已收金额：</th>
    <td><fmt:formatNumber value="${ord.paymoney}" type="currency" pattern="￥.00"/></td>
    </tr>
  <tr>
    <th>退款银行：</th>
    <td colspan="3"><input type="text"  style="width: 100px;" value="" name="payment.bank" id="payBank" autocomplete="off">    </td>
    </tr>
    <tr>
      <th>退款帐号：</th>
      <td colspan="3"><input type="text"  style="width: 200px;" value="" name="payment.account" id="payAccount" autocomplete="off"></td>
    </tr>
    <tr>
      <th>退款金额：</th>
      <td colspan="3"><input type="text"  style="width: 100px;" value="${ord.paymoney}" name="payment.money" autocomplete="off">      </td>
<!--      <th>收款人：</th>
      <td>admin</td> -->
    </tr>
  <tr>
    <th>退款类型：</th>
    <td colspan="3">
	    <label><input type="radio" style="" value="online" name="payment.pay_type">在线支付</label>
		<label><input type="radio" style="" checked="checked" value="offline" name="payment.pay_type">线下支付</label>
		<label><input type="radio" style="" value="deposit" name="payment.pay_type">预存款支付</label>	</td>
  </tr>
     <tr>
    <th>退款方式：</th>
    <td>
    <select selected="30"  style="" value="30"  name="payment.pay_method" type="select">
    <c:forEach items="${paymentList}"  var="payment">
    <option value="${payment.id}" label="${payment.name }">${payment.name }</option>
    </c:forEach>
	</select>	</td>
    <th>退款人：</th>
    <td><input type="text" style="width: 100px;" value="" name="payment.pay_user" autocomplete="off" /></td>
    </tr>
<!--    <tr>
    <th>当前状态:</th>
    <td>未支付</td>
      <th>收取支付费用:</th>
      <td>0.000</td>
     </tr> -->

    </tbody></table>
</div>