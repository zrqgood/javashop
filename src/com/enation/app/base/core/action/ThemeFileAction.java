package com.enation.app.base.core.action;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

import com.enation.app.base.core.model.FileNode;
import com.enation.app.base.core.service.IExplorerManager;
import com.enation.app.base.core.service.impl.ExplorerManager;
import com.enation.app.base.core.service.impl.StyleFileFilter;
import com.enation.app.base.core.service.impl.TplFileFilter;
import com.enation.eop.resource.IThemeManager;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.framework.action.WWAction;
import com.enation.framework.util.StringUtil;

public class ThemeFileAction extends WWAction {

	private IExplorerManager explorerManager;
	private IThemeManager themeManager;
	private int themeid;
	private String name;
	private String folderName;
	private String content;
	private String newName;
	private String parent;
	private File file;
	private String fileFileName;
	private String newPath;
	private String type;

	private List<FileNode> fileList;

	private void initExplorer() {
		
		folderName = StringUtil.isEmpty(folderName)?"":folderName;
		String themePath = themeManager.getTheme(themeid).getPath();
		String respath="";
		if(EopSetting.RESOURCEMODE.equals("1")){
			respath = EopSetting.IMG_SERVER_PATH;
		}else{
			respath= EopSetting.EOP_PATH;
		}
		String folderPath  = EopSetting.EOP_PATH
				+ EopContext.getContext().getContextPath() + "/themes/"
				+ themePath + folderName;
		String styleFldPath  = EopSetting.IMG_SERVER_PATH
		+ EopContext.getContext().getContextPath() + "/themes/"
		+ themePath + folderName;
		if("style".equals(type)){
			explorerManager = new ExplorerManager(styleFldPath);
		}else{
			explorerManager = new ExplorerManager(folderPath);
		}
		if(folderName.equals("") || folderName.equals("/")){
			parent= "/";
		}else{
			parent =folderName.substring(0, folderName.lastIndexOf('/')-1);
			parent = parent.substring(0,parent.lastIndexOf('/'))+"/";
		}
	}

	public String list() {
		this.initExplorer();
		FileFilter filter =null;
		if("file".equals(type)){
			filter = new TplFileFilter();
		}
		if("style".equals(type)){
			filter= new StyleFileFilter();
		}
		fileList = this.explorerManager.list(filter);
		return "list";
	}

	public String delete() {
		try {
			this.initExplorer();
			this.explorerManager.delete(name);
			this.msgs.add("文件删除成功");
			this.urls.put("返回目录", "themeFile!list.do?themeid="+ themeid +"&folderName="+folderName+"&type="+type);
		} catch (Exception e) {
			this.urls.put("返回目录", "themeFile!list.do?themeid="+ themeid +"&folderName="+folderName+"&type="+type);
			this.msgs.add(e.getMessage());
			
		}
		return this.MESSAGE;
	}

	public String toNewFile(){
		
		return "new_file";
	}
	
	public String addFile() {
		if(StringUtil.isEmpty(folderName)) folderName="/";
		try {
			this.initExplorer();
			FileNode node = new FileNode();
			node.setContent(content);
			node.setName(name);
			node.setIsfolder(0);
			this.explorerManager.add(node);
			this.msgs.add("文件创建成功");
			this.urls.put("返回目录", "themeFile!list.do?themeid="+ themeid +"&folderName="+folderName+"&type="+type);
		} catch (Exception e) {
			this.urls.put("返回目录", "themeFile!list.do?themeid="+ themeid +"&folderName="+folderName+"&type="+type);
			this.msgs.add(e.getMessage());
			
		}
		return this.MESSAGE;
	}

	public String addFolder() {
		try {
			this.initExplorer();
			FileNode node = new FileNode();
			node.setName(name);
			node.setIsfolder(1);
			this.explorerManager.add(node);
			this.json = "{result:1}";
		} catch (Exception e) {
			this.json = "{result:0,message:'" + e.getMessage() + "'}";
		}
		return this.JSON_MESSAGE;

	}

	public String toEdit(){
		this.initExplorer();
		FileNode node  = this.explorerManager.get(name);
		this.name = node.getName();
		this.content = node.getContent();
		return "edit_file";
	}
	
	
	public String edit() {
		if(StringUtil.isEmpty(folderName)) folderName="/";
		try {
			
			this.initExplorer();
			FileNode node = new FileNode();
			node.setContent(content);
			node.setName(name);
			this.explorerManager.edit(node);
			this.json = "{result:1}";
			this.msgs.add("文件修改成功!");
			this.urls.put("目录", "themeFile!list.do?themeid="+ themeid +"&folderName="+folderName+"&type="+type);
		} catch (Exception e) {
			this.urls.put("目录", "themeFile!list.do?themeid="+ themeid +"&folderName="+folderName+"&type="+type);
			this.msgs.add(e.getMessage());
			
		}
		return this.MESSAGE;
	}

	public String rename() {
		try {
			this.initExplorer();
			this.explorerManager.rename(name, newName);
			this.json = "{result:1}";
		} catch (Exception e) {
			this.json = "{result:0,message:'" + e.getMessage() + "'}";
		}
		return this.JSON_MESSAGE;
	}
	
	
	public String upload(){
		try{
			this.initExplorer();
			this.explorerManager.upload(file, fileFileName);
			this.json = "{result:1}";
		} catch (Exception e) {
			this.json = "{result:0,message:'" + e.getMessage() + "'}";
		}		
		return this.JSON_MESSAGE;
	}
	
	public String move(){
		try{
			this.initExplorer();
			this.explorerManager.move(name,folderName, newPath);
			this.json = "{result:1}";
		} catch (Exception e) {
			this.json = "{result:0,message:'" + e.getMessage() + "'}";
		}			
		return this.JSON_MESSAGE;
	} 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFolderName() {
		if(StringUtil.isEmpty(folderName)) folderName="/";
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public List<FileNode> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileNode> fileList) {
		this.fileList = fileList;
	}

	public IThemeManager getThemeManager() {
		return themeManager;
	}

	public void setThemeManager(IThemeManager themeManager) {
		this.themeManager = themeManager;
	}

	public int getThemeid() {
		return themeid;
	}

	public void setThemeid(int themeid) {
		this.themeid = themeid;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getNewPath() {
		return newPath;
	}

	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}

	public IExplorerManager getExplorerManager() {
		return explorerManager;
	}

	public void setExplorerManager(IExplorerManager explorerManager) {
		this.explorerManager = explorerManager;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
