
enation.eop.WidgetSetting ={};
/**
 * 共用设置
 */ 
enation.eop.WidgetSetting.Setting={
		operator:undefined,
		
		/*
		 * 同步控件的值和 挂件的样式
		 * 用预览对象样式值设置控件的值
		 */
		syncWidgetStyle:function(widget,type){
			if(!widget) return;  //如果是新建则不需要同步
			
			var that = this;
			//挂件的样式设置为eop_type='widget_$type'
			//按widet_$type的控件要求从widget取值，并更改控件的属性
			type= "widget_"+type;
			$("[eop_type='"+ type +"']").each(function(){
				var el = $(this);
				var styleName = el.attr("id");
				var styleValue = widget.css(styleName);
				if(styleValue && (styleValue.toUpperCase().indexOf('RGB')==0 ||  styleValue.indexOf('#')==0 ) ){
					styleValue = new RGBColor(styleValue).toHex();
				}	
				//debug("name " + styleName +",value " + styleValue );
				
				//对于宽和高取对象的w和h，因为宽和高不能预览
				if(styleName=="width"){
					styleValue = widget.attr("w");
				}
				if(styleName=="height"){
					styleValue = widget.attr("h");
				}				
				
				
				el.val(styleValue);
				el.parent(".colorSelector").children("div").css("background-color",styleValue);
				//that.syncPreview(styleName,styleValue);
			});	
			
			this.bindSyncPreview(type);
		},
		
		/*
		 *绑定用控件同步预览区的事件 
		 */
		bindSyncPreview:function(type){
			var that =this;
			$("[eop_type='"+ type +"']").change(function(){
				var el = $(this);
				that.syncPreview(el.attr("id"),el.val() );
			});
		}
		,
		
		/*
		 * 同步预览区域
		 */
		syncPreview:function(name,value){
			
			if(name!="width" && name!="height"){ //高和宽不能预览
				
				if(name=="background-image" ){
					if( value=='none'|| value ==''){
						value='none';
					}else{
						value = "url("+ value+")";
					}
				}
				
				
				if(this.operator.getSyncTarget()){
					//由操作者那里得到同步目标
					debug(" prev  "+ name +"->"+ value);
					this.operator.getSyncTarget().css(name,value);
				}else{
					alert("子类必须指定 syncTarget ");
				}
			}else{ //对高和宽的特殊处理 
				if(name == "width") name="w";
				if(name == "height") name="h";
				debug("name "+ name +" value "+ value); 
				this.operator.getSyncTarget().attr(name,value);
			}
		}
		,
		/*
		 * 初始化颜色选择器
		 * 如果用此方法,子类必须实现syncColorValue方法
		 */
		 initColorPicker:function(selector){
			
			var that = this;
	
			$(selector).ColorPicker({
				onShow: function (colpkr) {
				    if( $(this).attr("class") == "enable") 
						$(colpkr).fadeIn(500);
				    
					return false;
				},
				onHide: function (colpkr) {
					$(colpkr).fadeOut(500,function(){
						
					});
					return false;
				},
				/*使打开颜色选择器前先将颜色定位好*/
				onBeforeShow: function () {
					var colorHandlor = $(this);
					var value  = colorHandlor.next("input").val(); 
				 
					if(value){
						value= value.replace('#','');
						colorHandlor.ColorPickerSetColor(value);
					}
				},
				/*改变颜色*/
				onChange:function(hsb, hex, rgb,el){
					$(el).css('backgroundColor', '#' + hex);
					$(el).next("input").val('#' + hex);
					
					 
					that.syncColorValue('#' + hex);
					
				}
				
			});
			$('.colorpicker').css("position","absolute");
			$('.colorpicker').css("z-index","2002");			

			$("#widget_setting_con .colorSelector .text").change(function(){
				$(this).prev("div").css("background-color",this.value);				
			});
			$("#widget_setting_con .colorSelector .btn").click(function(){
				$(this).prev("input").val("transparent");
				$(this).prev("input").prev("div").css("background-color","transparent");
				that.syncColorValue('transparent' );
			});			
		}			

};
