var Goods=$.extend({},Eop.Grid,{
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
			alert("请选择要删除的商品");
			return ;
		}
	 
		if(!confirm("确认要将这些商品放入回收站吗？")){	
			return ;
		}
		
		$.Loading.show("正在将商品放入回收站...");
		
		this.deletePost1(basePath+"goods!delete.do");
			
	},
	deletePost1:function(url,msg){
		var self =this;
		url=url.indexOf('?')>=0?url+"&":url+"?";
		url+="ajax=yes";
	//	url=basePath+url;
		var options = {
				url : url,
				type : "POST",
				dataType : 'json',
				success : function(result) {	
					$.Loading.hide();
					if(result.result==0){
						self.deleteRows();
						if(msg)
							alert(msg);
					}else{
						alert(result.message);
					}
				},
				error : function(e) {
					$.Loading.hide();
					alert("出现错误 ，请重试");
				}
			};

			$('#gridform').ajaxSubmit(options);		
	},
	doClean:function(){
		if(!this.checkIdSeled()){
			alert("请选择要删除的商品");
			return ;
		}
	 
		if(!confirm("确认要将这些商品彻底删除吗？删除后将不可恢复")){	
			return ;
		}
 
		this.deletePost(basePath+"goods!clean.do");
	},
	
	doRevert:function(){
		if(!this.checkIdSeled()){
			alert("请选择要还原的商品");
			return ;
		}
	 
		this.deletePost(basePath+"goods!revert.do","选择的商品已被成功还原至商品列表中");		
	}
	
});

$(function(){
	Goods.init();
});