/**
 * fade - 图片切换插件
 * @requires jQuery v1.2 or above
 * @author liuzy
 * http://www.enation.cn
 * Copyright (c) 2010 enaiton
 * Version: 1.0
 */
/**
 * 使用说明:
 * $(".list").play();
 * 给定一个容器，会循环切换这个容器的子
 * 
 * 
 * 选项：
 * pictures:图片数组
 * step:停顿时长,默认5000
 * loadnum:预加载图片数量，默认2
 */

(function($){
	$.fn.play = function(options) {
		return this.each(function() {
			$.play(this,options);
		});
	};
	
	$.play = function(container, options) {
		var settings = {
			speed:4000,
			timeout:5000,
			loadnum:2
        };
		var loaded = 0;
        if (options)
            $.extend(settings, options);
		if (options && options.pictures){
			for(var i=0;i<settings.loadnum;i++){
				$(container).loadthumb(
					{
						"src":options.pictures[i],
						"callback":function(){
							loaded++;
							if(Number(loaded)>=Number(settings.loadnum)){
								var html = "";
								for(var n=0;n<options.pictures.length;n++){
									if(options.pictures[n])
										html = html + "<li><img src='" + options.pictures[n] + "' /></li>";
								}
								$(container).html(html);	
								$(container).innerfade(options);
							}
						}
					}
				);
			}
		}
	}
})(jQuery);

(function($) {
    $.fn.loadthumb = function(options) {
        return this.each(function() {
            $.loadthumb(this, options);
        });
    };
	
	$.loadthumb = function(container,options) {
		options = $.extend({
			 src : "",
			 callback : undefined
		},options);
		var that = this;
		var img = new Image();
		$(img).load(
			options.callback
		).attr("src", options.src);  //.atte("src",options.src)要放在load后面，
		return that;
	}
})(jQuery);

/* 图片变换 */
(function($) {

    $.fn.innerfade = function(options) {
        return this.each(function() {   
            $.innerfade(this, options);
        });
    };

    $.innerfade = function(container, options) {
        var settings = {
        	'animationtype':    'fade',
            'speed':            'normal',
            'type':             'sequence',
            'timeout':          2000,
            'containerheight':  'auto',
            'runningclass':     'innerfade',
            'children':         null
        };
        if (options)
            $.extend(settings, options);
        if (settings.children === null)
            var elements = $(container).children();
        else
            var elements = $(container).children(settings.children);
        if (elements.length > 1) {
            $(container).css('position', 'relative').css('height', settings.containerheight).addClass(settings.runningclass);
            for (var i = 0; i < elements.length; i++) {
                $(elements[i]).css('z-index', String(elements.length-i)).css('position', 'absolute').hide();
            };
            if (settings.type == "sequence") {
                setTimeout(function() {
                    $.innerfade.next(elements, settings, 1, 0);
                }, settings.timeout);
                $(elements[0]).show();
            } else if (settings.type == "random") {
            		var last = Math.floor ( Math.random () * ( elements.length ) );
                setTimeout(function() {
                    do { 
												current = Math.floor ( Math.random ( ) * ( elements.length ) );
										} while (last == current );             
										$.innerfade.next(elements, settings, current, last);
                }, settings.timeout);
                $(elements[last]).show();
						} else if ( settings.type == 'random_start' ) {
								settings.type = 'sequence';
								var current = Math.floor ( Math.random () * ( elements.length ) );
								setTimeout(function(){
									$.innerfade.next(elements, settings, (current + 1) %  elements.length, current);
								}, settings.timeout);
								$(elements[current]).show();
						}	else {
							alert('Innerfade-Type must either be \'sequence\', \'random\' or \'random_start\'');
						}
				}
    };

    $.innerfade.next = function(elements, settings, current, last) {
        if (settings.animationtype == 'slide') {
            $(elements[last]).slideUp(settings.speed);
            $(elements[current]).slideDown(settings.speed);
        } else if (settings.animationtype == 'fade') {
            $(elements[last]).fadeOut(settings.speed);
            $(elements[current]).fadeIn(settings.speed, function() {
							removeFilter($(this)[0]);
						});
        } else
            alert('Innerfade-animationtype must either be \'slide\' or \'fade\'');
        if (settings.type == "sequence") {
            if ((current + 1) < elements.length) {
                current = current + 1;
                last = current - 1;
            } else {
                current = 0;
                last = elements.length - 1;
            }
        } else if (settings.type == "random") {
            last = current;
            while (current == last)
                current = Math.floor(Math.random() * elements.length);
        } else
            alert('Innerfade-Type must either be \'sequence\', \'random\' or \'random_start\'');
        setTimeout((function() {
            $.innerfade.next(elements, settings, current, last);
        }), settings.timeout);
    };

})(jQuery);

// **** remove Opacity-Filter in ie ****
function removeFilter(element) {
	if(element.style.removeAttribute){
		element.style.removeAttribute('filter');
	}
}
