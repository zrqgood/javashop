var Area=$.extend({},Eop.Grid,{
	init:function(){
		var self =this;
 
		$("#delBtn").click(function(){self.doDelete();});
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);
	},
	doDelete:function(){

		if(!this.checkIdSeled()){
			alert("请选择要删除的地区");
			return ;
		}
	 
		if(!confirm("确认要将这些地区彻底删除吗？删除后将不可恢复")){	
			return ;
		}
		$.Loading.show("正在删除地区...");
		
		this.deletePost("area!delete.do");
			
	} 
	
});

$(function(){
	Area.init();
});
