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
	widgetParams:undefined,
	pageId:undefined,
	init:function(){
	    var self= this;
		var Eop =enation.eop;	
		var Config =  enation.eop.WidgetConfig;
		Eop.WidgetController.init($(Config.Selector.WIDGET));
		Eop.ContainerController.init($(Config.Selector.LAYOUT));
		this.initWidgetParams();
		$(".bt_save_custom").click(function(){
			self.savePage();
		});
	},
	/**
	 * 初始化挂件参数json
	 */
	initWidgetParams:function(){
		var self = this;
		 $.ajax
		 ({
		    url:"?",
		    data:"_method=PARAMJSON",
		    dataType:"json",
		    success:function(data){
			 	self.widgetParams = data.params;
			 	self.pageId = data.pageId;
		 	}
		  });   	 
	}
	,	
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
	},
	//生成新的挂件id
	//寻找最大的挂件id,并加一
	generateId:function(){
		var tempid=0;
		for(var index in this.widgetParams ){
			var wid = parseInt( this.widgetParams[index].id);
			if(tempid< wid ){
				tempid=wid;
			}
				 
		}
		return tempid+1;
	}
	,
	createWidget : function(_panel, widgetHandle) {
		WidgetDialog.init();
		var that =this;
		if(!this.created){//修复当拖进可用区域后再拖到不可用区域时的问题
			
			var createSuccess = this.createSuccess; 
			this.panel = _panel;
			
			//生成新挂件的id
		    var newWidgetId = this.generateId();
		    
		    //由拖拽的handle属性中得到挂件类型
			var type = widgetHandle.attr("widgetType");
	
			$("#widget_setting").html("");	 
			//装载设置页面
			$("#widget_setting").load(
					"widgetSetting/?act=create",
					{'id':newWidgetId,'type':type},function(){
							
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

		if(!this.widgetParams){ alert("挂件参数初始化异常");return ;}
		
		//形成要post的widget参数
		var paramJson={}; 
		$("[eop_type=widget_params]").each(function(){
			var param = $(this);
			paramJson[param.attr("name")] = param.val();
		});
		
		//将新的挂件参数加入到挂件参数数组中
		this.widgetParams[this.widgetParams.length]=paramJson;
 
		
		$.ajax({
			   type: "POST",
			   url: "widget?mode=edit",
			   data: paramJson,
			   success: function(newContent){
				    var newWidget =$(newContent);
				    GhostPlayer.newWidget(newWidget);
					//GhostPlayer.incarnation(newWidget,thatPanel);
					thatPanel.createSuccess=true;
					$("#widget_setting").dialog("close");					
			   },
			   error:function(){
				   alert("哎呀，出错啦:<");
			   }
		}); 
		WidgetDialog.doSubmit();

	},

	deleteWidget : function(widgetEl) {
		var widgetId = widgetEl.attr("id");
		for(var index in this.widgetParams ){
			if(this.widgetParams[index].id == widgetId)
			{
				delete this.widgetParams.index;
			}
		}		
		widgetEl.remove();
/*		var url="widget/?act=delete&id="+widgetEl.attr("id")+"&widgettype="+ widgetEl.attr("widgettype") + "&appid="+widgetEl.attr("appid");
		//alert( url );
		$.ajax({
			 type: "GET",
			 url: url,
			 dataType:   "html" ,
			 success: function(msg){  
				widgetEl.remove();
			 } 

			}) ;*/
	},
	
	
	/**
	 * 用挂件id查找某个挂件的参数
	 */
	findParams:function(widgetId){
		for(var index in this.widgetParams ){
			if(this.widgetParams[index].id == widgetId)
			{
				return this.widgetParams[index];
			}
		}
	}

	,
	editWidget : function(widgetEl) {
		WidgetDialog.init();
 
		var id =  widgetEl.attr("id");
	
		var postData=this.findParams(id);


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
		if(!this.widgetParams){ alert("挂件参数初始化异常");return ;}
		var paramJson={}; 
		$("[eop_type=widget_params]").each(function(){
			var param = $(this);
			paramJson[param.attr("name")] = param.val();
		});
		  
		for(var index in this.widgetParams ){
			if(this.widgetParams[index].id == paramJson.id)
				this.widgetParams[index] = paramJson;
		}
		
		$.ajax({
			   type: "POST",
			   url: "widget?mode=edit",
			   data: paramJson,
			   success: function(newContent){
				    var newWidget =$(newContent);
					widgetEl.replaceWith(newWidget);
					enation.eop.WidgetController.init(newWidget);  //初始化挂件的事件
					//enation.eop.WidgetSettingController.saveWidget(); //保存持件的设置//架构3.0后去掉，待整理
				   
			   },
			   error:function(){
				   alert("哎呀，出错啦:<");
			   }
		}); 
			
		
	},
	
	//保存页面改动
	savePage:function(){
		var json= Eop.Util.jsonToString(this.widgetParams) ;
		
		var page =$("#wrapertemp").empty().append( $("#AllWrap").clone());
		
		//将挂件的div更改为${widget_<id>}
		page.find("[eop_type=widget]").each(function(){
			var widget = $(this);
			widget.replaceWith("${widget_"+widget.attr("id")+"}");
		});
		
		$("#bodyHtml").val(page.html());
		$("#widgetParams").val(json );		
		var options = {
				url : "?_method=PUT",
				type : "POST",
				dataType : 'json',
				success : function(result) {
					alert("保存成功");
				},
				error : function(e) {
					alert("出现错误 " + e);
				}
			};

		$('#pageForm').ajaxSubmit(options);		
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


//这个方法已经废弃方法放到widgeter类中了
/*
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
*/