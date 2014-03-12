<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>管理中心 - 管理员列表 </title>
<meta name="robots" content="noindex, nofollow" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script language="JavaScript" src="../../js/utils.js"></script>
<script language="JavaScript" src="../../js/jquery.js"></script>
<script language="JavaScript" src="../../js/form.js"></script>
<script language="JavaScript" src="../../js/grid.js"></script>

<link href="../../admin/style/general.css" rel="stylesheet" type="text/css" />
<link href="../../admin/style/main.css" rel="stylesheet" type="text/css" />
<link href="../../admin/style/grid.css" rel="stylesheet" type="text/css" />
<link href="../../css/pager.css" rel="stylesheet" type="text/css" />

<script language="javascript" > 
grid_url='admin_list_panel.jsp';
grid_id="admin_list";

function del(){
	 
	 var flag = Utils.chk_selected("id");
	 
	 if(!flag){ 
	 	alert('请选择要删除管理员');
	 	return ;
	 }
	 
	 if (confirm('确认删除此管理员吗?删除后将不可恢复')){
		 gridpost('admin_from','../admin!delete.do');
	 }
	 
}

 


function admin_list_loadSuccess(){
	cell_color();
  
}


</script>
</head>
<body>

<h1>

<span class="action-span1"><a href="../home.jsp">管理中心</a>  - 管理员列表 </span>
<div style="clear:both"></div>
</h1>  
 
<html:ajaxpanel id="admin_list" url="admin_list_panel.jsp"></html:ajaxpanel >

<jsp:include page="../../admin/footer.jsp"/>
</body>
</html>


