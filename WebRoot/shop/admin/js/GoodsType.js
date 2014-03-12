var Prop={
	inputHtml:undefined,
	init:function(){
		var self=this;
		this.initinputHtml();
		$("#propAddBtn").click(function(){self.addPropInput();});
		$("#props_table  .delete").click(function(){
			$(this).parents("tr").remove();
		});
		$("#theForm").submit(function(){
			var result = true;
			$("[name=propnames]").each(function(){
				 if($.trim($(this).val()) == ''){
					 result =false
				 }
			});
			
			if(!result){
				alert("请填写属性名");
				return false;
			}
			return true;
		});
	},
	
	/*
	 * 初始化属性输入模板html
	 */
	initinputHtml:function(){
		var self=this;
		$.ajax({
			 type: "POST",
			 url: "type/prop_input_item.jsp?ajax=yes",
			 dataType:'html',
			 success: function(html){ 
				self.inputHtml=html;
			 },
			 error:function(){
				 alert("抱歉，属性模板加载失败，请重试..");
			 }
			});
		
	}
	,
	/*
	 * 增加一个属性输入项
	 */
	addPropInput:function(){
		$(this.inputHtml).insertAfter($("#props_table>tbody>tr:last"))
		.find("td .delete").click(function(){
			$(this).parents("tr").remove();
		});
	}
};



var Param={
		inputHtml:undefined,
		init:function(){
			var self=this;
			this.initInputHtml();
			
			//添加一个组
			$("#paramAddBtn").click(function(){self.addGroupInput();});
			
			//删除组
			$("tr.group .delete").click(function(){
				$(this).parents("table").remove();
			});
			
			//删除参数项
			$("tr.param .delete").click(function(){
				$(this).parents("tr").remove();
			});
			
			$("tr.group>td a.addBtn").click(function(){
				self.addParamItemInput( $(this).parents("table") );			
			});
			

			
		},
		
		/*
		 * 初始化属性输入模板html
		 */
		initInputHtml:function(){
			var self=this;
			$.ajax({
				 type: "POST",
				 url: "type/param_input_item.jsp?ajax=yes",
				 dataType:'html',
				 success: function(html){ 
					self.inputHtml=html;
				 },
				 error:function(){
					 alert("抱歉，参数模板加载失败，请重试..");
				 }
				});
			
		}
		,
		/*
		 * 增加一个参数组输入项
		 */
		addGroupInput:function(){
			var self=this;
			var group=$(this.inputHtml).appendTo($("#param_div"));
			group.find("tbody>tr.group>td .delete").click(function(){
				$(this).parents("table").remove();
			});
			
			group.find("tbody>tr.group>td .addBtn").click(function(){
				self.addParamItemInput( $(this).parents("table") );
			});
		},
		
		/*
		 * 增加一个参数输入项
		 */
		addParamItemInput:function(table){
			$(this.inputHtml).find("tbody>tr:last")
			.insertAfter(table.find("tbody>tr:last"))
			.find("td .delete").click(function(){
				$(this).parents("tr").remove();
			});
		}
		
};
var GoodsType=$.extend({},Eop.Grid,{
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
			alert("请选择要删除的类型");
			return ;
		}
	 
		if(!confirm("确认要将这些类型放入回收站吗?")){	
			return ;
		}
		
		$.Loading.show("正在类型放入回收站...");
		
		this.deletePost("type!delete.do");
			
	},
	doClean:function(){
		if(!this.checkIdSeled()){
			alert("请选择要删除的类型");
			return ;
		}
	 
		if(!confirm("确认要将这些类型彻底删除吗？删除后将不可恢复")){	
			return ;
		}
		this.deletePost("type!clean.do");
	},
	
	doRevert:function(){
		if(!this.checkIdSeled()){
			alert("请选择要还原的类型");
			return ;
		}
	 
		this.deletePost("type!revert.do","选择的类型已被成功还原至类型列表中");		
	},
	intChkNameEvent:function(){
		$(".submitlist .submitBtn").click(function(){
			$.Loading.show("正在检测类型名是否重复...");
			var name = $("#name").val();
			$("form").ajaxSubmit({
				url:'type!checkname.do?ajax=yes',
				type:'POST',
				dataType:'json',
				success:function(result){
					var form =$("form");
					if(result.result==1){
						if(confirm("类型"+name+"已经存在，您确定要保存吗？")){
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
						if(!$.browser.msie){
							if( form.attr("validate")=="true" ){
								form[0].submit();
							}	
						}	
					} 
				},error:function(){
					alert("检测名称出错");
				}
			});
		});	
	}	
});




var GoodsField={
	init:function(){
		Eop.Dialog.init({id:'addFieldDlg', width:500,height:200,title:'添加商品字段'});
		Eop.Dialog.init({id:'editFieldDlg', width:500,height:200,title:'修改商品字段'});
		var self  = this;
		$("#addFieldBtn").click(function(){
			self.openAddDlg();
		});
		$("#sortBtn").click(function(){
			self.saveSort();
		});		
		this.typeid=$("#type_id").val();
		this.loadFieldList(this.typeid);
		
		$("#okBtn").click(function(){
			location.href='type!list.do';
		});
	},
	
	/**
	 * 加载商品字段列表
	 */
	loadFieldList:function(typeid){
		var self = this;
		$("#field_box").load('field!list.do?ajax=yes&typeid='+typeid,function(){
			self.bindListEvent();
		});
	},
	
	/**
	 * 绑定列表事件
	 */
	bindListEvent:function(){
		var self = this;
		$(".grid .mod").click(function(){
			var fieldid = $(this).parent().attr("fieldid");
			self.openEditDlg(fieldid);
		});		

		$(".grid .del").click(function(){
			if(confirm("确认删除此字段吗？将会同时删除相应商品数据，删除后不可恢复！")){
				var fieldid = $(this).parent().attr("fieldid");
				self.deleteField(fieldid);
			}
		});	
	}
	
	,
	openAddDlg:function(){
		var self = this;
	 
		Eop.Dialog.open("addFieldDlg");
		$("#addFieldDlg").load('field!add.do?typeid='+self.typeid+'&ajax=yes',function(){
			$("#addFieldDlg .submitBtn").click(function(){
				self.addField();
			});
		});		
	},
	openEditDlg:function(fieldid){
		var self = this;
		Eop.Dialog.open("editFieldDlg");
		$("#editFieldDlg").load('field!edit.do?typeid='+self.typeid+'&fieldid='+fieldid+'&ajax=yes',function(){
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
				 		Eop.Dialog.close("addFieldDlg");
				 		self.loadFieldList(self.typeid);
				 	}else{
				 		alert(result.message);
				 	}
				},
				error : function(e) {
					$.Loading.hide();
					alert("出错啦:(");
 				}
 		};
 
		$("#addFieldDlg form").ajaxSubmit(options);		
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
				 		Eop.Dialog.close("editFieldDlg");
						self.loadFieldList(self.typeid);
						
				 	}else{
				 		alert(result.message);
				 	}
				},
				error : function(e) {
					alert("出错啦:(");
 				}
 		};
 
		$("#editFieldDlg form").ajaxSubmit(options);		
	},	
	addSuccess:function(){
		alert("添加成功");
		location.reload();
	},
	deleteField:function(fieldid){
		$.Loading.show('正在刪除，请稍侯...');
		var self= this;
		$.ajax({
			url:"field!delete.do?fieldid="+fieldid+"&ajax=yes",
		    type:"post",
		    dataType:"json",
		    success:function(result){
			$.Loading.hide();
			 	if(result.result==1){
					self.loadFieldList(self.typeid);
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
				 		self.loadFieldList(self.typeid);
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



