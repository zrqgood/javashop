var SpecValue = {
	 
	init:function(){
	
		var self =this;
		$(".specList input[name=spec]").attr("checked",false);
		//添加规格值
		$(".specList input[name=spec]").click(function(){
			self.specClick($(this));
		});


        Eop.Dialog.init({id:"goods_img_selected",title:"关联相册",height:"300px"});
		$("input[name=picnames]").each(function(){
	 		$("#goods_img_selected ul").append("<li><input type='radio'  name='gsimg' value='" +  $(this).val() +"'/><img src=\""+ $(this).val() +"\" width='30px' height='30px' /></li>" );	
		});

		//保存相册关联
		$("#goods_img_selected button").click(function(){
			var json= Eop.Util.jsonToString(spec_imgs) ;
			$("#spec_imgs").val(json);
			Eop.Dialog.close("goods_img_selected");	
		});

		//生成货品
		$("#specdlg button").click(function(){
			self.createPros();
			Eop.Dialog.close("specdlg");			
		});
		
	},
	specClick:function(chk){
		$("#spec_dialog .tab-page").show();
		if( chk.attr("checked") ){
			this.addSpec(chk.val(),chk.attr("spec_name"));
		}

		if(! chk.attr("checked") ){
			this.removeSpec(chk.val());
		}		
	},
	addSpec:function(spec_id,spec_name){
		var self =this;
		$.ajax({
			url:basePath+"goodsSpec!getValues.do",
			data:"ajax=yes&spec_id="+spec_id,
			dataType:"html",
			success:function(spechtml){
				var newTab = $("<li specid='"+spec_id+"'>"+spec_name+"</li>");
				$(".spec_tab .tab-bar ul.tab").append(newTab);
				$(".spec_tab .tab-page").append(spechtml);
				new Tab(".spec_tab");
				
				//添加全部规格
				$("#spec-tab-"+spec_id+" .add-spec-all").click(function(){
					
					$(this).parents("td").find(".specli").click();
				});
				$("#spec-tab-"+spec_id+" .spec-name .specli").click(function(){ 
					var span=$(this);
					self.addOne(spec_id,span.attr("specvid"));
				});
				
				newTab.click();
			},
			error:function(){
				alert("o,error:(");
			}
			
		});
	},
	removeSpec:function(spec_id){

		//如果要删除的选择卡为当前则让第一个选项卡选中
		var tab = $(".tab-bar ul.tab li[specid='"+spec_id+"']");
 
		var isCurrent =false;
		if("active" == tab.attr("class") ){
			isCurrent= true; 
		}
		if(isCurrent){
			tab.siblings().eq(0).click();		
		}
		tab.remove();
		$("#spec-tab-"+spec_id).remove();
	},

	addOne:function(spec_id,value_id){

		//已经有的规格不添加
	 	var isExist = false;
		$("input[name='specvalues']").each(function(){
			 
			if( parseInt($(this).attr("specvid"))==parseInt(value_id) ){
			 	isExist = true;
			}
		});
		 
		if(isExist) return ;
		var self =this;
		$.ajax({
			url:basePath+"goodsSpec!addOne.do",
			data:"ajax=yes&value_id="+value_id,
			dataType:"html",
			success:function(onehtml){
				var el =$(onehtml);
				el.find(".delete").click(function(){
					self.deleteOne($(this));
				});
							
				el.find(".join").click(function(){
					var joinBtn =$(this);
					Eop.Dialog.open("goods_img_selected");
					var imgchk =$("#goods_img_selected ul").find("input[name=gsimg]");
					imgchk.unbind("click").attr("checked",false);
					imgchk.click(function(){
						var chk= $(this);
						spec_imgs[value_id]=chk.val();
						joinBtn.parent().children("span").append("<img height='22' width='22' style='margin: 0pt 1px;' src='"+chk.val()+"'>");
					});	
					
					imgchk.each(function(){
						if($(this).val() ==  spec_imgs[value_id]){
							$(this).attr("checked",true);
						}
					});
				});
				$("#spec-tab-"+spec_id+" .spec-body").append(el);
				
			},
			error:function(){
				alert("o,error:(");
			}
			
		});		
	},
	//删除一个规格
	deleteOne:function(delBtn){
		delBtn.parents("tr").remove();
	}
	,
	//生成货品
	createPros:function(){
		var specs=[];
		var names=[];
		var i=0;
		$(".tab-page .item").each(function(){
			var specid = $(this).attr("specid");
			names[i] = $(this).attr("specname");
			specs[i]  =[];
			var j=0;
			$("#spec-tab-"+specid+" input[name='specvalues']").each(function(){
				var span = $(this);
				var specvid = span.attr("specvid");
				var specvalue = span.attr("specvalue");
				var spectype = span.attr("spectype");
				var specimage = span.attr("specimage");
				var spec = {'specid':specid,'specvid':specvid,'specvalue':specvalue,'spectype':spectype,'specimage':specimage};
				specs[i][j]= spec ;
				j++;
			});	
			i++;	
		});
		 
		Product.createPros(specs,names);
		 
	}

	
	

}; 

$(function(){
	SpecValue.init();
});