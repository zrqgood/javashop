var VersionChecker={
		init:function(){
			var self = this;
			$("#check_new_btn").click(function(){
				self.check();
			});
		},
		check:function(auto){
	
			var self = this;
			if(!auto){
				$.Loading.show('正在检查新版本，请稍候...');
			}
		
			$.ajax({
				url:'../core/admin/update!checkNewVersion.do?ajax=yes',
				dataType:'json',
				success:function(versionState){
					$.Loading.hide();
					if(versionState.haveNewVersion){
						self.version = versionState.newVersion;
						if(auto){
							if('skip'==$.cookie("ver_"+self.version) ){
								return ;
							}
						}
						var content = "<div id='version_box'>";
						content+="<div style='font-size:14px'>检测到新版本<b>"+versionState.newVersion+"</b>，功能更新：</div>";
						content+="<div style='height:200px;overflow:auto;margin-top:15px;margin-left:10px'>"+self.createLogHtml(versionState.updateLogList)+"</div>";
						content+="<div style='text-align:center'><input style='margin-left:10px;height:30px' type='button' id='ver_upd_btn' value='立刻升级' /><input type='button'  style='margin-left:10px;height:30px' id='ver_wait_btn' value='以后再说' /><input type='button'  style='margin-left:10px;height:30px' id='ver_skip_btn' value='不再提醒此版本' /></div>";
						$("body").append(content);
						Eop.Dialog.init({id:"version_box",modal:true,title:"软件升级",width:"500px",height:"400px"});
						Eop.Dialog.open("version_box");

						$("#ver_upd_btn").click(function(){
							self.update();
						});

						$("#ver_wait_btn").click(function(){
							Eop.Dialog.close("version_box");
						});
						
						$("#ver_skip_btn").click(function(){
							 self.skipVersion();
						});
						
					}else{
						if(!auto){
							alert("您的"+versionState.productid+"已经是最新版");
						}
					}
				},
				error:function(){
					$.Loading.hide();
				}
			});
		},
		/**
		 * 生成日志的html
		 */
		createLogHtml:function(updateLogList){
			var logBox = $("<ul></ul>");
			$.each(updateLogList,function(app_index,app){
				if(app.logList.length>0){
					var appbox=$("<li class='app'>"+app.appId+"<ul ></ul></li>");
					$.each(app.logList,function(log_index,log){
						appbox.children("ul").append("<li class='log'>"+log+"</li>");
					});
					logBox.append(appbox);
				}
			});
			
			return logBox.html();
		}
		,
		skipVersion:function(){
			  var date = new Date();
              date.setTime(date.getTime() + (360 * 24 * 60 * 60 * 1000));
			  var options = { path: '/', expires: date };
			  $.cookie("ver_"+this.version, 'skip', options);
			  Eop.Dialog.close("version_box");
		}
		,
		update:function(){
			var self= this;
			Eop.Dialog.close("version_box");
			$.Loading.show('正在升级，可能需要花费一些时间，请稍候...');
			$.ajax({
				url:'../core/admin/update!update.do?ajax=yes',
				dataType:'json',
				success:function(r){
					if(r.result==0){
						alert('升级失败:'+r.message);
						$.Loading.hide();
					}else{
						$.Loading.hide();
						alert("您的Javashop已经成功升级至"+self.version+",需要重起应用生效。");
					}
				},
				error:function(){
					alert("升级出错:(");
				}
			});
		}
		
	};

$(function(){ 
	if(mainpage){
		if(runmode==2 && domain!='www.enationsoft.com'){
			$("#check_new_btn").parent().remove();
		}else{
			VersionChecker.init();
			VersionChecker.check(true);
		}
	}
});