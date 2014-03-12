var Promotion=$.extend({},Eop.Grid,{
	init:function(){
		var self =this;
		$("#delBtn").click(function(){self.doDelete();});
	 
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);
	},
	doDelete:function(){
		
		if(!this.checkIdSeled()){
			alert("请选择要删除的规则");
			return ;
		}
	 
		if(!confirm("确认删除这些规则吗？")){	
			return ;
		}
		
		$.Loading.show("正在删除...");
		
		this.deletePost("promotion!delete.do");
			
	} 
	
});