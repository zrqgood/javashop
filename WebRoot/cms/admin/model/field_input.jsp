<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="input">
	 <form method="post"   id="fieldForm" >
	   <input type="hidden" name="dataField.model_id" value="${modelid }" />
	   <c:if test="${dataField.field_id!=null}">
	   <input type="hidden" name="dataField.field_id" value="${dataField.field_id }">
	   </c:if>
	   
	   <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	     <tr>
	       <th><label class="text">提示文字：</label></th>
	       <td><input type="text" name="dataField.china_name" value="${dataField.china_name }"  dataType="string" required="true" /></td>
	      </tr>
	     <tr>
	       <th><label class="text">字段名：</label></th>
	       <td><input type="text" name="dataField.english_name"   value="${dataField.english_name }"  required="true"  dataType="string"/></td>
	      </tr>
	     <tr>
	       <th><label class="text">展现形式：</label></th>
	       <td>
		       <select name="dataField.show_form" id="show_form">
		        <c:forEach items="${fieldPluginList}" var="plugin">
		        	<c:if test="${plugin.type=='field'}">
		        		<option haveSelectValue=${plugin.haveSelectValue }  value="${plugin.id }">${plugin.name }</option>
		        	</c:if>
		        </c:forEach>  	
		       </select>	   
		   </td>		   
	      </tr>	     
	      <!-- 
	     <tr>
	       <th><label class="text">数据类型：</label></th>
	       <td>
	       <select name="dataField.data_type" id="data_type">
	       	<option value="1">字符串</option>
	       	<option value="2">整数</option>
	       	<option value="3">浮点数</option>
	       	<option value="4">文本</option>
	     
	       	<option value="5">日期</option>
	       	<option value="6">图片</option>
	      
	       </select>
	       </td>
	      </tr>
	      
	     <tr>
	       <th><label class="text">字段大小：</label></th>
	       <td><input type="text" name="dataField.data_size"  style="width:50px"  value="${ dataField.data_size}"  required="true"  dataType="string"/></td>
	      </tr>
 --> 
	     <tr id="saveValueTr">
	       <th><label class="text">可选值(,号隔开)：</label></th>
	       <td>
	       <textarea style="width:250px" name="dataField.save_value">${dataField.save_value }</textarea>
		  </td>
	      </tr>	
	     <tr>
	       <th><label class="text">排序：</label></th>
	       <td><input type="text" name="dataField.taxis" style="width:50px"  value="${dataField.taxis }"  required="true"  dataType="int"/></td>
	      </tr>	      
	     <tr>
	       <th><label class="text">列表显示：</label></th>
	       <td> 
	       <input type="radio" value="1" id="is_show1"  name="dataField.is_show" checked>是&nbsp;&nbsp;<input type="radio" id="is_show0"  value="0"  name="dataField.is_show">否
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
	$("#saveValueTr").hide();
	$("#show_form option").click(function(){
		if($(this).attr("haveSelectValue") =="1"){
			$("#saveValueTr").show();
		}else{
			$("#saveValueTr").hide();
		}
	 
	});
	<c:if test="${isEdit==true}" >
 
	$("#show_form").val('${dataField.show_form}');
	if( $("#show_form option[value='${dataField.show_form}']").attr("haveSelectValue") =="1"){
		$("#saveValueTr").show();
	}
	$("#is_validate${dataField.is_validate}").attr("checked",true);
	$("#is_show${dataField.is_show}").attr("checked",true);
	</c:if>
	
});
</script>
