var Logi=$.extend({},Eop.Grid,{
	init:function(){
		var self =this;
		$("#delBtn").click(function(){self.doDelete();});
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);
		
	},
	doDelete:function(){

		if(!this.checkIdSeled()){
			alert("请选择要删除的物流公司");
			return ;
		}
	 
		if(!confirm("确认要将这些物流公司彻底删除吗？删除后将不可恢复")){	
			return ;
		}
		$.Loading.show("正在删除物流公司...");
		
		this.deletePost("logi!delete.do");
			
	}  
	
	
});

$(function(){
	Logi.init();
});
 