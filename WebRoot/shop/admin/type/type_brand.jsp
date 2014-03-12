 <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="fck" %>
<SCRIPT LANGUAGE="JavaScript">
<!--


function  contains(obj_sel,option){
	var options = obj_sel.options;
	for(var i=0;i<options.length;i++){
		if(options[i].value == option.value){
			return true;
		}
	}
	
	return false;
}


function rightMove(){

	var brand_sel = document.getElementById("brand_sel");
	var choose_sel = document.getElementById("choose_sel");
	
	var brand_options = brand_sel.options;
	var s = choose_sel.options.length;
	for(var i=0;i<brand_options.length;i++){
		var is_selected = brand_options[i].selected;
		if(is_selected){
			 var option  = new Option(brand_options[i].text  ,brand_options[i].value); 
			 if(!contains(choose_sel,option)){
				choose_sel.options[s++] = new Option(brand_options[i].text  ,brand_options[i].value); 
			}
		}
	}
	

}




function leftMove(){
	$("#choose_sel>option").each(function(){
		var option= $(this);
		if(option.attr("selected")){
			option.remove();
		}
	});
}




function clean(){

	$("#choose_sel>option").each(function(){
		 $(this).remove();
	});
	
}

function selectChoose(){
	$("#choose_sel>option").attr("selected","true");
	return true;
}

//-->
</SCRIPT>
<div class="input">
<span id="title" value="设置商品类型关联的品牌"></span>
<div class="main-div">
 <form method="post" action="type!saveBrand.do" name="theForm1" id="theForm1" onsubmit="javascript:return selectChoose();">
   <table cellspacing="1" cellpadding="3" width="100%"  class="form-table">
     <tr>
       <td width="19%" height="77" align="left" >&nbsp;
      
       	<select name="brandlist" id="brand_sel" multiple="true" style="width:200px;height:200px; border: 1px solid #000000">
		<c:forEach items="${brandlist}" var="brand">
			<option value="${brand.brand_id }">${brand.name }</option>
		</c:forEach>
		</select>
       
       <td width="13%" align="center" > 
	   
	   <input name="submit" type="button" class="button" value="  ->  "  onclick="javascript:rightMove();"/>
	   <br/>
	   <input name="submit" type="button" class="button" value="  <-  "  onclick="javascript:leftMove();"/>
	   <br/>
	   <input name="submit" type="button" class="button" value="  重置  " onclick="javascript:clean();"/>
	   </td>
       <td width="68%" height="77" align="left" >
		<select name="chhoose_brands" id="choose_sel" multiple="true" style="width:200px;height:200px; border: 1px solid #000000">
		<c:forEach items="${goodsType.brandList }" var="brand">
			<option value="${brand.brand_id }">${brand.name }</option>
		</c:forEach>
		</select>	</td>
     </tr>
     
 
   </table>
<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input name="submit" type="submit"	  value=" 保存   " class="submitBtn" />
   </td>
   </tr>
 </table>
</div>	   
 </form>
</div>
</div>