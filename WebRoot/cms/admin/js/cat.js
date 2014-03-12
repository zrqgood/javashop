var Cat={
	init:function(){
		 var self = this;
		 //保存排序事件
		$("#sortBtn").click(function(){
			self.saveSort();
		});

		//删除类别事件
		$(".delete").click(function(){
			if( confirm("确定要删除此类别吗") ){
				$.Loading.show('正在响应您的请求，请稍侯...');
				self.opBundle=$(this);
				self.doDelete( self.opBundle.attr("catid") );
			}
		});
		
		
	},	doDelete:function(catid){
		var self =this;
		$.ajax({
			 type: "POST",
			 url: "cat!delete.do",
			 data: "ajax=yes&cat_id="+catid,
			 dataType:"json",
			 success: function(result){
				 if(result.result==0){
					$.Loading.hide();
				 	alert(result.message);
			     }else{
			 		self.opBundle.parents("tr").remove();
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
		var options = {
				url :"cat!saveSort.do?ajax=yes",
				type : "POST",
				dataType : 'json',
				success : function(result) {				
				 	if(result.result==1){
				 		$.Loading.hide();
				 		alert("更新成功");
				 		location.reload();
				 	}else{
				 		alert(result.message);
				 	}
				},
				error : function(e) {
					$.Loading.hide();
					alert("出错啦:(");
 				}
 		};
 
		$("form").ajaxSubmit(options);		
	}
	
};