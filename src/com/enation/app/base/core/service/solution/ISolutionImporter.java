package com.enation.app.base.core.service.solution;

/**
 * 解决方案导入器
 * @author kingapex
 *
 */
public interface ISolutionImporter {

	/**
	 * 根据一个解决方案zip包导入解决方案
	 * @param zipPath
	 */
	public void imported(String productid,String zipPath,boolean cleanZip);
	
	
}
