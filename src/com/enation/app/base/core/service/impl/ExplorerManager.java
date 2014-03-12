package com.enation.app.base.core.service.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.FileFileFilter;

import com.enation.app.base.core.model.FileNode;
import com.enation.app.base.core.service.IExplorerManager;
import com.enation.framework.util.FileUtil;

/**
 * 资源管理器
 * @author kingapex
 * 2010-8-18下午06:14:44
 */
public class ExplorerManager implements IExplorerManager {
	
	private String rootPath;
	public ExplorerManager(String rootPath){
		if(rootPath==null) throw new IllegalArgumentException("param rootPath is NULL");
		this.rootPath = rootPath;
	}
	
	
	public void add(FileNode node) throws IOException {
		if(node==null)  throw new  IllegalArgumentException("param FileNode is NULL");
		
		String filePath  = rootPath +"/"+node.getName();
		File file  = new File(filePath);
		
		if(file.exists()){
			throw new RuntimeException("文件"+node.getName()+"已存在");
		}
		
		
		if(node.getIsfolder()==1){
			file.mkdirs();
		}else{
			file.createNewFile();
			FileUtil.write(filePath, node.getContent());
		}
	}

	
	public void delete(String name) {
		FileUtil.delete(rootPath+ name);
	}

	public void edit(FileNode node) {
		FileUtil.write(rootPath+node.getName(), node.getContent());
	}

	public List<FileNode> list(FileFilter filter) {
		List<FileNode> list  = new ArrayList<FileNode>();
		File file  = new File(rootPath);
		
		File[] files  = null;
		if(filter!=null){
			files =file.listFiles(filter);
		}else{
			files =file.listFiles();
		}
		if(files==null) return list;
		for(File f :files){
			
			FileNode node  = this.fileToNode(f);
			list.add(node);
		}
		return list;
	}
	
	
	public void move(String name,String oldFolder, String newFolder) {
		String oldRoot = this.rootPath;
		if(newFolder.startsWith("/") && !oldFolder.equals("/")){//相对根，原目录不是根，则将rootPath置为根。
			 rootPath = rootPath.replaceAll(oldFolder, "");
		}

		if(newFolder.startsWith("/") && oldFolder.equals("/")){//相对根，原目录是根，将新目录的/开头去掉
			newFolder= newFolder.substring(1,newFolder.length());
		}
		
		if(!newFolder.endsWith("/")){ //如果新目录末尾无/加/
			newFolder = newFolder+"/";
			
		}
		String target= rootPath+newFolder+name;
		if(!new File(rootPath+newFolder).exists() ){
			throw new RuntimeException(newFolder+" 不存在");
		}
		
		FileUtil.copyFile(oldRoot+name, target);
		FileUtil.delete(oldRoot+name);
		
	}

	public void rename(String oldname, String newname) {
		File file  = new File(rootPath +oldname);
		File newFile  = new File(rootPath +newname);
		boolean result = file.renameTo(newFile);
	}


	public FileNode get(String name) {
		File file  = new File(rootPath  +name);
		FileNode node  = this.fileToNode(file);
		node.setContent( FileUtil.read(rootPath+name, "UTF-8") );
		return node;
	}
	
	private FileNode fileToNode(File f){
		FileNode node  = new FileNode();
		node.setName(f.getName());
		node.setIsfolder(f.isDirectory()?1:0);
		node.setSize(f.length());
		node.setLastmodify(f.lastModified());
		if(node.getIsfolder()==0){
			node.setExt( FileUtil.getFileExt(node.getName()).toLowerCase() );
//			node.setContent( )
		}
		return node;
	}


	public void upload(File file, String fileFileName) {
		String filePath= this.rootPath+fileFileName;
		File temp  = new File(filePath);
		if(temp.exists()) throw new RuntimeException("文件"+ fileFileName+"已经存在"); 
		FileUtil.createFile(file, filePath);
	}

}
