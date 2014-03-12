var Article=$.extend({},Eop.Grid,{
	init:function(){
		var self =this;
		$("#delBtn").click(function(){self.doDelete();});
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);
	},
	doDelete:function(){
		
		if(!this.checkIdSeled()){
			alert("请选择要删除的文章");
			return ;
		}
	 
		if(!confirm("确认要将这些文章删除吗？")){	
			return ;
		}
		
		$.Loading.show("正在删除文章...");
		
		this.deletePost("article!delete.do");
			
	}
});

$(function(){
	Article.init();
});