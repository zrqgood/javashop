var AdColumn=$.extend({},Eop.Grid,{
	init:function(){
		var self =this;
		$("#delBtn").click(function(){self.doDelete();});
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);
	},
	doDelete:function(){
		
		if(!this.checkIdSeled()){
			alert("请选择要删除的广告位");
			return ;
		}
		
		if(!confirm("确认要删除选定的广告位？")){	
			return ;
		}
		
		$.Loading.show("正在删除广告位...");
	 
		this.deletePost("adColumn!delete.do", "广告位删除成功");
			
	}
	
});

$(function(){
	AdColumn.opation("idChkName","id");
	AdColumn.init();
});