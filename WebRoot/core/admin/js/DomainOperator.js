var Core = {};
Core.DomainOperator = {
	ainput:undefined,
	btnAdd:undefined,
	domaindlg:undefined,
	init : function() {
		this.refreshList();
		var that = this;
		this.btnAdd=$("#btnAdd");

		this.btnAdd.click(function() {
			var link = $(this).attr("disabled","true");
			that.saveDomain();
		});		
	},

	saveDomain:function(){
		var self=this;
		var siteid = $("#siteid").val();
		var domainname=$("#domainname").val();
		if($.trim(domainname)=='') {
			alert('请输入域名');
			return ;
		}
		$.Loading.show("load...");
		$.ajax( {
			type : "POST",
			url : "userSite!addDomainSave.do",
			data : "ajax=yes&sitedomain=" + domainname+"&siteid="+siteid,
			dataType : 'json',
			success : function(result) {
				if (result.result == 1) {
					$.Loading.hide();
					alert("域名新增成功");
					self.refreshList();
					$("#btnAdd").attr("disabled",false);
					
				} else {
					$.Loading.hide();
					$("#btnAdd").attr("disabled",false);
					alert(result.message + " 新增域名失败，请重试");
				}
			},
			error : function() {
				$.Loading.hide();
				$("#btnAdd").attr("disabled",false);
				alert("异步请求错误");
			}
		});		
	}
	,

	del_click : function(domainid) {
		var that = this;
		if (confirm('确认要删除此域名吗？删除后不可恢复')) {
			$.ajax( {
				type : "POST",
				url : "userSite!deleteDomain.do",
				data : "ajax=yes&domainid=" + domainid,
				dataType : 'json',
				success : function(result) {
					if (result.result == 1) {
						that.refreshList();
					} else {
						alert(result.message);
					}
				},
				error : function() {
					alert("删除异步请求错误");
				}
			});
		}
	},

	refreshList : function() {
		var that = this;
		var siteid = $("#siteid").val();
		$("#list_wrapper").load("userSite!domainlist.do", {
			id : siteid,
			ajax : 'yes'
		}, function() {
			$(".domainDel").click(function() {
				that.del_click($(this).parent("td").attr("domainid"));
			});
		});
	}
};

$(function() {
	Core.DomainOperator.init();
});