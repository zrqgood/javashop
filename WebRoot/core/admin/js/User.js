var User=$.extend({},Eop.Grid,{
	init:function(){
		var self =this;
		$("#delBtn").click(function(){self.doDelete();});
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);
	},
	doDelete:function(){

		if(!this.checkIdSeled()){
			alert("请选择要删除的用户");
			return ;
		}
		if(!confirm('将会同时删除的站点！确定要删除这些用户吗?')){
			return;
		}		
		
		$.Loading.show("正在删除记录...");
		this.deletePost("user!delete.do");
	 
		
	} 
	
});
 
$(function(){
	User.opation("idChkName","userid");
	User.init();
});