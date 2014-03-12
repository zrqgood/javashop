package com.enation.app.base.core.service.solution;

/**
 * 解决方案导出器
 * @author kingapex
 *
 */
public interface ISolutionExporter {
	
	
	
	/**
	 * 解决方案导出
	 * @param name 要导出的名字
	 * @param exportData  是否要导出示例数据
	 * @param exportTheme 是否要导出模板
	 * @param exportProfile 是否要导出配置文件
	 * @param exportAttr 是否导出附件
	 */
	public void export(String name,boolean exportData,boolean exportTheme,boolean exportProfile,boolean exportAttr);
	
	
	
	
}
