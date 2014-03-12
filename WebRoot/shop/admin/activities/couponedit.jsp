<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<div class="input" id="main" style="height: 280px; width: 1129px; visibility: visible; opacity: 1;">
<form action="coupon!saveEdit.do" method="post" id="doCouponInfoForm" class="validate">
<input type="hidden" name="pmtid" value="${coupons.pmt_id }" />
<div class="tableform">
<div class="division">
<table cellspacing="0" cellpadding="0" border="0" width="100%" class="form-table">
  <tbody><tr>
    <th>名称:<input type="hidden" name="coupons.cpns_id" value="${coupons.cpns_id }"/></th>
    <td><input type="text" required="true" name="coupons.cpns_name" value="${coupons.cpns_name }"></td>
  </tr>
  </tbody></table>
</div>
<div class="division">
<table cellspacing="0" cellpadding="0" border="0" width="100%" class="form-table">
  <tbody><tr>
    <th>号码:</th>
      <td><input type="hidden" required="true" name="coupons.cpns_prefix" value="${coupons.cpns_prefix }"/><input type="text" required="true" name="cpns_prefix" value="${coupons.cpns_prefix }" disabled="disabled"/></td>
    </tr>
  <tr>
    <th>状态:</th>
    <td><input type="radio" value="1" name="coupons.cpns_status" <c:if test="${coupons.cpns_status == 1 }">checked="checked"</c:if>/>启用
           <input type="radio" <c:if test="${coupons.cpns_status == 0 }">checked="checked"</c:if> value="0" name="coupons.cpns_status">禁用</td>
  </tr>
  <tr>
    <th>优惠券类型:</th>
    <td id="c_t"><input type="hidden" name="coupons.cpns_type" value="${coupons.cpns_type }"/>
    <input type="radio" disabled="disabled" <c:if test="${coupons.cpns_type == 0 }">checked="checked"</c:if> value="0" name="cpns_type" class="cpnstype">A类优惠券&nbsp;&nbsp;<span class="fontcolorGray">说明：此类优惠券，顾客只需获得一张，即可在规定的时间内重复使用。</span><br>
    <input type="radio" disabled="disabled" <c:if test="${coupons.cpns_type == 1 }">checked="checked"</c:if>value="1" name="cpns_type" class="cpnstype">B类优惠券&nbsp;&nbsp;<span class="fontcolorGray">说明：此类优惠券，顾客可一次获得多张，但在规定时间内每张只能使用一次，无法重复使用。</span></td>
  </tr>
</tbody></table>
</div>
<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input name="submit" type="submit"	  value=" 确    定   " class="submitBtn" />
   </td>
   </tr>
 </table>
</div>
</div>
</form>
<script type="text/javascript">
$("form.validate").validate();
</script>
</div>