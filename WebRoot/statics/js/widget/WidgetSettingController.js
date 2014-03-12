var WidgetDialog={
	onOpen:undefined,
	onSubmit:undefined,
	init:function(){
		this.onOpen= undefined;
		this.onSubmit = undefined;
	},
	doOpen:function(){
		if(this.onOpen && typeof( this.onOpen  )=='function' ){
			this.onOpen();
		} 
	},
	doSubmit:function(){
		if(this.onSubmit && typeof( this.onSubmit  )=='function' ){
			this.onSubmit();
		} 
	}
};

enation.eop.WidgetSettingController={
		
		pWidget:undefined,
		pTitle:undefined,
		pContent:undefined,
		widget:undefined,
		settingOperator:undefined,
		
		init:function(widget){
		
			/*
			 * 初始化参数操作
			 * 主要是使控件的值选中
			
			//enation.eop.WidgetSettingOperator.ParamsSettingOp.init(widget);
			
			this.pWidget = $("#widget_setting_con #wgt_preview>div");
			this.pTitle  =  this.pWidget.children(".title");
			this.pContent  =  this.pWidget.children(".content");
			
			if(widget){
				this.widget = widget;
				this.syncPrew();
				this.isEdit= true;
			}else{
				this.isEdit=false;
			}
			 */
			/*
			 * 在菜单的点击时初始化相关样式控件的值
			 * 此时调用的类名起的不好，更确切应该叫styleSettingOp
			 */
			this.MenuController.borderClick();
			this.bindMenuEvent();
		},
		
		/*
		 * 同步预览区域
		 */
		syncPrew:function(){
			
			//同步挂件的样式
			this.pWidget.addClass(this.widget.attr("class"));
			this.pWidget.attr("w",this.widget.css("width") );
			this.pWidget.attr("h",this.widget.css("height") );
			this.pWidget.attr("style",this.widget.attr("style") )
			.css("width","300px").css("height","200px");
			
			//同步挂件标题的样式
			var title  = this.widget.children(".title");
			this.pTitle.attr("style",title.attr("style"));
			//this.pTitle.attr("w",title.css("width") );
			//this.pTitle.attr("h",title.css("height") );
			
			//同步挂件内容的样式
			var content  = this.widget.children(".content");
			this.pContent.attr("style",content.attr("style"));
			//this.pContent.attr("w",content.css("width") );
			//this.pContent.attr("h",content.css("height") );
			
		}
		,
		
		/*
		 * 同步挂件样式
		 */
		syncWidgetStyle:function(){

	
			this.widget.attr("class",this.pWidget.attr("class"));
			this.widget.attr("style",this.pWidget.attr("style") );
		
			//isedit的作用是使在新增挂件时宽度无效 
			if(this.isEdit){
				debug("set width");
				this.widget.css("width",this.pWidget.attr("w") );
				this.widget.css("height",this.pWidget.attr("h") );
			}else{
				this.widget.css("width","100%").css("height","auto");
			}
			var titleStyle = this.pTitle.attr("style");
			titleStyle=titleStyle?titleStyle:"";
			this.widget.children(".title").attr("style",titleStyle );
			
			var contentStyle =this.pContent.attr("style");
			contentStyle=contentStyle?contentStyle:"";
			this.widget.children(".content").attr("style",contentStyle );
			
			
		}
		,
		
		/*
		 * 同步挂件属性
		 */
		syncWidgetAttr:function(){
			enation.eop.WidgetSettingOperator.ParamsSettingOp.setWidget(this.widget);
			enation.eop.WidgetSettingOperator.ParamsSettingOp.syncWidgetAttr();
		}
		
		,
		
		/**
		 * 绑定菜单的事件
		 */
		bindMenuEvent:function(){
			var that  = this;
			$("#wgt_setting_menu ul>li").click(function(){
				$("#wgt_setting_menu ul>li").removeClass("active");
								
				var li =$(this);
				li.addClass("active");
				
				var id = li.attr("id");
				if(id == "params"){
					that.hideSetting();
				}else{
					that.showSetting();
				}
				
				if(id=="custom"){
					that.MenuController.customClick(li);
				}
				
				if(id=="template"){
					that.MenuController.templateClick();
				}
				

				if(id=="border"){
					that.MenuController.borderClick();
				}
				
				if(id== "block"){
					that.MenuController.blockClick();
				}
				
				if(id== "title"){
					that.MenuController.titleClick();
				}

				if(id== "content"){
					that.MenuController.contentClick();
				}
				
				
			});
 	
		}
		,
		
	   showSetting:function(){
			$("#style_setting").removeClass("hid");
			$("#params_setting").addClass("hid");
	   }
		,
		hideSetting:function(){
			$("#style_setting").addClass("hid");
			$("#params_setting").removeClass("hid");
	   },
	   
	   
		/**
		 * 菜单控制器
		 */
		MenuController:{
		    customClick:function(li){
		   		li.next("ul").toggleClass("hid");
	   		},
			blockClick:function(){
				
				//$("#wgt_setting_menu ul #block").addClass("active");
				$("#widget_setting_con #wgt_setting_content").load(
					 "eop/setting/block.jsp",
					 {},
					 function(){
						 //初始化区块设置的tabs
						 var widget  = $("#widget_setting_con #wgt_preview>div");
						 enation.eop.WidgetSettingOperator.BlockSettingOp.initTabs(widget,'yes');
					 }
				 );
			},
			
			
			titleClick:function(){
				$("#widget_setting_con #wgt_setting_content").load(
						 "eop/setting/title.jsp",
						 {},
						 function(){
							 //初始化title设置的tabs
							 var widget  = $("#widget_setting_con #wgt_preview>div .title");
							 enation.eop.WidgetSettingOperator.TitleSettingOp.initTabs(widget,'no');
						 }
					 );				
			},
			
			
			contentClick:function(){
				$("#widget_setting_con #wgt_setting_content").load(
						 "eop/setting/content.jsp",
						 {},
						 function(){
							 //初始化title设置的tabs
							 var widget  = $("#widget_setting_con #wgt_preview>div .content");
							 enation.eop.WidgetSettingOperator.ContentSettingOp.initTabs(widget,'no');
						 }
					 );						
			},
			borderClick:function(){ 
				$("#widget_setting_con #wgt_setting_content").load(
						 "eop/border.do",
						 {},
						 function(){
							  enation.eop.WidgetSettingOperator.TemplateOp.init();
						 }
				 );					
			},			
			templateClick:function(){
				//因3.0架构去掉了挂件模板的操作，改变边框了
	/*			$("#widget_setting_con #wgt_setting_content").load(
						 "eop/setting/templatelist.jsp",
						 {},
						 function(){
							 enation.eop.WidgetSettingOperator.TemplateOp.init();
						 }
				 );		*/			
			}
		} ,
		
		/*
		 * 保存挂件
		 */
		saveWidget:function(widget){
			
			if(widget){
				this.widget= widget;
			}
		
			
			this.syncWidgetStyle();
			this.syncWidgetAttr();
			////因3.0架构去掉了挂件模板的操作，改变边框了
			//enation.eop.WidgetSettingOperator.TemplateOp.saveWidget();
			$(".colorpicker").remove();
		}
		
};
