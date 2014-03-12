var Menu  ={
	init:function(){
		var self  = this;
		Eop.Dialog.init({id:"addDlg",modal:false,title:"添加菜单",height:"300px",width:"450px"});
		Eop.Dialog.init({id:"editDlg",modal:false,title:"修改菜单",height:"300px",width:"450px"});
		$(".addBtn").click(function(){
			self.openAddDlg($(this).attr("parentid"));
		});
		$(".editBtn").click(function(){
			self.openEditDlg($(this).attr("menuid"));
		});
		$(".delBtn").click(function(){
			if( confirm('确认删除此菜单吗') ){
				self.deleteMenu($(this).attr("menuid"));
			}
		});		
		$(".sortBtn").click(function(){
			 self.sort();
		});				
	}, 
	
	/**
	 * 打开添加对话框
	 */
	openAddDlg:function(pid){
		var self  = this;
		$("#addDlg").load("menu!add.do?ajax=yes&parentid="+pid,function(){
			$("#saveAddBtn").click(function(){
				self.add();
			});
		});
		Eop.Dialog.open("addDlg");
	},
	/**
	 * 打开编辑对话框
	 */
	openEditDlg:function(id){
		var self  = this;
		$("#editDlg").load("menu!edit.do?ajax=yes&id="+id,function(){
			$("#saveEditBtn").click(function(){
				self.edit();
			});
		});
		Eop.Dialog.open("editDlg");
	}
	,
	edit:function(){
		if( !$("#editForm").checkall() ){
			return ;
		}
		$.Loading.show('正在保存，请稍侯...');
		var options = {
				url :"menu!saveEdit.do?ajax=yes",
				type : "POST",
				dataType : 'json',
				success : function(result) {	
					$.Loading.hide();
				 	if(result.result==1){
				 		
				 		alert("保存成功");
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
 
		$("#editForm").ajaxSubmit(options);			
	}
	,
	/**
	 * 保存添加
	 */
	add:function(){
		if( !$("#addForm").checkall() ){
			return ;
		}
		$.Loading.show('正在保存，请稍侯...');
		var options = {
				url :"menu!saveAdd.do?ajax=yes",
				type : "POST",
				dataType : 'json',
				success : function(result) {	
					$.Loading.hide();
				 	if(result.result==1){
				 		
				 		alert("添加成功");
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
 
		$("#addForm").ajaxSubmit(options);			
	},
	deleteMenu:function(id){
		$.Loading.show('正在删除，请稍侯...');
		$.ajax({
			url:'menu!delete.do?ajax=yes&id='+id,
			type:'post',
			dataType:'json',
			success:function(result){
				$.Loading.hide();
			 	if(result.result==1){
			 		alert("删除成功");
			 		location.reload();
			 	}else{
			 		alert(result.message);
			 	}			
			},
			error:function(){
				$.Loading.hide();
				alert("出错啦:(");
			}
		});
	},
	sort:function(){
		$.Loading.show('正在保存，请稍侯...');
		var options = {
				url :"menu!updateSort.do?ajax=yes",
				type : "POST",
				dataType : 'json',
				success : function(result) {	
					$.Loading.hide();
				 	if(result.result==1){
				 		alert("保存成功");
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
 
		$("#sortForm").ajaxSubmit(options);				
	}
};