var Eop=Eop||{};
Eop.Dialog={
	init:function(opations){
	 	 EopDlg.dialog(opations);
	},
	open:function(url){
 		url=app_path + url;
 		setDlgUrl(url);
 		EopDlg.dialog({closed:false});
	},
	close:function(){
		EopDlg.dialog({closed:true});
	}
	
};