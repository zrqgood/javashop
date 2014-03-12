//数字输入插件
//author:kingapex
(function($) {
	$.fn.numinput = function(options) {
		
		var opts = $.extend({}, $.fn.numinput.defaults, options);
		return this.each(function(){
			createEl($(this));
			bindEvent($(this));
		});		
	
		
		function createEl(target){
			var value=1;
			if(target.attr("value")) value=target.attr("value");
			input=$("<input type=\"text\" value=\""+value+"\" size=\"5\" name=\""+opts.name+"\">");
			incBtn =$('<span class="numadjust increase"></span>');
			decBtn =$('<span class="numadjust decrease"></span>');
			target.append(input).append(incBtn).append(decBtn);
		}
		 
		
		function fireEvent(input){
			if(opts.onChange){
				if(input.val()==""){alert("数字格式不正确");input.val("1");}
				opts.onChange(input);
			}			
		}
        
		function bindEvent(target){
			var input,incBtn,decBtn;
			var input =target.children("input");
			var incBtn =target.children("span.increase");
			var decBtn =target.children("span.decrease");
			incBtn
			.mousedown(function(){
				$(this).addClass("active");
			})
			.mouseup(function(){
				$(this).removeClass("active");
				input.val(parseInt(input.val())+1);
				fireEvent(input);
			});

			decBtn
			.mousedown(function(){
				$(this).addClass("active");
			})
			.mouseup(function(){
				$(this).removeClass("active");
				input.val( parseInt(input.val())==1?1 :parseInt(input.val()) -1);
				fireEvent(input);
			});
			
			input.keypress(function(event) {  
			         if (!$.browser.mozilla) {  
				             if (event.keyCode && (event.keyCode < 48 || event.keyCode > 57)) {  
				                 event.preventDefault();  
				             }  
				         } else {  
				             if (event.charCode && (event.charCode < 48 || event.charCode > 57)) {  
				                 event.preventDefault();  
				             }  
				         }  
			}); 
			
			input.change(function(){
				fireEvent($(this));
			});
		}
		

		
	};
	
	$.fn.numinput.defaults={};
})(jQuery);