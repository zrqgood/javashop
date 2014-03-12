<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<script type="text/javascript" src="${staticserver }/js/admin/jqModal1.1.3.1.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/jqDnR.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/Eop.AdminUI.js"></script>
<script type="text/javascript" src="js/Selector.js"></script>

<style>

.tableform {
background:none repeat scroll 0 0 #EFEFEF;
border-color:#DDDDDD #BEC6CE #BEC6CE #DDDDDD;
border-style:solid;
border-width:1px;
margin:10px;
padding:5px;
}


.division  {
background:none repeat scroll 0 0 #FFFFFF;
border-color:#CCCCCC #BEC6CE #BEC6CE #CCCCCC;
border-style:solid;
border-width:1px 2px 2px 1px;
line-height:150%;
margin:10px;
padding:5px;
white-space:normal;
}

h4  {
font-size:1.2em;
font-weight:bold;
line-height:1.25;
}
h1, h2, h3, h4, h5, h6 {
clear:both;
color:#111111;
margin:0.5em 0;
}

</style>
<div class="input">
<form  action="promotion!save.do" method="post"  class="validate">
<input type="hidden" name="pluginid" value="${pluginid }"/>
<input type="hidden" name="activityid" value="${activityid }"/>
<input type="hidden" name="pmtid" value="${pmtid }"/>
<div class="tableform">
<h4>优惠方式</h4>
<div class="division">
<table class="form-table"  cellspacing="0" cellpadding="0" border="0" width="100%">
	<tbody>
	
		<c:if test="${conditions.hasOrder}">
		<tr>
			<th>订单优惠条件：</th>
			<td><input type="text" value="${money_from }"   required="true" dataType="float" size="10" name="money_from" autocomplete="off"><b>≤</b>订单金额 ＜
			 <input type="text"    name="money_to" value="9999999" size="10" autocomplete="off" disabled="true">  
			</td>
		</tr>
		</c:if>
		<c:if test="${conditions.hasMemberLv}">
		<tr>
			<th>允许参加会员等级：</th>
			<td >
			<c:forEach items="${lvList}" var="lv">	
				<label> <input type="checkbox"  value="${lv.lv_id }" name="lvidArray" <c:if test="${lv.selected}">checked="checked"</c:if> >${lv.name }</label>
			</c:forEach>	
			<span id="lvtip" class="tip error">此项为必填</span>	
			</td>
		</tr>
		</c:if>
	</tbody>
</table>
</div>
</div>

<c:if test="${conditions.hasGoods}">
<div class="tableform">
<h4>选择优惠的商品</h4>
<div class="division" >
	
	<div class="grid" id="pmtGoodsList">
	<grid:grid  from="goodsList" pager="no" >
		<grid:header>
			<grid:cell  width="50px"><input type="button" name="addBtn" id="addBtn" value="添加"/>&nbsp;<input type="button" name="addCatBtn" id="addCatBtn" value="按分类添加" />&nbsp;<input type="button" name="addTagBtn" id="addTagBtn" value="按标签添加" /></grid:cell> 
			<grid:cell sort="name" width="250px">商品名称</grid:cell>
		</grid:header>
	  <grid:body item="goods" >
	  		<grid:cell><a href="javascript:;"><img class="delete" src="images/transparent.gif"/></a>
	  		<input type="hidden" name="goodsidArray" value="${goods.goods_id }" />
	  		</grid:cell> 
	        <grid:cell>&nbsp;${goods.name } </grid:cell> 
	  </grid:body>
	</grid:grid>
	</div> 	
</div>
</div>
</c:if>


<div class="tableform">
<h4>其他设置</h4>
<div class="division">
<table class="form-table"  cellspacing="0" cellpadding="0" border="0" width="100%">
	<tbody>
		<tr>
			<th>活动期间是否允许使用优惠券:</th>
			<td>是<input type="radio" checked="checked" value="1" name="ifcoupon"> 
			          否<input type="radio" value="0" name="ifcoupon"></td>
		</tr>
		<tr>
			<th>活动规则开始时间:</th>
			<td>
			<input type="text"   readonly dataType="date" required="true" class="dateinput" value="<fmt:formatDate value="${time_begin}" pattern="yyyy-MM-dd"/>" name="time_begin" autocomplete="off">
			</td>
		</tr>
		<tr>
			<th>活动规则结束时间:</th>
			<td>
			<input type="text"  readonly dataType="date" required="true" class="dateinput"   value="<fmt:formatDate value="${time_end}" pattern="yyyy-MM-dd"/>" name="time_end" autocomplete="off">
			</td>
		</tr>
	</tbody>
</table>
</div>
</div>


<div class="tableform">
<h4>优惠内容</h4>
<hr class="clear">
<div class="division">
 ${solutionHtml }
</div>
</div>


<div class="tableform">
<h4>规则描述</h4>
<textarea vtype="textarea" style="" required="true" rows="6" cols="60" name="describe" type="textarea">${ describe}</textarea>
<br>
<span class="fontcolorGray">此描述会显示在前台相关页面展示给顾客 </span>
</div>
 
 
 		<div class="submitlist" align="center">
		 <table>
		 <tr><td >
		   <input name="button" type="button" value=" 上一步 " class="submitBtn" id="backBtn"/>
		   <input name="submit" type="submit" value=" 保存   " class="submitBtn" />
		   </td>
		   </tr>
		 </table>
		</div>
		
		
</form>

</div>


<div id="selector" >
</div>


<script type="text/javascript">

function checklv(){
	var result=false;
	$("input[name=lvidArray]").each(function(){
		if(this.checked) result = true;
	});
	if(result)
		$("#lvtip").hide();
	else
		$("#lvtip").show();
    if($("#gift_selector").length>0){
    	result=($("input[name=giftidArray]").length>0);
    	if(result)
        	$("#gifttip").hide();
    	else
        	$("#gifttip").show();
    }
  	return result;
	 
}

var Selector={
	init:function(){
		var self = this;
		if(($("#gift_selector").length>0)&&($("input[name=giftidArray]").length>0))
			$("#gifttip").hide();
		function appendGoods(goodsList){
			self.appendGoods(goodsList);
		}
		$("#addBtn").click(function(){
			GoodsSelector.open("selector",appendGoods);
		});

		$("#addCatBtn").click(function(){
			GoodsCatSelector.open("selector",appendGoods);
		});

		$("#addTagBtn").click(function(){
			GoodsTagSelector.open("selector",appendGoods);
		});
		this.bindDeleteEvent();
	},
	bindDeleteEvent:function(){
	 	$("#pmtGoodsList .delete").unbind("click");
	 	$("#pmtGoodsList .delete").bind("click",function(){
		 	$(this).parents("tr").remove();
		 });
	}
	,
	appendGoods:function(goodsList){
	 	for(i in goodsList){
		 	var goods  = goodsList[i];
		 	if($("input[name=goodsidArray][value=" + goods.goods_id + "]").length<=0)
		 	$("#pmtGoodsList tbody").append("<tr><td><a href=\"javascript:;\"><input type='hidden' name='goodsidArray' value='"+goods.goods_id+"'><img class=\"delete\" src=\"images/transparent.gif\"/></a></td><td>"+goods.name+"</td></tr>");
		}

	 	this.bindDeleteEvent();
	}
};




$(function(){
	$("#lvtip").hide();
	$("form.validate").validate({},checklv);
	$("input[name=ifcoupon][value=${ifcoupon}]").attr("checked",true);

	$("#backBtn").click(function(){history.go(-1);});
	Eop.Dialog.init({id:"selector",modal:false,title:"选择商品", height:"200px",width:"700px"});
	Selector.init();
});


</script>
