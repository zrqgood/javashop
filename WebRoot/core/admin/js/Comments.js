var Comments=$.extend({},Eop.Grid,{
	init:function(){
		var self =this;
		$("#delBtn").click(function(){self.doDelete();});
		$("#cleanBtn").click(function(){self.doClean();	});
		$("#revertBtn").click(function(){self.doRevert();});
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);
	},
	doDelete:function(){
		
		if(!this.checkIdSeled()){
			alert("请选择要删除的评论");
			return ;
		}
	 
		if(!confirm("确认要将这些评论放入回收站吗？")){	
			return ;
		}
		
		$.Loading.show("正在将评论放入回收站...");
		
		this.deletePost("comments!delete.do");
			
	},
	doClean:function(){
		if(!this.checkIdSeled()){
			alert("请选择要删除的评论");
			return ;
		}
	 
		if(!confirm("确认要将这些评论彻底删除吗？删除后将不可恢复")){	
			return ;
		}
		this.deletePost("comments!clean.do");
	},
	
	doRevert:function(){
		if(!this.checkIdSeled()){
			alert("请选择要还原的评论");
			return ;
		}
	 
		this.deletePost("comments!revert.do","选择的评论已被成功还原至赠品列表中");		
	}
	
});

$(function(){
	Comments.opation("idChkName","id");
	Comments.init();
});