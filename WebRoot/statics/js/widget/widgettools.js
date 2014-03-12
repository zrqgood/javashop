$(function() {
	Widgeter.init();
	Widgeter.initDialog();
	enation.eop.WidgetTool.init();

	//为了修复挂件helper appendto pagemain 后造成的 helper不显示的问题
	// overflow:visable时会显示
	$("#pagemain").droppable( {accept : Selector.WIDGET_DROP_ACCPET,over:function(){
		$("#pagemain").css("overflow","auto");
	}
	,addClasses:false
	});
 //	hid(3);
 	
	
	
	/**
	 * 换肤
	 */
	$("#list_style").click(function(){
		$("#skinstyle").attr("href","css/red.css");
	});
});

enation.eop.WidgetTool = {
	bundleAr:undefined,	 //挂件束数组
	layoutDlg:undefined,
	init:function(){
		this.showLayoutList();
		this.initWidgetGroupEvent();
		this.tagBind();
		this.initDialog();
		var h = $(window).height();
		 $("#pagemain").height(h-28);
		
		var that = this;
		 
		 //版式/布局
		$("#custom_menu_widget2>span>a").click(function(event){
			  if (this == event.target) {
				that.layoutShow();
			  }
		});
		
		
		//挂件
		$("#custom_menu_widget1>span>a").click(function(event){
			  if (this == event.target) {
				  that.widgetShow();
				  }
			});
		
		//风格
		$("#custom_menu_widget3>span>a").click(function(event){
			  if (this == event.target) {
				  that.styleShow();
			 }
		}); 
         
		
	},
	initDialog:function(){
		var that = this;
		var screenW= $(window).width();
		var screenH;
		if (!$.browser.msie) { 
			screenH=  $(window).height();
		}else{
			screenH=700;
		}
		this.layoutDlg =$("#layout_preview").dialog({
			autoOpen:false,
			width:screenW,
			height:screenH,
			modal : true,
			title:'预览布局',
			buttons: { 
			"取消": function() { 
			   if(confirm("取消后您对新布局的更改将不会被保存，确定要取消吗?"))
				$(this).dialog("close");
			},
			"应用": function() { 
				 if(confirm("确定要替换为新布局吗？"))
					 that.applyLayout();
				}
			},
			bgiframe: true,
			resizable: false,
			draggable: false
		});		
	}
	,
	layoutShow:function(){
		tag(2);
	}
	,
	styleShow:function(){
		tag(3);
	},
	widgetShow:function(){
		if(!this.bundleAr){ //第一次使用时初始化挂件束数组
			this.initBundle();
		}
		tag(1);
	},
	
	//异步从服务器读取数据，初始化挂件束数组
	initBundle:function(){
		$("#systemwidgetLayout").html("正在加载...");
		var that =this;
		$.ajax({
			 type: "POST",
			 url: WidgetConfig.HOST+"widgetBundle/",
			 dataType:'json',
			 success: function(data){
				$("#systemwidgetLayout").empty();
				that.bundleAr = data;
				that.drawBundle();
			 } 
		}); 
	},
	//用bundle数组生成html
	drawBundle:function(){
		$.each(this.bundleAr,function(n,bundle){
				var bundleEl = $("<li>"+bundle.widgetname +"</li>");
				bundleEl.attr("eop_type","widgetHandle");
				bundleEl.attr("appid",bundle.appid);
				bundleEl.attr("widgettype",bundle.widgettype);
				$("#systemwidgetLayout").append(bundleEl );	
		});
		//初始化挂件束的事件
		enation.eop.WidgetBundleController.init($("#eop_widget_tool .widget_types>li"));
	},
	//显示某个应用下的挂件
	showBundle:function(appid){
		var bundlelist = $("#systemwidgetLayout").children();
		bundlelist.not("[appid="+appid+"']").hide();
		bundlelist.filter("[appid='"+appid+"']").show();
	},

	//按应用分组显示挂件的事件
	initWidgetGroupEvent:function(){
		 var widgetGroup = $("#eop_widget_tool ul.widget_all>li");
		 var that = this;
		 //挂件类型的选项卡
		 widgetGroup.click(function(event){
				 var cur = this;
				 var curEl =$(this);
				 var p = curEl.parent();
				 p.children().each(function(){
					 if(cur==this){
						 $(this).addClass("now");
					 }else{
						 $(this).removeClass("now");
					 }
				 });
				 var appid = curEl.attr("appid");
				 that.showBundle(appid);				 
			 
		 });  		 
	}
	,
	
	layoutList:undefined,
	showLayoutList:function(){
	
		if(!this.layoutList){ //第一次使用时初始化挂件束数组
			 
			this.initLayoutList();
		}
	}
	,
	initLayoutList:function(){
		$("#layoutList").html("正在加载...");
		var that =this;
		$.ajax({
			 type: "POST",
			 url: WidgetConfig.HOST+"widgetLayout/?_method=list",
			 dataType:'json',
			 success: function(data){
				$("#layoutList").empty();
				that.layoutList = data;
				that.drawLayoutList();
			 },error:function(){alert("加载布局出错");}
		}); 		
	},
	
	drawLayoutList:function(){
		$.each(this.layoutList,function(n,layout){
			var layoutEl = $("<button layoutid='"+layout.id+"' ><span><span class='none'>"+layout.layoutname+"</span></span></button>");
			layoutEl.children("span").css("background","transparent url('"+layout.thumb+"') no-repeat scroll  0 0");
			$("#layoutList").append(layoutEl );	
		});

		this.bindLayout();
	}
	,
	/**
	 * 绑定layout的事件
	 */
	bindLayout:function(){
		var that =this;
		$("#layoutList").children("button").click(function(event){
			if(event.target==this){
				var btn = $(this);
				var layoutid= btn.attr("layoutid");
				var p_frame = $("#layout_preview iframe");
				p_frame.attr("src","widgetLayout/?_method=GET&id="+layoutid); 
				p_frame.css("width","100%");
				p_frame.css("height","100%");
//				that.layoutDlg.dialog('option','width',$(window).width())
//	 				.dialog('option','height',$(window).height()).dialog('open');
				that.layoutDlg.dialog('open');
			}
		});	
	}
	,
	/*
	 * 应用布局
	 */
	applyLayout:function(){
		var p_frame = document.getElementById("lp_frm");//$("#layout_preview iframe").get(0);
		
		var frmDoc;
		if (!$.browser.msie) { 
			p_frame.contentWindow.applyLayout();
			frmDoc= p_frame.contentDocument;
		}else{
			p_frame.contentWindow.applyLayout();
			frmDoc= p_frame.contentWindow.document;
		}
		$("#layoutstyle").attr( "href",frmDoc.getElementById("layoutstyle").getAttribute("href") );
		 var newHtml = frmDoc.body.innerHTML;
		 $("#pagemain").html(newHtml);
		 this.layoutDlg.dialog('close');
		 Widgeter.init();
	}
	,
	
	/*
	  工具箱中的 tag 切换
	*/
	tagBind:function(){
		 var widgetli = $("#eop_widget_tool .widget_types>li");
	     var widgetli1 = $("#eop_widget_tool .widget_create>li");
	     var pagebtn = $("#eop_widget_tool .page_style>button");
	     var stylebtn = $("#eop_widget_tool .list_style>button");
	     var filterli = $("#eop_widget_tool #filter_mode_custom>li");
	     
	    
	     var pagebgimg = $("#eop_widget_tool #widgetlistpage_img>div");
	     var pagebgcolor = $("#eop_widget_tool #pagebgcolor>div");
	     var pagetransparent = $("#pagetransparent");
	     var bgimg_big = $("#bgimg_big");
	     var bgimg_normal = $("#bgimg_normal");
	     var bgimg_small = $("#bgimg_small");
	     var bgimg_upload = $("#bgimg_upload");
	     
		 widgetli.mouseover(function(event){
		       $(this).addClass("hidden");
			});
		 widgetli.mouseout(function(event){
		       $(this).removeClass("hidden");
			   
		 });
		  widgetli1.mouseover(function(event){
		       $(this).addClass("hidden");
			});
		 widgetli1.mouseout(function(event){
		       $(this).removeClass("hidden");
			   
		 });
		 pagebtn.mouseover(function(event){
		       $(this).addClass("now");
			});
		 pagebtn.mouseout(function(event){
		       $(this).removeClass("now");
			   
		 });
		 
		 stylebtn.mouseover(function(event){
		       $(this).addClass("now");
		 });
		 stylebtn.mouseout(function(event){
		       $(this).removeClass("now");
			   
		 });
		 
		 filterli.mouseover(function(event){
		       $(this).addClass("selected");
		 });
		 filterli.mouseout(function(event){
		       $(this).removeClass("selected");
			   
		 });
		 

		 
		 //设置背景图片
		 pagebgimg.click(function(){
			  var bgimg = $(this).css("background");
			  $("#pagemain").css("background",bgimg);
		 });  
		 
		 //设置背景颜色
		 pagebgcolor.click(function(){
			  var bgcolor = $(this).css("background");
			  $("#pagemain").css("background",bgcolor);
			  $("#pageshowcolor").val(bgcolor.substring(0,bgcolor.lastIndexOf(')')+1));
		 });  
		 //设置背景图片透明
		 pagetransparent.click(function(){
			  $("#pagemain").css("background","transparent");
			  $("#pageshowcolor").val("transparent");
		 });  
		 //放大背景图片
		 bgimg_big.click(function(){
			 var p = $("#widgetlistpage_img");
			 for(var i = 0;i<p.children().size();i++){
				  var s =  p.children().eq(i);
				  s.height(190);
				  s.width(270);
			 }
		 });  
		 //一般大背景图片
		 bgimg_normal.click(function(){
			 var p = $("#widgetlistpage_img");
			 for(var i = 0;i<p.children().size();i++){
				  var s =  p.children().eq(i);
				  s.height(70);
				  s.width(88);
			 }
		 });  
		 //缩小背景图片
		 bgimg_small.click(function(){
			 var p = $("#widgetlistpage_img");
			 for(var i = 0;i<p.children().size();i++){
				  var s =  p.children().eq(i);
				  s.height(60);
				  s.width(65);
			 }
		 });  
		 //上传背景图片
		 bgimg_upload.click(function(){
			 alert("这里应该弹出上传图片对话框。");
		 });  
		 
		
	}
	
	
};


/**
 * 选项卡，挂件，风格，布局
 * @param value
 * @return
 */
function tag(value){
  var i = 1;
  for(;i<=3;i++){
     if(i == value){
       $("#custom_menu_widget"+i).addClass("now");
       $("#customFrames"+i).css("display","block");
     }else{
        $("#custom_menu_widget"+i).removeClass("now");
         $("#customFrames"+i).css("display","none");
     }
  }
}

/**
 * 挂件工具箱的隐藏于显示
 * @param value
 * @return
 */
function hid(value){
  var h = $(window).height();
  if(value == 1){
      $("#show_panl").show();
      $("#show_panl").css("z-index",99);
	  $("#pagemain").height(h-28);
      $("#toolsposition").hide();
  }else if(value==3){
    //  $("#show_panl").show();
      $("#show_panl").css("z-index",99);
	  $("#pagemain").height(h-28);
	 // $("#toolsposition").hide();
  }else{
	  $("#show_panl").hide();
	  $("#toolsposition").show();
	  $("#pagemain").height(h-Selector.PAGEMAIN_HEIGHT);
	  
      
  }
}


/**
 * 挂件模板，自定义和默认的切换
 * @param value
 * @return
 */
function widgetStyle(value){
	 var modeJson = {
				"borderwidth":"0px",//边框宽度
				"bordercolor":"fff",//边框颜色
				"borderStyle":"solid",//边框样式
				"backgroundcolor":"transparent",//背景颜色
				"showbar":"block",//显示标题栏
				"barbg":"transparent",//标题栏背景
				"barcolor":"000"//标题文字色
				};
   if(value == 'defualt'){
	   $('#default_style').attr("checked","true");
	   $('#defined_style').removeAttr("checked");
	   $('#default_style_ul').css("display","block");
	   $('#defined_style_ul').css("display","none");
   }else{
	   $('#default_style').removeAttr("checked");
	   $('#defined_style').attr("checked","true");
	   $('#default_style_ul').css("display","none");
	   $('#defined_style_ul').css("display","block");
 
		$("#mode_style").css("border",modeJson.borderwidth+" "+modeJson.borderStyle+" #"+modeJson.bordercolor);
		$("#mode_style").css("background-color", modeJson.backgroundcolor);
		$("#mode_style_title").css("color", modeJson.barcolor);
		$("#mode_style_title").css("display",modeJson.showbar);
		$("#mode_style_title").css("background-color", modeJson.barbg);
		$("#mode_style_more a").css("color", modeJson.barcolor);
   }

}

/**
 * 选择默认挂件模板
 * @param value
 * @param totals
 * @return
 */
function defaultStyle(value,totals){
var obj = $("#widget_style_"+value);

var modeJson = {
		"borderwidth":"1px",//边框宽度
		"bordercolor":"ff0000",//边框颜色
		"borderStyle":"solid",//边框样式
		"backgroundcolor":"ffff00",//背景颜色
		"showbar":"block",//显示标题栏
		"barbg":"fff000",//标题栏背景
		"barcolor":"ff0000"//标题文字色
		};

$("#mode_style").css("border",modeJson.borderwidth+" "+modeJson.borderStyle+" #"+modeJson.bordercolor);
$("#mode_style").css("background-color", modeJson.backgroundcolor);
$("#mode_style_title").css("color", modeJson.barcolor);
$("#mode_style_title").css("display",modeJson.showbar);
$("#mode_style_title").css("background-color", modeJson.barbg);
$("#mode_style_more a").css("color", modeJson.barcolor);

	for(var i = 1;i<=totals;i++){
	    if(i == value){
	    	$("#widget_style_"+i).parent().addClass("selected");
	    }else{
	    	$("#widget_style_"+i).parent().removeClass("selected");
	    }
	}
}

/**
 * 挂件是否需要更多链接
 * @param value
 * @return
 */
function morecheck(value){
	
	if(value == 0){
		$("#moreName").show();
	}else{
		$("#moreName").hide();
	}
	
}
/**
 * 挂件编辑框中的模块伸缩
 * @param obj
 * @param value
 * @return
 */
function showSetTitle(obj,value){
	var obj = $(obj);
	var classstr = obj.attr("class");
	if(classstr == 'setting_show'){
		obj.removeClass('setting_show');
		obj.addClass('setting_hide');
		$("#"+value).show();
	}
	if(classstr == 'setting_hide'){
		obj.removeClass('setting_hide');
		obj.addClass('setting_show');
		$("#"+value).hide();
	}
}

/**
 * 自定义挂件模板设置
 * @param value
 * @param element
 * @return
 */
function widgetMode(value,element){

	var obj = $(element);
	 if(element == 'borderwidth'){
		 var type = $("#borderStyle").val();
		 var color = $("#border-color").val();
		 if(color != 'transparent')$("#mode_style").css("border",value+" "+type+" #"+color);
	 }
     if(element == 'border-color'){
    	 var type = $("#borderStyle").val();
		 var width = $("#borderwidth").val();
		 $("#mode_style").css("border",width+" "+type+" #"+value);
	 }
     if(element == 'borderStyle'){
    	 var width = $("#borderwidth").val();
		 var color = $("#border-color").val();
		 if(color != 'transparent')$("#mode_style").css("border",width+" "+value+" #"+ color);
	 }
	 if(element == 'backgroundcolor'){
		 $("#mode_style").css("background-color","#"+value);
	 }
	 if(element == 'showbar'){
		 $("#mode_style_title").css("display",value);
	 }
	if(element == 'barbg'){
		 $("#mode_style_title").css("background-color","#"+value);
	}
	if(element == 'barcolor'){
		$("#mode_style_title").css("color","#"+value);
		$("#mode_style_more a").css("color","#"+value);
 
	}
	var modeJson = {
			"borderwidth":$("#borderwidth").val(), //边框宽度
			"bordercolor":$("#border-color").val(), //边框颜色
			"borderStyle":$("#borderStyle").val(), //边框样式
			"backgroundcolor":$("#backgroundcolor").val(), //背景颜色
			"showbar":$("#showbar").val(), //显示标题栏
			"barbg":$("#barbg").val(),//标题栏背景
			"barcolor":$("#barcolor").val()//标题文字色
			};
}



