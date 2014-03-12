enation.eop.WidgetSettingOperator ={
		syncTarget:undefined,		
		
		initTabs:function(widget,isbox){
			this.syncTarget = undefined; 
			var that = this;
			$("#setting_tabs").tabs({selected: 0 });
			
			//加载border的设置界面
			$("#tab-border").load("eop/setting/border.jsp",{},function(){
				enation.eop.WidgetSetting.BorderSetting.init(that,widget);				
			});
			
			//加载背景的设置界面
			$("#tab-background").load("eop/setting/background.jsp",{},function(){
				enation.eop.WidgetSetting.BackgroundSetting.init(that,widget);
			});

			//加载位置的设置界面
			$("#tab-position").load("eop/setting/position.jsp",{'isbox':isbox},function(){
				enation.eop.WidgetSetting.PositionSetting.init(that,widget);
			});	

		}		
		
};
/**
 * 区块设置操作者
 */
enation.eop.WidgetSettingOperator.BlockSettingOp=$.extend({},
enation.eop.WidgetSettingOperator
,{
		
	getSyncTarget:function(){ 
		if(!this.syncTarget) {
			this.syncTarget = $("#widget_setting_con #wgt_preview>div" );
		}
		return this.syncTarget;
	}

});


/**
 * 标题设置操作者
 */
enation.eop.WidgetSettingOperator.TitleSettingOp=$.extend({},
enation.eop.WidgetSettingOperator,
{
 
	getSyncTarget:function(){ 
		if(!this.syncTarget) {
			this.syncTarget = $("#widget_setting_con #wgt_preview>div .title" );
		}
		return this.syncTarget;
	}
}
);



/**
 * 内容设置操作者
 */
enation.eop.WidgetSettingOperator.ContentSettingOp=$.extend({},
enation.eop.WidgetSettingOperator,
{
	getSyncTarget:function(){ 
		if(!this.syncTarget) {
			this.syncTarget = $("#widget_setting_con #wgt_preview>div .content" );
		}
		return this.syncTarget;
	}
}
);

/**
 * 参数设置操作者
 */
enation.eop.WidgetSettingOperator.ParamsSettingOp={
		
	widget:undefined,
	setWidget:function(widget){
		this.widget =widget;	
	},
	init:function(widget){
		
		if(widget){
			this.widget = widget;
			this.initParams();
		}
	},
	/*
	 * 初始化挂件的参数
	 */
	initParams:function(){
		var that =this;
		$("[eop_type='widget_params']").each(function(){
			var paramEl = $(this);
			var paramName = paramEl.attr("id");
			var paramValue = that.widget.attr(paramName); 
			paramEl.val(paramValue);
		});
	},
	
	/*
	 * 同步挂件的属性
	 */
	syncWidgetAttr:function(){
		var that =this;
		
		$("[eop_type='widget_params']").each(function(){
			
			var paramEl = $(this);
			var paramName = paramEl.attr("id");
			var paramValue = paramEl.val();
		
			that.widget.attr(paramName,paramValue); 
			
		});
		
		if($("#overflow").val()=='visible'){ //自动增高
			this.widget.css("overflow","visible");
			this.widget.children(".content").css("height","");
			this.widget.children(".content").css("overflow","visible");
			
		}else{
			this.widget.css("overflow","hidden");
			this.widget.children(".content").css("overflow","hidden");
			 
		}
	}
	

};


enation.eop.WidgetSettingOperator.TemplateOp={
	pWidget:undefined,
	init:function()	{
		this.bindChangeEvent();
		this.pWidget = $("#widget_setting_con #wgt_preview>div");
		 
	},
	bindChangeEvent:function(){
		var that = this;
		$("#widget_setting_con .wdg_tpl ul li").click(function(){
			$("#widget_setting_con .wdg_tpl ul li").removeClass("active");
			var li =$(this);
			li.addClass("active");
			that.changeTpl(li);
		});
	},
	changeTpl:function(li){
		var id = li.attr("id");
		$.ajax({
			  type: "GET",
			  url: "widget/?act=create&rmd="+ new Date().getTime(),
			  data:"border="+id+"&type=preview_widget",
			  dataType: "html",
			  success : function(result) {
				$("#wgt_preview").html(result);
			  },
			  error:function(){alert("error");}
		});
		$("#wdborder").val(id);
	}
	
/*	
	 * 切换挂件样式名
	 
	changeTpl:function(li){
		var id = li.attr("id");
		var name = li.attr("name");
		
		if(SiteContext){
			this.cleanWidgetStyle();
			
			var styleExist = false;
			var styleUrl = SiteContext.staticserver
			+"/user/"
			+SiteContext.userid 
			+"/" 
			+ SiteContext.siteid
			+"/tpl/"+name+ "/"+  id +".css";
			
			$("link").each(function(){
				if($(this).attr("href")==styleUrl){
					styleExist= true;
				}
			});
			
			if(!styleExist)
				this.getCSS( styleUrl);
			
			this.pWidget.addClass("widget");
			this.pWidget.addClass(id);
//			this.pWidget.attr("h", this.pWidget.css("height") );
//			this.pWidget.attr("w", this.pWidget.css("width") );
		}else{
			alert("站点上下文初始化错误");
		}
	}
	
	,
	
	 * 清除预览区的样式
	 
	cleanWidgetStyle:function(){
		this.pWidget.removeAttr("class").removeAttr("style")
					.css("width","300px").css("height","200px");
		this.pWidget.children(".title").removeAttr("style");
		this.pWidget.children(".content").removeAttr("style");
	},
	saveWidget:function(){
		if(this.tplStyle){
			this.tplStyle.removeAttr("id");
		}
	}
	,
	getCSS :function( url, media ){
		 
		 this.tplStyle = $("#tpl_style");
		 if(this.tplStyle.size()==0){
			 
			 this.tplStyle = $(document.createElement('link') ).attr({
		    	  id:'tpl_style',
		          media: media || 'screen',
		          type: 'text/css',
		          rel: 'stylesheet'
		      }).appendTo('head');	
		      debug("tpl_style not exist append");
		 }else{
			  debug("tpl_style is  exist");
		 }
		 
		 this.tplStyle.attr("href",url);
		 
	   }
	*/
};
