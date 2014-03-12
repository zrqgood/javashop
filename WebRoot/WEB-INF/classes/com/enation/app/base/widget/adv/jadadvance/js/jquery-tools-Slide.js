var defaultOpts = { interval: 10000, fadeInTime: 300, fadeOutTime: 200 };
//Iterate over the current set of matched elements
	var _titles = $("ul.slide-txt li");
	var _titles_bg = $("ul.op li");
	var _bodies = $("ul.slide-pic li");
	var _count = _titles.length;
	var _current = 0;
	var _intervalID = null;
	var stop = function() { window.clearInterval(_intervalID); };
	var slide = function(opts) {
		if (opts) {
			_current = opts.current || 0;
		} else {
			_current = (_current >= (_count - 1)) ? 0 : (++_current);
		};
		_bodies.filter(":visible").fadeOut(defaultOpts.fadeOutTime, function() {
			_bodies.eq(_current).fadeIn(defaultOpts.fadeInTime);
			_bodies.removeClass("cur").eq(_current).addClass("cur");
		});
		_titles.removeClass("cur").eq(_current).addClass("cur");
		_titles_bg.removeClass("cur").eq(_current).addClass("cur");
	}; //endof slide
	var go = function() {
		stop();
		_intervalID = window.setInterval(function() { slide(); }, defaultOpts.interval);
	}; //endof go
	var itemMouseOver = function(target, items) {
		stop();
		var i = $.inArray(target, items);
		slide({ current: i });
	}; //endof itemMouseOver
	_titles.hover(function() { if($(this).attr('class')!='cur'){itemMouseOver(this, _titles); }else{stop();}}, go);
	//_titles_bg.hover(function() { itemMouseOver(this, _titles_bg); }, go);
	_bodies.hover(stop, go);
	//trigger the slidebox
	go();
	
var slideX={
	thisUl:$('#catalog ul.imgbox'),
	btnLeft:$('#catalog a.left'),
	btnRight:$('#catalog a.right'),
	thisLi:$('#catalog ul.imgbox li'),
	init:function(){
		slideX.thisUl.width(slideX.thisLi.length*112);
		slideX.slideAuto();
		slideX.btnLeft.click(slideX.slideLeft).hover(slideX.slideStop,slideX.slideAuto);
		slideX.btnRight.click(slideX.slideRight).hover(slideX.slideStop,slideX.slideAuto);
		slideX.thisUl.hover(slideX.slideStop,slideX.slideAuto);
		},
	slideLeft:function(){
		slideX.btnLeft.unbind('click',slideX.slideLeft);
		slideX.thisUl.find('li:last').prependTo(slideX.thisUl);
		slideX.thisUl.css('marginLeft',-112);
		slideX.thisUl.animate({'marginLeft':0},350,function(){
			slideX.btnLeft.bind('click',slideX.slideLeft);
			});
		return false;
		},
	slideRight:function(){ 
		slideX.btnRight.unbind('click',slideX.slideRight);
		slideX.thisUl.animate({'marginLeft':-112},350,function(){
			slideX.thisUl.css('marginLeft','0');
			slideX.thisUl.find('li:first').appendTo(slideX.thisUl);
			slideX.btnRight.bind('click',slideX.slideRight);
			});
		return false;
		},
	slideAuto:function(){   
		slideX.intervalId=window.setInterval(slideX.slideRight,10000);
		},
	slideStop:function(){
		window.clearInterval(slideX.intervalId);
		}
	}
$(document).ready(function(){
	slideX.init();
})


