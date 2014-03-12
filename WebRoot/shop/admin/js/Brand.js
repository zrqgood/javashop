var Brand=$.extend({},Eop.Grid,{
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
			alert("请选择要删除的品牌");
			return ;
		}
		
		var self= this;
		$("form").ajaxSubmit({
			url:'brand!checkUsed.do?ajax=yes',
			type:'POST',
			dataType:'json',
			success:function(result){
				if(result.result==1){
					if(confirm("要删除的品牌已经关联商品，确认要删除吗？如果删除对应的商品将不显示品牌。")){
						$.Loading.hide();
						$.Loading.show("正在将品牌放入回收站...");
						self.deletePost("brand!delete.do");
					 
					}
					$.Loading.hide();
				} else{
					if(confirm("确认要将这些品牌放入回收站吗？")){
						$.Loading.hide();
						$.Loading.show("正在将品牌放入回收站...");
						self.deletePost("brand!delete.do");
					}
					$.Loading.hide();
				} 
			},error:function(){
				alert("检测关联性出错");
			}
		});		
		
	},
	doClean:function(){
		if(!this.checkIdSeled()){
			alert("请选择要删除的品牌");
			return ;
		}
	 
		if(!confirm("确认要将这些品牌彻底删除吗？删除后将不可恢复")){	
			return ;
		}
		this.deletePost("brand!clean.do");
	},
	
	doRevert:function(){
		if(!this.checkIdSeled()){
			alert("请选择要还原的品牌");
			return ;
		}
	 
		this.deletePost("brand!revert.do","选择的品牌已被成功还原至品牌列表中");		
	}
	
});

var BrandInput={
		init:function(){
			$(".submitlist .submitBtn").click(function(){
				$.Loading.show("正在检测品牌名是否重复...");
				var name = $("#name").val();
				$("form").ajaxSubmit({
					url:'brand!checkname.do?ajax=yes',
					type:'POST',
					dataType:'json',
					success:function(result){
						var form = $("form");
						if(result.result==1){
							if(confirm("品牌"+name+"已经存在，您确定要保存吗？")){
								$.Loading.hide();
							
								form.submit();
								if( form.attr("validate")=="true" ){
									form[0].submit();
								}								
							}
							$.Loading.hide();
						} else{
							$.Loading.hide();
							form.submit();
							if( form.attr("validate")=="true" ){
								form[0].submit();
							}			
						} 
					},error:function(){
						alert("检测名称出错");
					}
				});
			});	
		}
};
$(function(){
	Brand.init();
});