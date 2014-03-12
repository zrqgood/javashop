var DataModel={
		init:function(){
			var self  = this;
			$(".grid .delete").click(function(){
				if(confirm("确认删除此模型吗？将会同时删除相应文章数据，删除后不可恢复！")){
					var modelid = $(this).parent().attr("modelid");
					$.Loading.show("正在检测模型是否已经被使用...");
					$.ajax({
						url:'model!check.do?modelid='+modelid+'&ajax=yes',
						type:'post',
						dataType:'json',
						success:function(result){
							if(result.result==0){
								self.deleteModel(modelid);
							}else{
								alert(result.message);
							}
						},
						error:function(){
							alert("检查模型失败");
						}
					});
					$.Loading.hide();
				}
			});					
		},
		deleteModel:function(modelid){
			$.ajax({
				url:"model!delete.do?modelid="+modelid+"&ajax=yes",
			    type:"post",
			    dataType:"json",
			    success:function(result){
				 	if(result.result==1){
						alert("删除成功");
						location.reload();
				 	}else{
				 		alert(result.message);
				 	}			
				},
				error:function(){
					alert("出错啦:(");
				}
			});			
		}		
};

var ModelField={
	init:function(){
		Eop.Dialog.init({id:'addFieldDlg', width:500,height:200,title:'添加模型字段'});
		Eop.Dialog.init({id:'editFieldDlg', width:500,height:200,title:'修改模型字段'});
		var self  = this;
		$("#addFieldBtn").click(function(){
			self.openAddDlg();
		});
		$("#sortBtn").click(function(){
			self.saveSort();
		});		
		
		$(".grid .modify").click(function(){
			var fieldid = $(this).parent().attr("fieldid");
			self.openEditDlg(fieldid);
		});		

		$(".grid .delete").click(function(){
			if(confirm("确认删除此字段吗？将会同时删除相应文章内容，删除后不可恢复！")){
				var fieldid = $(this).parent().attr("fieldid");
				self.deleteField(fieldid);
			}
		});		
	},
	openAddDlg:function(){
		var self = this;
		var modelid = $("#modelid").val();
		Eop.Dialog.open("addFieldDlg");
		$("#addFieldDlg").load('field!add.do?modelid='+modelid+'&ajax=yes',function(){
			$("#addFieldDlg .submitBtn").click(function(){
				self.addField();
			});
		});		
	},
	openEditDlg:function(fieldid){
		var self = this;
		var modelid = $("#modelid").val();
		Eop.Dialog.open("editFieldDlg");
		$("#editFieldDlg").load('field!edit.do?modelid='+modelid+'&fieldid='+fieldid+'&ajax=yes',function(){
			$("#editFieldDlg .submitBtn").click(function(){
				self.editField();
			});
		});		
	},	
	addField:function(){
		$.Loading.show('正在保存，请稍侯...');
		var self= this;
		var options = {
				url :"field!saveAdd.do?ajax=yes",
				type : "POST",
				dataType : 'json',
				success : function(result) {	
					$.Loading.hide();
				 	if(result.result==1){
				 		self.addSuccess();
				 	}else{
				 		alert(result.message);
				 	}
				},
				error : function(e) {
					$.Loading.hide();
					alert("出错啦:(");
 				}
 		};
 
		$("#fieldForm").ajaxSubmit(options);		
	},
	editField:function(){
		$.Loading.show('正在保存，请稍侯...');
		var self= this;
		var options = {
				url :"field!saveEdit.do?ajax=yes",
				type : "POST",
				dataType : 'json',
				success : function(result) {				
				 	if(result.result==1){
				 		$.Loading.hide();
						alert("修改成功");
						location.reload();
				 	}else{
				 		alert(result.message);
				 	}
				},
				error : function(e) {
					alert("出错啦:(");
 				}
 		};
 
		$("#fieldForm").ajaxSubmit(options);		
	},	
	addSuccess:function(){
		alert("添加成功");
		location.reload();
	},
	deleteField:function(fieldid){ 
		$.ajax({
			url:"field!delete.do?fieldid="+fieldid+"&ajax=yes",
		    type:"post",
		    dataType:"json",
		    success:function(result){
			 	if(result.result==1){
					alert("删除成功");
					location.reload();
			 	}else{
			 		alert(result.message);
			 	}			
			},
			error:function(){
				alert("出错啦:(");
			}
		});
	},
	saveSort:function(){
		$.Loading.show('正在保存排序，请稍侯...');
		var options = {
				url :"field!saveSort.do?ajax=yes",
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
 
		$("#FieldListForm").ajaxSubmit(options);			
	}
	
};