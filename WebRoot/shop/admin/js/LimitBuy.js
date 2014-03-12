var LimitBuy={
	init:function(){
		var self = this;
		Eop.Dialog.init({id:"goods_dlg",modal:false,title:"添加相关商品", width:"600px"});
		function  appendGoods(goodsList){
			self.appendGoods(goodsList);
		}
		
		$("#addGoodsBtn").click(function(){
			GoodsSelector.open("goods_dlg",appendGoods);
		});	
		
		$("#goodstable .delete").click(function(){
			self.removeGoods($(this));
		});
		
		
		
	},
	appendGoods:function(goodsList){
		
		for(i in goodsList){
			var goods = goodsList[i];
			var html = $( $("#temp_table").html() );
			html.find(".goodsname").html(goods.name);
			html.find("input[name=price]").val(goods.price);
			html.find("input[name=goodsid]").val(goods.goods_id);
			html.find(".delete").click(function(){
				this.removeGoods($(this));
			});
			$("#goodstable").append(html);
		}
	 
	},
	removeGoods:function(btn){
		btn.parents("tr").remove();
	}
};

$(function(){
	LimitBuy.init();
});