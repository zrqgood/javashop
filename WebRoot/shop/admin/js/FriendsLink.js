var FriendsLink=$.extend({},Eop.Grid,{
	init:function(){
		var self =this;
		$("#delBtn").click(function(){self.doDelete();});
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);
	},
	doDelete:function(){
		if(!this.checkIdSeled()){
			alert("请选择要删除的友情链接");
			return ;
		}
	 
		if(!confirm("确认要删除这些友情链接吗？")){	
			return ;
		}
		
		$.Loading.show("正在删除友情链接...");
		
		this.deletePost("friendsLink!delete.do");
			
	}
	
});

$(function(){
	FriendsLink.opation("idChkName","id");
	FriendsLink.init();
});