<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="fck" %>

 <form method="post" action="seo!save.do" name="theForm" id="theForm" >
 <input type="hidden" name="seo.id" value="1" />
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
     <tr>
       <th>首页标题：</th>
       <td  ><input type="text" name="seo.title" maxlength="60" value="${seo.title }"  dataType="string" required="true" />       </td>
      </tr>
      <tr>
       <th>META_KEYWORDS<br/>(关键词)：</th>
       <td  >
       	<input type="text" name="seo.meta_keywords" maxlength="60" value="${seo.meta_keywords }" />
       </td>
      </tr>
      <tr>
       <th>META_DESCRIPTION<br/>(页面描述)：</th>
       <td  >
       	<textarea name="seo.meta_description" default="" type="textarea" display="false" style="width: 360px;" />${seo.meta_description }</textarea>
       </td>
      </tr>
	<tr>
		<th>商店页面启用伪静态URL：</th>
		<td><input type="checkbox" name="seo.use_static" value="0"
			<c:if test="${seo.use_static==0 }">checked</c:if> /> 是&nbsp;</td>
	</tr>
	<tr>
		<th>通知搜索引擎不索引列表页：</th>
		<td><input type="checkbox" name="seo.noindex_catalog" value="0"
			<c:if test="${seo.noindex_catalog==0 }">checked</c:if> /> 是&nbsp;</td>
	</tr>
   </table>
   <p class="submitlist"> <input name="submit" type="submit" class="button" value=" 确定 " />
           <input name="reset" id="reset" type="button" class="button" value=" 重置 " />       </p>
 </form>
