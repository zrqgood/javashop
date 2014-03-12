var dialog;

function debug(message){
	//log.debug(message);
	//$("#footer").html( $("#footer").html()+"<br/>" + message  );
}
 
function error(message){
//	log.error(message);
}

function info(message){
//	log.info(message);
}


var Widgeter = {
	panel : undefined,
	createSuccess:false,
	created:false,
	init:function(){
		var Eop =enation.eop;	
		var Config =  enation.eop.WidgetConfig;
		Eop.WidgetController.init($(Config.Selector.WIDGET));
		Eop.ContainerController.init($(Config.Selector.LAYOUT));	
	},
	initDialog:function(){
		$("#widget_setting").dialog( {
			autoOpen:false,
			bgiframe: true ,		
			width : 840,
			height:530,
			minHeight:530,
			modal : true,
			resizable:false
		});	
	}
	,
	createWidget : function(_panel, widgetHandle) {
		WidgetDialog.init();
		var that =this;
		if(!this.created){//修复当拖进可用区域后再拖到不可用区域时的问题
			var createSuccess = this.createSuccess; 
			this.panel = _panel;
			var appId = widgetHandle.attr("appId");
			var widgetType = widgetHandle.attr("widgetType");
			var that = this;

			$("#widget_setting").html("");	 
			//装载设置页面
			$("#widget_setting").load(
					"widgetSetting/?appid=" + appId + "&widgettype=" + widgetType+"&act=create"
							+ "",{a:1},function(){
							
			    enation.eop.WidgetSettingController.init();
			    WidgetDialog.doOpen();
 
			});
			$('#widget_setting').dialog('option','title','创建挂件');
			//绑定创建挂件的事件
			$("#widget_setting").dialog('option', 'buttons', { "确定": function() {
				that.doCreateWidget();
			} })
			//绑定关闭事件,如果不成功除鬼
			.bind('dialogclose', function(event, ui) {
				if(!createSuccess){
					GhostPlayer.setCanDestory(true);
					GhostPlayer.destroy();
				}
				that.created =false;
			//	$("#widget_setting").dialog( 'destroy' );
				
			})
			.dialog("open");
			
			this.created =true; 
		}
	},
 

	doCreateWidget : function() {
		var thatPanel = this.panel;
	 
		var options = {
			url : "widget/?act=create&mode=edit&rmd="+ new Date().getTime() ,
			dataType : 'json',
			type : "POST",
			success : function(result) {
				var newWidget = $("<div class='widget'>"+result.content+"</div>");
				enation.eop.WidgetController.HandleController.init( 
						newWidget.children("div.handle").children("span").children("a.edit"),
						newWidget.children("div.handle").children("span").children("a.delete")
				);

				//保存挂件的属性及样式
				GhostPlayer.comTrueStart=function(widget){
					enation.eop.WidgetSettingController.saveWidget( widget);
				};
				
				GhostPlayer.incarnation(newWidget,thatPanel);
	
				
				thatPanel.createSuccess=true;
				$("#widget_setting").dialog("close");
				 
			},
			error : function(e) {
				alert("出现错误 " + e);
			}
		};
		WidgetDialog.doSubmit();
		$('#widget_form').ajaxSubmit(options);
	},

	deleteWidget : function(widgetEl) {
		var url="widget/?act=delete&id="+widgetEl.attr("id")+"&widgettype="+ widgetEl.attr("widgettype") + "&appid="+widgetEl.attr("appid");
		//alert( url );
		$.ajax({
			 type: "GET",
			 url: url,
			 dataType:   "html" ,
			 success: function(msg){  
				widgetEl.remove();
			 } 

			}) ;
	},
	
	editWidget : function(widgetEl) {
		WidgetDialog.init();
		var widgetType = widgetEl.attr("widgettype");
		var appId = widgetEl.attr("appid");
		var id =  widgetEl.attr("id");
		
		var attrs  = widgetEl.get(0).attributes;
		var postData={};
		
		$.each(attrs,function(i,n){
			postData[n.nodeName]=n.nodeValue;
		});
		
		var that = this;
		$("#widget_setting").html("");
		$("#widget_setting").load(
				"widgetSetting/?act=edit",
				postData,
				//设置对话框打开后绑定事件
				function(){ 
					 enation.eop.WidgetSettingController.init(widgetEl);
					 WidgetDialog.doOpen();
				});
				
		$('#widget_setting').dialog('option','title','修改挂件');
		$('#widget_setting').dialog('option', 'buttons', { "确定": function() {
			that.saveWidget(widgetEl);
			$(this).dialog("close");
		 
		} }).dialog("open");
	},
	
	/**
	 * 保存挂件
	 */
	saveWidget:function(widgetEl){
		//enation.eop.WidgetSettingController.saveWidget();
		
		var paramJson={}; 
		$("[eop_type=widget_params]").each(function(){
			var param = $(this);
			paramJson[param.attr("name")] = param.val();
		});
		alert(paramJson["id"]);
		/*
		var options = {
				url : "widget/?act=edit&mode=edit",
				type : "POST",
				dataType : 'json',
				success : function(result) {
					var newContent = $(result.content);
					widgetEl.empty();
					widgetEl.append(newContent);
					enation.eop.WidgetSettingController.saveWidget(); //保存持件的设置
					enation.eop.WidgetController.init(widgetEl);  //初始化挂件的事件
				},
				error : function(e) {
					alert("出现错误 " + e);
				}
			};
		WidgetDialog.doSubmit();
		$('#widget_form').ajaxSubmit(options);		
		*/
		
	}
};

function copyChildStyle(){
	$("[eop_type='widget']").each(function(){
		var widget = $(this);
		var tStyle  = widget.children(".title").attr("style");
		var cStyle  = widget.children(".content").attr("style");
		widget.attr("tStyle",tStyle);
		widget.attr("cStyle",cStyle);
	});
}

function savePage(showMsg) {
	
	$(".colorpicker").remove();
	copyChildStyle();
	$("#bodyHtml").val($("html").html());
	var options = {
		url : "?_method=PUT",
		type : "POST",
		dataType : 'json',
		success : function(result) {
			$("#bodyHtml").val('');
			if(showMsg){
				alert(result.message);
			}
			
		},
		error : function(e) {
			alert("出现错误 " + e);
		}
	};

	$('#pageForm').ajaxSubmit(options);

}
