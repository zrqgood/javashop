var GroupBuy={
	init:function(){
		var self = this;
		Eop.Dialog.init({id:"goods_dlg",modal:false,title:"添加相关商品", width:"600px"});
		$("#addRuleBtn").click(function(){
			self.addRule();
		});
		
		$("#ruletable .delete").click(function(){
			self.removeRule($(this));
		});

		function  appendGoods(goodsList){
			self.appendGoods(goodsList);
		}
		
		$("#selgoodsBtn").click(function(){
			GoodsSelector.open("goods_dlg",appendGoods);
		});
		
		$("#dis0").click(function(){
			$("#discount").attr("disabled",false);
			$("#price").attr("disabled",true);
		});

		$("#dis1").click(function(){
			$("#discount").attr("disabled",true);
			$("#price").attr("disabled",false);
		});
		
		$("#discount").change(function(){
			var price  = parseFloat( $("#price").val() ); 
			var discount = parseFloat( $("#discount").val() ); 
			if(price!=0 && discount!=0)
			$("#price").val( price*discount  );
		});
	},
	
	appendGoods:function(goodsList){
		if(goodsList.length>1) {alert("只能选择一个商品"); return ;}
		if(goodsList.length==0) {alert("请选择一个商品"); return ;}
		var goods = goodsList[0];
		$("#goodsid").val(goods.goods_id);
		$("#goodsname").html(goods.name);
		$("#price").val(goods.price);
	}
	,
	addRule:function(){
		var self = this;
		var html = $( $("#temp_table").html() );
		var btn =html.find(".delete");
		$("#ruletable").append( html );
		 btn.click(function(){
			 self.removeRule($(this));
		 });
	},
	removeRule:function(btn){
		btn.parents("tr").remove();
	}
};
$(function(){
	GroupBuy.init();
});