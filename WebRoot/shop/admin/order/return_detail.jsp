<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<div class="input">
	<div class="main-div">
		   <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
		     <tr>
		       <th><label class="text">订单编号：</label></th>
		       <td>${rorder.ordersn }
		       </td>
		      </tr>
		     <tr>
		       <th><label class="text">状态：</label></th>
		       <td>${rorder.state }
		       </td>
		      </tr>
		     <tr>
		       <th><label class="text">类型</label></th>
		       <td>${rorder.type }</td>
		     </tr>
		     
		          
		     <tr>
		       <th><label class="text">联系人</label></th>
		       <td>
		        ${rorder.linkman }
				</td>
		     </tr>
		     <tr>
		       <th><label class="text">联系电话</label></th>
		       <td>
		        ${rorder.linkman }
				</td>
		     </tr>	
		     <tr>
		       <th><label class="text">收货地址</label></th>
		       <td>
		        ${rorder.address }
				</td>
		     </tr>
		     <tr>
		       <th><label class="text">附件情况</label></th>
		       <td>
		       	${rorder.attachment }
				</td>
		     </tr>	
		     <tr>
		       <th><label class="text">产品外观</label></th>
		       <td>
		        ${rorder.facade }
				</td>
		     </tr>	
		     <tr>
		       <th><label class="text">包装情况</label></th>
		       <td>
		        ${rorder.wrap }
				</td>
		     </tr>	
		     <tr>
		       <th><label class="text">是否带发票</label></th>
		       <td>
		        ${rorder.invoice}
				</td>
		     </tr>	
		     		     	     
		     <tr>
		       <th><label class="text">邮寄方式</label></th>
		       <td>
		        ${rorder.shiptype }
				</td>
		     </tr>
		     <tr>
		       <th><label class="text">问题描述</label></th>
		       <td>
		        ${rorder.remark }
				</td>
		     </tr>		     
	     <tr>
		       <th><label class="text">提交时间</label></th>
		       <td>
		        ${rorder.add_time}
				</td>
		     </tr>			     		     		     	     
		   </table>
 	   
 	   
	</div>
	
<div style="clear:both;padding-top:5px;"></div>	
	
<div class="grid" style="width:600px">
 
 <table>
 <tr>
      <th>货号</th>
      <th>商品名称</th>
      <th>价格</th>
      <th>购买量</th>
      <th>已发货量</th> 
 </tr>
 <c:forEach items="${rorder.itemList}" var="item">
          <tr>
          <td>${item.sn }</td>
          <td class="textleft">
          <a target="_blank" href="../goods-${item.goods_id }.html">${item.name }</a>
   		 </td>
         
          <td ><fmt:formatNumber value="${item.price}" type="currency" pattern="￥.00"/></td>
          <td >${item.num}</td>
          <td >${item.ship_num}</td>
        </tr>
</c:forEach> 	
 </table>
 
<div style="clear:both;padding-top:5px;"></div>
</div>

</div>
 