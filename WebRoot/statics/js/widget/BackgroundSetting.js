
/*背景设置*/
enation.eop.WidgetSetting.BackgroundSetting=$.extend({},
enation.eop.WidgetSetting.Setting,		
{
	init:function(invoker,widget){
		this.operator = invoker;
		this.syncWidgetStyle(widget,"background"); //继承的
		this.initColorPicker("#tab-background .colorSelector div");
		this.bindCleanBkImg();
	},
	syncColorValue:function(value){
		this.syncPreview("background-color",value);
	},
	bindCleanBkImg:function(){
		var that =this;
		$("#widget_setting_con .back_img_clean").click(
				function(){
					if(confirm("确认要清除背景图吗?")){
						$(this).prev("input").val("none");
						that.syncPreview("background-image","none");
					}
				}				
		);
	} 
});
