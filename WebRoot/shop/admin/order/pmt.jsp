<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="grid">
      <table cellspacing="0" cellpadding="0" border="0" width="100%" class="finderInform">

        <thead>
        <tr>
          <th>优惠方案</th>
          </tr>
      </thead>
      <tbody>
      <c:forEach items="${pmtList}" var="pmt">
	      <tr>
	      <td>${pmt.pmt_describe }</td>
	      </tr>
      </c:forEach>
      </tbody>
      </table>
 </div>