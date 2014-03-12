/**
 * 商品属性字串生成器
 */
var PropUrlCreator={
	uri:undefined,
	haveProp:false,// uri中是否含有属性字串
	propReg:/prop{(.+)}/, //属性串的正则
	propInputName:'text_prop',//文本属性input的name
	init:function(uri){
		this.uri = uri;
		return this;
	}
	,
	/**
	*创建属性字串
	*首先拼合不是文本输入的属性
	*然后根据页面name为text_prop的input拼合文本输入属性字串
	*拼合的规则是：用input的pos属性做为属性下标，用value做为属性值
	*/
	createPropUri:function(){
		//匹配出属性字串
		var propstr =this.propReg.exec(this.uri);
		if(!propstr){
			propstr="";
			this.haveProp =false;
		}else{
			this.haveProp=true;
			propstr=propstr[1];
		}

		//属性字串中的属性是以,号分隔的，拆分为数组。
		var prop_ar =  propstr.split(",");
		var result="";

		//循环属性，找到不是文本输入的属性重新拼合
		for(var i=0;i<prop_ar.length;i++){

			//拆分某个属性
			var ar= prop_ar[i].split("_");
		
			var length =$("input[pos="+ar[0]+"]").length;
			if(!length>0){//不属于文本属性
				if(result!="") result+=",";
				result+=prop_ar[i];
			} 
		}

		//循环文本输入属性框，并组合为属性串
		//用input的pos属性做为属性下标，用value做为属性值
		$("input[name='"+this.propInputName+"']").each(function(){
			if(($(this).val().indexOf(',')>0)||($(this).val().indexOf('?')>0)||($(this).val().indexOf('_')>0)){
				alert("请不要输入非法字符[_,?]");
				$(this).focus();
				return ;
			}
			if($(this).val()!=''){
				if(result!="")result+=",";
				result+=$(this).attr("pos") +"_"+ $(this).val();
			}
		});
		
		if(this.haveProp){
			result= this.uri.replace(this.propReg,"prop{"+result+"}");
		}else{
			result= this.uri.replace(".html","prop{"+result+"}.html");
		}	
		return encodeURIComponent(result.trim()).replace("%2F", "");		
	}
	
};

String.prototype.trim=function()
{
     return this.replace(/(^\s*)(\s*$)/g, '');
}
 
 
