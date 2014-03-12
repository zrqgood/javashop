<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/DlyTypeInput.js"></script>
<script type="text/javascript" src="js/jquery.checktree.js"></script>
<script type="text/javascript">

$(function(){
	DlyTypeInput.init();
	<c:if test="${isEdit}">
	var type = ${type.json};
	DlyTypeInput.editInit(type);
	</c:if>
	$("form.validate").validate();
});

</script>
          
<link rel="stylesheet" type="text/css" media="screen" href="setting/checktree.css" />
<style>
.division {
	line-height: 150%;
	margin: 10px;
	padding: 5px;
	white-space: normal;
}
deliveryArea.ol  {
list-style-type:decimal;
}
deliveryArea.ul, deliveryArea.ol {
margin:0;
padding:0;
}

li.division {
background:none repeat scroll 0 0 #FFFFFF;
border-color:#CCCCCC #BEC6CE #BEC6CE #CCCCCC;
border-style:solid;
border-width:1px 2px 2px 1px;
line-height:150%;
margin:10px;
padding:5px;
white-space:normal;
}

.lnk {
color:#000099;
cursor:pointer;
text-decoration:underline;
}
.checktree{
	height:350px;
	overflow-y:auto;
}

</style>
<c:if test="${!isEdit}">
<form action="dlyType!saveAdd.do" class="validate"  method="post">
</c:if>
<c:if test="${isEdit}">
<form action="dlyType!saveEdit.do" class="validate"  method="post" >
<input type="hidden" name="type.type_id" value="${type.type_id }"/>
</c:if>
<div class="input">
<div class="division">
<table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	<tbody>
		<tr>
			<th>配送方式名称:</th>
			<td><input type="text" maxlength="20"   name="type.name" value="${type.name }"  dataType="string" required="true"></td>
			<th>选择默认物流公司:</th>
			<td><select id="corp_id" name="type.corp_id">
				<option value="0">请选择物流公司</option>
				<c:forEach items="${logiList}" var="logi">
					<option value="${logi.id }" >${logi.name }</option>
				</c:forEach>
			</select></td>
		</tr>
		<tr>
			<th>重量设置:</th>
			<td>首重重量&nbsp;<select id="firstunit" name="firstunit">
				<option value="500" label="500克">500克</option>
				<option selected="selected" value="1000" label="1公斤">1公斤</option>
				<option value="1200" label="1.2公斤">1.2公斤</option>
				<option value="2000" label="2公斤">2公斤</option>
				<option value="5000" label="5公斤">5公斤</option>
				<option value="10000" label="10公斤">10公斤</option>
				<option value="20000" label="20公斤">20公斤</option>
				<option value="50000" label="50公斤">50公斤</option>
			</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;续重单位&nbsp;
			<select id="continueunit" name="continueunit">
				<option value="500" label="500克">500克</option>
				<option selected="selected" value="1000" label="1公斤">1公斤</option>
				<option value="1200" label="1.2公斤">1.2公斤</option>
				<option value="2000" label="2公斤">2公斤</option>
				<option value="5000" label="5公斤">5公斤</option>
				<option value="10000" label="10公斤">10公斤</option>
				<option value="20000" label="20公斤">20公斤</option>
				<option value="50000" label="50公斤">50公斤</option>
			</select></td>
			<th>&nbsp;</th>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<th>&nbsp;</th>
			<td><input type="checkbox" value="1" id="protect" name="type.protect">支持物流保价

			</td>
			<th>&nbsp;</th>
			<td>&nbsp;</td>
		</tr>
		<tr id="protectrate" style="display:none">
			<th>保价设置:</th>
			<td>费率&nbsp;<input type="text" id="protect_rate" name="type.protect_rate" style="width:25px"  dataType="float" />
			%
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			最低保价费&nbsp;<input type="text" style="width: 25px;" id="min_price" name="type.min_price" dataType="float" ></td>
			<th>&nbsp;</th>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<th>地区费用类型:</th>
			<td>
			<div id="deliveryAreaToggle">
				<label>
				<input type="radio"  value="1" name="type.is_same">统一设置
				</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label>
				<input type="radio" value="0" name="type.is_same">指定配送地区和费用
				</label>
			</div>
			</td>
			<th>&nbsp;</th>
			<td>&nbsp;</td>
		</tr>
	</tbody>
</table>
</div>


<div id="def_dexp" class="division">
<table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	<tbody>
		<tr>
			<th>配送费用:</th>
			<td>
			<div class="deliveryexpbox" style="line-height: 30px;">			
				<jsp:include page="dlyprice.jsp"></jsp:include>
				<label><input type="hidden" name="has_cod" value="0"/><input type="checkbox"  value="1" name="have_cod_check" >支持货到付款</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
			</td>
		</tr>
	</tbody>
</table>
</div>




<div id="def_area_dexp" class="division" style="display:none">
<table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	<tbody>
		<tr>
			<th>&nbsp;</th>
			<td><input type="checkbox" value="1" id="defAreaFee" name="defAreaFee">启用默认费用&nbsp;&nbsp;&nbsp;&nbsp;<span
				style="color: rgb(153, 153, 153);">注意：未启用默认费用时，不在指定配送地区的顾客不能使用本配送方式下订单</span></td>

		</tr>
		<tr id="area_dexp" style="display:none">
			<th>&nbsp;</th>
			<td>
			<div class="deliveryexpbox" style="line-height: 30px;">			
				<jsp:include page="dlyprice.jsp"></jsp:include>
			</div>
			</td>
		</tr>
	</tbody>
</table>
</div>


<div id="deliveryAreabox" class="division"  style="display:none">
<table cellspacing="1" cellpadding="3" width="100%" class="form-table">
	<tbody>
		<tr>
			<th>支持的配送地区:</th>
			<td>
			<div class="deliveryArea">
			<ol style="list-style: decimal outside none;">
			
			   
				<li style="line-height: 30px;display:none" class="division">
	 				
					<div class="deliverycity" >
	                     <span class="delCfgBtn" style="float: right;"><img border="none" title="删除" alt="删除" style="width: 16px; height: 16px; background-image: url('images/ImageBundle.gif'); background-repeat: no-repeat; background-position: 0pt -91px; cursor: pointer;" src="images/transparent.gif"></span>
	                  	 配送地区 <input type="text"  disabled="true"  readonly="true" name="areaGroupName" style="width: 300px;" dataType="string" required="true">
	                     <input type="hidden"  disabled="true" name="areaGroupId" ><img border="none" class="editAreaImg" title="编辑地区" alt="编辑地区" style="width: 16px; height: 16px; background-image: url('images/ImageBundle.gif'); background-repeat: no-repeat; background-position: 0pt -139px; cursor: pointer;" src="images/transparent.gif" class="regionSelect">
	                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                     <label><input type="hidden" disabled="true" name="has_cod" value="0"/><input type="checkbox" disabled="true" value="1" name="have_cod_check">支持货到付款</label>
	                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                </div>
	                
					<div class="deliveryexpbox" style="line-height: 30px;">			
						<jsp:include page="dlyprice.jsp"></jsp:include>
					</div>	                
                	
				</li>
				 
			<c:forEach var="typeArea"  items="${type.typeAreaList}" varStatus="area">
				<li style="line-height: 30px;" class="division" id="deliverycity_${area.index}">
	 				
					<div class="deliverycity" >
	                     <span class="delCfgBtn" style="float: right;"><img border="none" title="删除" alt="删除" style="width: 16px; height: 16px; background-image: url('images/ImageBundle.gif'); background-repeat: no-repeat; background-position: 0pt -91px; cursor: pointer;" src="images/transparent.gif"></span>
	                  	 配送地区
	                  	  <input type="text" readonly="true" name="areaGroupName" style="width: 300px;" value="${typeArea.area_name_group}"  dataType="string" required="true"/>
	                     <input type="hidden"  name="areaGroupId" value="${typeArea.area_id_group }" /><img border="none" class="editAreaImg" title="编辑地区" alt="编辑地区" style="width: 16px; height: 16px; background-image: url('images/ImageBundle.gif'); background-repeat: no-repeat; background-position: 0pt -139px; cursor: pointer;" src="images/transparent.gif" class="regionSelect">
	                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                     <label><input type="hidden" name="has_cod" value="0"/><input type="checkbox" value="1" name="have_cod_check">支持货到付款</label>
	                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                </div>
	                
					<div class="deliveryexpbox" style="line-height: 30px;">			
						<jsp:include page="dlyprice.jsp"></jsp:include>
					</div>	                
                	
				</li>
			</c:forEach>				
				
			</ol>
			</div>
			<input type="button" id="addCfgBtn" class="sysbtn" value="为指定的地区设置运费" />
			</div>
			</td>
		</tr>
	</tbody>
</table>
</div>


<div class="division">
        <table cellspacing="0" cellpadding="0" border="0" width="100%" class="form-table">
            <tbody><tr>
                <th>排序:</th>
                <td>
                    <input type="text"  size="5" id="ordernum" name="type.ordernum" value="${type.ordernum}"  dataType="int" required="true">
                </td>
            </tr>
            <tr>
                <th>状态:</th>
                <td><input type="radio" value="0" checked="" name="type.disabled">启用<input type="radio" value="1" name="type.disabled">关闭</td>
            </tr>
         </tbody></table>
  
</div>
    
	<div class="division">
    <table cellspacing="0" cellpadding="0" border="0" width="100%" class="form-table">
	  <tbody><tr>
	    <th>详细介绍:</th>
	    <td><textarea id="detail" name="type.detail" >${type.detail }</textarea>
	    
	    </td>
	  </tr>
	    </tbody></table>
    </div>    
</div>

<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input name="submit" type="submit"	  value=" 确    定   " class="submitBtn" />
   </td>
   </tr>
 </table>
</div>
</form>

<div id="area_selected">
<ul class="checktree" style="margin-left: 15px;">
</ul>
<div class="footContent" >
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



<div id="check_exp_dlg">
<div class="dlg"></div>
<div class="footContent" >
<div style="width: 200px; height: 40px; margin: 0pt auto;"
	class="mainFoot">
<table style="margin: 0pt auto; width: auto;">
	<tbody>
		<tr>
			<td><b class="save">
			<button class="submitBtn">确定</button>
			</b></td>
		</tr>
	</tbody>
</table>
</div>
</div> 
</div>

