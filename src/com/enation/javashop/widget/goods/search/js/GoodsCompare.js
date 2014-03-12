var GoodsCompare={
	init:function(){
		var self = this;
		$(".btncmp").click(function(){
			 self.add($(this).attr("goodsid"), $(this).attr("goodsname"),$(this).attr("typeid"));
			 self.show();
		});
		$("#clearn_compare").click(function(){
			self.clean();
		});
		$("#goods_compare .close").click(function(){
			$("#goods_compare").hide();
		});		
		$("#goods_compare").scrollFollow(); 
	},
	add:function(goodsid,goodsname,typeid){
		var goodsstr =$.cookie('cmpgoods');
		
		if(goodsstr ){
			var goodsArray  = goodsstr.split(",");
			for(i in goodsArray){
				var goods  = goodsArray[i].split("|");
				
				if(parseInt(typeid)!=parseInt(goods[2])){
					alert("只能对比同一类型的商品");
					return ;
				}
				if(parseInt(goodsid)==parseInt(goods[0])){
					return;
				}
				
			}
		}
		if(goodsstr){
			goodsstr+=","+ goodsid +"|"+ goodsname+"|"+typeid;
		}else{
			goodsstr= goodsid +"|"+ goodsname+"|"+typeid;
		}
		$.cookie('cmpgoods', goodsstr );	
	},
	show:function(){
		var self =this;
		var goodsbox=$("#compare_goods");
		goodsbox.empty();
		var goodsStr =$.cookie('cmpgoods');
		var goodsArray  = goodsStr.split(",");
		for(i in goodsArray){
			var goods  = goodsArray[i].split("|");

			var goodsrow ='<div goodsid="'+goods[0]+'" class="division clearfix">';
			goodsrow+='<div class="span-3">';
			goodsrow+='<span title="'+goods[1]+'">';
			goodsrow+='<a href="goods-'+goods[0]+'.html">'+goods[1]+'</a></span>';
			goodsrow+='</div>';
			goodsrow+='<a href="javascript:;" class="floatRight lnk">删除</a>';
			goodsrow+='</div>';
			goodsbox.append(goodsrow);
		}
		$("#compare_goods .lnk").click(function(){
			var goodsid = $(this).parent().attr("goodsid");
			self.remove(goodsid);
			$(this).parent().remove();
		});
		$("#goods_compare").show();
	},
	remove:function(goodsid){
		var goodsstr =$.cookie('cmpgoods');
		var newStr="";
		if(goodsstr ){
			var goodsArray  = goodsstr.split(",");
			for(i in goodsArray){
				var goods  = goodsArray[i].split("|");
				if(parseInt(goodsid)==parseInt(goods[0])){
					continue;
				}
				if(newStr!="")
					newStr+=",";
				newStr+=goods[0]+"|"+goods[1];
			}
			$.cookie('cmpgoods', newStr );	
		}		
	},
	clean:function(){
		$.cookie('cmpgoods', null );
		$("#compare_goods").empty();
	} 
};