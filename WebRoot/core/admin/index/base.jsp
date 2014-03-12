<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<table cellspacing="0" cellpadding="0" border="0" width="100%">
      <tbody>        
          <tr>
            <th width="80px"><span>站点名称：</span></th>
            <td><div style="width:120px;height:18px;overflow:hidden"><a title="${site.sitename}" class="imp" href="${ctx }/core/admin/user/userSite!edit.do?id=${site.id}">${ site.sitename}</a></div></td>
          </tr>
          <tr>
            <th><span>最后登录：</span></th>
            <td><span><html:dateformat time="${site.lastlogin*1000}" pattern="yyyy-MM-dd HH:mm:ss"></html:dateformat></span></td>
          </tr>
          <tr>
            <th><span>创建时间：</span></th>
            <td><span><html:dateformat time="${site.createtime*1000}" pattern="yyyy-MM-dd HH:mm:ss"></html:dateformat></span></td>
          </tr>
          <tr>
            <th style="height:21px"><span>&nbsp;</span></th>
            <td><span>&nbsp;</span></td>
          </tr>          
        </tbody>
 </table>