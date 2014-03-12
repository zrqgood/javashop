<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="fck" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>管理中心 - 支付方式配置 </title>
<meta name="robots" content="noindex, nofollow" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="style/general.css" rel="stylesheet" type="text/css" />
<link href="style/main.css" rel="stylesheet" type="text/css" />
<link href="style/validator.css" rel="stylesheet" type="text/css" />

</head>
<body>
<h1>
<span class="action-span"><a href="setting/pay_list.jsp">支付方式列表</a></span>
<span class="action-span1"><a href="../home.jsp">管理中心</a>  - 支付方式配置  </span>
<div style="clear:both"></div>
</h1><!-- start add new category form -->
<div class="main-div">
 <form method="post" action="payment!save.do" name="theForm" id="theForm"  >
 <input type="hidden" name="id" value="${payCfg.id}"/>
   <table cellspacing="1" cellpadding="3" width="100%">
     <tr>
       <td width="27%" class="label">支付方式名称</td>
       <td width="40%">支付宝</td>
       <td width="33%" align="left">&nbsp;</td>
     </tr>

     <tr>
       <td width="27%" class="label">合作者身份(parterID)</td>
       <td width="40%"><input type="text" name="parterID" id="parterID"  style="width:300px"  value="${payCfg.parterID }" />       </td>
       <td width="33%" align="left">&nbsp;</td>
     </tr>
     	 
     <tr>
       <td width="27%" class="label">交易安全校验码(key)</td>
       <td width="40%"><input type="text" name="key" id="key"  style="width:300px" value="${payCfg.key }" />       </td>
       <td width="33%" align="left">&nbsp;</td>
     </tr>
	 
     <tr>
       <td width="27%" class="label">支付宝帐户(email)</td>
       <td width="40%"><input type="text" name="seller_email" id="seller_email"  style="width:300px" maxlength="60" value="${payCfg.seller_email }" />       </td>
       <td width="33%" align="left">&nbsp;</td>
     </tr>
     	 
     <tr>
       <td width="27%" class="label">支付费率</td>
       <td width="40%"><input type="text" name="payCfg.pay_fee" id="name"  style="width:100px" value="${payCfg.pay_fee }" />%       </td>
       <td width="33%" align="left">&nbsp;</td>
     </tr>
	 
     <tr>
       <td width="27%" class="label">描述</td>
       <td colspan="2">
	   <fck:editor basePath="/editor/" id="payCfg.biref" width="80%" height="450" toolbarSet="Basic">${payCfg.biref }</fck:editor>	   </td>
      </tr>	 	 	 	 
     <tr>
       <td colspan="3" align="center"><br /> 
           <input name="submit" type="submit" class="button" value=" 确定 " class="btn"/>
           <input name="reset" type="reset" class="button" value=" 重置 " class="btn"/>       </td>
     </tr>
   </table>
 </form>
</div>

<jsp:include page="../footer.jsp"/>
</body>
</html>
