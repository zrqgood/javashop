var AuthAction={
		
	init:function(){
		var self = this;
		$("#new_action").click(function(){
			self.newAction();
		});
		self.bindBoxEvent();
		Eop.Dialog.init({id:"actionDlg",title:"权限点"});
	},
	newAction:function(){
		var self  = this;
		$("#actionDlg").load('auth!add.do?ajax=yes',function(){
			self.createTree();
			$("ul.checktree").checkTree();
			$("#authBtn").click(function(){
				self.saveAuth();
			});
		});
		Eop.Dialog.open("actionDlg");
	},
	/**
	创建菜单树
	*/
	createTree:function(){
		var self = this;
		$.each(menu.app,function(k,v){
			var li = self.createNode(v);
			var ul = self.createChildren(v.children);
			li.append(ul);
			$("#menubox>.checktree").append(li);
		});
	},
	/**
	根据子创建ul结点
	*/
	createChildren:function(menuAr){
		var ul=$("<ul></ul>");
		var self = this;
		$.each(menuAr,function(k,v){
			var li = self.createNode(v);
		
			//如果有子则递归 
			var children =v.children;
			if(children && children.length>0){
				li.append(self.createChildren(children));
			}	
			ul.append(li);
		});
		return ul;
	},
	/**
	根据menu json创建菜单节点
	*/
	createNode:function(v){
		var li = $("<li><input type=\"checkbox\" name=\"menuids\" value=\""+v.id+"\"><label>"+v.text+"</label></li>");
		return li;
	},
	/**
	保存权限 
	*/
	saveAuth:function(){
		var self= this;
		var authname= $.trim( $("#authname").val());
		if(authname == ''){
			 alert("请输入权限名称");
			 return ;
		}		 
		$.Loading.show('正在保存，请稍侯...');
		var options = {
				url :"auth!save.do?ajax=yes",
				type : "POST",
				dataType : 'json',
				success : function(result) {				
				 	if(result.result==1){
				 		$.Loading.hide();
				 		var isEdit  = $("#isEdit").val();
				 		if(isEdit==1){//修改更换名称
				 			$("#li_"+result.authid+">span").html(authname);
				 		}else{//添加加入一行
				 			$("#actbox>ul").append("<li id='li_"+result.authid+"'><input type=\"checkbox\" name=\"acts\" value=\""+result.authid+"\" /><span>"+authname+"</span><img class=\"modify\" src=\"images/transparent.gif\" authid='"+result.authid+"' >&nbsp; <img class=\"delete\" src=\"images/transparent.gif\"  authid='"+result.authid+"'> </li>");
				 		}
				 		self.bindBoxEvent();
				 	}else{
				 		alert(result.message);
				 	}
				 	Eop.Dialog.close("actionDlg");
				},
				error : function(e) {
					$.Loading.hide();
					alert("出错啦:(");
					Eop.Dialog.close("actionDlg");
 				}
 		};
		$("#authForm").ajaxSubmit(options);	
	} 
	,
	
	//绑定修改和删除事件
	bindBoxEvent:function(){
		var self=this;
		$("#actbox .modify").unbind("click").bind("click",function(){
			self.modifyAuth($(this).attr("authid"));
		});
		$("#actbox .delete").unbind("click").bind("click",function(){
			self.deleteAuth($(this).attr("authid"));
		});
	},
	
	/**
	 * 绑定对话框打开后各种事件
	 */
	bindDlgEvent:function(){
		var self  = this;

		self.createTree();
		var objvalue = $("#objvalue").val();
		if(objvalue){
			var values=objvalue.split(",");
			for(var i=0;i<values.length;i++){
				var v= values[i];
				$("ul.checktree input[value='"+v+"']").attr("checked",true);
			}
		}
		$("ul.checktree").checkTree();
		$("#authBtn").click(function(){
			self.saveAuth();
		});		
	}
	,
	modifyAuth:function(authid){
		var self  = this;
		$("#actionDlg").load('auth!edit.do?ajax=yes&authid='+authid,function(){
			self.bindDlgEvent();
		});
		Eop.Dialog.open("actionDlg");		
	},
	deleteAuth:function(authid){
		
		if(!confirm("确认删除此权限吗?")){
			return ;
		}
		
		$.ajax( {
			url :"auth!delete.do?ajax=yes",
			data:'authid='+authid,
			type : "POST",
			dataType : 'json',
			success : function(result) {				
			 	if(result.result==1){
			 		$.Loading.hide();
			 		$("#li_"+result.authid).remove();
			 	}else{
			 		alert(result.message);
			 	}
			},
			error : function(e) {
				$.Loading.hide();
				alert("出错啦:(");
				}
		});
	}
	
	
	
};
