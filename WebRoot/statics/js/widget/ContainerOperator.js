
/**
 *挂件容器操作者
 */
enation.eop.ContainerOperator={
	
	/*
	 * v1.0
	 * 响应挂件容器放置over事件
	 */
	dropOverEvent:function(event,ui){
		debug("进入" +this.id );
		var widget = ui.draggable;
		var type = widget.attr(EopTypes.NAME);
		debug("type is   " + type +"  name si "+ EopTypes.NAME);
		if(type&& type == EopTypes.WIDGET_BUNDLE){
			GhostPlayer.create(widget,$(this));
		}
		
		if (type && type == EopTypes.WIDGET) {
			if (this !=  widget.parent().get(0)) {
				GhostPlayer.create(widget,$(this));
			}else{
				GhostPlayer.destroy(); //为了修复回到自己鬼魂还在的问题
			}
		}	
	},
	
	/*
	 * v1.0
	 * 响应挂件容器放置out事件
	 */
	dropOutEvent:function(event,ui){
		debug("离开 " + this.id);
//		var widget = ui.draggable;
//		var type = widget.attr(EopTypes.NAME);
//		if (type && type == "widget") {
//			if (this !=  widget.parent().get(0)) {
//				GhostPlayer.out(widget,$(this));
//			}
//
//		}		
	},
	
	/*
	 * v1.0
	 * 响应挂件容器放置drop事件
	 */
	dropEvent:function(event,ui){
		var widget = ui.draggable;
		var type = widget.attr(EopTypes.NAME);
		debug("drop in.." + this.id);
		if (type && type ==EopTypes.WIDGET_BUNDLE) {
			Widgeter.createWidget($(this),widget);
			GhostPlayer.setCanDestory(false); //挂件束放置后并未直接转世，所以要手工设置droped,以防止鬼魂被除掉
		}

		if (type && type == EopTypes.WIDGET) {
			debug("this is "+ this.id +" widget parent is "+ widget.parent().get(0).id);
				GhostPlayer.incarnation(widget,$(this));
		}		
	},
	
	
	layouts:undefined,
	skinstyle:undefined,
	
	/**
	 * 初始化布局对象
	 */
	init:function(){
		if(!this.layouts){
			this.layouts = $("[eop_type='layout']"); 
		}
		
		if(!this.skinstyle){
			this.skinstyle =  $("#skinstyle");
		}
	}
	,
	/**
	 * 获取当前布局个数
	 */
	getLayoutCount:function(){
		return this.layouts.size();
	}
	
	,
	/**
	 * 根据下标获取布局的innerHTML
	 * 如果下标大于布局的个数则返回undefined
	 */
	getLayoutHtml:function(index){
		if(index<=( this.layouts.size() -1 ) ){
			 var html =this.layouts.eq(index).html();
			return html;
		}else{
			return undefined;
		}		
	}
	,

	
	getStyleHrefs:function(prevDoc){
		var hrefs =[];
		var i=0;
		$("link").each(function(){
			var link = $(this);
			var id = link.attr("id");
			id=id?id:'';	
			if( !link.attr("eop_type") && id!='layoutstyle' ){
				hrefs[i]= link.attr("href");
				i++;
			}
		});
		
		return hrefs;
	}
	
};



