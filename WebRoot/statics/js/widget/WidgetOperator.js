
/**
 * 挂件操作者
 * 包含了挂件的各种事件响应、属性改变
 */
enation.eop.WidgetOperator={
		
	
	
	/*v1.0
	 * 响应挂件开始拖动事件
	 */
	dragStartEvent:function(){
	debug("drag start...")
		//给挂件加上正在拖动的样式，一般来说此样式使挂件看起来不可用
		$(this).addClass(ClassName.WIDGET_DRAGIN  );
	},
	
	
	
	
	/*
	 * v1.0
	 * 响应挂件拖动响应停止事件
	 */
	dragStopEvent:function(event,ui){
		debug("drag end...")
		//修补拖动事件过快ghost会残留的问题,既无论如何都销毁ghost
		GhostPlayer.destroy();
		
		//去除挂件的正的拖动样式
		$(this).removeClass(ClassName.WIDGET_DRAGIN );		
	},
	
	setWrapHelperSize:function(widget){
		if (!$.browser.msie) {  //如果是兼容性浏览器
			var borderwidth =WidgetConfig.WIDGET_WRAPHELPER_BORDERWIDTH *2;
			widget.children(".wrapHelper").width(  widget.width()-borderwidth ).height( widget.height()-borderwidth );
		}else{
			widget.children(".wrapHelper").width(  widget.width() ).height( widget.height());
		}		
	}
	,
	showWrapHelper:function(widget){
		this.setWrapHelperSize(widget);
		widget.children(".wrapHelper").show();
	},
	hideWrapHelper:function(widget){
		 
		widget.children(".wrapHelper").hide();
	}
	,
	
	
	/*
	 * v1.0
	 * 响应鼠标的悬停事件
	 * 作用实现handle动态效果
	 */
	hoverInEvent:function(){
		var handleAble = enation.eop.WidgetController.handleAble;
		
		if(!handleAble) return; //防止其它快速的操作事件响应
		//debug("show..");
		var wdgs = $(this);
		wdgs.children(Selector.WIDGET_CHILD_HANDLE ).each(function(){
			var handle = $(this);
			handle.attr("hideAble","1"); //修补鼠标移动太快，动画延迟事件的问题
//			debug("hideAble" + handle.attr("hideAble") );
			handle.fadeIn(800,function(){
					if(handle.attr("hideAble")!="1")  //修补鼠标移动太快，动画延迟事件的问题:必须要hide了
						handle.hide();
			});
		});	
		
		enation.eop.WidgetOperator.showWrapHelper(wdgs);
		//wdgs.addClass(ClassName.WIDGET_HOVER);		
	},
	
	
	/*
	 * v1.0
	 * 响应鼠标悬停离开事件
	 * 作用:实现handle动态效果
	 */
	hoverOutEvent:function(){
		var handleAble = enation.eop.WidgetController.handleAble;
		
		if(!handleAble) return; //防止其它快速的操作事件响应
	
		//debug("hide..");
		
		var wdgs = $(this);
		wdgs.children(Selector.WIDGET_CHILD_HANDLE ).each(function(){
			var handle =$(this);
			handle.attr("hideAble","0"); //修补鼠标移动太快，动画延迟事件的问题,强制告诉Hover事件，要hide
			handle.hide();
		});	
		enation.eop.WidgetOperator.hideWrapHelper(wdgs);
	//	wdgs.removeClass(ClassName.WIDGET_HOVER);		
	},
	
	/*
	 * v1.0响应挂件handel的编辑事件
	 */
	handleSettingEvent:function(event){
		
		if (this == event.target) {
			var widgetEl = $(this.parentNode.parentNode.parentNode);

			Widgeter.editWidget(widgetEl);
		} 
				
	},
	
	/*
	 * v1.0
	 * 响应挂件handel的删除事件
	 */
	handleDeleteEvent:function(event){
		if (this == event.target) {
			if(confirm("确认删除此挂件吗？删除后不可恢复!")){
				var widgetEl = $(this.parentNode.parentNode.parentNode);
				Widgeter.deleteWidget(widgetEl);
			}
		}
	},
	
 
	/*
	 * v1.0
	 * 被做为容器放置时over事件
	 */
	containerOver: function(event, ui) {
		debug("进入" + this.id );
	    var widget = ui.draggable;
		var type = widget.attr(EopTypes.NAME);
		debug("over widget..." + this.id );

		if(type&& type == EopTypes.WIDGET_BUNDLE){
			GhostPlayer.create(widget,$(this));
		}
		
		if (type && type == EopTypes.WIDGET) {
			 if( $(this) !=widget){
				GhostPlayer.create(widget,$(this));
			 }
			else{ //拖到自己上面来了
				GhostPlayer.destroy();//为了修补回到自己鬼魂还在的问题
			} 
		}
	},

	/*v1.0
	 *被做为容器放置时Out事件
	 */
	containerOut : function(event, ui) {
		debug("离开" + this.id );
		var widget = ui.draggable;
		var type = widget.attr(EopTypes.NAME);
	
		var type = ui.draggable.attr(EopTypes.NAME);
	//	if (type && type == "widget" ) {

			GhostPlayer.display($(this),widget,true);
	
		//}
	},
	
	/*
	 * 被做为容器放置时drop事件
	 */
	containerDrop : function(ev, ui) {
		var widget = ui.draggable;
		var type = widget.attr(EopTypes.NAME);
		debug("drop..." + this.id );
		
		//拖过来的是挂件束
		if (type && type == EopTypes.WIDGET_BUNDLE) {
			  Widgeter.createWidget($(this),ui.draggable);
			  GhostPlayer.setCanDestory(false);
		}
		
		//拖过来的是挂件
		if (type && type == EopTypes.WIDGET) {
			if ($(this) != widget) {
				GhostPlayer.incarnation(widget,$(this));
			
			}
		}
	},
	/*
	 * 挂件拖动时的helper
	 */
	dragHelper:function(){
		var widgetClone = $(this).clone();
		widgetClone.removeAttr("width");
		widgetClone.removeAttr("height");
		widgetClone.removeAttr("style");
		
		widgetClone.children().each(function(i){
			if(i!=0){
				$(this).remove();
			}else{
				var handle = $(this);
				handle.empty();
				handle.removeAttr("width");
				handle.removeAttr("height");
				handle.removeAttr("style");
			}
		});
		
		widgetClone.removeClass(ClassName.WIDGET);
		widgetClone.addClass(ClassName.WIDGET_HELPER);
	//	widgetClone.appendTo($("body"));
		return widgetClone;		
	}	
	
};

