<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<script type="text/javascript"> 
$(function(){
	 
 	 $(".goods_input .tab>li").click(function(){
 	 	 var tabid=$(this).attr("tabid");
 	 	
 	 	 $(".goods_input .tab>li").removeClass("active");
 	 	 $(this).addClass("active");
 	 	 $(".tab-page .tab-panel").hide();
 	 	 $("div[tabid=tab_"+tabid+"]").show();
 	 });
	 $("form.validate").validate();
	 $("input[type=text]").attr("autocomplete","off");
	 if (CKEDITOR.instances['intro']) {
	    CKEDITOR.remove(CKEDITOR.instances['intro']);
	 }
	 $('#intro' ).ckeditor();
	 $("input[type=submit]").click(function(){
		 $.Loading.text="正在生成缩略图，请稍候...";
	 });
	 $(".market_enable[value='${goodsView.market_enable}']").attr('checked',true);
});
	
</script>
<div class="input">

<form method="post" name="theForm" action="${actionName }" id="theForm" class="validate">
<c:if test="${is_edit }">
	<input type="hidden" name="goods.goods_id"
		value="${goodsView.goods_id  }" />
</c:if>

<div style="display: block;" class="goods_input">
<div class="tab-bar" style="position: relative;">
<ul class="tab">
	<li tabid="0" class="active">基本信息</li>
	<c:forEach var="tab" items="${tabs}">
		<li tabid="${tab.key }">${tab.value }</li>
	</c:forEach>
</ul>
</div>
<div class="tab-page">
 
${goodstype }
<div tabid="tab_0" class="tab-panel">
 

<table class="form-table" style="width:55%;float:left">

	<tr>
		<th>商品名称：</th>
		<td><input type="text" name="goods.name" id="goods_name"
			class="input_text" dataType="string" required="true"
			value="${goodsView.name }" style="width:250px"/></td>
	</tr>
	<tr>
		<th>商品编号：</th>
		<td><input type="text" name="goods.sn" class="input_text"
			value="${goodsView.sn}" />&nbsp;&nbsp;<span class="help_icon" helpid="goods_sn"></span></td>
	</tr>
	<tr>
		<th>是否上架销售：</th>
		<td>
		<input type="radio" name="goods.market_enable" checked="checked" value="1" class="market_enable" /> 是&nbsp;&nbsp;
		<input type="radio" name="goods.market_enable" value="0" class="market_enable"/> 否
		</td>
	</tr>


	<tr>
		<th>市场价格：</th>
		<td><input type="text" name="goods.mktprice" id="mktprice"
			class="input_text" dataType=float required="true"
			value="${goodsView.mktprice}"  style="width:100px"/>&nbsp;&nbsp;<span class="help_icon" helpid="goods_mktprice"></span></td>
	</tr>

	<tr id="point_tr">
		<th>积分：</th>
		<td><input type="text" name="goods.point" id="point"
			class="input_text" dataType=int
			value="${goodsView.point }" style="width:100px" />&nbsp;&nbsp;<span class="help_icon" helpid="goods_point"></span></td>
	</tr>
	

</table>
${album }

 
${goodsspec}

<table class="form-table">
	<tr>
		<th>详细描述：</th>
		<td>
		<textarea id="intro" name="goods.intro" cols="100" rows="8" style="width:98%;height:300px;"><c:out value="${goodsView.intro }" escapeXml="false"></c:out></textarea>

	     </td>
	</tr>
</table>
</div>

${goodstag }


${goodsseo }
${goodscomplex }
${goodsadjunct }
${goods_price }
${xianGuoGoodsPlugin }

 </div>

<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input name="submit" type="submit"	  value=" 确    定   " class="submitBtn" />
   </td>
   </tr>
 </table>
</div>

</div>

</form>
</div>