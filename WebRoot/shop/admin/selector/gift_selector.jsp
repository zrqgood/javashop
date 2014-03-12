<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<form id="giftSerchform">
<div class="input">
<table class="form-table">
	<tr>
		<th>
	赠品名称 ：</th>
		<td><input type="text" name="keyword"  value="${keyword }"/><input type="button" name="searchBtn" value="搜索"/></td>
	</tr>
</table>		
</div>

<div style="clear:both;padding-top:5px;"></div>

<div class="grid" id="giftlist">

<grid:grid  from="webpage" ajax="yes" >

	<grid:header>
		<grid:cell  width="50px"><input type="checkbox" name="toggleChk"/></grid:cell> 
		<grid:cell sort="name" width="250px">赠品名称</grid:cell>
		<grid:cell sort="cat_id"  width="100px">分类</grid:cell> 
		<grid:cell >超始时间</grid:cell>
		<grid:cell >终止时间</grid:cell>
 		<grid:cell >库存</grid:cell>
	</grid:header>


  <grid:body item="gift" >
  
  		<grid:cell><input type="checkbox" name="giftid" value="${gift.fo_id }" />${gift.fo_id }</grid:cell>
        <grid:cell>&nbsp;${gift.fo_name }</grid:cell> 
        <grid:cell>&nbsp;${gift.cat_name} </grid:cell> 
        <grid:cell>&nbsp; <html:dateformat time="${gift.startdate}" pattern="yyyy-MM-dd"></html:dateformat>  </grid:cell>
        <grid:cell>&nbsp; <html:dateformat time="${gift.enddate}" pattern="yyyy-MM-dd"></html:dateformat>  </grid:cell> 
        <grid:cell>${goods.score} </grid:cell> 
         
  </grid:body>
  
</grid:grid>
</div>
<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input   type="button" value=" 确    定   "  class="submitBtn" />
   </td>
   </tr>
 </table>
</div>	

</form>
