var Cat ={
    
	opBundle:undefined,
	init:function(){
		var self = this;
		$("#cat_list .delete").click(function(){
			if( confirm("确定要删除此类别吗") ){
				$.Loading.show('正在响应您的请求，请稍侯...');
				self.opBundle=$(this);
				self.doDelete( self.opBundle.attr("catid") );
			}
		});
		
		$("#sortBtn").click(function(){
			self.saveSort();
		});
	},
	doDelete:function(catid){
		var self =this;
		$.ajax({
			 type: "POST",
			 url: "cat!delete.do",
			 data: "ajax=yes&cat_id="+catid,
			 dataType:"json",
			 success: function(result){
				 if(result.result==1){
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
		var that =this;
		var options = {
			url : "cat!saveSort.do?ajax=yes",
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
	},
	
	intChkNameEvent:function(){
		$(".submitlist .submitBtn").click(function(){
			$.Loading.show("正在检测类别名是否重复...");
			var name = $("#name").val();
			$("form").ajaxSubmit({
				url:'cat!checkname.do?ajax=yes',
				type:'POST',
				dataType:'json',
				success:function(result){
					var form = $("form");
					if(result.result==1){
						if(confirm("类别"+name+"已经存在，您确定要保存吗？")){
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
	Cat.init();
	
});