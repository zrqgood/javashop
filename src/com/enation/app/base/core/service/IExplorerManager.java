package com.enation.app.base.core.service;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;

import com.enation.app.base.core.model.FileNode;

/**
 * 资源管理器接口
 * @author kingapex
 * 2010-8-18下午12:03:03
 */
public interface IExplorerManager {
	
	/**
	 * 列取某个文件夹的文件列表
	 * @return
	 */
	public List<FileNode> list(FileFilter filter); 
	
	
	/**
	 * 获取一个文件的详细
	 * @param name
	 * @return
	 */
	public FileNode get(String name);
	
	
	
	
	/**
	 * 新增一个文件 或文件夹
	 * @param node
	 */
	public void add(FileNode node) throws IOException;
	
	
	/**
	 * 修改一个文件的内容，必须是文件属性
	 * @param node
	 */
	public void edit(FileNode node);
	
	
	
	
	/**
	 * 重命名一个文件或文件夹
	 * @param olename
	 * @param newname
	 */
	public void rename(String oldname,String newname);
	
	
	
	
	/**
	 * 移动某个文件至新文件夹
	 * @param name
	 * @param newFolder
	 */
	public void move(String name,String oldFolder,String newFolder);
	
	
	
	
	
	/**
	 * 删除一个文件或文件夹
	 * @param name
	 */
	public void delete(String name);
	
	
	/**
	 * 上传新文件
	 * @param file
	 * @param fileFileName
	 */
	public void upload(File file,String fileFileName);
	
	
}
