var Activity=$.extend({},Eop.Grid,{
	init:function(){
		var self =this;
		$("#delBtn").click(function(){self.doDelete();});
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);
	},
	doDelete:function(){
		
		if(!this.checkIdSeled()){
			alert("请选择要删除的活动");
			return ;
		}
		
		if(!confirm("确认要删除这些活动吗？")){	
			return ;
		}
		
		$.Loading.show("正在将删除活动...");
		
		this.deletePost("activity!delete.do");
			
	}
	
});

$(function(){
	Activity.opation("idChkName","id");
	Activity.init();
});