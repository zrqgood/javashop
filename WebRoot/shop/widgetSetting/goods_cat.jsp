<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table  border="0" cellspacing="0" cellpadding="2"  >
	     <tr><td height="30" width="200">
	        <select id="goodstype" name="goodstype" eop_type="widget_params" >
			<option value="goods_cats" selected>商品分类</option>
		    </select>
		 </td></tr>
</table>
<table  border="0" cellspacing="0" cellpadding="2"  >
	     <tr>
	     <td>商品分类模板:</td>
	     <td height="30" width="200">
	        <select id="catmode" name="catmode" eop_type="widget_params" >
			<option value="defualt">默认</option>
			<option value="pulldown" >折叠式</option>
			<option value="popup" >弹出式</option>
		    </select>
		 </td></tr>
</table>
<table  border="0" cellspacing="0" cellpadding="2"  >
	     <tr>
	     <td>商品分类循环深度:</td>
	     <td height="30" width="200">
	        <select id="catdepth" name="catdepth" eop_type="widget_params" >
			<option value="1">显示一级分类</option>
			<option value="2" >显示二级分类</option>
			<option value="3" >显示三级分类</option>
		    </select>
		 </td></tr>
</table>
