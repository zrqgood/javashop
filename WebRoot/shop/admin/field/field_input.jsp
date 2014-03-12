<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="input">
	 <form method="post"   id="fieldForm" >
	   <input type="hidden" name="goodsField.type_id" value="${typeid }" />
	   <c:if test="${goodsField.field_id!=null}">
	   <input type="hidden" name="goodsField.field_id" value="${goodsField.field_id }">
	   </c:if>
	   
	   <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	     <tr>
	       <th><label class="text">提示文字：</label></th>
	       <td><input type="text" name="goodsField.china_name" value="${goodsField.china_name }"  dataType="string" required="true" /></td>
	      </tr>
	     <tr>
	       <th><label class="text">字段名：</label></th>
	       <td><input type="text" name="goodsField.english_name"   value="${goodsField.english_name }"  required="true"  dataType="string"/></td>
	      </tr>
	     <tr>
	       <th><label class="text">字段插件：</label></th>
	       <td>
		       <select name="goodsField.pluginid" id="pluginid">
		        <c:forEach items="${fieldPluginList}" var="plugin">
		        		<option   value="${plugin.id }">${plugin.name }</option>
		        </c:forEach>  	
		       </select>	   
		   </td>		   
	      </tr><!--	     
	
	     <tr>
	       <th><label class="text">数据来源：</label></th>
	       <td>
	   
	      <input type="radio" name="goodsField.data_source" value="manual" checked />手工输入
	          &nbsp;&nbsp;
	       <input type="radio" name="goodsField.data_source" value="dict" />数据字典
	       <select name="source_id">
	       <c:forEach  items="${modelList}" var="model">
	       	<option value="${model.model_id }">${model.china_name }</option>
	       </c:forEach>
	       </select>
	      
	       </td>
	      </tr>	      
	
	     --><tr>
	       <th><label class="text">排序：</label></th>
	       <td><input type="text" name="goodsField.field_sort" style="width:50px"  value="${goodsField.field_sort }"  required="true"  dataType="int"/></td>
	      </tr>	      
	     <tr>
	       <th><label class="text">列表显示：</label></th>
	       <td> 
	       <input type="radio" value="1" id="is_show1"  name="goodsField.is_show" checked>是&nbsp;&nbsp;<input type="radio" id="is_show0"  value="0"  name="goodsField.is_show">否
	       </td>
	      </tr>
	       <tr>
	       <th><label class="text">是否验证：</label></th>
	       <td> 
	       <input type="radio" value="1" id="is_validate1"  name="dataField.is_validate" checked>是&nbsp;&nbsp;<input type="radio" id="is_validate0"  value="0"  name="dataField.is_validate">否
	       </td>
	      </tr>	 
	   </table>
 <div class="submitlist" align="center">
 <table>
 <tr><td >
  <input   type="button" value=" 确    定   "  class="submitBtn" />
   </td>
   </tr>
 </table>
</div>	
	 </form>
</div>
<script type="text/javascript">
$(function(){

	<c:if test="${isEdit==true}" >
	$("#pluginid").val('${goodsField.pluginid}');
	//$("#is_validate${dataField.is_validate}").attr("checked",true);
	//$("#is_show${dataField.is_show}").attr("checked",true);
	</c:if>
	
});
</script>
