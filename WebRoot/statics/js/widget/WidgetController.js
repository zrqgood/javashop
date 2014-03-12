
/**
 * 挂件控制器
 * 使挂件具有可拖拽、可缩放等功能
 */
enation.eop.WidgetController={
		
	widgets:undefined,  //挂件集合
	handleAble:true,//是允许handle效果,为了防治reize时的怪癖
	widgetOperator:{},
	
	/*
	 * 集成一切
	 */
	init:function(widgets){
		this.widgets= widgets;
		
		this.widgetOperator = enation.eop.WidgetOperator;
		this.initWidget();
		this.dragAble();
		this.resizeAble();
		this.handleDynamic();
		this.dropAble();
		this.adjustAable(widgets.children("div.handle").children("span.adjust"));
		this.HandleController.init(
				widgets.children("div.handle").children("span").children("a.edit"),
				widgets.children("div.handle").children("span").children("a.delete")
		);
		return this;
	},
	
	/*
	 * 初始化挂件
	 */
	initWidget:function(){
		this.widgets.children(Selector.WIDGET_HANDLE).hide();
		this.widgets.children(Selector.WIDGET_WRAPHELPER).hide();
		if (!$.browser.msie) { 
			this.widgets.css("height","auto");
		}else{
			this.widgets.each(function(){
				var widget  = $(this);
				widget.css("height",widget.height()) //为是解决挂件resize helper左侧不显示的问题 
			});
		}
	},
	
	/*
	 * 使挂件可拖动
	 */	
	dragAble:function(){

		this.widgets.draggable({handle : '.handle',revert : 'invalid',addClasses : false,cursor : 'move', scrollSensitivity: 100});
		this.autoDrag(this.widgets);
		
	},

	
	/*
	 * 使挂件可微调
	 */
	adjustAable:function(adjustSpan){
		var that=this;
		var chks =adjustSpan.children("input[type=checkbox]");
		chks.attr("checked",false);
		chks.click(function(){
			var widget =  $(this.parentNode.parentNode.parentNode);
			if(this.checked){
				that.adjustDrag(widget);
			}else{
				that.autoDrag(widget);			
			}			
		});
		
	},
	
	/*
	 * 自动式拖动
	 */
	autoDrag:function(widget){
		widget.draggable('option',  'containment', 'document');	
		widget.draggable('option', 'helper', this.widgetOperator.dragHelper);
		widget.draggable('option', 'cursorAt', { top : 10,left : 50 });
		widget.draggable('option', 'opacity', 0.7);
		widget.bind('dragstart', this.widgetOperator.dragStartEvent);
		widget.bind('dragstop', this.widgetOperator.dragStopEvent);

		var panel = widget.parent("[eop_type='layout']");
		this.widgets = panel.children("[eop_type=widget]");
		this.dropAble();
		
	}
	,
	/*
	 * 微调式拖动
	 */
	adjustDrag:function(widget){
		widget.draggable('option', 'helper', 'original');
		widget.draggable('option',  'containment', 'parent');	
		widget.draggable('option', 'cursorAt', { top:undefined,left:undefined});
		widget.draggable('option', 'opacity', 1);
		  
		widget.unbind('dragstart');
		widget.unbind('dragstop');		
		
		//当挂件微调时，同一布局下的挂件不再可放置挂件
		var panel = $( widget.get(0).parentNode );
		panel.children("[eop_type=widget]").each(function(){
			var childWidget = $(this);
			childWidget.droppable('destroy');
		});
	},
	
	//使挂件可缩放
	resizeAble:function(){
		//		alsoResize:function(){ return $(this).parent()},
		//.children(".wrapHelper")
		var rh="e,s,se";
		if(this.widgets.attr("overflow")=='visible'){
			var rh="e";
		}
		
		var startheight,startwidth,c,cStartHeight,cStartWidth;
		var handleAble = this.handleAble;
		var thatWidget = this.widgets;
		this.widgets.resizable( 'destroy' );
		this.widgets.resizable( {autoHide:true,
		containment: 'parent',
		minWidth:WidgetConfig.WIDGET_RESIZE_MINWIDTH,
		minHeight:WidgetConfig.WIDGET_RESIZE_MINHEIGHT,
		handles:rh,
			start:function(event,ui){
			 var widget  = $(this);
				if(widget.attr("overflow")=='hidden'){
				   
				    var content =widget.children(".content");
					startheight = widget.height();
					startwidth = widget.width();
					cStartHeight = content.height();
				}
			},
			resize:function(event){
				handleAble = false;
				var widget = $(this);
				if (!$.browser.msie) {  //如果是兼容性浏览器
					var borderwidth =WidgetConfig.WIDGET_WRAPHELPER_BORDERWIDTH *2;
					widget.children(".wrapHelper").width(  widget.width()-borderwidth ).height( widget.height()-borderwidth );
				}else{
					widget.children(".wrapHelper").width(  widget.width() ).height( widget.height());
				}
				
				var widget  = $(this);
				
				if(widget.attr("overflow")=='hidden'){
					var content =widget.children(".content");
					var endheight =widget.height();
					var endwidth = widget.width();
					content.height(  cStartHeight+ (endheight-startheight) );
				}
			},
			stop:function(event){
				handleAble=true;
			}
		});
		
	},
	
	/*
	 *挂件handle动态效果 
	 *及绑定事件 
	 */
	handleDynamic:function(){
		this.widgets.hover(this.widgetOperator.hoverInEvent,this.widgetOperator.hoverOutEvent);	
	},
	
	/*
	 * v1.0:
	 *1.使挂件可以拖到挂件上
	 *2.使 挂件束可以拖到挂件上
	 */
	dropAble:function(){
		
		///*greedy:true  切不可加入此参数，会使挂件拖到挂件再拖回容器时无效，因为事件中断
		this.widgets.droppable( {accept : Selector.WIDGET_DROP_ACCPET,addClasses : false});
		this.widgets.bind('dropover',this.widgetOperator.containerOver);
		this.widgets.bind('dropout',this.widgetOperator.containerOut);
		this.widgets.bind('drop',this.widgetOperator.containerDrop);
		
	},
	
	/*
	 * 挂件handle的控制器
	 */
	HandleController:{
		widgetOperator:undefined,
		init:function(settingSpan,deleteSpan){
			this.widgetOperator = enation.eop.WidgetOperator;
			settingSpan.click(this.widgetOperator.handleSettingEvent);
			deleteSpan.click(this.widgetOperator.handleDeleteEvent);
			$(Selector.WIDGET_HANDLE).disableSelection();
		}
 
		
	} 
	
};


/**
 * 挂件束控制器
 */
enation.eop.WidgetBundleController={
	bundle:{},	
	init:function(bundle){
		this.bundle = bundle;
		this.dragAble();
	},
	/*
	 * 使挂件束可拖动
	 */
	dragAble:function(){
		 
		 		this.bundle.draggable( {
			revert : 'invalid',
			opacity : 0.7,
			cursorAt:{top : 10,left : 50},
			cursor : 'move',
			scrollSensitivity: 100,
			zIndex: 99999999,
			helper : function(){
			 
				//构建挂件束拖动的helper
				var helper = $(this).clone();
				helper.addClass("bundleHelper");
     			helper.appendTo( $("#pagemain") );
     			
				return  helper;
			},
			addClasses : false
			,start:function(){
				//为了修复挂件helper appendto pagemain 后造成的 helper不显示的问题
				// overflow:visable时会显示
				$("#pagemain").css("overflow","visible");
				enation.eop.WidgetController.handleAble=false; //防止拖动时挂件handle还出现
				
			},
			stop:function(){
				//为了修复挂件helper appendto pagemain 后造成的 helper不显示的问题
				// overflow:visable时会显示
				$("#pagemain").css("overflow","auto");
				GhostPlayer.destroy();
				enation.eop.WidgetController.handleAble=true;
			}
		});	 
	}

};
//	this.widgets.draggable('option', 'cursorAt', { top : 10,left : 50 });
