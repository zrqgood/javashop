<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<script type="text/javascript" src="js/GoodsType.js"></script>
<script type="text/javascript">$(function(){Param.init();})</script>
<script type="text/javascript">
<!--
function setParamNum(){
	document.getElementById("paramnum").value='';
	var result = checkInputs();
	if(result){	
		var param_div = document.getElementById("param_div");
		var tbls  = param_div.getElementsByTagName("table");
		var paramnum = document.getElementById("paramnum").value;
		
		for(var i=0;i<tbls.length;i++){
		
			var texts = tbls[i].getElementsByTagName("input");
			
			if(paramnum!=''){
				paramnum+=",";
			}
			
			paramnum+=(texts.length-1);
			
		}
		document.getElementById("paramnum").value   =paramnum;
		return true;
	}
	return false;
	
}
function checkInputs(){
	var groups = document.getElementsByName("groupnames");
	var params = document.getElementsByName("paramnames");
	var paramsLen = params.length;
	var groupsLen = groups.length;
	for(var i=1;i<groupsLen;i++){
		if(groups[i].value==""){
			alert("请输入参数组名称");
			return false;
		}
	}
	for(var i=1;i<paramsLen;i++){
		if(params[i].value==""){
			alert("请输入参数名称");
			return false;
		}
	}	
	return true;
}

//-->
</script>

	<form method="post" action="type!saveParams.do" name="theForm1" id="theForm1" onsubmit="javascript:return setParamNum();">
	 <input type="hidden" name="paramnum" id="paramnum" />
	 
	<div style="text-align:center">
	<div class="list-div" id="listDiv"   style="width:100%;text-align:left">
	
		<div class="toolbar">
			<ul>
				<li><a href="javascript:;" id="paramAddBtn" >新增参数组</a></li>
	 		</ul>
			<div style="clear:both"></div>
		</div>
		
	<div class="input">	
		<div id="param_div">
			<c:forEach var="group" items="${goodsType.paramGroups}">
			    <table cellpadding="3" cellspacing="0" style="width:100%;"  >
			
			     <tr class="group">
			       <td  style="background-color:#DDEEF2;">参数组名</td>
			       <td  style="background-color:#DDEEF2;"><input type="text" name="groupnames"  maxlength="60" value="${group.name }" /></td>
			       <td width="64%"  style="background-color:#DDEEF2;">
			       <span><a href="javascript:;"  class="sysbtn addBtn">新增参数</a></span>
			       <span style="display:inline;float:left"><a href="javascript:;" ><img class="delete" src="images/transparent.gif" ></a></span>
			       </td>
			     </tr>
			       
			     <c:forEach var="gparam" items="${group.paramList}">   
			     	<tr class="param">
			        <td width="10%" align="right">参数名</td>
			        <td width="26%"><input type="text" name="paramnames" value="${gparam.name }"/></td>
			        <td>&nbsp;<a href="javascript:;" ><img class="delete" src="images/transparent.gif" ></a></td>
			        </tr>
			     </c:forEach>
			   </table>
			</c:forEach>   
		 </div>
	</div>
<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input name="submit" type="submit"	  value=" 下一步   " class="submitBtn" />
   </td>
   </tr>
 </table>
</div>		 
	 
	</div>
	</div>
	</form>

 