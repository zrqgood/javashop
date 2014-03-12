<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<table cellspacing="0" cellpadding="0" border="0" width="100%" id="item_point">
      <tbody>        
          <tr>
            <th><span>可用余额：</span></th>
            <td><span>${site.point }点</span></td>
          </tr>
          <tr>
            <th><span>免费领取：</span></th>
            <td>
			<c:if test="${canget==1}">
				<a href='${ctx}/core/admin/site!pullpoint.do'>领取本月赠点</a>
			</c:if>
			<c:if test="${canget==0}">
				本月已领取
			</c:if>
			</td>
            
          </tr>
          <tr>
            <th><span>立即充值：</span></th>
            <td><span><a href="#">充值</a></span></td>
          </tr>
        </tbody>
</table>

