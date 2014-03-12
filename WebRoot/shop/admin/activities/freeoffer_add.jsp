<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<div class="input">
 <form class="validate" method="post" action="freeOffer!save.do" name="theForm" id="theForm" enctype="multipart/form-data">
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
     <tr>
       <th><label class="text">赠品名称：</label></th>
       <td><input type="text" name="freeOffer.fo_name" maxlength="60" value=""  dataType="string" required="true" />       </td>
      </tr>
      <tr>
       <th><label class="text">赠品分类：</label></th>
       <td>
       <select name="freeOffer.fo_category_id" >
       	<c:forEach var="item" items="${categoryList }">
       	  <option value="${item.cat_id }">${item.cat_name }</option>
       	</c:forEach>
       </select>
       </td>
      </tr>
	<tr>
		<th><label class="text">是否发布：</label></th>
		<td><input type="radio" name="freeOffer.publishable" value="0"
			checked /> 是&nbsp;&nbsp; <input type="radio"
			name="freeOffer.publishable" value="1" /> 否</td>
	</tr>
	<tr>
		<th><label class="text">做为推荐赠品：</label></th>
		<td><input type="radio" name="freeOffer.recommend" value="0"
			 /> 是&nbsp;&nbsp; <input type="radio"
			name="freeOffer.recommend" value="1" checked/> 否</td>
	</tr>
	<tr>
       <th><label class="text">起始时间：</label></th>
       <td  ><input type="text" name="mstartdate" maxlength="60" value=""  dataType="date" required="true" class="dateinput"/></td>
      </tr>
      <tr>
       <th><label class="text">终止时间：</label></th>
       <td  ><input type="text" name="menddate" maxlength="60" value=""  dataType="date" required="true" class="dateinput"/></td>
      </tr>
      <tr>
       <th><label class="text">允许兑换<br/>的会员等级：</label></th>
       <td>
       <c:forEach var="item" items="${member_lvList }">
       <input type="checkbox" name="lv_ids" id="lvids_${item.lv_id }" value="${item.lv_id }" />${item.name }&nbsp;
       </c:forEach>
       </td>
      </tr>
      <tr>
       <th><label class="text">限购数量：</label></th>
       <td  ><input type="text" name="freeOffer.limit_purchases" maxlength="60" id="limit_purchases" required="true"  dataType="int"  fun="chknum"/></td>
      </tr>      
      <tr>
       <th><label class="text">价格：</label></th>
       <td  ><input type="text" name="freeOffer.price" maxlength="60" value="" required="true"   dataType="float"  /></td>
      </tr>
      <tr>
       <th><label class="text">简介：</label></th>
       <td  ><input type="text" name="freeOffer.synopsis" maxlength="60" value=""  /></td>
      </tr>

      <tr>
       <th><label class="text">赠品图片：</label></th>
       <td><input type="file" name="pic" id="pic" size="45"/>
           <span class="notice-span"  id="warn_pic">请上传图片</span></td>
      </tr>
      <tr>
       <th><label class="text">兑换所需积分：</label></th>
       <td  ><input type="text" name="freeOffer.score" maxlength="60"  required="true"   dataType="float"  /></td>
      </tr>
      <tr>
       <th><label class="text">重量：</label></th>
       <td  ><input type="text" name="freeOffer.weight" maxlength="60"  required="true"  dataType="float"  /></td>
      </tr>
      <tr>
       <th><label class="text">库存：</label></th>
       <td  ><input type="text" name="freeOffer.repertory" maxlength="60"  required="true"   dataType="int" /></td>
      </tr>
	<tr>
       <th><label class="text">详细说明</label></th>
       <td>
        <textarea id="descript" name="freeOffer.descript"></textarea>
       </td>
     </tr>
 
   </table>
   <div class="submitlist" align="center">
 <table>
 <tr><td >
  <input name="submit" type="submit"	  value=" 确    定   " class="submitBtn" />
   </td>
   </tr>
 </table>
</div>
 </form>
 <script type="text/javascript">
$(function(){
	 $('#descript' ).ckeditor( );
	 $("form.validate").validate();
	 function chknum(){
	 	if( $("#limit_purchases").val()>"0" ){
	 		return true;
	 	}
	 	else{ 
	 		return "限购数量最小为1";
	 	}
	 }	
});
</script>
</div>