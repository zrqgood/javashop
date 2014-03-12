var SpecGrid=$.extend({},Eop.Grid,{
	init:function(){
		var self =this;
		$("#delBtn").click(function(){self.doDelete();});
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);
	},
	doDelete:function(){
		
		if(!this.checkIdSeled()){
			alert("请选择要删除的规格");
			return ;
		}
 
		
		var self= this;
		$("form").ajaxSubmit({
			url:basePath+'spec!checkUsed.do?ajax=yes',
			type:'POST',
			dataType:'json',
			success:function(result){
				if(result.result==1){
					alert("要删除的规格被商品使用，不能删除");
					$.Loading.hide();
				} else{
					if(confirm("确认要将删除这些规格吗?删除后将不可恢复")){
						$.Loading.hide();
						$.Loading.show("正在删除规格...");
						self.deletePost(basePath+"spec!delete.do","选择中规格已被彻底删除");
					}
					$.Loading.hide();
				} 
			},error:function(){
				alert("检测关联性出错");
			}
		});		
			
	}
	
});

$(function(){
	SpecGrid.init();
});