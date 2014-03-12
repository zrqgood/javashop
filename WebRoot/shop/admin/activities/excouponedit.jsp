<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<div class="input" id="main" style="height: 280px; width: 1129px; visibility: visible; opacity: 1;">
<form action="coupon!saveAddExchange.do" method="post" id="doActivityInfoForm" class="validate">
  <div class="tableform">
    <div class="division">
      <table class="form-table">
        <tbody><tr>
          <th>优惠券名称：</th>
          <td>      
            <input type="hidden" name="cpnsid" value="${coupons.cpns_id }"/>${coupons.cpns_name }
          </td>
        </tr>
        <tr>
          <th>积分兑换数额：</th>
           <td><input type="text" required="true" name="point" style="width: 50px;" dataType="int" value="${coupons.cpns_point }"> &nbsp; &nbsp; 			<span id="el_1">
		<img border="none" style="width: 14px; height: 14px; background-image: url(&quot;images/ImageBundle.gif&quot;); background-repeat: no-repeat; background-position: 0pt -1577px;" src="images/transparent.gif">		</span>
				
			</td>
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