<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="${staticserver }/js/admin/jqModal1.1.3.1.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/jqDnR.js"></script>
<script type="text/javascript" src="${staticserver }/js/admin/Eop.AdminUI.js"></script>
<script type="text/javascript" src="js/GoodsSelector.js"></script>
<style>

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


</style>
<form  action="promotion!saveStep2.do" method="post" >
<div class="division" >
	<h4>选择优惠的商品</h4>
	<div class="grid" id="pmtGoodsList">
	<grid:grid  from="goodsList" pager="no" >
		<grid:header>
			<grid:cell  width="50px"><input type="button" name="addBtn" id="addBtn" value="添加"/></grid:cell> 
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
	 		<div class="submitlist" align="center">
		 <table>
		 <tr><td >
		  <input name="submit" type="submit" value=" 下一步   " class="submitBtn" />
		   </td>
		   </tr>
		 </table>
		</div>
</div>
</form>

<div id="selector" >
<div id="selector_con"></div>
<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input name="btn" type="button" value=" 确    定   " id="okBtn" class="submitBtn" />
   </td>
   </tr>
 </table>
</div>	
</div>
