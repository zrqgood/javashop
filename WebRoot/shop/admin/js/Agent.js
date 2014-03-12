var Agent ={
    
	opBundle:undefined,
	init:function(){
		var self = this;
		$(".delete").click(function(){
			if( confirm("确定要删除此代理商吗") ){
				$.Loading.show('正在响应您的请求，请稍侯...');
				self.opBundle=$(this);
				self.doDelete( self.opBundle.attr("agentid") );
			}
		});
//		$(".audit").click(function(){
//			if( confirm("确定要审核批准此代理商吗") ){
//				$.Loading.show('正在响应您的请求，请稍侯...');
//				self.opBundle=$(this);
//				self.doAudit( self.opBundle.attr("agentid") );
//			}
//		});
	},
	doDelete:function(agentid){
		var self =this;
		$.ajax({
			 type: "POST",
			 url: "agent!delete.do",
			 data: "ajax=yes&agentid="+agentid,
			 dataType:"json",
			 success: function(result){
				 if(result.result==1){
					$.Loading.hide();
				 	alert(result.message);
			     }else{
			 		self.opBundle.parent("a").parent("td").parent("tr").remove();
				    $.Loading.hide();
				 }
			 },
			 error:function(){
				 $.Loading.hide();
				 alert("操作失败，请重试");
			 }
		}); 		
	},
	doAudit:function(agentid){
		var self =this;
		$.ajax({
			 type: "POST",
			 url: "agent!audit.do",
			 data: "ajax=yes&agentid="+agentid,
			 dataType:"json",
			 success: function(result){
				 if(result.result==1){
					$.Loading.hide();
				 	alert(result.message);
			     }else{
			 		self.opBundle.parent("a").parent("td").parent("tr").remove();
				    $.Loading.hide();
				 }
			 },
			 error:function(){
				 $.Loading.hide();
				 alert("操作失败，请重试");
			 }
		}); 		
	}
};

$(function(){
	Agent.init();
});