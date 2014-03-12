<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>

<style>
.mycontent  {
background:none repeat scroll 0 0 #EFEFEF;
border-color:#DDDDDD #BEC6CE #BEC6CE #DDDDDD;
border-style:solid;
border-width:1px;
margin:10px;
padding:5px;
_height:auto;
overflow:hidden;
}
li{
background:none repeat scroll 0 0 #FFFFFF;
border-color:#CCCCCC #BEC6CE #BEC6CE #CCCCCC;
border-style:solid;
border-width:1px 2px 2px 1px;
line-height:150%;
margin:10px;
padding:5px;
white-space:normal;
}
.cell{
width:50%;
float:left;
}
h4 {
font-size:1.2em;
font-weight:bold;
line-height:1.25;
}
table{
border:1px solid #EFEFEF;
margin:2px;
width:100%;
}
</style>
<div class="grid">
	<div class="mycontent">
		<ul>
			<div class="cell">
			<h4>客户平均订单金额<br/></h4>
			<li>
				<table>
					<thead>
					<tr>
						<th>
							总订单金额
						</th>
						<th>
							总订单数
						</th>
						<th>
							平均订单金额
						</th>
					</tr>
					</thead>
					<tr>
						<td>
							<fmt:formatNumber value="${rankall.amount }" type="currency" pattern="￥.00"/>
						</td>
						<td>
							${rankall.num }
						</td>
						<td>
							<fmt:formatNumber value="${rankall.amount / (rankall.num == 0 ? 1 : rankall.num) }" type="currency" pattern="￥.00"/>
						</td>
					</tr>
				</table>
			</li>
			</div>
			<div class="cell">
			<h4>每次访问平均订单金额</h4>
			<li>
				<table>
					<thead>
					<tr>
						<th>
							总订单金额
						</th>
						<th>
							总访问次数
						</th>
						<th>
							平均订单金额
						</th>
					</tr>
					</thead>
					<tr>
						<td>
							<fmt:formatNumber value="${rankall.amount }" type="currency" pattern="￥.00"/>
						</td>
						<td>
							${rankall.view_count }
						</td>
						<td>
							<fmt:formatNumber value="${rankall.amount / (rankall.view_count == 0 ? 1 : rankall.view_count) }" type="currency" pattern="￥.00"/>
						</td>
					</tr>
				</table>
			</li>
			</div>
			<div class="cell">
			<h4>订单转化率</h4>
			<li>
				<table>
					<thead>
					<tr>
						<th>
							总订单量
						</th>
						<th>
							总访问次数
						</th>
						<th>
							订单转化率
						</th>
					</tr>
					</thead>
					<tr>
						<td>
							${rankall.num }
						</td>
						<td>
							${rankall.view_count }
						</td>
						<td>
							<fmt:formatNumber value="${rankall.num / (rankall.view_count == 0 ? 1 : rankall.view_count) * 100 }" maxFractionDigits="2" />%
						</td>
					</tr>
				</table>
			</li>
			</div>
			<div class="cell">
			<h4>注册会员购买率</h4>
			<li>
				<table>
					<thead>
					<tr>
						<th>
							有过订单的会员数
						</th>
						<th>
							总会员数
						</th>
						<th>
							注册会员购买率
						</th>
					</tr>
					</thead>
					<tr>
						<td>
							${rankall.buy_member_count }
						</td>
						<td>
							${rankall.member_count }
						</td>
						<td>
							<fmt:formatNumber value="${rankall.buy_member_count / (rankall.member_count == 0 ? 1 : rankall.member_count) * 100 }" maxFractionDigits="2" />%
						</td>
					</tr>
				</table>
			</li>
			</div>
			<div class="cell">
			<h4>平均会员订单量</h4>
			<li>
				<table>
					<thead>
					<tr>
						<th>
							总订单数
						</th>
						<th>
							总会员数
						</th>
						<th>
							平均会员订单量
						</th>
					</tr>
					</thead>
					<tr>
						<td>
							${rankall.num }
						</td>
						<td>
							${rankall.member_count }
						</td>
						<td>
							<fmt:formatNumber value="${rankall.num / (rankall.member_count == 0 ? 1 : rankall.member_count) }" maxFractionDigits="2" />
						</td>
					</tr>
				</table>
			</li>
			</div>
		</ul>
	</div>
</div>


