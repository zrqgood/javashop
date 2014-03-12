var ExCoupons =$.extend({},Eop.Grid,{
    
	init:function(){
		var self = this;
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);
		$("#delBtn").click(function(){self.doDelete();});
	},
	doDelete:function(){
		if(!this.checkIdSeled()){
			alert("请选择要删除的积分兑换优惠券");
			return ;
		};
		if( !confirm("确定要删除这些积分兑换优惠券吗") ){	
			return ;
		}
		$.Loading.show('正在响应您的请求，请稍侯...');
		
		this.deletePost("coupon!deleteExchange.do");
		
	}
	
});

$(function(){
	ExCoupons.init();
});