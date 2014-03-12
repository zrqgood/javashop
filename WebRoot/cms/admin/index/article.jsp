<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<table cellspacing="0" cellpadding="0" border="0" width="100%">
      <tbody>        
          <tr>
            <th><span>发表文章数：</span></th>
            <td><span><a <c:if test="${datass.count > 0 }">  href="${ctx }/cms/admin/cat!list.do" class="imp"</c:if>>${ datass.count}</a>个</span></td>
          </tr>
          <tr>
            <th><span>栏目数 ：</span></th>
            <td><span><a <c:if test="${datass.catcount > 0 }">  href="${ctx }/cms/admin/cat!list.do" class="imp"</c:if>>${ datass.catcount}</a>个</span></td>
          </tr>
          <tr>
            <th><span>新留言：</span></th>
            <td><span><a <c:if test="${datass.msgcount > 0 }">  href="${ctx }/core/admin/guestBook!list.do" class="imp"</c:if>>${ datass.msgcount}</a>条</span></td>
          </tr>
          <tr>
            <th style="height:21px"><span> </span></th>
            <td><span></span></td>
          </tr>          
        </tbody>            
 </table>