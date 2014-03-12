var Eop = Eop||{};


Eop.Spec={
 
	init:function(haveSpec){
		$(".Numinput").numinput({name:"num"});
		
		if(haveSpec==1){
			var self = this;
			this.refresh();
			$("#goods-spec .spec-item a[specvid!='']").click(function(){
	
				var link = $(this);
				if(link.attr("class")!='selected')
					self.specClick($(this));
				
				return false;
			});
		}else{
//			canBuy();
//			var pro =this.findProduct(product_ar);
			BtnTipRefresh.refresh(Products);
		}
		
	},
	specClick:function(specLink){
		specLink.parents("ul").find("a[specvid!='']").removeClass("selected");
		specLink.parent().parent().parent().parent().find("em").addClass("checked");
		specLink.addClass("selected");
		var img  = spec_imgs[parseInt(specLink.attr("specvid"))];
		if(img){
			img = img.substring(0,img.indexOf('.'))+"_thumbnail"+img.substring(img.indexOf('.'),img.length);
			$(".strip_wrapper img[src='"+img+"']").click();
		}
		this.refresh();
	},
	findGoodsImg:function(vid){
		for(i in  spec_imgs){
			var specimg = spec_imgs[i];
			if(specimg.specvid==parseInt(vid)){
				return specimg.goods_img;
			}
			                        
		}
	},
	//根据当前选择的规格找到货品
	findProduct:function(vidAr){
		var pros =[];
		//判断两个数组元素值是否相同，不判断位置情况
		function arraySame(ar1,ar2){
			//if(ar1.length!=ar2.length) return false;
			
			for(i in ar1){
				if($.inArray(ar1[i],ar2)==-1){ //不存在
					return false;
				}
			}
			return true;
		}
		
		var self = this;
	 
		for(i in Products){
			var product= Products[i];
			if(arraySame(vidAr,product.specs)){
				pros[pros.length] =product; 
			}
		}	
		 
		return pros;
	}
	,
	refresh:function(){
		var self = this;
		var product_ar=[];
 
		$("#goods-spec .spec-item a.selected").each(function(){
			var link = $(this);
			product_ar[product_ar.length]=parseInt(link.attr("specvid"));
		});
				
		var pro =this.findProduct(product_ar);
		
		for(i in Refresh){
			Refresh[i].refresh(pro);
		}
		if(pro){
			
		}
		else{
			alert("缺货");
		}
	}

};

var SelectTipRefresh={
	refresh:function(pro){
			var i=0;
			var specHtml="";
			$("#goods-spec .spec-item a.selected").each(function(){
				if(i==0) specHtml="";
				if(i!=0) specHtml+="、";
				specHtml +="<font color='red'>"+$(this).attr("title")+"</font></span>";
				i++;
			});	
			
			if(i>0){
				specHtml="<em>您已选择：</em><span>"+specHtml;
			}else{
				specHtml="<em>请选择：</em><span>下列规格</span>";
			}
			
			$(".spec-tip").html(specHtml);
		 
	}
};
var PriceRefresh={
	refresh:function(pro){
		if(pro.length==1){
			$(".goods-price .price1").text("￥"+pro[0].price );
			$("#productid").val(pro[0].product_id);
		}
		else{
			var maxPrice=0,minPrice=-1;
			for(i in pro){
				if( maxPrice<pro[i].price){
					maxPrice = pro[i].price;
				}
				if(minPrice==-1|| minPrice>pro[i].price){
					minPrice = pro[i].price;
				}
			}	
			$(".goods-price .price1").text("￥"+minPrice+"-￥" +maxPrice);
			
		}
	}
};

function canBuy(){
	$(".btn-buy").unbind('click');
	$(".btn-buy").bind('click',function(){return true;});
	
	$(".btn-buy").css("cursor","pointer");
	$(".btn-buy").tip({'disable':true});
	
	$(".btn-notify").unbind('click');
	$(".btn-notify").bind('click',function(){
		gnotify();
	});
	
	$(".btn-notify").css("cursor","pointer");
	$(".btn-notify").tip({'disable':true});
}

function gnotify(){
	if(!islogin){
		alert("您尚未登陆，不能进行缺货登记。");
		return;
	}
	$.ajax({
		url:'widget.do?type=member_gnotify&ajax=yes&action=add',
		data:'goodsid='+goodsid,
		dataType:'json',
		success:function(result){
			if(result.result==1){
				alert("登记成功");
			}else{
				alert(result.message);
			}
		}
	
	});
}


var BtnTipRefresh = {
	refresh:function(pro){
		$(".btn-buy").attr('tip','');
		$(".btn-notify").attr('tip','');
		if(pro.length==1){
			if(pro[0].store==0){
				$("input[name='action']").attr('value','gnotify');
				canBuy();
				$(".btn-notify").show();
				$(".btn-buy").hide();
			}else{
				canBuy();
				$(".btn-notify").hide();
				$(".btn-buy").show();
			}
		}else{
			$(".btn-buy").unbind('click');
			$(".btn-buy").bind('click',function(){return false;});
			$(".btn-buy").css("cursor","not-allowed");
			
			var i=0;
			var tip='';
			$("#goods-spec .spec-item em").each(function(){
				var em = $(this);
				
				if(em.attr("class")!='checked'){
					if(i!=0)tip+="、";
					tip+=em.text();
					i++;
				}
				
			});
			 
			$(".btn-buy").tip({'disable':false,className:"cantbuy",text:"请选择:"+tip});
		}
	}	
};
var Refresh=[SelectTipRefresh,PriceRefresh,BtnTipRefresh];

//tip插件
(function($) {
	$.fn.tip = function(options) {
		 
		var opts = $.extend({}, $.fn.tip.defaults, options);
		var tipEl= $(".tipbox");
	
		if(tipEl.size()==0){
			var html="<div class='tipbox' style='position: absolute;'>";
			html+='<div class="tip-top"></div>';
			html+='<div class="tip">';
			html+='<div class="tip-text"></div>';
			html+='</div>';
			html+='<div class="tip-bottom"></div>';
			html+='</div>';
			tipEl=$(html).appendTo($("body"));
			tipEl.addClass(opts.className);
			tipEl.hide();
		}
		 
		 tipEl.find(".tip>.tip-text").html(opts.text);
		 if( opts.disable){
			 $(this).unbind("mouseover").unbind("mousemove").unbind("mouseout");
		 }else{

			 $(this).bind("mouseover",function(e){
				 tipEl.show(); 
			 }).bind("mousemove",function(e){
				 tipEl.css('top',e.pageY+15).css('left',e.pageX+15);
			 }).bind("mouseout",function(){
				tipEl.hide();
			 });
		 }
	};
	
 
	
    $.fn.tip.defaults = {
    	className:"tip",
        text:"", 
        disable:false
    };
    
})(jQuery);





