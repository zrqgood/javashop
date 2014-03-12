var Payment=$.extend({},Eop.Grid,{
	init:function(){
		var self =this;
		$("#delBtn").click(function(){self.doDelete();});
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);
	},
	doDelete:function(){
		
		if(!this.checkIdSeled()){
			alert("请选择要删除的品牌");
			return ;
		}
	 
		if(!confirm("确认要将删除支付方式吗？")){	
			return ;
		}
		
		$.Loading.show("正在删除支付方式...");
		
		this.deletePost("payCfg!delete.do");
			
	}
	
});
