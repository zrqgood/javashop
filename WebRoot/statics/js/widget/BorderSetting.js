
enation.eop.WidgetSetting.BorderSetting =$.extend(
{},enation.eop.WidgetSetting.Setting,
{
	
	pos:['top','bottom','left','right'],
	styles:["width","style","color"],
	init:function(invoker,widget){
		this.operator = invoker;
		this.syncWidgetStyle(widget,"border"); //继承的
		this.initSetting();
		this.bindSameEvent();
		this.initColorPicker("#tab-border .colorSelector div");
		this.bindValueSync();
	},
	
	/**
	 * 绑定控件的change事件
	 * 使当在全部相同选中时一起变
	 * 使当在全部相同未选中时一起变
	 */
    bindValueSync:function(){
		var that =this;
		$.each(this.styles,function(s_i,style){
			$("#border-top-"+style).change(function(){
				var el = $(this);
				var value = el.val();
		
				if( $("#border-"+style).attr("checked") ){
					$.each(that.pos,function(p_i,pos){
						if(p_i>0){
							$("#border-"+pos+"-"+ style).val(value );
							that.syncPreview("border-"+pos+"-"+ style,value);
						}	
						
						if(style=="color"){
							that.syncColorValue(value);	
						}
						
					});
				}						
			}
			);
		});
	},
	
	
	/*同步颜色值*/
	syncColorValue:function(value){
		var that = this;
		if( $("#border-color").attr("checked") ){
			$.each(that.pos,function(p_i,pos){
				if(p_i>0){
					$("#border-"+pos+"-color")
					.val(value )
					.prev("div").css("background-color",value);
					
				}
				that.syncPreview("border-"+pos+"-color",value);
			});
		}				
	},

	
	/**
	 * 绑定是否全部相同checkbox的事件
	 */
	bindSameEvent:function(){
		var that =this;
		$("#border-width").click(function(){
			if(this.checked){
				that.disableOther("width");
				that.syncPreview("border-width",$("#border-top-width").val());
			}else{
				that.enableAll("width");
			}
		});

		$("#border-style").click(function(){
			if(this.checked){
				that.disableOther("style");
				that.syncPreview("border-style",$("#border-top-style").val());
			}else{
				that.enableAll("style");
			}
		});

		$("#border-color").click(function(){
			if(this.checked){
				that.disableOther("color");
				var value = $("#border-top-color").val();
				that.syncColorValue(value); 
				that.syncPreview("border-width",value);
			}else{
				that.enableAll("color");
			}

			
		});
		
		
	}
	
	,
	/**
	 * 初始化边框设置
	 * 主要目的是将该选中的选中
	 */
	initSetting:function(){
		var widths=[],styles=[],colors=[];
		var widthsame=true,stylesame=true,colorsame=true;
		
		$.each(this.pos,function(i,v){
			widths[i] = $("#border-"+v+"-width").val();
			styles[i] = $("#border-"+v+"-style").val();
			colors[i] = $("#border-"+v+"-color").val();
		});
	 
		/*
		 * 计算值是否全部相等
		 * */
		$.each(widths,function(i,v){
			if(widths[0] != v){ widthsame =false;}
		});

		$.each(styles,function(i,v){
			if(styles[0] != v){ stylesame =false;}
		});

		$.each(colors,function(i,v){
			if(colors[0] != v){ colorsame =false;}
		});
		
		if(  widthsame ){
			$("#border-width").attr("checked","true");
			this.disableOther("width");
		}
		
		if(stylesame){
			$("#border-style").attr("checked","true");
			this.disableOther("style");
		}

		if(colorsame ){
			$("#border-color").attr("checked","true");
			this.disableOther("color");
		}
	}
	,
	
	/**
	 * 全部相同是禁用其它（除了第一个）
	 * 并且将值赋为第一个的值
	 */
	disableOther:function(type){
		var value  = $("#border-top-"+ type ).val();
		value=value?value:'';
		for(var i=1;i<this.pos.length;i++ ){
			$("#border-"+this.pos[i]+"-"+ type )
			.attr("disabled",true)
			.val(value )
			.prev("div")
			.removeClass("enable") ;
		}
		
	},
	
	/**
	 * 不同时使控件可用 
	 * 并且将值赋为第一个的值
	 */
	enableAll:function(type){
		for(var i=0;i<this.pos.length;i++ ){
			$("#border-"+this.pos[i]+"-"+ type )
			.attr("disabled",false)
			.prev()
			.addClass("enable") ;
		}		
	 
	}
}

);