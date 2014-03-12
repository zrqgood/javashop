
var Core={};
Core.PasswordOperator={
	init:function(){
		var dlgEl =  Eop.Dialog;
		$("#btn").click(function(){
			dlgEl.init({ width:350,height:200,resizable:false });
			dlgEl.open('/core/admin/user/userAdmin!editPassword.do?id=' + $("#btn").attr("adminid"));
		});
	}
};


$(function(){
	Core.PasswordOperator.init();
	//UserAdmin.init();
});