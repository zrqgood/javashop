/**
 * ApexSlider - 图片纵向滚动插件
 * @requires jQuery v1.2 or above
 * @author kingapex
 * http://www.enation.cn
 * Copyright (c) 2010 enaiton
 * Version: 1.1
 * Catlog:
 * 新增ApexMarquee插件
 */
/**
 * 使用说明:
 * $(".list").ApexSlider();
 * 给定一个容器，会循环滑动这个容器的子,
 * 不限定容器及其子的html元素类型,
 * 容器和子的高度差不要太大，否则效果不太好。
 * 必须在css中定义容器和其子的height
 * 选项：
 * speed:滑动速度,默认500
 * step:停顿时长,默认4000
 */
(function($) {                                          
	$.fn.ApexSlider = function(o) {
		
	    o = $.extend({
	        speed: 500,
	        step:4000
	    }, o || {});
	    
		 return this.each(function() {
			 var timer =undefined;
			 var box = $(this);
			 var children=  $(this).children();
			 var height  =parseInt( box.css("height").replace("px"));
			 box.css("position","relative");
			 children.css("position","absolute");
			 children.each(function(i){
				 $(this).css("top",i*height +"px");
			 }).hover(function(){
				 stop();
			 },
			 function(){
				 start();
			 }
			 );
			 
			 function start(){
				 timer = setInterval(function() {
	               go();
	            },o.step);
			 }
			 
			 function stop(){
				 if(timer){
					 clearInterval(timer);
				 }
			 }
			 start();
			 
			 function go(){
			 
					 children.animate({top:"-="+height},o.speed,'swing',function(){
						 restore();
					 });
 
			 }
			 
			 function restore(){
				  var top=0;
				  var topEl=undefined;
				  
				  children.each(function(i){
					  var temp = parseInt($(this).css("top").replace("px") );
					  if(temp<top){
						  topEl =$(this);
						  top=temp;
					  }
				 });
				 if(topEl)
					 topEl.css('top',(children.size()-1)*height+'px');
				
			 }
			 
		 });
	};
})(jQuery);

/**
 * 使用说明:
 * $(".list").ApexMarquee();
 * 给定一个容器，会循环滑动这个容器的子,
 * 不限定容器及其子的html元素类型,
 * 必须在css中定义容器和其子的height
 * 选项：
 * speed:滑动速度,默认100
 * step:每次滑动距离,默认10
 */
(function($) {                                          
	$.fn.ApexMarquee = function(o) {
		
	    o = $.extend({
	        speed: 100,
	        step:10
	    }, o || {});
	    
		 return this.each(function() {
			 var timer =undefined;
			 var box = $(this);
			 var children=  $(this).children();
		//	 var height  =parseInt( box.css("height").replace("px"));
			 var childHeight= parseInt(children.css("height").replace("px"))
			 
			 box.css("position","relative");
			 children.css("position","absolute");
			 children.each(function(i){
				 $(this).css("top",i*childHeight +"px");
			 }).hover(function(){
				 stop();
			 },
			 function(){
				 start();
			 }
			 );
			 
			 function start(){
				 timer = setInterval(function() {
	               go();
	            },o.speed);
			 }
			 
			 function stop(){
				 if(timer){
					 clearInterval(timer);
				 }
			 }
			 start();
			 
			 function go(){
				
				 children.each(function(){
					 var  top = parseInt($(this).css("top").replace("px") );
					 $(this).css("top",(top-o.step)+"px");
					 top = parseInt($(this).css("top").replace("px") );
					 if(top<=(0-childHeight)){
						$(this).css('top',(children.size()-1)*childHeight+'px');
					 }
				 });
				 
			 }
 
			 
		 });
	};
})(jQuery);

/**
 * 使用说明:
 * $(".list").LeftMarquee();
 * 给定一个容器，会循环向左滑动这个容器的子,
 * 不限定容器及其子的html元素类型,
 * 必须在css中定义容器和其子的Width
 * 选项：
 * speed:滑动速度,默认100
 * step:每次滑动距离,默认10
 */
(function($) {                                          
	$.fn.LeftMarquee = function(o) {
		
	    o = $.extend({
	        speed: 100,
	        step:2,
			width:-1,
			btnLeft:undefined,
			btnRight:undefined
	    }, o || {});
	    
		 return this.each(function() {
			 var timer = undefined;
			 var moveTimer = undefined;
			 var move = 0;
			 var box = $(this);
			 var children = $(this).children();
			 var childWidth = o.width==-1 ? parseInt(children.css("width").replace("px")) : o.width;

			 box.css("position","relative");
			 children.css("position","absolute");
			 children.each(function(i){
				 $(this).css("left",i*childWidth +"px");
			 }).hover(function(){
				 stop();
			 },
			 function(){
				 start();
			 }
			 );
			 
			 function start(){
				 timer = setInterval(function() {
	               go();
	            },o.speed);
			 }
			 
			 function stop(){
				 if(timer){
					 clearInterval(timer);
				 }
			 }
			
			 start();

			 if(o.btnLeft){
				$(o.btnLeft).hover(function(){
					stop();
				},function(){
					start();
				}).click(function(){
					if(moveTimer)
						return false;

					move = 9999;
					children.each(function(){	
						var left = parseInt($(this).css("left").replace("px") );
						if(left>=0 && move>left)
							move = left;
					});
					if(move==0)		//	如果对齐了，就移动到下一个
						move=childWidth;
					moveTimer = setInterval(function() {
						movego(7);
					},10);
					return false;
				});
			 }
			 if(o.btnRight){
				$(o.btnRight).hover(function(){
					stop();
				},function(){
					start();
				}).click(function(){
					if(moveTimer)
						return false;

					move = 9999;
					var min = 0;
					var max = 0;
					var $max = undefined;
					children.each(function(){	
						var left = parseInt($(this).css("left").replace("px") );
						var boxWidth = box.width();
						if(left<min)
							min = left;
						if(max<left){
							max=left;
							$max = $(this);
						}
						if(left>=0 && left<=boxWidth)
							if(boxWidth-left<move)
								move = boxWidth - left;
					});
					if(move==0)		//	如果对齐了，就移动到下一个
						move=childWidth;
					if(move+min>0){	//	需要补位
						if(max){
							$max.css("left",(min - childWidth) + "px");
						}
					};
						
					moveTimer = setInterval(function() {
						movego(-5);
					},10);
					return false;
				});
			 }
			 function movego(step){
				var absstep = step > 0 ? step : -step;
				if(move>0){
					if(move>absstep){
						move = move - absstep;
						go(step);
					}
					else{
						go(step > 0 ? move : -move);
						move = 0;
					}
				}
				else{
					if(moveTimer) {
						clearInterval(moveTimer);
						moveTimer = undefined;
					}
				}
			 }
			 function go(step){
				if(!step)
					step = o.step;
				 children.each(function(){
					 var left = parseInt($(this).css("left").replace("px") );
					 $(this).css("left",(left-step)+"px");
					 left = parseInt($(this).css("left").replace("px") );
					 if(step>0){
						 if(left<=(0-childWidth)){
							$(this).css('left',(children.size()-1)*childWidth+'px');
						 }
					}
					else if(left>box.width()){
					/*
						var min = 0;
						children.each(function(){
							var left = parseInt($(this).css("left").replace("px"));
							if(left<min)
								min = left;
						});
						$(this).css('left',min - childWidth+'px');
					*/
					}
				 });	 
			 }
			 
		 });
	};
})(jQuery);