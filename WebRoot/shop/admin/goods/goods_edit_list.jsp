<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript"  >
loadScript("js/Goods.js");
</script>

<div class="grid">
<form action="goods!list.do?is_edit=true" method="get">
	<div class="toolbar" style="height:auto;">
	<div style="float:left;height:25px;">
		<ul>
			<li><a href="goods!add.do">添加</a></li>
			<li><a href="javascript:;" id="delBtn">删除</a></li>
			<li><a href="goods!trash_list.do">回收站</a></li>
			<li><a href="javascript:;" id="saveBtn">保存</a></li>
			<li><a href="goods!list.do" >返回列表</a></li>
		</ul>
	</div> 
		<div style="margin-left:10px">&nbsp;&nbsp;&nbsp;&nbsp;商品名称：<input type="text" style="width:90px" name="name" value="${name }"/>&nbsp;&nbsp;&nbsp;&nbsp;商品编号：<input type="text" style="width:90px" name="sn" value="${sn }"/>&nbsp;&nbsp;&nbsp;&nbsp;<span id="searchCat"></span>&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="submit" value="搜索"></div>
		<div style="clear:both"></div>
	</div>
</form>	
<form id="gridform">
<grid:grid  from="webpage">

	<grid:header>
		<grid:cell  width="50px"><input type="checkbox" id="toggleChk"/></grid:cell> 
		<grid:cell sort="sn" width="150px">商品编号</grid:cell> 
		<grid:cell sort="name" width="250px">商品名称</grid:cell>
		<grid:cell sort="cat_id"  width="150px">分类</grid:cell> 
		<grid:cell sort="price" width="100px">销售价格</grid:cell>
		<grid:cell sort="store" width="100px">库存</grid:cell>
		<grid:cell sort="market_enable" width="100px">上架</grid:cell>
		<grid:cell  width="50px">操作</grid:cell> 
	</grid:header>


  <grid:body item="goods" >
  
  		<grid:cell><input type="checkbox" name="id" value="${goods.goods_id }" />${goods.goods_id }
  			<input type="hidden" name="goodsidArray" value="${goods.goods_id }" />
  		</grid:cell>
        <grid:cell>${goods.sn }</grid:cell>
        <grid:cell><div ><input type="text" class="editinput" value="${goods.name }" name="name" autocomplete="off" style="width:90%"  /></div></grid:cell> 
        <grid:cell><div catid="${goods.cat_id }" class="catwrapper"></div></grid:cell> 
        <grid:cell><input type="text" class="editinput" value="<fmt:formatNumber value="${goods.price}" type="currency" pattern="0.00"/>" name="price" autocomplete="off" style="90%"  /> </grid:cell> 
        <grid:cell><div><input type="text" name="store" value="${goods.store}" /></div></grid:cell> 
        <grid:cell><div><input type="checkbox" class="editinput market_enable"  <c:if test="${goods.market_enable==1}" >checked="true"</c:if> /><input type="hidden" name="market_enables" value="${goods.market_enable }" /></div></grid:cell>
  
        <grid:cell>
        <a href="goods!edit.do?goods_id=${goods.goods_id }" > 
      	 <img class="modify" src="../shop/admin/images/transparent.gif" > 
        </a>
        </grid:cell> 
         
  </grid:body>
  
</grid:grid>
</form>
<div style="clear:both;padding-top:5px;"></div>
</div>
<script type="text/javascript">
function checkint(val){   return parseInt(val) == val;};
function checkfloat(val){    return parseFloat(val) == val;};
var ListEditor={
	init:function(){
		var self = this;
		$.ajax({
			url:basePath+'goods!getCatTree.do?ajax=yes',
			type:"get",
			dataType:"html",
			success:function(html){
				$("div.catwrapper").append(html);
				var serachCatSel =$(html).appendTo("#searchCat");
				serachCatSel.removeClass("editinput").attr("name","catid");
				serachCatSel.children(":first").before("<option value=\"\" selected>全部类别</option>");
				<c:if test="${catid!=null}">serachCatSel.val(${catid})</c:if>
				$("select[name=catidArray]").each(function(){
					$(this).val( $(this).parent().attr("catid") );
				});
				self.bindevent();
			},
			error:function(){
				alert("获取分类树出错");
			}
		});

	},
	bindevent:function(){
		var self = this;
		$(".editinput").change(function(){
			$(this).parent().css({padding:'2px',background:'#B5EDBC'});
			$(this).parents("tr").attr("changed","yes");
		});
		$("#saveBtn").click(function(){	
			self.save();
		});		
		$("input.market_enable").click(function(){
			if($(this).attr("checked")){
				$(this).next().val(1);
			}else{
				$(this).next().val(0);
			}
		});		
	}
	,
	checkprice:function(){
		var result = true;
		$("[name='price']").each(function(){
			if (! checkfloat( $(this).val() ) ){
				result =false;
			}
		});
		return result;	
	},
	checkstore:function(){
		var result = true;
		$("[name='store']").each(function(){
			if (! checkint( $(this).val() ) ){
				result =false;
			}
		});
		return result;	
	},	
	save:function(){

		if(!this.checkprice()){
			alert("价格必须是数字");
			return ;
		}
		if(!this.checkstore()){
			alert("库存必须是数字");
			return ;
		}
		$.Loading.show("正在保存数据...");
		$("#gridform").ajaxSubmit({
			url:basePath+"goods!batchEdit.do?ajax=yes",
			type:"POST",
			dataType:"json",
			success:function(result){
				if(result.result==1){
					$.Loading.hide();
					alert("保存成功");
					$(".editinput").parent().css({padding:'0',background:'transparent'});
				}else{
					$.Loading.hide();
					alert("保存失败");
				}
			},
			error:function(){
				$.Loading.hide();
				alert("批量修改错误");
			}
			
		});
	}
};
$(function(){
	ListEditor.init();
	EopPager.onLoad= function(){
		ListEditor.init();
	};
});

</script>
