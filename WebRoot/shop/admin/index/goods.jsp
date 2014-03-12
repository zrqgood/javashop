<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<table cellspacing="0" cellpadding="0" border="0" width="100%">
      <tbody>      
          <tr>
            <th><span>商品总数：</span></th>
            <td><span ><a <c:if test="${goodsss.allcount > 0 }"> href="${ctx }/shop/admin/goods!list.do" target="ajax" class="imp"</c:if>>${ goodsss.allcount}</a>个</span></td>
          </tr>
          <tr>
            <th><span>回收站商品：</span></th>
            <td><span ><a <c:if test="${goodsss.disabledcount > 0 }"> href="${ctx }/shop/admin/goods!trash_list.do" target="ajax" class="imp"</c:if>>${ goodsss.disabledcount}</a>个</span></td>
          </tr>          
          <tr>
            <th><span>上架商品：</span></th>
            <td><span ><a <c:if test="${goodsss.salecount > 0 }"> href="${ctx }/shop/admin/goods!list.do?market_enable=1" target="ajax" class="imp"</c:if>>${ goodsss.salecount}</a>个</span></td>
          </tr>
          <tr>
            <th><span>下架商品：</span></th>
            <td><span><a <c:if test="${goodsss.unsalecount> 0 }"> href="${ctx }/shop/admin/goods!list.do?market_enable=0" target="ajax" class="imp"</c:if>>${ goodsss.unsalecount}</a>个</span></td>
          </tr>
          <tr>
            <th><span>商品评论：</span></th>
            <td><span><a <c:if test="${goodsss.discuss > 0 }"> href="${ctx }/shop/admin/comments!bglist.do?object_type=discuss"  class="imp"</c:if>>${ goodsss.discuss}</a>条</span></td>
          </tr>
          <tr>
            <th><span>商品咨询：</span></th>
            <td><span><a <c:if test="${goodsss.ask> 0 }"> href="${ctx }/shop/admin/comments!bglist.do?object_type=ask"  class="imp"</c:if>>${ goodsss.ask}</a>条</span></td>
          </tr>          
        </tbody>            
 </table>