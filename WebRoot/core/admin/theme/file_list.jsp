<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript" src="js/Explorer.js"> 
</script>
<script type="text/javascript">
$(function(){
	Explorer.init();
});
</script>
<div class="grid">

<div class="toolbar">

<ul>
	<li><a href="themeFile!list.do?themeid=${themeid }&folderName=${parent}&type=${type}">向上</a></li>
	<li><a href="themeFile!toNewFile.do?themeid=${themeid }&folderName=${folderName}&type=${type}" id="newFileBtn">新建文件</a></li>
	<li><a href="javascript:;" id="newFolderBtn">新建目录</a></li>
	<li><a href="javascript:;" id="uploadBtn">上传</a></li>
</ul>

<div style="clear: both;"></div>
</div>
<div>
当前路径：${folderName }
</div>


<table>

	<thead>
		<tr>
			<th width="200px">文件名称</th>
			<th width="200px">文件大小</th>
			<th width="200px" >最后修改时间</th>
			<th>操作</th>
		</tr>
	</thead>

	<tbody>
	<c:forEach items="${fileList}" var="file" >
		<tr>
			<td>
			<c:if test="${file.isfolder==0}"  >
			<img height="16" border="0" align="absmiddle" width="16" src="images/FileType/${file.ext }.gif">
			${file.name }
			</c:if>
			<c:if test="${file.isfolder==1}"  >
			<img height="16" border="0" align="absmiddle" width="16" src="images/FileType/folder.gif">
			<a href="themeFile!list.do?themeid=${themeid }&folderName=${folderName}${file.name}/&type=${type}" >${file.name }</a>
			</c:if>
			
			</td>
			<td>${file.size }</td>
			<td><html:dateformat pattern="yyyy-MM-dd hh:mm:ss" time="${file.lastmodify }"></html:dateformat></td>
			<td>
			<c:if test="${file.isfolder==0}"  >
				<a href="themeFile!toEdit.do?themeid=${themeid }&folderName=${folderName}&name=${file.name}&type=${type}" >编辑</a>
				&nbsp;&nbsp;
			</c:if>
			
			<a href="javascript:;" name="${file.name }" class="rename">改名</a>
			&nbsp;&nbsp;
			<a href="themeFile!delete.do?themeid=${themeid }&folderName=${folderName}&name=${file.name}&type=${type}" onclick="javascript:return confirm('确定删除此文件吗？删除后不可恢复');" class="del">删除</a>
			<c:if test="${file.isfolder==0}"  >
			&nbsp;&nbsp;
			<a href="javascript:;" name="${file.name }" class="move">移动</a>
			</c:if>
			</td>
		</tr>
	</c:forEach>	 
	</tbody>

</table>

<!-- 重命名对话框 -->
<div id="renameDlg">
<form method="POST" id="renameForm">
<input type="hidden" name="themeid" value="${themeid }" />
<input type="hidden" name="folderName" value="${folderName }" />
<input type="hidden" name="type" value="${type }" />

<div id="addcontent">
<input type="hidden" name="name" id="oldName" />
新文件名：<input type="text" name="newName" id="newName"  /> 
</div>
</form>
<div class="footContent" >
<div style="width: 200px; height: 40px; margin: 0pt auto;"
	class="mainFoot">
<table style="margin: 0pt auto; width: auto;">
	<tbody>
		<tr>
			<td><b class="save">
			<button class="submitBtn" id="renameBtn">保存</button>
			</b></td>
		</tr>
	</tbody>
</table>
</div>
</div> 
</div>

<!-- 新文件夹对话框 -->
<div id="newFolderDlg">
<form method="POST" id="newFolderForm">
<input type="hidden" name="themeid" value="${themeid }" />
<input type="hidden" name="folderName" value="${folderName }" />
<input type="hidden" name="type" value="${type }" />
<div id="addcontent">
名称：<input type="text" name="name" id="newFolderName"  /> 
</div>
</form>
<div class="footContent" >
<div style="width: 200px; height: 40px; margin: 0pt auto;"
	class="mainFoot">
<table style="margin: 0pt auto; width: auto;">
	<tbody>
		<tr>
			<td><b class="save">
			<button class="submitBtn" id="saveFolderBtn">保存</button>
			</b></td>
		</tr>
	</tbody>
</table>
</div>
</div> 
</div>

<!-- 上传对话框 -->
<div id="uploadDlg">
<form method="POST" id="uploadForm">
<input type="hidden" name="themeid" value="${themeid }" />
<input type="hidden" name="folderName" value="${folderName }" />
<input type="hidden" name="type" value="${type }" />
<div id="addcontent">
选择本地文件:<input type="file" name="file" id="file" />
</div>
</form>
<div class="footContent" >
<div style="width: 200px; height: 40px; margin: 0pt auto;"
	class="mainFoot">
<table style="margin: 0pt auto; width: auto;">
	<tbody>
		<tr>
			<td><b class="save">
			<button class="submitBtn" id="saveUploadBtn">保存</button>
			</b></td>
		</tr>
	</tbody>
</table>
</div>
</div> 
</div>

<!-- 移动话框 -->
<div id="moveDlg">
<form method="POST" id="moveForm">
<input type="hidden" name="themeid" value="${themeid }" />
<input type="hidden" name="folderName" value="${folderName }" />
<input type="hidden" name="type" value="${type }" />
<input type="hidden" name="name" id="mvName"  />
<div id="addcontent">
移动文件:<span id="mvNameSpan"></span><br/>
至<input type="text" name="newPath" id="newPath"  /><br/>
新位置前面不加'/'表示相对于当前位置，加'/'表示相对于根目录。
</div>
</form>
<div class="footContent" >
<div style="width: 200px; height: 40px; margin: 0pt auto;"
	class="mainFoot">
<table style="margin: 0pt auto; width: auto;">
	<tbody>
		<tr>
			<td><b class="save">
			<button class="submitBtn" id="saveMoveBtn">保存</button>
			</b></td>
		</tr>
	</tbody>
</table>
</div>
</div> 
</div>

</div>
