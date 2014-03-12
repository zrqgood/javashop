<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table cellspacing="0" cellpadding="0" border="0" align="center" width="100%">

  <tbody><tr>
    <td nowrap="" colspan="2">
      <fieldset><legend align="top">公式验算</legend> 

      <div class="notice">
                您可以在这里测试配送公式是否计算正确、有效
            </div>
          <table cellspacing="0" cellpadding="2" border="0" width="98%">
            <tbody><tr> 
              <td nowrap="" width="70%">配送公式：
                <input type="text" size="50" value="" id="dlg_expressions">
              </td>
            </tr>
            <tr> 
              <td nowrap="" width="70%">商品重量：
                <input type="text" value="0" size="8" maxlength="20" class="inputstyle" name="weight" id="weight">
                克 </td>
              <td nowrap="" width="30%" rowspan="3"> 
                <p><button class="checkbtn" >计算</button></p>
              </td>
            </tr>
            <tr>
              <td nowrap="" width="70%">订单价格：
                <input type="money" vtype="money" style="" value="0" size="8" maxlength="20" class="inputstyle _x_ipt" name="orderprice" id="orderprice" autocomplete="off">
              </td>
            </tr>
            <tr> 
              <td nowrap="" width="70%">
                <span id="result"></span>
              </td>
            </tr>
          </tbody></table>
          </fieldset></td>
  </tr>
</tbody></table>