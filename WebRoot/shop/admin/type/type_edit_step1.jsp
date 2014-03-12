<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<script type="text/javascript" src="js/GoodsType.js"></script>
<div class="input">
	<div class="main-div">
	 <form class="validate" method="post" action="type!step2.do" name="theForm" id="theForm" >
	  <input type="hidden" name="is_edit" value="${is_edit }" />
	  <input type="hidden" name="goodsType.type_id" value="${goodsType.type_id }"  />
	   <table cellspacing="1" cellpadding="3" width="100%"  class="form-table">
	     <tr>
	       <td class="label" style="width:190px;"><label style="padding-left:5px;" class="text">类型名称</label></td>
	       <td><input type="text" name="goodsType.name" id="name"  maxlength="60" value="${goodsType.name }" dataType="string" required="true"/>       </td>
	      </tr>
	     <tr>
	       <td class="label" style="width:190px;"><label style="padding-left:5px;" class="text">此类型下商品是否拥有属性</label></td>
	       <td><input type="radio" name="goodsType.have_prop" value="1"  <c:if test="${goodsType.have_prop==1 }">checked</c:if>/>
	         是&nbsp;&nbsp;
	         <input type="radio" name="goodsType.have_prop" value="0" <c:if test="${goodsType.have_prop==0 }">checked</c:if>/>
	        否 &nbsp;&nbsp;<span class="help_icon" helpid="type_have_prop"></span></td>
	     </tr>	      

	     <tr>
	       <td class="label" style="width:190px;"><label style="padding-left:5px;"  class="text">此类型下商品是否拥有参数</label></td>
	       <td><input type="radio" name="goodsType.have_parm" value="1" <c:if test="${goodsType.have_parm==1 }">checked</c:if>/>
	         是&nbsp;&nbsp;
	         <input type="radio" name="goodsType.have_parm" value="0" <c:if test="${goodsType.have_parm==0 }">checked</c:if>/>
	        否 &nbsp;&nbsp;<span class="help_icon" helpid="type_have_parm"></span></td>
	     </tr>

	     <tr> 
	       <td class="label" style="width:190px;"><label style="padding-left:5px;"  class="text">此类型是否关联品牌</label></td>
	       <td><input type="radio" name="goodsType.join_brand" value="1"  <c:if test="${goodsType.join_brand==1 }">checked</c:if> />
	         是&nbsp;&nbsp;
	         <input type="radio" name="goodsType.join_brand" value="0" <c:if test="${goodsType.join_brand==0 }">checked</c:if>/>
	        否 &nbsp;&nbsp;<span class="help_icon" helpid="type_join_brand"></span></td>
	      </tr>
	     <tr>  
	       <td class="label" style="width:190px;"><label style="padding-left:5px;" class="text">此类型是否自定义字段</label></td>
	       <td><input type="radio" name="goodsType.have_field"  value="1" <c:if test="${goodsType.have_field==1 }">checked</c:if> />
	         是&nbsp;&nbsp;
	         <input type="radio" name="goodsType.have_field" <c:if test="${goodsType.have_field==0 }">checked</c:if> value="0" />
	        否 &nbsp;&nbsp;<span class="help_icon" helpid="type_have_field"></span></td>
	      </tr>	  	      
	   </table>
<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input type="button"	value=" 下一步   " class="submitBtn" /> 
   </td>
   </tr>
 </table>
</div>	   
	 </form>
	</div>
</div>
<script type="text/javascript">
$(function(){
	$("form.validate").validate();
	GoodsType.intChkNameEvent();
});
</script>
