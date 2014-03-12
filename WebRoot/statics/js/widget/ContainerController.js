
/**
 *容器控制器
 */
enation.eop.ContainerController={
	container:undefined,
	containerOperator:{},
	
	init:function(container){
		this.containerOperator = enation.eop.ContainerOperator;
		this.container = container;
		
		this.initContainer();
		this.dropAble();
		//this.resizeAble();
		this.containerOperator.init();
		return this;
	},
	
	
	/*
	 *使挂件布局可自动变化 
	 */
	initContainer:function(){
		this.container.each(function(){
			var panel  = $(this);
			var height  ;
			var minHeight = panel.css("min-height");
		 
			if( minHeight  && minHeight!='0px' && minHeight!='auto' ){
				height = panel.css("min-height");
			}else{
				height = panel.css("height");
				panel.css("min-height",height);
			}
			
			if (!$.browser.msie) {  //如果是兼容性浏览器
				panel.css("height","auto");
			}else{
				 
				panel.css("_height",height);
				panel.css("height",height);
			} 
		
		    
		});
	}	
	,
	resizeAble:function(){
		$("div[eop_type=layout]").resizable();
	},
	
	/*
	 * 1.greedy:true不能加上这个，加上后事件中断，会导致挂件全部进、出布局后不响应事件
	 * 2.不能用bind方式绑定事件，会使挂件drop over 事件无效
	 */
	dropAble:function(){
		
		var controllor = this;
		
		this.container.droppable(
			{
				accept : Selector.WIDGET_DROP_ACCPET,
				activeClass : ClassName.LAYOUT_ACTIVE,
				over:controllor.containerOperator.dropOverEvent,
				out:controllor.containerOperator.dropOutEvent,
				drop:controllor.containerOperator.dropEvent
			});
		
	}
	
};
