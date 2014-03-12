	$(function() {

		var ContainerOperator = parent.enation.eop.ContainerOperator;
		var layouts = $("[eop_type='layout']");

		/**
		 * 加载当前的皮肤
		 */
		var skinElHrefs = ContainerOperator.getStyleHrefs();
		$.each(skinElHrefs,function(i,href){
			var skinEl  = $("<link type=\"text/css\" rel=\"stylesheet\" id=\"skinstyle\" />");
			skinEl.attr("href",href);
			$("head").append(skinEl);
		});

		
		/**
		 *对比前布局个数和现布局个数
		 *如果现布局个数少，则将多余的挂件堆积在最后一个布局
		 */
		var oldCount = ContainerOperator.getLayoutCount(); //前布局个数
		var newCount = layouts.size(); //现布局个数
		var difCount = 0;
		if (newCount < oldCount) {
			difCount = oldCount - newCount;
		}

		layouts.each(function(n) {
			var panel = $(this);
			var html = ContainerOperator.getLayoutHtml(n);
			putWidget(html, panel);
			if (n == (newCount - 1)) { //最后一个布局
					for ( var i = 0; i < difCount; i++) {
						n++;
						html = ContainerOperator.getLayoutHtml(n);
						putWidget(html, panel);
					}
				}

		});
		
		var Eop = enation.eop;
		var Config = enation.eop.WidgetConfig;
		$(".ui-resizable-handle").remove();
		$("[eop_type='widget']").draggable( 'destroy' );
 
		Eop.WidgetController.init($(Config.Selector.WIDGET));
		Eop.ContainerController.init($(Config.Selector.LAYOUT));

	});

	function putWidget(html, panel) {
		html = $.trim(html);
		if (html && html != '') {

			/*
			 *计算挂件的宽和布局的宽
			 *如果挂件的宽大于布局的宽则以布局的宽为准
			 *挂件的高不变
			 */
			var widget = $(html); //当前布局中得到的挂件
			widget.css("width", "");
			if (widget.width() > panel.width()) {
				widget.width(panel.width());
			}

			widget.appendTo(panel);

		}
	}
	
	function applyLayout(){
		$("[eop_type='widget']").resizable( 'destroy' );
	}