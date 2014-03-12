var DataLog=$.extend({},Eop.Grid,{
	init:function(){
		var self =this;
		$("#delBtn").click(function(){self.doDelete();});
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);
		this.bindContextEnt();
	},
	doDelete:function(){

		if(!this.checkIdSeled()){
			alert("请选择要删除的记录");
			return ;
		}
		if(!confirm('确定要删除这些记录吗?')){
			return;
		}		
		
		$.Loading.show("正在删除记录...");
		this.deletePost("dataLog!delete.do");
	 
		
	},
	bindContextEnt:function(){
		$(".c_short").hover(
				function(){
					$(this).css("overflow","visible").css("height","auto").css("z-index",3000).slideDown("slow");
				},function(){
					 $(this).css("overflow","hidden").css("height","100px").css("z-index",1);
				}
		);
				
	}
	
});
 
$(function(){
	DataLog.opation("idChkName","ids");
	DataLog.init();
});