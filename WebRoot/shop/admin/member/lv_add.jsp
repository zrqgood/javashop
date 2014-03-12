<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<div class="input">
 <form class="validate" method="post" action="member!saveAddLv.do" name="theForm" id="theForm"  >
   <table cellspacing="1" cellpadding="3" width="100%" class="form-table">
     <tr>
       <th><label class="text">等级名称</label></th>
       <td width="27%"><input type="text" name="lv.name" id="name" maxlength="60" value="" dataType="string" required="true"/>       </td>
       <td width="58%" align="left"><div id="nameTip" style="width:80px;"></div></td>
     </tr>
     <tr>
       <th><label class="text">所需积分</label></th>
       <td width="27%"><input type="text" name="lv.point" id="name"  style="width:80px" value="0" dataType="int" required="true"/>       </td>
       <td width="58%" align="left"><div id="nameTip" style="width:80px;"></div></td>
     </tr>
     <tr>
       <th><label class="text">优惠百分比</label></th>
       <td  >
       <input type="text" name="lv.discount" id="discount" style="width:50px;" dataType="float" required="true"/>%&nbsp;&nbsp;
		</td>
       <td   align="left"><div id="default_lvTip"  >如果输入80，表示该会员等级以销售价80%的价格购买。</div></td>
     </tr>
     
     <tr>
       <th><label class="text">默认等级</label></th>
       <td  >
       <input type="radio" name="lv.default_lv"  value="0" checked />否&nbsp;&nbsp;<input type="radio" name="lv.default_lv"  value="1" />是
       </td>
       <td   align="left"><div id="default_lvTip" style="width:80px;"></div></td>
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
	 $("form.validate").validate();
 })

</script>
 </div>
