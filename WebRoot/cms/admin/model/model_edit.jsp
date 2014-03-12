<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/datamodel.js"></script>    
<div class="input">
	 <form method="post" action="model!saveEdit.do" class="validate" name="theForm" id="theForm" >
	 <input type="hidden" name="dataModel.model_id" value="${dataModel.model_id }" id="modelid"/>
	   <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	     <tr>
	       <th><label class="text">模型名称：</label></th>
	       <td><input type="text" name="dataModel.china_name" maxlength="60" value="${dataModel.china_name }"  dataType="string" required="true" /></td>
	      </tr>
	     <tr>
	       <th><label class="text">表名：</label></th>
	       <td><input type="text" name="dataModel.english_name"  maxlength="60" size="40" value="${ dataModel.english_name}"  required="true"  dataType="string"/></td>
	      </tr>
	     <tr>
	       <th><label class="text">备注</label></th>
	       <td><textarea name="dataModel.brief" style="height:50px;width;100px">${dataModel.brief }</textarea>
		  </td>
	     </tr>
	 
	   </table>
	<div class="submitlist" align="center">
	 <table>
	 <tr><td >
	  <input name="submit" type="submit"	  value=" 确    定   " class="submitBtn" />
	   </td>
	   </tr>
	 </table>
	</div>
	 </form>
</div>
<div style="clear:both;padding-top:5px;"></div>


<div class="grid" style="width:600px">
	<div class="toolbar">
		<ul>
			<li><a href="javascript:;" id="addFieldBtn">添加字段</a></li>
			<li><a href="javascript:;" id="sortBtn">保存排序</a></li>
		</ul>
		<div style="clear:both"></div>
	</div>
<form method="POST" id="FieldListForm">
<grid:grid  from="fieldList">

	<grid:header>
	<grid:cell width="50px">id</grid:cell>
	<grid:cell  width="100px">提示文字</grid:cell> 
	<grid:cell>字段名</grid:cell> 
	<grid:cell  width="100px">排序</grid:cell> 
	<grid:cell  width="100px">修改</grid:cell> 
	<grid:cell  width="100px">删除</grid:cell> 
	</grid:header>

  <grid:body item="field">
  		<grid:cell>${field.field_id } </grid:cell>
        <grid:cell>${field.china_name} </grid:cell>
        <grid:cell>${field.english_name} </grid:cell> 
         <grid:cell>
         <input type="hidden" name="ids" value="${field.field_id}" />
         <input type="text" name="sorts" style="width:50px;" value="${field.taxis}" />
          </grid:cell> 
        <grid:cell> <a  href="javascript:;" fieldid="${field.field_id }"><img class="modify" src="images/transparent.gif" ></a> </grid:cell> 
 		<grid:cell> <a  href="javascript:;" fieldid="${field.field_id }"><img class="delete" src="images/transparent.gif" ></a> </grid:cell>         
  </grid:body>  
  
</grid:grid>  
</form>	
<div style="clear:both;padding-top:5px;"></div>
</div>
<div id="addFieldDlg"></div>
<div id="editFieldDlg"></div>
<script type="text/javascript">
$(function(){
	$("form.validate").validate();
	ModelField.init();
});

</script>