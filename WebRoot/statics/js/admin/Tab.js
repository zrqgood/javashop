var Tab=function(id){
	this.currentIndex=0,
	selectedIndex=0,
	
	this.init=function(id){
		this.id=id;
		var self  = this;
		$(this.id+" .tab>li").click(function(){
			var selected=$(this);
			self.toggle(selected);
		});
	};
	this.init(id);
	//切换
	this.toggle =function(selected){
		this.toggleTab(selected);
		this.toggleBody();
	};
	
	this.toggleTab=function(selected){
		var self = this;
		var i = 0;
		$(this.id+" .tab>li").each(function(){
			
			var tab= $(this);
			
			//找到当前的
			if(tab.attr("class")=='active'){
				self.currentIndex =i;
				tab.removeClass('active');
			}
			
			//当前中的
			if( this ==selected.get(0) ){
				self.selectedIndex =i;
				tab.addClass('active');
			}
			
			i++;			 
		});
		
	};
	
	
	//切换内容体
	this.toggleBody=function(){
		var self =this;
		var i=0;
		$(this.id+" .tab-page>div").each(function(){
			var body = $(this);
			//当前的
			if(i == self.currentIndex){
				body.hide();
			}
			//选择中的
			if(i==self.selectedIndex) {
				
				body.show();
			}
			
			i++;
		});
	};

};

