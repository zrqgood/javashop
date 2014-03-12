<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/commonlibs.jsp"%>

<script type="text/javascript" src="../js/DomainOperator.js">
</script>
<link href="css/admin.css" rel="stylesheet" type="text/css" />
<div class="input">
<form  class="validate"  action="userSite!editSave.do" method="post" name="theForm" id="theForm" enctype="multipart/form-data">
<div class="tableform">
<h4>网站基本设置</h4>
<div style="position: static;" class="division">
<table cellspacing="0" cellpadding="0" border="0" width="100%"
	class="shop-setting">
	<tbody>
		<tr>
			<th>网站名称：<input type="hidden" name="eopSite.id"  value="${eopSite.id }" /></th>
			<td>
			<input type="text" name="eopSite.sitename" 
			value="${eopSite.sitename }" dataType="string" required="true" style="width:325px"/></td>
		</tr>
		<tr>
			<th>网站标题：</th>
			<td>
			<input type="text" name="eopSite.title" 
			value="${eopSite.title }" dataType="string" required="true" style="width:325px"/></td>
		</tr>
		<tr>
			<th><label class="text">META_KEYWORDS：<br/>
				(页面关键词)：</label></th>
			<td>
			<input type="text" name="eopSite.keywords" 
			value="${eopSite.keywords }" style="width:325px"/></td>
		</tr>
		<tr>
			<th><label class="text">META_DESCRIPTION：<br/>
					(页面描述)</label></th>
			<td>
			<textarea name="eopSite.descript" style="width:405px"/>${eopSite.descript }</textarea></td>
		</tr>
		<tr>
			<th>版权声明：</th>
			<td>
			<textarea name="eopSite.copyright" style="width:405px"/><c:out value="${eopSite.copyright }" escapeXml="true"/></textarea></td>
		</tr>
	</tbody>
</table>
</div>
<h4>网站Logo设置</h4>
<div style="position: static;" class="division">
<table cellspacing="0" cellpadding="0" border="0" width="100%"
	class="shop-setting">
	<tbody>
		<tr>
			<th>网站前台Logo：</th>
			<td width=250>
			<div id="sitelogo" style="float: none; position: static;">
			<c:if test="${eopSite.logofile!=null }">
			<img style="float: none; position: static;" src="${eopSite.logofile }" />&nbsp;
			</c:if>
			<div></div>
			<input type="file" name="cologo" id="cologo" size="10" style="width:145px"/> </div>
			</td>
			<th>网站后台Logo：</th>
			<td width=250>
			<div id="sitelogo" style="float: none; position: static;">
			<c:if test="${eopSite.bklogofile!=null }">
			<img style="float: none; position: static;" src="${eopSite.bklogofile }" />&nbsp;
			</c:if>
			<div></div>
			<input type="file" name="bklogo" id="bklogo" size="10" style="width:145px"/> </div>
			</td>
		</tr>
		<tr>
			<th>网站登录界面Logo：</th>
			<td width=250>
			<div id="sitelogo" style="float: none; position: static;">
			<c:if test="${eopSite.bkloginpicfile!=null }">
			<img style="float: none; position: static;" src="${eopSite.bkloginpicfile }" />&nbsp;
			</c:if>
			<div></div>
			<input type="file" name="bkloginpic" id="bkloginpic" size="10" style="width:145px"/> </div>
			</td>
			<th>网站ico文件：</th>
			<td width=250>
			<div id="sitelogo" style="float: none; position: static;">
			<c:if test="${eopSite.icofile!=null }">
			<img style="float: none; position: static;" src="${eopSite.icofile }" />&nbsp;
			</c:if>
			<div></div>
			<input type="file" name="ico" id="ico" size="10" style="width:145px"/> </div>
			</td>
		</tr>
	</tbody>
</table>
</div>
<table cellspacing="0" cellpadding="0" border="0"
	class="shop-setting">
	<tr>
		<td width="400px"><h4>站长信息&nbsp;&nbsp;<span class="help_icon" helpid="site_userinfo"></span></h4></td>
		<td><h4>在线客服&nbsp;&nbsp;<span class="help_icon" helpid="site_state"></span></h4></td>
	</tr>	
</table>
<div class="division" style="height: 207px">
<table cellspacing="0" cellpadding="0" border="0"
	class="shop-setting">
	<tbody>
		<tr>
			<th style="width: 100px">站长姓名：</th>
			<td style="width:230px">
			<input type="text" name="eopSite.username" 
			value="${eopSite.username }" dataType="string" required="true" style="width: 133px"></td>
			<th style="width: 102px">是否开启：</th>
			<td>
			<input type="radio" name="eopSite.state" namespace="state" id="eopsitekfon0"
				default="1" <c:if test="${eopSite.state==1 }">checked="checked"</c:if> value="1"><label
				for="eopsitekfon0">是</label>&nbsp;<input type="radio"
				name="eopSite.state" 
				 namespace="state" value="0" <c:if test="${eopSite.state==0 }">checked="checked"</c:if> id="eopsitekfon1"><label
				for="eopsitekfon1">否</label></td>
		</tr>
		<tr>
			<th style="width: 100px">性别：</th>
			<td style="width:230px"><input type="radio" name="eopSite.usersex" namespace="usersex" id="eopsiteusersex0"
				default="0" <c:if test="${eopSite.usersex==0 }">checked="checked"</c:if> value="0"><label
				for="eopsiteusersex0">男</label>&nbsp;<input type="radio"
				name="eopSite.usersex" id="eopsiteusersex1"
				 namespace="usersex" value="1" <c:if test="${eopSite.usersex!=0 }">checked="checked"</c:if> ><label
				for="eopsiteusersex1">女</label></td>
			<th style="width: 102px;">QQ：</th>
			<td>
			<input style="float:left" type="checkbox" value="1" name="eopSite.qq" <c:if test="${eopSite.qq == 1 }">checked</c:if> />&nbsp;&nbsp;
			<input type="text" vtype="text"
				name="eopSite.qqlist" value="${eopSite.qqlist }" style="width: 143px;">&nbsp;&nbsp;<span class="help_icon" helpid="qq_format"></span></td>
		</tr>

		<tr>
			<th style="width: 100px">电话：</th>
			<td style="width:230px">
			<input type="text" vtype="text"
				name="eopSite.usertel" value="${eopSite.usertel }" style="width: 133px">  
			</td>
			<th style="width: 102px">MSN：</th>
			<td>
			<input style="float:left" type="checkbox" value="1" name="eopSite.msn" <c:if test="${eopSite.msn == 1 }">checked</c:if> />&nbsp;&nbsp;
			<input type="text" vtype="text"
				name="eopSite.msnlist" value="${eopSite.msnlist }" style="width: 143px;">&nbsp;&nbsp;<span class="help_icon" helpid="msn_format"></span></td>
		</tr>
		<tr>
			<th style="width: 100px">手机：</th>
			<td style="width:230px">
			<input type="text" vtype="text"
				name="eopSite.usermobile" value="${eopSite.usermobile }" style="width: 133px"></td>
			<th style="width: 102px">旺旺：</th>
			<td>
			<input style="float:left" type="checkbox" value="1" name="eopSite.ww" <c:if test="${eopSite.ww == 1 }">checked</c:if> />&nbsp;&nbsp;
			<input type="text" vtype="text"
				name="eopSite.wwlist" value="${eopSite.wwlist }" style="width: 143px;">&nbsp;&nbsp; <span class="help_icon" helpid="ww_format"></span> 
			</td>
		</tr>
		<tr>
			<th style="width: 100px">其它电话：</th>
			<td style="width:230px">
			<input type="text" vtype="text"
				name="eopSite.usertel1" value="${eopSite.usertel1 }" style="width: 133px"></td>
			<th style="width: 102px">电话：</th>
			<td>
			<input type="checkbox" value="1" name="eopSite.tel" <c:if test="${eopSite.tel == 1 }">checked</c:if> />&nbsp;&nbsp;
			<input type="text" vtype="text"
				name="eopSite.tellist" value="${eopSite.tellist }" style="width: 163px"></td>
		</tr>
		<tr>
			<th style="width: 100px">电子邮件：</th>
			<td style="width:230px">
			<input type="text" vtype="text" required="true" datatype="email"
				name="eopSite.useremail" value="${eopSite.useremail }" style="width: 133px"></td>
			<th style="width: 102px">营业时间：</th>
			<td>
			<input type="checkbox" value="1" name="eopSite.wt" <c:if test="${eopSite.wt == 1 }">checked</c:if> />&nbsp;&nbsp;
			<input type="text" vtype="text"
				name="eopSite.worktime" value="${eopSite.worktime }" style="width: 163px"></td>
		</tr>

	</tbody>
</table>
	

</div>
<h4>其它设置</h4>
<div class="division">
<table cellspacing="0" cellpadding="0" border="0" width="100%"
	class="shop-setting">
	<tbody>
		<tr>
			<th>备案号：</th>
			<td><input type="text" vtype="text"
				name="eopSite.icp" value="${eopSite.icp }" style="width: 176px"><span class="notice-inline">此处填写您在工信部备案管理网站申请的备案编号，详请登陆<a
				target="_blank" href="http://www.miibeian.gov.cn">官方网站</a></span></td>
		</tr>
		<tr>
			<th>详细地址：</th>
			<td><span id="el-e927a-51407"><input type="text" vtype="text" required="true" dataType="string"
				name="eopSite.address" value="${eopSite.address }" style="width: 519px"></span></td>
		</tr>
		<tr>
			<th style="height: 34px">邮政编码：</th>
			<td style="height: 34px">
			<input type="text" vtype="text" required="true" dataType="zipcode"
				name="eopSite.zipcode" id="el-e927a-babcf4" value="${eopSite.zipcode }"
				autocomplete="off"></td>
		</tr>
		<tr>
			<th style="height: 34px">联系人：</th>
			<td style="height: 34px">
			<input type="text" vtype="text" required="true" dataType="string"
				name="eopSite.linkman" id="el-e927a-babcf4" value="${eopSite.linkman }"
				autocomplete="off"></td>
		</tr>
		<tr>
			<th style="height: 34px">联系电话：</th>
			<td style="height: 34px">
			<input type="text" vtype="text" required="true" dataType="string"
				name="eopSite.linktel" value="${eopSite.linktel }"
				autocomplete="off"></td>
		</tr>
		<tr>
			<th style="height: 34px">电子邮件：</th>
			<td style="height: 34px">
			<input type="text" vtype="text" required="true" dataType="email"
				name="eopSite.email" value="${eopSite.email }"
				autocomplete="off"></td>
		</tr>
		<tr>
			<th>开启站点：</th>
			<td>
			<input type="radio" name="eopSite.siteon" namespace="siteon" id="eopsiteon0"
				default="0" <c:if test="${eopSite.siteon==0 }">checked="checked"</c:if> value="0"><label
				for="eopsiteon0">是</label>&nbsp;<input type="radio"
				name="eopSite.siteon" id="eopsiteon1"
				 namespace="siteon" value="1" <c:if test="${eopSite.siteon!=0 }">checked="checked"</c:if> ><label
				for="eopsiteon1">否</label></td>
		</tr>
		<tr>
			<th>关闭原因：</th>
			<td>
			<textarea style="width:519px;height:200px" name="eopSite.closereson">${eopSite.closereson }</textarea>
			</td>
		</tr>		
	</tbody>
</table>
</div>
<div class="submitlist" align="center">
 <table>
 <tr><td >
  <input name="submit" type="submit"	  value="保存设置" class="submitBtn" />
   </td>
   </tr>
 </table>
</div>
</div>
</form>
</div>




<script type="text/javascript">
$("form.validate").validate();
</script>



	


