var PrintTmpl=$.extend({},Eop.Grid,{
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
			alert("请选择要删除的模板");
			return ;
		}
	 
		if(!confirm("确认要将这些模板放入回收站吗？")){	
			return ;
		}
		
		$.Loading.show("正在将模板放入回收站...");
		
		this.deletePost("printTmpl!delete.do");
			
	},
	doClean:function(){
		if(!this.checkIdSeled()){
			alert("请选择要删除的模板");
			return ;
		}
	 
		if(!confirm("确认要将这些模板彻底删除吗？删除后将不可恢复")){	
			return ;
		}
		this.deletePost("printTmpl!clean.do");
	},
	
	doRevert:function(){
		if(!this.checkIdSeled()){
			alert("请选择要还原的模板");
			return ;
		}
	 
		this.deletePost("printTmpl!revert.do","选择的模板已被成功还原至模板列表中");		
	}
	
});

$(function(){
	PrintTmpl.init();
});