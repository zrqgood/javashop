<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>淘宝开放平台测试</title>
<style type="text/css">
li{
	list-style-type:none;
}
</style>
<script type="text/javascript" src="${staticserver }/js/common/jquery-1.3.2.js"></script>
<script type="text/javascript">
var topApi = "${top_api}";
var topResult = {
	result:${top_result},
	getSales:function(){
		var html = "<table><thead><tr><th align='left' width='100'>买家昵称</th><th align='left'>订单列表</th></tr></thead><tbody>";
		for(var i=0;i<Number(this.result.rsp.totalResults);i++){
			html = html + "<tr>";
			html = html + "<td>" + this.result.rsp.trades[i].buyer_nick + "</td>";
			html = html + "<td><ul>";
			for(var j=0;j<this.result.rsp.trades[i].orders.length;j++){
				html = html + "<li>名称：" + this.result.rsp.trades[i].orders[j].title + "</li>";
				html = html + "<li>价格：" + this.result.rsp.trades[i].orders[j].price + "</li>";
				html = html + "<li>数量：" + this.result.rsp.trades[i].orders[j].num + "</li>";
				html = html + "<li><hr height='1' /></li>";
			}
			html = html + "</ul></td>"; 
			html = html + "</tr>";
		}
		return html + "</tbody></table>";
	},
	getItems:function(){
		var html = "<table><thead><tr><th align='left' width='600'>标题</th><th align='right'>售价</th></tr></thead><tbody>";
		for(var i=0;i<Number(this.result.rsp.totalResults);i++){
			html = html + "<tr>";
			html = html + "<td>" + this.result.rsp.items[i].title + "</td><td align='right'>" + this.result.rsp.items[i].price + "</td>";
			html = html + "</tr>";
		}
		return html + "</tbody></table>";
	}
};
$(function(){
	if(topApi=="taobao.trades.sold.get")
		$("#display").append($(topResult.getSales()));
	else(topApi=="taobao.items.onsale.get")
		$("#display").append($(topResult.getItems()));
});
</script>
</head>
<body>
<div>
	<p>应用参数</p>
	<p>appKey:${top_appkey }</p>
	<p>sign:${top_sign }</p>	
	<p>sessionKey:${top_session }</p>
</div>
<hr />
<div>
	<form action="taobao.do" method="post">
		<input name="top_session" type="hidden" value="${top_session }"/>
		<input name="top_appkey" type="hidden" value="${top_appkey }"/>
		<input name="top_sign" type="hidden" value="${top_sign }"/>
		
		<input name="top_api" type="radio" value="taobao.trades.sold.get" title="taobao.trades.sold.get">获取卖出列表</input>
		<input name="top_api" type="radio" value="taobao.items.onsale.get" title="taobao.items.onsale.get">获取商品列表</input>
		<input type="submit" value="获取产品列表"></input>
	</form>
</div>
<hr />
<div id="display">

</div>
</body>
</html>