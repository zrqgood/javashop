var DlyTypeInput={
		areaNameInput:undefined,
		areaIdInput:undefined,
		/**
		 * 公用初始化方法
		 */
		init:function(){
			$('#detail' ).ckeditor( );	
		 	var self = this;
			Eop.Dialog.init({id:"area_selected",modal:true,title:"选择地区",width:"300px",height:"445px"});
			Eop.Dialog.init({id:"check_exp_dlg",modal:true,title:"验证公式",width:"600px",height:"210px"});
		

			//切换统一设置或指定地区和费用
			$("#deliveryAreaToggle input").click(function(){
				 
					if($(this).val()=='1'){
						$("#def_dexp").show().find("input").attr("disabled",false);
						$("#def_area_dexp").hide().find("input").attr("disabled",true);
						$("#deliveryAreabox").hide().find("input").attr("disabled",true);
					}
					
					if($(this).val()=='0'){
					 	$("#def_dexp").hide().find("input").attr("disabled",true);
						$("#def_area_dexp").show();$("#defAreaFee").attr("disabled",false); 					
						$("#deliveryAreabox").show().find("input").attr("disabled",false); 
						$(".deliveryArea li:first").find("input").attr("disabled",true); 
					}
				 				
			});
			$("#deliveryAreaToggle input[value=1]").click();
	
			//切换是否保价
			$("#protect").click(function(){
				if($(this).attr("checked"))
				$("#protectrate").show();
				else
				$("#protectrate").hide();
			});

			//启用默认费用
			$("#defAreaFee").click(function(){
				if($(this).attr("checked"))
					$("#area_dexp").show().find("input").attr("disabled",false);
				else
					$("#area_dexp").hide().find("input").attr("disabled",true);
			});

	
			//添加一项地区-费用配置
			$("#addCfgBtn").click(function(){
				var newArea =$(".deliveryArea li:first").clone().appendTo($(".deliveryArea ol")).show();
				newArea.find("input").attr("disabled",false);
				self.areaNameInput =newArea.find("input[name=areaGroupName]");
				self.areaIdInput = self.areaNameInput.siblings("input[name=areaGroupId]");
				self.areaNameInput.val("");
				self.areaIdInput.val("");
				self.openAreaDlg();
				self.bindAreaBoxEvent();
			});
			
			this.bindAreaBoxEvent();
			
			//保存地区
			$("#area_selected .submitBtn").click(function(){
				self.saveArea();
			});
		},
		/**
		 * 绑定地区费用配置区的事件
		 * 初始化和新增时都会被调用
		 */
		bindAreaBoxEvent:function(){
			var self =this;
			//删除配置项
			$(".delCfgBtn").unbind("click").bind("click",function(){
				$(this).parents("li").remove();
			});

						
			$("input[name=have_cod_check]").unbind("click").bind("click",function(){
				if(this.checked)
					$(this).siblings("[name=has_cod]").val(1);
				else
					$(this).siblings("[name=has_cod]").val(0);
			});


		     //点击地区编辑icon打开地区选择对话框
			$(".editAreaImg").unbind("click").bind("click",function(){
				self.areaNameInput = $(this).siblings("input[name=areaGroupName]");
				self.areaIdInput = self.areaNameInput.siblings("input[name=areaGroupId]");
				self.openAreaDlg();
			});
			
			//点击地区名称input打开地区选择对话框
			$("input[name=areaGroupName]").unbind("click").bind("click",function(){
				self.areaNameInput = $(this);
				self.areaIdInput = self.areaNameInput.siblings("input[name=areaGroupId]");
				self.openAreaDlg();
			});

			//启用公式
			$(".lnk.showexp").unbind("click").bind("click",function(){
				var parent=$(this).parent();
				parent.hide();
				parent.siblings(".deliveryexp").show();
				parent.siblings("input").val(1);
			});
	
			//关闭公式
			$(".lnk.hideexp").unbind("click").bind("click",function(){
				var parent=$(this).parent();
				parent.hide();
				parent.siblings(".deliveryexp").show();
				parent.siblings("input").val(0);
			});
			
			$(".checkexp").unbind("click").bind("click",function(){
				Eop.Dialog.open("check_exp_dlg");
				
				$("#check_exp_dlg .submitBtn").unbind("click").bind("click",function(){
					btn.siblings("input[name=expressions]").val($("#dlg_expressions").val());
					Eop.Dialog.close("check_exp_dlg");
				});
				var btn=$(this);
				$("#check_exp_dlg .dlg").load("setting/check_exp.jsp?ajax=yes",function(){
					var exp =btn.siblings("input[name=expressions]").val();
					$("#dlg_expressions").val(exp);
					$("#check_exp_dlg .dlg .checkbtn").click(function(){
						self.checkExp();
					});
				});
			});
						
		}
		,
		/**
		 * 验证表达式
		 */
		checkExp:function(){
			function tint(value){return value<0?0:value;}
			var w =  parseFloat($("#weight").val());
			var orderprice = parseFloat($("#orderprice").val());
			var exp = $("#dlg_expressions").val();
			var result="";
			try{
				result ="计算结果："+eval(exp);
			}catch(e){
				result="公式错误";
			}
			$("#result").html(result);
		}
		,
		/**
		 * 编辑时初始化
		 */
		editInit:function(type){
			
			$("#corp_id").val(type.corp_id);
			$("#firstunit").val(type.typeConfig.firstunit);
			$("#continueunit").val(type.typeConfig.continueunit);
			$("input[name=type.disabled]").each(function(){
				if( $(this).val() == type.disabled){
					$(this).attr("checked",true);
				}else{
					$(this).attr("checked",false);
				}
			});
			if(type.protect==1){
				$("#protect").attr("checked",true);
				$("#protectrate").show();
			}
			$("#protect_rate").val(type.protect_rate);
			$("#min_price").val(type.min_price);
			$(".deliveryAreaToggle input[value="+type.is_same+"]").click();
			
			
			if(type.is_same==1){//统一配置
				this.setPrice("def_dexp", type.typeConfig);
				$("#deliveryAreaToggle input[value=1]").click();
			}else{ //地区分别配置
				$("#deliveryAreaToggle input[value=0]").click();
				
				if(type.typeConfig.defAreaFee==1){
					$("#defAreaFee").attr("checked",true);
					$("#area_dexp").show().find("input").attr("disabled",false);
					this.setPrice("def_area_dexp", type.typeConfig);
				}
				
				var typeAreaList = type.typeAreaList;
				for( i in typeAreaList){
					var area = typeAreaList[i];
					this.setPrice( "deliverycity_"+i,area.typeAreaConfig);
									 
				}
			}
		},
		/**
		 * 为费用区赋值的通用方法
		 */
		setPrice:function(conclz,config){
			$("#"+conclz+" input[name=firstprice]").val(config.firstprice);
			$("#"+conclz+" input[name=continueprice]").val(config.continueprice);
			$("#"+conclz+" input[name=useexp]").val(config.useexp);
			$("#"+conclz+" input[name=expressions]").val(config.expression);
			
			
			$("#"+conclz+" .deliveryexp").eq(config.useexp==0?0:1).show();
			$("#"+conclz+" .deliveryexp").eq(config.useexp==0?1:0).hide();
			
			$("#"+conclz+" input[name=has_cod]").val(config.have_cod);
			$("#"+conclz+" input[name=have_cod_check]").attr("checked",config.have_cod==1);
			 
		}
		,
		/**
		 * 保存选中的地区id和name并关闭对话框
		 */
		saveArea:function(){
			var self =this;
			var area_id="";
			var area_name=""; 
			$("#area_selected li").each(function(){
				var li=$(this);
				var input = li.children("input");
				
				if(input.attr("checked")){
					var isClose = li.children("div:first.collapsed").size()==1;
					var label=li.children("label");
					
					if(area_id!="")area_id+=",";
					area_id+=input.val();					
					if(isClose)area_id= area_id+"|close";
					
					if( li.parent(".checktree").size()!=0 ){
						if(area_name!="")area_name+=",";
						 area_name+=label.html();
					} 
				}
			});
			this.areaIdInput.val(area_id);
			this.areaNameInput.val(area_name);
			Eop.Dialog.close("area_selected");
		}
		,
		/**
		 * 打开地区选择对话框
		 */
		openAreaDlg:function(){
			if( this.areaIdInput.val()!=""){
				this.areaIdArray = this.areaIdInput.val().split(",");
			}else{
				this.areaIdArray = undefined;
			}
			Eop.Dialog.open("area_selected");
			this.loadArea($(".checktree"), 0,false);
		},
		
		/**
		 * 在一个ul中载入某个区域的子
		 * @param checked 载入的子是否要选中
		 */
		loadArea:function(parent,regionid,checked){
			
			var self = this;
			$.ajax(
					{
						url:"area!listChildren.do?ajax=yes&regionid="+regionid,
						dataType:"json",
						success:function(regionArray){
							parent.empty();
							self.createAreaDom(parent, regionArray,checked);
						},
						error:function(){
							alert("出错了:(");
						}
					}
			);
		},
		checkInArray:function(regionid){
			for(i in this.areaIdArray){
				if(regionid == parseInt(this.areaIdArray[i])){
					return true;
				}
			}
			return false;
		}
		,
		/**
		 * 用一个地区数组生成子地区的dom
		 * @param checked 子是否要选中
		 */
		createAreaDom:function(parent,regionArray,checked){
			var self = this;
			for(i in regionArray){
				var region = regionArray[i];

				if( self.areaIdArray){
					checked = self.checkInArray(region.region_id);
				}
				 
				var li="<li><input type='checkbox' name='region'";
				if(checked){
					li+=" checked ";
				}
				li+="  value='"+region.region_id+"'> <label>"+region.local_name+"</label>";
				if(region.childnum>0){
					li+="<ul></ul>";
				}
				li+="</li>";
				parent.append(li);
			}
			parent.checkTree(
								{
									onExpand:function(li){
										self.loadArea(li.children("ul"), li.children("input").val(),li.children("input").attr("checked"));
									}
								}
							);
		}
};
