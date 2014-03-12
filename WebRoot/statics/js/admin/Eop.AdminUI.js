var Eop=Eop||{};
$.ajaxSetup({ cache:false });
var basePath="";
Eop.AdminUI={
	opts:undefined,
	init:function(opations){
	
		if(typeof opations=="string") {
			this.opts ={};
			this.opts.wrapper = $( opations);
			
		}
		
		if(typeof opations=="object"){
			this.opts = opations;
		}
		
	},
	
	load:function(link){
		Eop.Help.close();
		if(Eop.onRedirect){
			if(!Eop.onRedirect()){
				return false;
			}			
			Eop.onRedirect=undefined;
		}
		var target = link.attr("target");
		var url = link.attr("href");
		if(target){
			if(target=="_self"){
				location.href=url;
				return true;
			}
			if(target=='ajax'){
				url = url.replace('http://'+location.hostname,'');
				url = url.replace(':'+location.port,'');
				this.loadUrl(url);
				return false;
			}
			
			if(target=='iframe'){
				this.loadUrlInFrm(url);
				return false;
			}
			if(target=='_blank'){
				return true;
			}
			if(target=='_top'){
				return true;
			}
			this.loadUrlInFrm(url);
			 
			
		}else{
			this.loadUrlInFrm(url);
		}
		return false;
	}
	,
	loadUrlInFrm:function(url){
		$.Loading.show('正在加载所需内容，请稍侯...');
		this.opts.wrapper.empty();
		this.opts.frm   =$("<iframe id='auto_created_frame' width='100%' height='100%' frameborder='0' ></iframe>");
		
		this.opts.frm.appendTo( this.opts.wrapper ).load(function(){
			$.Loading.hide();
		});	
		this.opts.frm.attr("src",url);
	}
	,
	loadUrl:function(url,fun){
		//alert("load:"+url);
		$.Loading.show('正在加载所需内容，请稍侯...');
		basePath= url.substring(0,url.lastIndexOf("/")+1);
		//alert("basePath is " + basePath);
		var self =this;
		$.ajax({
			 type: "GET",
			 url: url,
			 data:"ajax=yes&rmd="+ new Date().getTime(),
			 dataType:"html",
			 success: function(html){ 
				self.opts.wrapper.empty().append($(html));
				if(fun) fun();
				self.requestProxy();
				$.Loading.hide();
				Eop.Help.init();
			 },error:function(e){
				 
				 //alert("出错了:(" +e.status);
				 $.Loading.hide();
			 }
		}); 
	},
	requestProxy:function(){
		var self =this;
		
		//代理a连接
		this.opts.wrapper.find("a").click(function(){
			if($(this).attr("target") == '_blank' ) return true;
			if($(this).attr("target") == '_top' ) return true;
			var href=$(this).attr("href");
			href = href.substring( href.lastIndexOf("/")+1, href.length );
			 
			//alert("href1:"+href);
			if(href!="javascript:;"){
				loadUrl(href);
				return false;
			}
			return true;
		});
		
		//代理表单提交
 		var $form = this.opts.wrapper.find("form");
 		$form.append("<input type='hidden' name='ajax' value='yes' />");
 		$form.submit(function(){
 			if("false"==$(this).attr("validate")) return false;
 			var method = $(this).attr("method");
 			if(!method) method="POST";
 			
 			$.Loading.show('正在提交数据...');
 			var options = {
 					url :basePath+$form.attr("action"),
 					type : method,
 					dataType : 'html',
 					success : function(result) {				
 						self.opts.wrapper.html(result);
 						self.requestProxy();
 						Eop.onRedirect=undefined;
 						$.Loading.hide();
 						$.Loading.text = undefined;
 					},
 					error : function(e) {
 						//alert("出错啦:(");
 						$.Loading.hide();
 					}
 				};
 
 			$(this).ajaxSubmit(options);
 			return false;
 		});
 	 
	 		
		
	}
};

 
Eop.Dialog={
 
	defaults: {
		      //    height: 400,
		          modal:true
		    },
	init:function(options){
		this.opts = $.extend({}, this.defaults, options);
		
		var self=this;

		if( $("#dlg_"+self.opts.id).size()==0){
			
	 
			var html ='<div  class="dialog" style="display:none;"><div class="dialog_box">';
					html+='<div class="head">';
					html+='<div class="title">'+this.opts.title+'</div>';
					html+='<span class="closeBtn"></span>';
					html+='</div>';
			html+='<div class="body dialogContent"></div>';
			//html+='<img style="cursor: nw-resize; position: absolute; bottom: 1px; right: 1px;" src="images/resize-btn.gif" class="resizeBtn"></div>';	
			html+='</div>';
			
				self.dialog=$(html);
				self.dialog.appendTo('body');
			 	self.dialog.attr("id","dlg_"+self.opts.id);
		}else{
			self.dialog =$("#dlg_"+self.opts.id);
		 }
			self.dialog.css('width',self.opts.width);
		//	if(self.opts.height) self.dialog.css('height',self.opts.height);
			self.dialog.find(".dialogContent").empty().append($("#"+self.opts.id));
			self.dialog.jqDrag('.head')
		    .jqm(this.opts)
		    .jqmAddClose('.closeBtn');
	},
	open:function(id){
		if(id){
			$("#dlg_"+id).jqmShow();
		}
		else{
			this.dialog.jqmShow();
		}
	},
	close:function(id){
		if(id){
			$("#dlg_"+id).jqmHide();
			//$("#dlg_"+id).remove();
		}
		else{
			this.dialog.jqmHide();
		}		
	}
};

function loadScript(url){
	$.ajax({
		 type: "GET",
		 url:basePath+ url,
		 dataType:   "script" 
	}) ;
}

function loadUrl(url){

	url=basePath+url;
	Eop.AdminUI.loadUrl(url);
}

Eop.Help={
	init:function(){
		$("#HelpClose ").click(function(){
			$("#HelpCtn").hide();
		});		
		$(".help_icon").click(function(e){
			var helpid = $(this).attr("helpid");
			var y =e.pageY-22;
			$("#HelpCtn").css("top",y).css("left",e.pageX+10).show();
			$("#HelpBody").html("正在加载...");		
			$("#HelpBody").load(app_path+"/core/admin/help.do?ajax=yes&helpid="+helpid);
		});		
	},
	close:function(){
		$("#HelpCtn").hide();
	}

};

Eop.InputFile = {
		init:function(){
			$("input[type='file']").after("&nbsp;<input type='button' value='清空'/>").next("input").click(function(){
				var ie = (navigator.appVersion.indexOf("MSIE") != -1);//IE 
				var ff = (navigator.userAgent.indexOf("Firefox") != -1);//Firefox 

				if (ie) {
					var file = $(this).prev("input").attr("name");
					var file2 = file.cloneNode(false);
					file2.onchange = file.onchange;
					file.parentNode.replaceChild(file2, file);
				} else
					$(this).prev("input").attr("value", "");
			});
		}
	};

$(function(){
	Eop.Help.init();
	Eop.InputFile.init();
});

	


