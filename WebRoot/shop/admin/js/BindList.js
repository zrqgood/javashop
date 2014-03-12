var Bind ={
    
	opBundle:undefined,
	init:function(){
		var self = this;
		$(".delete").click(function(){
			if( confirm("确定要删除此捆绑商品吗") ){
				$.Loading.show('正在响应您的请求，请稍侯...');
				self.opBundle=$(this);
				self.doDelete( self.opBundle.attr("goods_id") );
			}
		});
	},
	doDelete:function(goods_id){
		var self =this;
		$.ajax({
			 type: "POST",
			 url: "bind!delete.do",
			 data: "ajax=yes&goods_id="+goods_id,
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
	Bind.init();
});