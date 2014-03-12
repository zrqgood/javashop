var spec_imgs={};
function getProduct(){
	return Product;
}

function getPicNames(){
 
	return $("[name=picnames]");
}

var Product= {
	gridTable:undefined,
	init:function(){
	 
		this.gridTable = this.createEmptyTable();
 
		$("#body2").hide();
		var self=this;
		$("#specOpenBtn").click(function(){
			$("#body1").hide();
			$("#body2").show();
			$("#haveSpec").val(1);
			self.openSpecDialog();
		});

		$("#specCloseBtn").click(function(){
			$("#body2 table").replaceWith(self.gridTable);
			$("#body2").hide();
			$("#body1").show();
			$("#haveSpec").val(0);
			
		});
 
		Eop.Dialog.init({id:"specdlg",title:"规格",width:"500px",height:'495px'});
		
		//编辑时货品的删除事件
		$("#productNode .delete").click(function(){
			self.deleteProRow($(this));
		});
		
		if($("#haveSpec").val()=='0'){
			$("#body2").hide();
			$("#body1").show();			

		}else{
			$("#body2").show();
			$("#body1").hide();
		}
	}
	,
	openSpecDialog:function(){
		Eop.Dialog.open("specdlg");
		$("#spec_dialog").html("loding...");
		$("#spec_dialog").load(basePath+'goodsSpec.do?ajax=yes');
	},
	createEmptyTable:function(){
		var table  = $("#body2 table").clone();
		var theadHtml ='<tr>';
		theadHtml+='<th style="width: 200px;">货号</th>';
		theadHtml+='<th style="width: auto;" id="storeTh">库存</th>';
		theadHtml+='<th style="width: auto;">销售价</th>';
		theadHtml+='<th style="width: auto;">成本价</th>';
		theadHtml+='<th style="width: auto;">重量</th>';
		theadHtml+='<th style="width: auto;">操作</th>';
		theadHtml+='</tr>';
		table.find("thead").empty().append(theadHtml);
		table.find("tbody").empty();
		return table;
	}
	,
	

	createPros:function(specs,names){
		
		/** 
		 * 获取会员价,成功后生成货品grid
		 */
		var self = this;
		$.ajax({
			url:'../shop/admin/memberPrice!disLvInput.do?ajax=yes',
			dataType:'html',
			success:function(html){
				self.doCreatePros(specs, names,html);
			},
			error:function(){
				alert("获取会员价出错");
			}
		});
		
	},
	
	doCreatePros:function(specs,names,memPriceInput){
		
		var price  = $("#price").val();
		var store  = $("#store").val();
		var weight  = $("#weight").val();
		var cost  = $("#cost").val();
		
		var self =this;
		for(i in names){
			$("#storeTh").before("<th>"+names[i]+"</th>");
		}
		var products = combinationAr(specs);
		for(i in products){
			//每个规格的会员价名称为顺序排列，如第一个货品的会员价名称为：lvid_0和lvPrice_0
			var priceInputHtml = memPriceInput.replace(/name="lvid"/g,'name="lvid_'+i+'"');
			priceInputHtml = priceInputHtml.replace(/name="lvPrice"/g,'name="lvPrice_'+i+'"');
			var specar = products[i];
			var html ='<tr><td><input type="text"  name="sns" size="15"  autocomplete="off"></td>';
			var specvids="";
			var specids ="";
			for(j in specar){
				
				var spec = specar[j];
				if(j!=0){
					specvids+=",";
					specids+=",";
				}
				specvids+=spec.specvid;
				specids +=spec.specid;
				html+="<td>" ;
				if( parseInt(spec.spectype) == 0){
					html+="<div class='select-spec-unselect spec_selected'><span>" + spec.specvalue +"</span></div>";
				}else{
					var img  = '<img height="20" width="20" title="'+spec.specvalue+'" alt="'+spec.specvalue+'" src="'+spec.specimage+'">';
				//	img = img.replace(Eop.ContextPath+"/plugin/spec/","");
					html+='<div class="select-spec-unselect spec_selected"><center>'+img+'</center></div>';
				}
				html+="<input type=\"hidden\" name=\"specvalue_"+i+"\" value=\""+spec.specvalue+"\" />";
				html+="</td>";
			}

			html+="<td>";
			html+="<input type='hidden' name='specids' value='"+ specids +"' />";
			html+="<input type='hidden' name='specvids' value='"+specvids+"' />";			
			html+='<input type="text" name="stores" size="4" autocomplete="off"  value="'+ store +'"> </td>';
			
			html+='<td><input type="text" class="price" name="prices" size="8" autocomplete="off" value="'+ price +'"><input type="button" class="mempriceBtn" value="会员价" /><div class="member_price_box" index="'+i+'">'+priceInputHtml+'</div></td>';
			html+='<td><input type="text" name="costs" size="8" autocomplete="off" value="'+cost+'"></td>';
			html+='<td><input type="text" name="weights" size="10" autocomplete="off" value="'+weight+'"></td>';
			html+='<td><a href="javascript:;" ><img  class="delete" src="../shop/admin/images/transparent.gif" ></a></td>';
			html+="</tr>";
			var trEl =$(html);
			$("#productNode").append(trEl);
			trEl.find("img.delete").click(function(){
				self.deleteProRow($(this));
			});	 
			MemberPrice.bindMbPricBtnEvent();
		}
	},
	deleteProRow:function(link){
		if(confirm("确定删除此货品吗？删除后不可恢复"))
			link.parents("tr").remove();
	}	
	
	
};

$(function(){
	
	Product.init();
});



/**
* 将一个值放在一个数组未尾，形成新的数据
*/
function putAr(ar1,obj){
	var newar =[];
	for(var i=0;i<ar1.length;i++){
	    	newar[i] =ar1[i];
	}
	newar[ar1.length] = obj;
	return newar;
};


/*
*
* 组合两个数组
* 如果第一个数组是二维数组，则调用putAr分别组合
* 如果第一个数组是一维数组，则直接和ar2组合
*/
function combination(ar1,ar2){
	var ar = new Array();
	var k=0;
	if(!ar2) { //数组只有一唯的情况
	 
		for(var i=0;i<ar1.length;i++){
			ar[k] = [ar1[i]];
			k++;
		}	
		return ar;
	}

	
 
	
 
	for(var i=0;i<ar1.length;i++){
	 
		
		for(var j=0;j<ar2.length;j++){
 
			if(ar1[i].constructor == Array ){
				ar[k]= putAr(ar1[i],ar2[j]);
				 
			}else{
				ar[k] = [ar1[i],ar2[j]];
			}	 
		  
			k++;
		}
	}
	
	return  ar;
};



function  combinationAr(spec_ar){
	var ar;
	var m =0 ;

	if(spec_ar.length==1){ return combination(spec_ar[0]);}

	while(m<spec_ar.length-1){
		if(m==0){
			ar = spec_ar[0];
		}
		
		ar = combination(ar,spec_ar[m+1]);
		m++;
	};
	
	return ar;
	
};