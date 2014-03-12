$.getJSONP("http://club.360buy.com/clubservice.aspx?callback=getIndexComments&method=SearchIndexComments",getIndexComments);
function getIndexComments(result){
	$(".column_zxpj").empty();
	$(".column_zxpj").append("<h2>最热评价</h2><ul></ul>");	
	var list = result.Comments;
	if (list.length > 0)
	{
		var content;		
		for (var element in list)
		{
		    var url = "http://club.360buy.com/repay/";
		    var thead = "";
			if (list[element].Pros != null)
			{
				content = list[element].Pros.length >= 82 ? list[element].Pros.substring(0, 82) + "..." : list[element].Pros;
			}
			else 
			{
				content = list[element].Content.length >= 82 ? list[element].Content.substring(0, 82) + "..." : list[element].Content;
			}
			if (list[element].ReferenceType == "Order")
			{
			    url = "http://club.360buy.com/bbsDetail/";
			    thead = "<span style='color:#ff9900;font-weight:bold'>[晒单]</span>"
			}			
			$(".column_zxpj ul").append("<li><h5>"+thead+"<a href='"+url+list[element].ReferenceId+"_"+list[element].Id+"_1.html' class='link_1'>"+list[element].Title+"</a></h5><div class='text1'><a href='http://www.360buy.com/product/"+list[element].ReferenceId+".html'><img src='http://img10.360buyimg.com/S5/"+list[element].ReferenceImage+"' /></a><a href='"+url+list[element].ReferenceId+"_"+list[element].Id+"_1.html'>"+content+"</a></div><div class='text2'><a href='http://club.360buy.com/person/"+list[element].UserId+"-1-1.html' class='float_Right link_1'><strong>"+list[element].UserId+"</strong></a><span style='color:#12A000;'>共"+list[element].ReplyCount+"条回复</span></div></li>");
		}
	}
}