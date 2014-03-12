//类别、类型、品牌联动
var TypeCat={
	init:function(){
		var self  = this;

		//类型切换事件
		$("#type_id").change(function(){
			 self.type_change_event($(this).val(),$(this).find("option:selected").attr("join_brand"));
		});
		 
		
		//类别切换联动
		$("#cat_id").change(function(){
			var tyepid =$(this).find("option:selected").attr("typeid");
			self.changeType($(this).val(), tyepid);
		});
		
	},
	changeCat:function(catid){
		var option = $("#cat_id>option[value="+catid+"]");
		option.attr("selected",true);
		var typeid = option.attr("typeid");
		this.changeType(catid, typeid);
	}
	,
	loadField:function(typeid){
		$("#custom_field_table").load(basePath+'field!disInputHtml.do?typeid='+typeid+"&ajax=yes");
	}
	,
	//分类切换事件
	changeType:function(cat_id,type_id){
		
		//if(confirm("是否根据所选分类的默认类型重新设定商品类型？\n如果重设，可能丢失当前所输入的类型属性、关联品牌、参数表等类型相关数据。")){
			$("#type_id").val(type_id);
			this.type_change_event(type_id,this.getJoinBrand(type_id));
			this.loadField(type_id);
		//}		
	},
	
	//获取某类型是否关联品牌
	getJoinBrand:function(typeid){
	    var join_brand = 0;
		$("#type_id option").each(function(){
			if($(this).val() == typeid && $(this).attr("join_brand")== 1 ){
				join_brand=1;
			} 
		});
		return join_brand;
	},
	type_change_event1:function(type_id,join_brand,brand_id){
		 
		if(parseInt(join_brand)==1){  
			$("#brand_tr").show();
			this.loadBrandsInput(type_id,brand_id);
		}else{
			 
			$("#brand_id").empty();
			$("#brand_tr").hide();
			 
		}
	 
	}
	,
	
	//类型更换事件
	type_change_event:function(type_id,join_brand,brand_id){
		if(parseInt(join_brand)==1){  
			$("#brand_tr").show();
			this.loadBrandsInput(type_id,brand_id);
		}else{
			 
			$("#brand_id").empty();
			$("#brand_tr").hide();
			 
		}
		 
			if(!brand_id){ //如果传递brand_id 说明在编辑
				this.loadPropsInput(type_id);
				this.loadParamsInput(type_id);
			}	
	},
	//异步读取品牌输入项
	loadBrandsInput:function(type_id,brand_id){
		$.ajax({
		type: "get",
		url:basePath+"type!listBrand.do?ajax=yes",
		data:"type_id=" + type_id +"&m=" + new Date().getTime(),
		dataType:"html",
		success:function(result){
			 
			   $("#brand_id").empty().append(result);
			   if(brand_id){
			   	$("#brand_id").val(brand_id);
			   }
		  
		},
		 error :function(res){alert("异步读取失败:" + res);}
		});
	},
	//加载参数输入项
	loadParamsInput:function(type_id){
		$.ajax({
		type: "get",
		url:basePath+"type!disParamsInput.do?ajax=yes",
		data:"type_id=" + type_id +"&m=" + new Date().getTime(),
		dataType:"html",
		success:function(result){
			try{
				$("#tab_params").html(result);
		    }catch(e){ alert(e); }
		  
		},
		 error :function(res){alert("异步读取失败:" + res);}
		});
	},
	 loadPropsInput:function(type_id){
		$.ajax({
		type: "get",
		url:basePath+"type!disPropsInput.do?ajax=yes",
		data:"type_id=" + type_id +"&m=" + new Date().getTime(),
		dataType:"html",
		success:function(result){
		   document.getElementById("tab_props").innerHTML = result;
		//   $("form.validate").addinput($("#tab_props input,#tab_props select"));
			var catid = $("#cat_id").val();
		 
			if(catid =='3' || catid =='4' || catid =='12' || catid =='19' ){}else {
				$("#weight_tr").hide();
			}
		 
		},
		 error :function(res){alert("异步读取失败:" + res);}
		});
	}
	
};
