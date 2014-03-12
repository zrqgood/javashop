<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="division">
	<form id="remark_form">
    <div class="grid">
      <table cellspacing="0" cellpadding="0" border="0" width="100%" class="finderInform">
        <tbody>
        <tr>
          <th>订单附言</th>
          <td><!--input name="orderId" value="${orderId }" type="hidden"/>
          <input name="remark" value="${ord.remark }" type="text"/-->
          ${ord.remark }
          </td>
        </tr>
        <!-- tr>
          <td colspan="2"><input type="button" id="save_remark" value="修改" />
          </td>
        </tr-->
      </tbody>
      </table>
    </div>
    </form>
 
  </div>