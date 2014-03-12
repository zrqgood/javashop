var Explorer={
	init:function(){
		var self  = this;
		Eop.Dialog.init({id:"renameDlg",modal:false,title:"重命名",height:"115px"});
		Eop.Dialog.init({id:"newFolderDlg",modal:false,title:"新建目录",height:"115px"});
		Eop.Dialog.init({id:"uploadDlg",modal:false,title:"上传文件",height:"115px"});
		Eop.Dialog.init({id:"moveDlg",modal:false,title:"移动文件",height:"115px"});
		
		//改名
		$(".rename").click(function(){
			var name= $(this).attr("name");

			Eop.Dialog.open('renameDlg');
			$("#oldName").val(name);
			$("#newName").val(name);
		});
		
		$("#renameBtn").click(function(){
			self.rename();
		});
		
		
		//新建目录
		$("#newFolderBtn").click(function(){
			Eop.Dialog.open('newFolderDlg');
		});

		$("#saveFolderBtn").click(function(){
			self.newFolder();
		});
		
		//上传
		$("#uploadBtn").click(function(){
			Eop.Dialog.open('uploadDlg');
		});
	
		$("#saveUploadBtn").click(function(){
			self.upload();
		});

		//移动
		$(".move").click(function(){
			var name = $(this).attr("name");
			$("#mvNameSpan").html(name);
			$("#mvName").val(name);
			Eop.Dialog.open('moveDlg');
		});	
		$("#saveMoveBtn").click(function(){
			self.move();
		});		
	},
	move:function(){
		if( $.trim( $("#newPath").val() )=='' ){
			alert('请输入目录名称');
			return ;
		}		
		var self = this;
		$.Loading.show('正在移动，请稍侯...');
		var options = {
				url : "themeFile!move.do?ajax=yes",
				type : "POST",
				dataType : 'json',
				success : function(result) {	
					$.Loading.hide();

					if(result.result==1){
						Eop.Dialog.close('moveDlg');
						alert("移动成功");
						location.reload();
					}else{
						alert(result.message);
					}
				},
				error : function(e) {
					$.Loading.hide();
					alert("出现错误 ，请重试");
				}
			};

		$('#moveForm').ajaxSubmit(options);				
	}
	,
	upload:function(){
		if( $.trim( $("#file").val() )=='' ){
			alert('请选择上传文件');
			return ;
		}		
		var self = this;
		$.Loading.show('正在上传，请稍侯...');
		var options = {
				url : "themeFile!upload.do?ajax=yes",
				type : "POST",
				dataType : 'json',
				success : function(result) {	
					$.Loading.hide();

					if(result.result==1){
						Eop.Dialog.close('uploadDlg');
						alert("上传成功");
						location.reload();
					}else{
						alert(result.message);
					}
				},
				error : function(e) {
					$.Loading.hide();
					alert("出现错误 ，请重试");
				}
			};

		$('#uploadForm').ajaxSubmit(options);		
	},
	/**
	 * 创建新文件夹
	 */
	newFolder:function(){
		if( $.trim( $("#newFolderName").val() )=='' ){
			alert('请输入文件夹名称');
			return ;
		}
		
		var self = this;
		$.Loading.show('正在保存，请稍侯...');
		var options = {
				url : "themeFile!addFolder.do?ajax=yes",
				type : "POST",
				dataType : 'json',
				success : function(result) {	
					$.Loading.hide();

					if(result.result==1){
						Eop.Dialog.close('newFolderDlg');
						alert("保存成功");
						location.reload();
					}else{
						alert(result.message);
					}
				},
				error : function(e) {
					$.Loading.hide();
					alert("出现错误 ，请重试");
				}
			};

		$('#newFolderForm').ajaxSubmit(options);		
	}
	,
	/**
	 * 重命名
	 */
	rename:function(){
		if( $.trim( $("#newName").val() )=='' ){
			alert('请输入名称');
			return ;
		}
				
		var self = this;
		$.Loading.show('正在保存，请稍侯...');
		var options = {
				url : "themeFile!rename.do?ajax=yes",
				type : "POST",
				dataType : 'json',
				success : function(result) {	
					$.Loading.hide();
					Eop.Dialog.close('renameDlg');
					alert("保存成功");
					if(result.result==1){
						location.reload();
					}else{
						alert(result.message);
					}
				},
				error : function(e) {
					$.Loading.hide();
					alert("出现错误 ，请重试");
				}
			};

		$('#renameForm').ajaxSubmit(options);
	}
};