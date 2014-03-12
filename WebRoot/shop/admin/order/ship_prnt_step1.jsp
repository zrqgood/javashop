<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<link rel="stylesheet" href="css/admin.zcss" type="text/css" media="screen, projection">
<div class="print-title"><span style="color:#d0d0d0">|</span>&nbsp;&nbsp;&nbsp;&nbsp;<Strong>快递单打印</Strong>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#d0d0d0">|</span></div>

<form id="dly_printer_form" action="orderPrint!ship_prnt_step2.do" method="post" class="validate">
	
<input type="hidden" name="orderId" value="${orderId }" />

<div class="tableform" id="x-order_tableform">
    <table cellspacing="0" cellpadding="0" border="0" width="100%"><tbody><tr><td>
       <h4>收货地址信息</h4>
       </td>

    </tr>
    </tbody></table>
    <div class="division">
      <table cellspacing="0" cellpadding="0" border="0" width="100%" >
      <tbody><tr>
	      <th>姓名：</th>
          <td colspan="3"><input size="10" style="width:80px;" value="${ord.ship_name }" name="ord.ship_name" /></td>
	      <td rowspan="5" style="vertical-align:middle;">

            <center><div class="division" style="width:150px;">
          <table border="0" cellpadding="0" cellspacing="0">
             <tr>
               <td style="text-align:left; color:#aaaaaa;">您也可以将编辑过的收货地址更新至订单</td>
             </tr>
             <tr>
               <td style="height:40px;"><input type="checkbox" value="1" name="saveAddr"/>存订单地址</td>
             </tr>

         </table> </div></center>         </td>
      </tr>
	   
      <tr>
	      <th>所在城市：</th>
          <td><input type="text" name="ord.shipping_area" value="${ord.shipping_area }"/></td>

	      <th>邮编：</th>
	      <td><input name="ord.ship_zip" value="${ord.ship_zip }" /></td>
          </tr>
	        <tr>
	      <th>地址：</th>
          <td colspan="3"><input size="50" style="width:400px;" name="ord.ship_addr" value="${ord.ship_addr }"/></td>
	        </tr>
	  
      <tr>

	      <th>手机：</th>
          <td><input value="${ord.ship_mobile }" name="ord.ship_mobile"/></td>
	      <th>电话：             </th>
	      <td><input value="${ord.ship_tel }" name="ord.ship_tel"/></td>
          </tr>
      <tr>
        <th>备注：</th>

        <td colspan="3"><input size="50" style="width:400px;" name="ord.remark" value="${ord.remark }" /></td>
        </tr>
	   </tbody></table>
  </div>
	
    <h4>  发货地址信息 </h4>
    <span style=" padding-left:10px;">发货点选择:
     <select name="dly_center_id" id="dly_center_id"  required="true" >
   <c:forEach items="${dlyCenterList }" var="dlyCenter">
   <option value="${dlyCenter.dly_center_id }">${dlyCenter.name }</option>
   </c:forEach>
   </select>
    </span>&nbsp;&nbsp;&nbsp;&nbsp;
    <span style=" padding-left:10px;">打印模板选择： 
    <select name="prt_tmpl_id"  required="true" >
   <c:forEach items="${printTmplList }" var="printTmpl">
   <option value="${printTmpl.prt_tmpl_id }">${printTmpl.prt_tmpl_title }</option>
   </c:forEach>
   </select> 
   </span>  
  </div>

<div >

 
</div>
<div class="clear:both"></div>
<div class="submitlist" align="center" style="text-align: center">
<table>
	<tr>
		<td><input name="submit" type="submit" value=" 打印  "
			class="submitBtn" /></td>
	</tr>
</table>
</div>
  

</form>
<script type="text/javascript">
$(function(){
	$("form.validate").validate();
});

</script>

