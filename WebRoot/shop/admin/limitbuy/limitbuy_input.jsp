<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/LimitBuy.js"></script>
<script type="text/javascript" src="js/Selector.js"></script>

<style>
#ruletable input{width:100px;}
</style>
<div class="input" >

<c:if test="${isEdit}" >
<form method="post" action="limitBuy!saveEdit.do" class="validate"  onsubmit="javascript:return checkgoods();" enctype="multipart/form-data">
<input type="hidden" name="limitBuy.id" value="${limitBuy.id }" />
</c:if>
<c:if test="${!isEdit}" >
<form method="post" action="limitBuy!saveAdd.do" class="validate"  onsubmit="javascript:return checkgoods();" enctype="multipart/form-data">
</c:if>


<table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	<tr>
		<th><label class="text">标题：</label></th>
		<td>
		<input type="text" name="limitBuy.name" value="${limitBuy.name}" dataType="string" required="true"  style="width:300px" />
		</td>
	</tr>

	
	
	<tr>
		<th><label class="text">开始时间：</label></th>
		<td>
	 <c:if test="${isEdit}" >
		<input type="text" name="start_time"   dataType="date" required="true" class="dateinput" value="<html:dateformat pattern="yyyy-MM-dd" time="${limitBuy.start_time*1000}"></html:dateformat>" />

	</c:if>	
	 <c:if test="${!isEdit}" >
		<input type="text" name="start_time"   dataType="date" required="true" class="dateinput"   />
	</c:if>		
		<select name="start_hour" id="start_hour">
		<%for( int i=0;i<24;i++ ){%>
			<option value="<%=i %>"><%=i %></option>
		<%} %>
		</select>时
		 </td>
	</tr>
	
	<tr>
		<th><label class="text">结束时间：</label></th>
		<td>
		 <c:if test="${isEdit}" >
		<input type="text" name="end_time"   dataType="date" required="true" class="dateinput" value="<html:dateformat pattern="yyyy-MM-dd" time="${limitBuy.end_time*1000}"></html:dateformat>"/>
		</c:if>	
 		<c:if test="${!isEdit}" >
		<input type="text" name="end_time"   dataType="date" required="true" class="dateinput" />
		</c:if>	
		
		<select name="end_hour" id="end_hour">
		<%for( int i=0;i<24;i++ ){%>
			<option value="<%=i %>"><%=i %></option>
		<%} %>
		</select>
			时
		 </td>
	</tr>
	
	<tr>
		<th><label class="text">图片：</label></th>
		<td>
	 		<input type="file" name="imgFile" >
		</td>
	</tr>
	
	<c:if test="${limitBuy.img!=null &&limitBuy.img!=''}">
	<tr>
		<th></th>
		<td>
		<img src="${limitBuy.image }" />
		</td>
	</tr>
	</c:if>
	
	<tr>
		<th><label class="text">是否首页显示：</label></th>
		<td>
	 		<input type="radio" id="index_0" name="limitBuy.is_index" value="0" checked=true />否&nbsp;
	 		<input type="radio" id="index_1" name="limitBuy.is_index" value="1" />是&nbsp;
		</td>
	</tr>
</table>

<div style="clear:both;padding-top:5px;"></div>


<div class="grid" style="width:600px">
	<div class="toolbar">
		<ul>
			<li><a href="javascript:;" id="addGoodsBtn">增加商品</a></li>
		</ul>
		<div style="clear:both"></div>
	</div>
 
 <table id="goodstable">
 <tr>
 <th>商品名称</th>
 <th>商品价格</th>
 <th>操作</th>
 </tr>
 <c:forEach items="${limitBuy.goodsList}" var="goods">
 	<tr>
 		<td>
 			 ${goods.name }
 		</td>
 		
 		<td>
 			<input type="text" name="price" value="${goods.price }" /> <input type="hidden" name="goodsid" value="${goods.goods_id }"  />
 		</td>
 		
 		
 		<td>
 		<a  href="javascript:;" ><img class="delete" src="images/transparent.gif" ></a>
 		</td>
 		
 	</tr>
</c:forEach> 	
 </table>
 
<div style="clear:both;padding-top:5px;"></div>
</div>



<div class="submitlist" align="center">
<table>
	<tr>
		<td><input type="submit" value=" 确    定   " class="submitBtn" />
		</td>
	</tr>
</table>
</div>
</form>
</div>

<table id="temp_table" style="display:none">
 	<tr>
 		<td class="goodsname">
 		</td>
 		
 		<td>
 			<input type="text" name="price"  /> <input type="hidden" name="goodsid"  />
 		</td>
 		
 		
 		<td>
 		<a  href="javascript:;" ><img class="delete" src="images/transparent.gif" ></a>
 		</td>
 		
 	</tr>
</table>

<div id="goods_dlg"></div>
<script type="text/javascript">
function checkgoods(){
	if( $("#goodstable tr").size()==1 ){
		alert("请选择商品");
		return false;
	}
	return true;
}

	$(function() {
		$("form.validate").validate();

	 	<c:if test="${isEdit}" >
			 $("#start_hour").val(${start_hour});
			 $("#end_hour").val(${end_hour});
			 $("#index_${limitBuy.is_index}").attr("checked",true);
	 	</c:if>
	});
</script>