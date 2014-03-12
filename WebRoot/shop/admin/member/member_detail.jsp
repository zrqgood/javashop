<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="${staticserver }/js/admin/Tab.js"></script>

<script type="text/javascript" src="js/MemberDetail.js"></script>
<style>
<!--
.division {
background:none repeat scroll 0 0 #FFFFFF;
border-color:#CCCCCC #BEC6CE #BEC6CE #CCCCCC;
border-style:solid;
border-width:1px 2px 2px 1px;
line-height:150%;
margin:10px;
padding:5px;
white-space:normal;
}
.division table{margin:0;padding:0} 
.orderdetails_basic th {
color:#336699;
text-align:left;
white-space:nowrap;
}

.division th {
background:none repeat scroll 0 0 #E2E8EB;
border-right:1px solid #CCCCCC;
font-size:14px;
text-align:right;
white-space:nowrap;
width:140px;
}
.division th, .division td {
border-color:#FFFFFF #DBE2E7 #DDDDDD #FFFFFF;
border-left:1px solid #FFFFFF;
border-right:1px solid #DBE2E7;
border-style:solid;
border-width:1px;
padding:5px;
vertical-align:top;
}

.tableform {
background:none repeat scroll 0 0 #EFEFEF;
border-color:#DDDDDD #BEC6CE #BEC6CE #DDDDDD;
border-style:solid;
border-width:1px;
margin:10px;
padding:5px;
}

h5  {
font-size:1em;
font-weight:bold;
}
h1, h2, h3, h4, h5, h6 {
clear:both;
color:#111111;
margin:0.5em 0;
}

#order_dialog .con{
		background:none repeat scroll 0 0 #FFFFFF;
		overflow-x:hidden;
		overflow-y:auto;
		height: 400px;   visibility: visible; opacity: 1; position: relative;
}
ul.tab li{padding:0 10px;}
-->
</style>

<div style="display: block;" class="member_detail">
	<div class="tab-bar" style="position: relative;">
	<ul class="tab">
		<li id="base" class="active">会员信息</li>
		<li id="edit">编辑会员</li> 
		<li id="orderLog">他的订单</li>
		<li id="editPoint">他的积分</li>
		<li id="pointLog">积分历史</li>
		<li id="advance">他的预存款</li>
		<li id="ask">他的咨询</li>
		<li id="discuss">他的评论</li>
		<li id="remark">会员备注</li>
	</ul>
	</div>
	
	<div class="tab-page">
		<div id="baseTab"></div>
		<div id="editTab"></div>
		<div id="orderLogTab"></div>
		<div id="editPointTab"></div>
		<div id="pointLogTab"></div>
		<div id="advanceTab"></div>
		<div id="askTab"></div>
		<div id="discussTab"></div>
		<div id="remarkTab"></div>
	</div>
</div>
<script>

$(function(){
	MemberDetail.init(${member_id});
});
</script>