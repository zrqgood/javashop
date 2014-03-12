var Bind={
	init:function(){
		var self= this;
		Eop.Dialog.init({id:"product_item_input",modal:false,title:"添加捆绑商品", width:"650px",height:"400px"});
		function fillgoods(productlist){
			self.appendGoods(productlist);
		}
		$("#adj_item_addbtn").click(function(){
				ProductSelector.open("product_item_input",fillgoods);
		});
		
		this.bindEvent();
	},
	bindEvent:function(){
		var that = this;
		$(".adjunctitem_body .delete").unbind("click");
		$(".adjunctitem_body .delete").bind("click",function(){
			$(this).parent("a").parent("td").parent("tr").remove();
			that.cal();
		});
		$(".adjunctitem_body input[name=pkgnum]").unbind("change");
		$(".adjunctitem_body input[name=pkgnum]").bind("change", function(){
			that.cal();
		});
		that.cal();
	},
	cal:function(){
		var mktprice = 0;
		var weight = 0;
		$("input[name=pkgnum]").each(function(){
			mktprice += parseFloat($(this).val()) * parseFloat($(this).attr("mkt_price")); 
			weight += parseFloat($(this).val()) * parseFloat($(this).attr("weight")); 
			});
		//计算价格
		$("#bind_mktprice").val(mktprice);
		//计算重量
		$("#bind_weight").val(weight);
	},
	appendGoods:function(productList){
		for(i in productList){
			var product  = productList[i];
			var html = "<tr class='product_item_" + product.product_id + "'><td><a href='javascript:;'><img class='delete' src='images/transparent.gif' ></a><input type='hidden' name='product_id' value='" + product.product_id + "' /></td><td>"+product.name+"</td><td><input type='text' name='pkgnum' value='1' mkt_price='"+ product.price +"' weight='"+product.weight+"'/></td></tr>";
			$(html).appendTo($(".adjunctitem_body"));
		}
		this.bindEvent();
	}
};