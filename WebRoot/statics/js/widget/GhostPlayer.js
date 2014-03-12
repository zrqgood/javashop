
/**
 * v1.0
 * 鬼魂演员:)
 */
var GhostPlayer=enation.eop.GhostPlayer = {


	/*
	 * 创建鬼
	 */	
	create:function(widget,panel){
//		this.droped = false; 
		this.can_destory = true; //可以除鬼
		var type =panel.attr(EopTypes.NAME);
		
		if (type==EopTypes.LAYOUT&&panel.get(0) !=  widget.parent().get(0) ) {
			this.doCreate(panel,widget);
			this.display(panel,widget);
		}

		if (type==EopTypes.WIDGET &&panel.get(0)!=  widget.get(0) ) {
				
			this.doCreate(panel,widget);
			if( widget.get(0).nextSibling == panel.get(0)  )
				this.display(panel,widget,true);
			else
				this.display(panel,widget);
		}
	
		if(type==EopTypes.WIDGET_BUNDLE){
			this.doCreate(panel,widget);
			this.display(panel,widget);			
		}
	},
	/**
	 *  在这里控制鬼长的啥样
	 */
	doCreate:function(creator,widget){
		
		if(!this.ghost){
			
			if(widget.attr(EopTypes.NAME)==EopTypes.WIDGET_BUNDLE){
				this.ghost = $('<div class="widget"  eop_type="widget" ></div>');
				this.ghost.attr("widgettype",widget.attr("widgettype"));
				this.ghost.attr("appid",widget.attr("appid"));
			}else{
				this.ghost =   widget.clone(false);
				this.ghost.empty();
				this.ghost.attr("style","");
				this.ghost.attr("class","widget");
//				this.ghost = $('<div  appid="shop" widgetType="goods_list" class="widget"  eop_type="widget" ></div>');	
				
			}
			this.ghost.addClass(ClassName.WIDGET_GHOST);
		//	this.ghost.css("background-color","transparent");
	 		debug(creator.attr("id") + " ghost create...");
		}else{
			this.ghost.addClass(ClassName.WIDGET_GHOST);
			debug("鬼魂尤在...");
		}
	},
		
	/*
	 * 使鬼显现
	 *
	 */
	display:function(creator,widget,after){
		
		//清除微调时可能赋予的样式
		this.ghost.css("width","");
		this.ghost.css("height","");
		this.ghost.css("top","");
		this.ghost.css("left","");
		
		
		if(after){
			if(this.ghost){
				var chk_lockwidth = widget.children("div.handle").children("span."+ WidgetConfig.ClassName.LOCKWIDTH ).children("[type='checkbox']");
				
				debug(" widget is :"+ widget.children("div.handle").attr("class"));
				
				if( chk_lockwidth &&chk_lockwidth.attr("checked")){ //锁定挂件宽度
					this.ghost.width( widget.width() );
					debug("ghost in widget width: "+ widget.width());
				}else{
					this.ghost.width( creator.parent("[eop_type='layout']").width() );
				}				
				this.ghost.height(widget.height());
				this.ghost.insertAfter(creator);
				
			}
			return;
		}
		
		var type = creator.attr(EopTypes.NAME);
		debug("type is "+ type +", draw ghost on " + creator.attr("id"));
		
		
 		
		if(type== EopTypes.WIDGET){ //拖到挂件上了
			
			var chk_lockwidth = widget.children("div.handle").children("span."+ WidgetConfig.ClassName.LOCKWIDTH ).children("[type='checkbox']");
			
			debug(" widget is :"+ widget.children("div.handle").attr("class"));
			
			if( chk_lockwidth &&chk_lockwidth.attr("checked")){ //锁定挂件宽度
				this.ghost.width( widget.width() );
				debug("ghost in widget width: "+ widget.width());
			}else{
				this.ghost.width( creator.parent("[eop_type='layout']").width() );
			}
			
			this.ghost.insertBefore(creator);
			this.ghost.height( widget.height() );
		}
		
		if(type==EopTypes.LAYOUT ){
			var chk_lockwidth = widget.children("div.handle").children("span."+ WidgetConfig.ClassName.LOCKWIDTH ).children("[type='checkbox']");
			
			debug("classnae" + WidgetConfig.ClassName.LOCKWIDTH)
			debug("chk_lockwidth checked :"+ chk_lockwidth.attr("checked"));
			if(  chk_lockwidth &&chk_lockwidth.attr("checked")){ //锁定挂件宽度
				this.ghost.width( widget.width() );
				debug("ghost in layout width: "+ widget.width());
			}else{			
				this.ghost.width( creator.width() );
			}
			if(widget.attr(EopTypes.NAME) ==  EopTypes.WIDGET_BUNDLE){
				this.ghost.height(100);
			}else{
				this.ghost.height( widget.height() );
			}

			this.ghost.appendTo(creator);
		}
		
		
	}
	,
	getGhost:function(){
		return this.ghost;
	}
	,
	/*
	 * 使鬼转世
	 */
	incarnation:function(widget,panel){
		this.can_destory = false; //正在转世不能除鬼
		if(this.ghost){ //有鬼才能成真
			debug("有鬼，成真..");
		 	var that = this;
			widget.fadeOut(function(){
				that.comeTrue(panel,widget);
			 }
			);
		}else{
			debug("无鬼，放弃..");
		}
			
	},
	
	/*
	 * 消灭鬼
	 */
	destroy:function(creator,widget){
		if(this.can_destory){
			debug("distory...");
			if(this.ghost){
				this.ghost.remove();
				this.ghost =undefined;
				debug("distory..ok..");
			}
		}
	},
	
	comTrueStart:undefined,
	
	newWidget:function(newWidget){
		if(this.ghost){
			this.ghost.replaceWith(newWidget);
			enation.eop.WidgetController.init(newWidget);  //初始化挂件的事件		
		}
	}
	,
	comeTrue:function(creator,widget){
		if(this.ghost){
			
			
			widget.resizable('destroy');
			this.ghost.append(widget.children() );
			this.ghost.attr("class",widget.attr("class"));
			//this.ghost.removeClass(ClassName.WIDGET_GHOST);	
			var tmp_width = this.ghost.css("width");
		
			this.ghost.attr("style",widget.attr("style"));
			//清除上世的记忆
			this.ghost.css("top","");
			this.ghost.css("left","");			
			this.ghost.css("width",tmp_width); //但是宽已经是设字好的了
			
			if(this.comTrueStart && typeof this.comTrueStart == 'function' ){
				this.comTrueStart(this.ghost);
				this.comTrueStart= undefined;
				
			}	
			
			this.ghost.show();			
			this.ghost.css("height","");//清除掉当新创建时造成的在ie下高是定死的，使
			
		
			enation.eop.WidgetController.init(this.ghost);
			if (!$.browser.msie) {  //如果是兼容性浏览器
				this.ghost.css("height","auto");
				var borderwidth =WidgetConfig.WIDGET_WRAPHELPER_BORDERWIDTH *2;
				this.ghost.children(".wrapHelper").width(this.ghost.width()-borderwidth).height(this.ghost.height()-borderwidth);
			}else{
				this.ghost.children(".wrapHelper").width(this.ghost.width()).height(this.ghost.height());
			}

			widget.remove();
			this.ghost =undefined;
			
			debug("ghost comeTure ok...");
		}else
		{
			error("ele is undefined");
		}	
	},
	setGhostId:function(id){
		this.ghost.attr("id",id);
	},
	setCanDestory:function(canDestory){
		this.can_destory =canDestory;
	}

};
