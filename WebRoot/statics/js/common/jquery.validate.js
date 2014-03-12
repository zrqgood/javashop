/**
 * validate- jQuery Plug-in
 * @version 1.0
 * @author kingapex
 * Copyright 2009 enation  
 */
(function($){
	$.Validator={};
	var DefLang ={
			
			validate_fail:'您提交的表单中有无效内容，请检查高亮部分内容。',
			required:'此项为必填',
			string:'',
			is_not_int:'此选项必须为整型数字',
			is_not_float:'此选项必须为浮点型数字',
			is_not_date:'日期格式不正确',
			is_not_email:'email格式不正确',
			is_not_mobile:'手机号码格式不正确',
			is_not_id_card:'cart not valid...',
			is_not_post_code:'邮政编码格式不正确',
			is_not_url:'不是有效的地址格式'
			
			
		};
		$.isDate=function(val){var reg = /^\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/; return reg.test(val);};
		$.isTime=function(val) { var reg = /^([0-1]\d|2[0-3]):[0-5]\d:[0-5]\d$/; return reg.test(val); };
		$.isEmail=function(val){var reg = /^([a-z0-9+_]|\-|\.|\-)+@([\w|\-]+\.)+[a-z]{2,4}$/i; return reg.test(val);};	
		$.isNumber=function(val){if(val=='') return true; return parseInt(val) == val;}			

		function validator_lang_exist(key){
			  if (typeof(DefLang) != "object") return false;
			  if (typeof(DefLang[key]) == 'string') return true;
			  return false;
		}
		
		
		dt_string = function ()
		{
		  this.check = function (val) {return true;};
		  this.test = function (val, testStr) {return new RegExp(testStr).test(val)};
		  this.errorMsg = function () { return DefLang.string ;};
		};
		dt_int = function ()
		{
		  this.check = function (val){ if(val=='') return true; return parseInt(val) == val;};
		  this.test = function (val, testStr) {
		    var arr = testStr.split(',');
		    val = parseInt(val);
		    var test = arr[0].trim();
		    if (test != '*' && val < parseInt(test)) return false;
		    if (arr.length > 1){
		      test = arr[1].trim();
		      if (test != '*' && val > parseInt(test)) return false;
		    }
		    return true;
		  };
		  this.errorMsg = function () {if (validator_lang_exist('is_not_int')) {return DefLang.is_not_int} else {return "this value is not int!"}};
		};


		dt_float = function ()
		{
		  this.check = function (val){if(val=='') return true;   return parseFloat(val) == val;};
		  this.test = function (val, testStr) {
			if(val=='') return true;  
		    var arr = testStr.split(',');
		    val = parseFloat(val);
		    var test = arr[0].trim();
		    if (test != '*' && val < parseFloat(test)) return false;
		    if (arr.length > 1){
		      test = arr[1].trim();
		      if (test != '*' && val > parseFloat(test)) return false;
		    }
		    return true;
		  };
		  this.errorMsg = function () {if (validator_lang_exist('is_not_float')) {return DefLang.is_not_float} else {return "this value is not float!"}};
		};
		dt_date = function()
		{
		  var self = this;
		  this.check = function (val){return $.isDate(val);};
		  this.errorMsg = function () {if (validator_lang_exist('is_not_date')) {return DefLang.is_not_date} else {return "this value is not date!"}};
		};

		dt_email = function()
		{
		  this.check = function (val) {if(val=='') return true;  return $.isEmail(val);};
		  this.test = function() {return true};
		  this.errorMsg = function () {if (validator_lang_exist('is_not_email')) {return DefLang.is_not_email} else {return "this value is not email!"}};
		};
		dt_tel_num = function()
		{
		  this.check = function (val) {return /^[\d|\+|\_|\-]+$/.test(val);};
		  this.errorMsg = function () {if (validator_lang_exist('is_not_tel_num')) {return DefLang.is_not_tel_num} else {return "this value is not telephone Number!"}};
		};
		dt_mobile = function()
		{
		  this.check = function (val) {return /^[\d|-|\+]{3,20}$/.test(val);};
		  this.errorMsg = function () {if (validator_lang_exist('is_not_mobile')) {return DefLang.is_not_mobile} else {return "this value is not mobile Number!"}};
		};
		dt_id_card = function()
		{
		  this.check = function (val) {return true};
		  this.errorMsg = function () {if (validator_lang_exist('is_not_id_card')) {return DefLang.is_not_id_card} else {return "this value is not IDCard Number!"}};
		};
		dt_post_code = function()
		{
		  this.check = function (val) {return /^[1-9]\d{5}(?!\d)$/.test(val);};
		  this.errorMsg = function () {if (validator_lang_exist('is_not_post_code')) {return DefLang.is_not_post_code} else {return "this value is not postCode!"}};
		};
		dt_url = function()
		{
		  this.check = function (val) {return val.match(/^(?:^(https?):\/\/[\-A-Z0-9+&@#\/%?=~_|!:,.;]*[\-A-Z0-9+&@#\/%=~_|]$)$/i);};
		  this.errorMsg = function () {if (validator_lang_exist('is_not_url')) {return DefLang.is_not_url} else {return "this value is not url!"}};
		};
		dt_file = function ()
		{
		  this.check = function (val) {return true};
		  this.test = function (val, testStr) {return true};
		};
			
	
	var Validator={
			types :{"string":new dt_string(),"int":new dt_int(),"date":new dt_date(), "email":new dt_email,"tel_num":new dt_tel_num(),
			    "mobile":new dt_mobile(), "id_card":new dt_id_card(), "post_code":new dt_post_code(), "url":new dt_url(), "region":new dt_string(), "file":new dt_file(),"float":new dt_float()
			  },
			/*
			 * 显示提示信息
			 */
			note:function(frm_ele){
				var required = frm_ele.attr("required");
				if( required  ){
					this.showNote(frm_ele, DefLang.required);			
				} 			
			},
			
			
			
			/*
			 * 校验一个元素
			 */
			check:function(frm_ele){
				if(!frm_ele) return true;
				if(frm_ele.attr("disabled")) return true;
				try{
					
					var required = frm_ele.attr("required");

				if( required  ){
					 if( $.trim(frm_ele.val() ) == '' ){
						 this.showError(frm_ele, DefLang.required);
						 
						 return false;
					 }
				}
				
				var dataType = frm_ele.attr("dataType");
				if( dataType  && this.types[dataType] ){
					var checker  = this.types[dataType];
					if( checker.check(  frm_ele.val() ) ){
						this.showOk(frm_ele ,"");
					}else{
						this.showError(frm_ele, checker.errorMsg());
						return false;
					}
				}
		 	
				var fun = frm_ele.attr("fun");
				eval('result= typeof(' + fun + ') == "function"');
				if(result== true){
					
					var r = eval(fun+"(frm_ele.val())");
					if( typeof(r)=='string' ){
						this.showError(frm_ele, r);
						return false;
					}
					
					if(!r) return false;					
					
				}
				 
				this.showOk(frm_ele ,"");
				return true;
				}catch(e){
					//alert(e);
					//alert(frm_ele.attr("name"));
				}
			},
			
			
			/*
			 * 显示提示信息
			 */
			showNote:function(frm_ele,msg){
				var note_span = this.getNoteSpan(frm_ele);
				note_span.removeClass("error");
				note_span.removeClass("ok");
				note_span.addClass("note");
				note_span.text(msg);
			},	
			
			/*
			 * 显示验证正确
			 */
			showOk:function(frm_ele,msg){
				var note_span = this.getNoteSpan(frm_ele);
				note_span.removeClass("error");
				note_span.removeClass("note");
				note_span .addClass("ok");
				note_span.text(msg);
			},
			
			/*
			 * 显示错误
			 */
			showError:function(frm_ele,msg){
				
				var note_span = this.getNoteSpan(frm_ele);
				note_span.removeClass("ok");
				note_span.removeClass("note");
				note_span.addClass("error");
				note_span.text(msg);
			},
			
			
			/*
			 * 获取提示的span
			 */
			getNoteSpan:function(frm_ele){
				var note_span = frm_ele.next("span.tip");
				if(note_span && note_span.size()>0  ){
				}else{
					note_span = $("<span class=\"tip\"></span>").insertAfter(frm_ele);
				}
				
				return note_span;
			}		
	};
	
	var opts,inputs;

	/*
	 * 检查所有的表单项
	 */
	var checkAll=function(){
		var result =true;
		inputs.each(function(){
			if(this){
				var el =$(this);
				if( el.attr("required") || el.attr("dataType")|| el.attr("fun") ){
					 
					if ( !Validator.check(  el ) ) {  el.focus(); result = false;   }
				}
			}
		});
		return result;
	};
	
	$.fn.checkall=function(){
		if(checkAll()){
			$(this).attr("validate","true");
			return true;
		}else{
			$(this).attr("validate","false");
			alert(DefLang.validate_fail);
			return false;
		}
	};
	
	$.fn.validate=function(options,customFun){

		var defaults = {   
	    types: 'input[type=text],input[type=password],select,textarea',
	    lang:DefLang
	  };   
	  
	  opts = $.extend({},defaults, options||{});   
	 
	  DefLang = opts.lang;
	  
	  var self = this;
		
	   inputs  = this.find(opts.types);
		
		this.submit(function(){

			if(customFun) {
				if(!customFun()) {
					alert(DefLang.validate_fail);
					return false;
				}
			}
			if(checkAll()){
				$(this).attr("validate","true");
				return true;
			}else{
				$(this).attr("validate","false");
				alert(DefLang.validate_fail);
				return false;
			}
			

		});
		
		
		inputs.blur(function(){
			var el=  $(this);
			if( !Validator.check( el ) ){
				el.addClass("fail");
			}else{
				el.removeClass("fail");
			}
		})
		.focus(function(){
			Validator.note( $(this) );
		});
		
	};

	
})(jQuery);
