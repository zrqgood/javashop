var ArticleCat ={
    
	opBundle:undefined,
	init:function(){
		var self = this;
		$(".delete").click(function(){
			self.opBundle=$(this);
			if(self.opBundle.attr("cat_id")=="1"||self.opBundle.attr("cat_id")==2||self.opBundle.attr("cat_id")=="9999"){
				alert("您所选择的类别对于网站运行有重大影响,不可删除!");
				return ;
			}
			if( confirm("确定要删除此类别吗") ){
				$.Loading.show('正在响应您的请求，请稍侯...');
				
				self.doDelete( self.opBundle.attr("cat_id") );
			}
		});
		
		$("#sortBtn").click(function(){
			self.saveSort();
		});
	},
	doDelete:function(cat_id){
		var self =this;
		$.ajax({
			 type: "POST",
			 url: "articleCat!delete.do",
			 data: "ajax=yes&cat_id="+cat_id,
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
	saveSort:function(){
		$.Loading.show('正在保存排序，请稍侯...');
		var that =this;
		var options = {
			url : "articleCat!saveSort.do?ajax=yes",
			type : "POST",
			dataType : 'json',
			success : function(result) {	
				$.Loading.hide();
				if(result.result==0){
					location.reload();
				}else{
					alert(result.message);
				}
			},
			error : function(e) {
				$.Loading.hide();
				alert("出现错误 ，请重试");
			}
		};

		$('form').ajaxSubmit(options);		
	}
};

$(function(){
	ArticleCat.init();
});