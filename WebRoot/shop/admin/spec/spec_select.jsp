<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<link type="text/css" rel="stylesheet" href="${ctx }/shop/admin/spec/css/style.css" />

<style>
	.specList li{
		float:left;
		margin-left:5px;
	}
	.specList ul li{
		 list-style:none outside none;
	}	

	
	.dialogbody{
		background:none repeat scroll 0 0 #FFFFFF;
		overflow-x:hidden;
		overflow-y:auto;
		height: 400px;   visibility: visible; opacity: 1; position: relative;
	}
	#goods_img_selected li{
		float:left;
		margin-left:5px;
	}
	
	#goods_img_selected ul{
		height:300px
	}
</style>
<div class="dialogbody">
<div class="specList">
<ul> 
	<c:forEach items="${specList}" var="spec">
		<li><input type="checkbox" name="spec" value="${spec.spec_id}" spec_name="${spec.spec_name}">${spec.spec_name}<c:if test="${spec.spec_memo!=null &&spec.spec_memo!='' }">[${spec.spec_memo}]</c:if></li>
	</c:forEach>
</ul>
</div>

<div style="clear: both"></div>
<div  class="spec_tab">
	<div class="tab-bar" style="position: relative;">
	<ul class="tab">
		 
	</ul>
	</div>

<div class="tab-page spec-items division" style="margin: 2px 0px 0px; border: 1px solid rgb(204, 204, 204);display:none">
</div>	

</div>


<div id="goods_img_selected">
<ul>
 </ul>

<div class="footContent" style="">
<div style="width: 200px; height: 40px; margin: 0pt auto;"
	class="mainFoot">
<table style="margin: 0pt auto; width: auto;">
	<tbody>
		<tr>
			<td><b class="save">
			<button class="submitBtn">保存</button>
			</b></td>
		</tr>
	</tbody>
</table>
</div>
</div> 
</div>
 </div>
<script type="text/javascript">
loadScript("spec/js/spec_value.js");
</script>
