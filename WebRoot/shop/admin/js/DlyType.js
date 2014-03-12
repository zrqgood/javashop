var DlyType=$.extend({},Eop.Grid,{
	init:function(){
		var self =this;
		$("#delBtn").click(function(){self.doDelete();});
		$("#protect1").click(function(){self.ctl_pro(true);});
		$("#protect2").click(function(){self.ctl_pro(false);});
		$("#toggleChk").click(function(){
			self.toggleSelected(this.checked);}
		);
		
	},
	doDelete:function(){

		if(!this.checkIdSeled()){
			alert("请选择要删除的配送方式");
			return ;
		}
	 
		if(!confirm("确认要将这些配送方式彻底删除吗？删除后将不可恢复")){	
			return ;
		}
		$.Loading.show("正在删除配送方式...");
		
		this.deletePost("dlyType!delete.do");
			
	}  ,
	ctl_pro:function(chked){
		var dis;
		if(chked){
			dis='';
		}else{
			dis='none';
		}
	
		var con1 = document.getElementById("protect_con1");
		var con2 = document.getElementById("protect_con2");
	 	con1.style.display=dis;
	 	con2.style.display=dis;
 
	}
	
	
	
});

$(function(){
	DlyType.init();
});

function ctl_price(chked,index){
 
	document.getElementById("price_"+ index).disabled=!chked;
 
};