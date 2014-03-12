<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/Member.js"></script>
<div class="grid">
<form action="member!memberlist.do" method="post">
	<div class="toolbar">
	
		<ul>
			<li><a href="member!add_member.do">添加</a></li>
			<li><a href="javascript:;" id="delMBtn">删除</a></li>
			<li style="display:none;"><a href="javascript:;" id="delBtn">删除</a></li>
		</ul>
		<div style="margin-left:10px">&nbsp;&nbsp;&nbsp;&nbsp;用户名:<input type="text" style="width:150px" name="uname" value="${uname }"/>&nbsp;&nbsp;&nbsp;&nbsp;姓名或公司名称:<input type="text" style="width:150px" name="name" value="${name }"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="submit" value="搜索"></div>
		<div style="clear:both"></div>
	</div>
</form>
<form method="POST" id="gridform">
<grid:grid  from="webpage" >

	<grid:header>
	<grid:cell ><input type="checkbox" id="toggleChk" /></grid:cell> 
	<grid:cell sort="uname">用户名</grid:cell> 
	<grid:cell sort="mobile">手机</grid:cell> 
	<grid:cell sort="lv_id">会员等级</grid:cell> 
	<grid:cell sort="email">电子邮件</grid:cell> 
	<grid:cell sort="regtime">注册时间</grid:cell> 
	<grid:cell>上次登录日期</grid:cell>
	<grid:cell>本月登录次数</grid:cell>
	<grid:cell sort="province|city">城市</grid:cell> 
	<grid:cell sort="sex">性别</grid:cell> 
	<grid:cell >登录</grid:cell> 
	<grid:cell>操作</grid:cell>
	</grid:header>

  <grid:body item="member">
	 <grid:cell><input type="checkbox" name="id" value="${member.member_id}" /></grid:cell>
     <grid:cell >${member.uname }</grid:cell>
     <grid:cell >${member.mobile} </grid:cell> 
     <grid:cell >${member.lv_name} </grid:cell>
     <grid:cell >${member.email} </grid:cell>
     <grid:cell ><html:dateformat pattern="yyyy-MM-dd HH:mm:ss" time="${member.regtime}"></html:dateformat> </grid:cell>
     <grid:cell><html:dateformat pattern="yyyy-MM-dd" time="${member.lastlogin*1000}"/></grid:cell>
     <grid:cell>${member.logincount}</grid:cell>      
	<grid:cell>${member.province}-${member.city} </grid:cell>
    <grid:cell><c:if test="${member.sex==1}">男</c:if><c:if test="${member.sex==2}">女</c:if></grid:cell> 
    <grid:cell><a href="member!sysLogin.do?name=${member.uname }" target="_blank" />登录</a> </grid:cell>
    <grid:cell><a href="member!detail.do?member_id=${member.member_id }" ><img class="modify" src="images/transparent.gif" ></a></grid:cell>
  </grid:body>  
  
</grid:grid>  
</form>	
<DIV style="clear:both;margin-top:5px;"></DIV>

</div>


 
 

