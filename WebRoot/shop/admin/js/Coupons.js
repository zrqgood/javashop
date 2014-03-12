var Coupons =$.extend({},Eop.Grid,{
    
	init:function(){
		var self = this;
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);
		$("#delBtn").click(function(){self.doDelete();});
	},
	doDelete:function(){
		if(!this.checkIdSeled()){
			alert("请选择要删除的优惠券");
			return ;
		};
		if( !confirm("确定要删除这些优惠券吗") ){	
			return ;
		}
		$.Loading.show('正在响应您的请求，请稍侯...');
		
		$("[name=id]:checkbox").each(function(){
			if(this.checked){
				$(this).parent("td").find("[name=pmtIdArray]:input").attr("disabled", false);
			};
		});
		
		this.deletePost("coupon!delete.do");
		
	}
	
});

$(function(){
	Coupons.init();
});