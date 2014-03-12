<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/Access.js"></script>
<style>
.sumtb th{text-align:right}
</style>

<form method="POST" >
<div class="grid">
	<div class="toolbar">
		 
		本月 由<select name="startday" id="startday">
		 <option value="0">全部</option>
		 <%
		 	for(int i=1;i<=31;i++){
		 %>
		 	 <option value="<%=i %>"><%=i %>日</option>
		 <%
		 	}
		 %> </select>
		 
		  至
		 
	<select name="endday" id="endday">
		 <option value="0">全部</option>
		 <%
		 	for(int i=1;i<=31;i++){
		 %>
		 	 <option value="<%=i %>"><%=i %>日</option>
		 <%
		 	}
		 %> </select>

		 <input type="submit" name="submit" value="查询" />   <a href="access!history.do" >其它月份报表下载</a>
		 		 
<div style="float:right;width:300px">
 	<table class="sumtb">
 		<tbody>
 			<tr>
 			<th style="text-align:right">本日来访:</th><td>${sData.todayaccess }次</td>
			<th style="text-align:right">本月来访:</th><td>${sData.monthaccess }次</td>
 			<th style="text-align:right">总访问:</th><td>${sData.sumaccess }次</td>
 			</tr>
 		</tbody>
 	</table>
</div>
		
		<div style="clear:both"></div>
		
	</div>



<grid:grid  from="webpage">

	<grid:header>
		<grid:cell  width="90px">会员</grid:cell> 
		<grid:cell  width="120px">IP</grid:cell> 
		<grid:cell   width="210px">来访时间（按天统计）</grid:cell> 
		<grid:cell  width="100px">地区</grid:cell> 
		<grid:cell  width="150px">页面名称</grid:cell> 
		<grid:cell  width="100px">时间跨度 </grid:cell>
		<c:if test="${runmodel==2}">
			<th>消耗积分</th>
		</c:if>
	</grid:header>

  <grid:body item="access">
  		 <grid:cell>
  		 <c:if test="${access.mname!=null}">
  		 	${access.mname}
  		 </c:if>
  		 <c:if test="${access.mname==null}">
  		 	访客/未登录
  		 </c:if>  		 
  		 </grid:cell>
        <grid:cell>
         <a href="javascript:;" class="expand" ip="${access.ip }" daytime="${access.daytime }"><img src="images/sitemapclosed.gif" border="0"/></a> 
         ${access.ip}
         </grid:cell>
        <grid:cell>
         <html:dateformat pattern="yyyy-MM-dd" time="${access.mintime*1000}"></html:dateformat>
       	从 <html:dateformat pattern="HH:mm:ss" time="${access.mintime*1000}"></html:dateformat>
                     至
        <html:dateformat pattern="HH:mm:ss" time="${access.maxtime*1000}"></html:dateformat>
         </grid:cell>          
        <grid:cell>${access.area} </grid:cell> 
        <grid:cell>${access.count}个 </grid:cell> 
        <grid:cell><html:dateformat pattern="HH:mm:ss" time="${access.sum_stay_time*1000-8*60*60*1000}"></html:dateformat></grid:cell> 

		<c:if test="${runmodel==2}">
           <td>${access.point}   </td> 
		</c:if>        
        
  </grid:body>  
  
</grid:grid>  

<div style="clear:both;padding-top:5px;"></div>
</div>
</form>	 

<script>
$(function(){
	$("#startday").val(${startday});
	$("#endday").val(${endday});
	Access.init();
});
</script>