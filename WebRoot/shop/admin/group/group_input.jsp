<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/GroupBuy.js"></script>
<script type="text/javascript" src="js/Selector.js"></script>

<style>
#ruletable input{width:100px;}
</style>
<div class="input" >

<c:if test="${isEdit}" >
<form method="post" action="groupBuy!saveEdit.do" class="validate" id="brandForm" onsubmit="javascript:return checkgoods();" enctype="multipart/form-data">
<input type="hidden" name="groupBuy.groupid" value="${groupBuy.groupid }" />
</c:if>
<c:if test="${!isEdit}" >
<form method="post" action="groupBuy!saveAdd.do" class="validate" id="brandForm"  onsubmit="javascript:return checkgoods();" enctype="multipart/form-data">
</c:if>


<table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	<tr>
		<th><label class="text">团购标题：</label></th>
		<td>
		<input type="text" name="groupBuy.title" value="${groupBuy.title }" dataType="string" required="true"  style="width:300px" />
		</td>
	</tr>
	
	<tr>
		<th><label class="text">团购描述：</label></th>
		<td>
		<textarea name="groupBuy.descript" dataType="string" required="true"  style="width:300px">${groupBuy.descript }</textarea>
		</td>
	</tr>
		
	<tr>
		<th><label class="text">商品：</label></th>
		<td>
		<span id="goodsname">${groupBuy.goods.name }</span>
		<input type="hidden" name="groupBuy.goodsid" value="${groupBuy.goodsid }"  id="goodsid"/>
		<input type="button" id="selgoodsBtn" value="选择" />
		</td>
	</tr>	
	
	<tr>
		<th><label class="text">价格：</label></th>
		<td>
		
		按折扣:<input type="radio" name="groupBuy.dis_type" id="dis0" value="0" checked="true"/><input type="text" name="groupBuy.discount" id="discount" value="${groupBuy.discount }" style="width:100px" />(9折输入0.9)
		<br>按价格:<input type="radio" name="groupBuy.dis_type" id="dis1" value="1"/><input disabled="true" type="text" name="groupBuy.price" id="price" value="${groupBuy.price }" style="width:100px" />
		
		 </td>
	</tr>
	
	<tr>
		<th><label class="text">开始时间：</label></th>
		<td>
	 <c:if test="${isEdit}" >
		<input type="text" name="start_time"   dataType="date" required="true" class="dateinput" value="<html:dateformat pattern="yyyy-MM-dd" time="${groupBuy.start_time*1000}"></html:dateformat>" />
	</c:if>	
	 <c:if test="${!isEdit}" >
		<input type="text" name="start_time"   dataType="date" required="true" class="dateinput"   />
	</c:if>		
		 </td>
	</tr>


			
	<tr>
		<th><label class="text">结束时间：</label></th>
		<td>
		 <c:if test="${isEdit}" >
		<input type="text" name="end_time"   dataType="date" required="true" class="dateinput" value="<html:dateformat pattern="yyyy-MM-dd" time="${groupBuy.end_time*1000}"></html:dateformat>"/>
		</c:if>	
 		<c:if test="${!isEdit}" >
		<input type="text" name="end_time"   dataType="date" required="true" class="dateinput" />
		</c:if>			
		 </td>
	</tr>
	
	<tr>
		<th><label class="text">图片：</label></th>
		<td>
	 		<input type="file" name="imgFile" >
		</td>
	</tr>
	
	<c:if test="${groupBuy.img!=null &&groupBuy.img!=''}">
	<tr>
		<th></th>
		<td>
		<img src="${groupBuy.image }" />
		
		</td>
	</tr>
	</c:if>
	<tr>
		<th><label class="text">是否首页显示：</label></th>
		<td>
	 		<input type="radio" id="index_0" name="groupBuy.is_index" value="0" checked=true/>否&nbsp;
	 		<input type="radio" id="index_1" name="groupBuy.is_index" value="1" />是&nbsp;
		</td>
	</tr>
		
	<tr>
		<th><label class="text">购买次数：</label></th>
		<td>
		 
		<input type="text" name="groupBuy.buy_count" dataType="int" required="true"  value="${groupBuy.buy_count }"/>
	 
		 </td>
	</tr>
	
	
	<tr>
		<th><label class="text">描述</label></th>
		<td><textarea id="brief" name="groupBuy.content">${groupBuy.content }</textarea></td>
	</tr>
</table>

<div style="clear:both;padding-top:5px;"></div>


<div class="grid" style="width:600px">
	<div class="toolbar">
		<ul>
			<li><a href="javascript:;" id="addRuleBtn">增加规则</a></li>
		</ul>
		<div style="clear:both"></div>
	</div>
 
 <table id="ruletable">
 <c:forEach items="${ruleList}" var="rule">
 	<tr>
 		<td>
 			 <input type="text" name="start"  value="${rule.start_time }">小时至<input type="text" name="end"  value="${rule.end_time }">小时&nbsp;
 			增加<input type="text" name="num" value="${rule.num }"/>
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
 		<td>
 			 <input type="text" name="start"  value="0">小时至<input type="text" name="end"  value="0">小时&nbsp;
 			增加<input type="text" name="num" value="1"/>
 		</td>
 		<td>
 		<a  href="javascript:;" ><img class="delete" src="images/transparent.gif" ></a>
 		</td>
 	</tr>
</table>
<div id="goods_dlg"></div>
<script type="text/javascript">
function checkgoods(){
	if( $("#goodsid").val()=='' ){
		alert("请选择商品");
		return false;
	}
	return true;
}

	$(function() {
		$("form.validate").validate();
	 	$('#brief').ckeditor();

	 	<c:if test="${isEdit}" >
			$("#dis${groupBuy.dis_type}").click();
			$("#index_${groupBuy.is_index}").attr("checked",true);
	 	</c:if>
	});
</script>