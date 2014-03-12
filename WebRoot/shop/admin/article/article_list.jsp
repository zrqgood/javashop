<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/Article.js"></script>
 
<form action="" id="articlefrom" method="post">

<div class="grid" id="gridDiv">


	<div class="toolbar">
	<ul>
	<li><a href="article!add.do?cat_id=${cat_id }">添加</a></li>
	<li><a href="javascript:;" id="delBtn">删除</a></li>
	</ul>
	<div style="clear:both"></div>
	</div>
	
<grid:grid  from="articleList">

	<grid:header>
	<grid:cell align="left"><input type="checkbox" id="toggleChk" />&nbsp;标题</grid:cell> 
	<grid:cell width="200px" sort="create_time">添加日期</grid:cell> 
	<grid:cell  width="200px">操作</grid:cell> 
	</grid:header>

  <grid:body item="article">
        <grid:cell  align="left"><input type="checkbox" name="id" value="${article.id }" />${article.title } </grid:cell>
         <grid:cell><html:dateformat pattern="yyyy-MM-dd hh:mm:ss" time="${article.create_time }"></html:dateformat> </grid:cell> 
           <grid:cell align="center"> 
           <a  href="article!edit.do?article_id=${article.id }" >
           	<img class="modify" src="images/transparent.gif" >
           </a>
            </grid:cell> 
  </grid:body>  
  
</grid:grid>  

</div>

</form>

