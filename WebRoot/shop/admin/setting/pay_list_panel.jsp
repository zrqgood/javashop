<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../commons/taglibs.jsp"%>
<sql:datasource id="paylist" type="list">
  
 <sql:append >
 	select * from js_payment_cfg
 </sql:append>

</sql:datasource>

 
<form action="" id="area_from" method="post">
<div class="grid-div" id="gridDiv">
 
	
<grid:grid  from="paylist" pager="no" type="list">

	<grid:header>
	<grid:cell  width="20px"><input type="checkbox" onclick="javascript:Utils.chk_ctl_all('id',this.checked);" /></grid:cell> 
	<grid:cell>支付方式名称</grid:cell> 
	<grid:cell  width="100px">操作</grid:cell> 
	</grid:header>

  <grid:body item="pay">
	 <grid:cell><input type="checkbox" name="id" value="${pay.id }" /></grid:cell>
     <grid:cell>${pay.name} </grid:cell> 
     <grid:cell align="center"> <a href="../payment!edit.do?id=${pay.id }" ><img src="../images/icon_edit.gif" border="0"/></a> </grid:cell> 
  </grid:body>  
  
</grid:grid>  

</div>
</form>
 

