/**
 * Google Map - 谷歌地图嵌入插件
 * @requires jQuery v1.2 or above
 * @author lzy
 * http://www.enation.cn
 * Copyright (c) 2010 enaiton
 * Version: 1.0
 * Catlog:
 */
/**
 * 使用说明:
 * $("#wrapper").showMap({...});
 * 给定一个区域，Googme Map显示在显示在里面
 * 选项：
 * address:要定位的地址：如北京市昌平区龙德紫金
 * content:点击定位点后，弹出的信息内容，支持html
 * title:定位点的提示信息
 * toolDiv:工具栏容器的ID，为空则不显示
 * zoom:显示比例
 */
(function($) {                   
	$.fn.showMap = function(o) {
		var directionDisplay;
		var directionsService = new google.maps.DirectionsService();
		var geocoder;
		var map;
		
	    o = $.extend({
	        address: "",
			content: "",
			title:"",
			toolDiv:undefined,
			zoom:15
	    }, o || {});
		
		return this.each(function() {
			var that = this;
			if(o.toolDiv){
				$("#"+o.toolDiv).append(
					"<table style='font-size:12px;width:100%;height:100%'>"+
					"<tr><td>输入您所在的位置：</td><td><input id='gmStart' type='text' style='width:270px;'></input>"+
					"<input id='gmSearch' type='button' value='看看开车怎么走'></input>"+
					"</td><td width='120px'><a target='_blank' href='http://maps.google.com/maps?f=q&source=s_q&hl=zh-CN&q="+o.address+"&hnear="+o.address+
					"&z="+o.zoom+"'>查看完整的谷歌地图</a></td></tr></table>");
			}
			$("#gmSearch").click(function(){
				calcRoute($("#gmStart").val(),o.address);
			});
			this.geocoder = new google.maps.Geocoder();
			if (this.geocoder) {
				this.geocoder.geocode({'address': o.address},function(results, status) {
					if (status == google.maps.GeocoderStatus.OK) {
						var myLatlng = results[0].geometry.location;
						var myOptions = {
							zoom:o.zoom,
							mapTypeId: google.maps.MapTypeId.ROADMAP,
							center: myLatlng
						}
						this.map = new google.maps.Map(that, myOptions);
						this.directionsDisplay = new google.maps.DirectionsRenderer();
						this.directionsDisplay.setPanel(document.getElementById("directionsPanel"));					
						this.directionsDisplay.setMap(this.map);			
						
						var infowindow = new google.maps.InfoWindow({content: o.content});
						var marker = new google.maps.Marker({
							position: myLatlng,
							map: this.map,
							title:o.title
						});
						google.maps.event.addListener(
							marker, 'click', function() {
								infowindow.open(this.map,marker);
						});
					}
				});
			}
			
			function calcRoute(start,end) {
				var request = {
					origin:start, 
					destination:end,
					travelMode: google.maps.DirectionsTravelMode.DRIVING
				};
				directionsService.route(request, function(response, status) {
				  if (status == google.maps.DirectionsStatus.OK) {
					directionsDisplay.setDirections(response);
				  }
				});
			}
		});
	}
})(jQuery);