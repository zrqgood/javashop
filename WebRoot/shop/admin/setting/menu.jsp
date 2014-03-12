<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>JavaShop Menu</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../style/general.css" rel="stylesheet" type="text/css" />
<link href="../style/menu.css" rel="stylesheet" type="text/css" />
 
</head>
<body>

<div id="main-div">
<div id="menu-list">

<ul>

<div onclick="toggleMenu(0);" class="explode">
<img src="../images/menu_minus.gif" id="menu_img_0" style="padding-right:5px;"/>配送方式管理
</div>
	 
	<li class="menu-item"><a href="area_list.jsp" target="main-frame">配送地区</a></li>
	<li class="menu-item"><a href="dly_type_list.jsp" target="main-frame">配送方式</a></li>   
	<li class="menu-item"><a href="logi_list.jsp" target="main-frame">物流公司</a></li>    
     
</ul> 



<ul>

<div onclick="toggleMenu(0);" class="explode">
<img src="../images/menu_minus.gif" id="menu_img_0" style="padding-right:5px;"/>支付方式管理
</div>
	 
	<li class="menu-item"><a href="pay_list.jsp" target="main-frame">支付方式</a></li>
     
</ul> 


<ul>

<div onclick="toggleMenu(0);" class="explode">
<img src="../images/menu_minus.gif" id="menu_img_0" style="padding-right:5px;"/>网店管理员
</div>
	 
	<li class="menu-item"><a href="admin_list.jsp" target="main-frame">管理员列表</a></li>
     
</ul> 
<ul>
<div onclick="toggleMenu(0);" class="explode">
<img src="../images/menu_minus.gif" id="menu_img_0" style="padding-right:5px;"/>备份管理
</div>
	 <li class="menu-item"><a href="../backup!setting.do" target="main-frame">备份设置</a></li>
	<li class="menu-item"><a href="../backup.do" target="main-frame">系统备份</a></li>
	<li class="menu-item"><a href="../backup!regain.do" target="main-frame">系统恢复</a></li>
     
</ul> 
<ul>
<div onclick="toggleMenu(0);" class="explode">
<img src="../images/menu_minus.gif" id="menu_img_0" style="padding-right:5px;"/>系统升级
</div>
	 <li class="menu-item"><a href="../update!list.do" target="main-frame">系统更新</a></li>
     
</ul> 
</div>

<div id="help-div" style="display:none">
<h1 id="help-title"></h1>
<div id="help-content"></div>
</div>
</div>

</body>
</html>