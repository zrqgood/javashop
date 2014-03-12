var FreeOfferCategory=$.extend({},Eop.Grid,{
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
			alert("请选择要删除的赠品");
			return ;
		}
	 
		if(!confirm("确认要将这些赠品放入回收站吗？")){	
			return ;
		}
		
		$.Loading.show("正在将赠品放入回收站...");
		
		this.deletePost("freeOfferCategory!delete.do");
			
	},
	doClean:function(){
		if(!this.checkIdSeled()){
			alert("请选择要删除的赠品");
			return ;
		}
	 
		if(!confirm("确认要将这些赠品彻底删除吗？删除后将不可恢复")){	
			return ;
		}
		this.deletePost("freeOfferCategory!clean.do");
	},
	
	doRevert:function(){
		if(!this.checkIdSeled()){
			alert("请选择要还原的赠品");
			return ;
		}
	 
		this.deletePost("freeOfferCategory!revert.do","选择的赠品已被成功还原至赠品列表中");		
	}
	
});

$(function(){
	FreeOfferCategory.opation("idChkName","id");
	FreeOfferCategory.init();
});