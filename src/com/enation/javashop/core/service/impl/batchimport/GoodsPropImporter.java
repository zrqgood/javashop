package com.enation.javashop.core.service.impl.batchimport;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;

import com.enation.javashop.core.model.Attribute;
import com.enation.javashop.core.model.ImportDataSource;
import com.enation.javashop.core.service.batchimport.IGoodsDataImporter;

/**
 * 商品属性导入器
 * @author kingapex
 *
 */
public class GoodsPropImporter implements IGoodsDataImporter {
	protected final Logger logger = Logger.getLogger(getClass());
	public void imported(Object value, Element node,
			ImportDataSource importConfig, Map goods) {

		List<Attribute> list  = importConfig.getPropList();
		if(value==null) value="";
		
		int propindex = Integer.valueOf( node.getAttribute("propindex") );
		
		if(this.logger.isDebugEnabled()){
			logger.debug("开始导入商品属性["+propindex+"]...");
		}
		int i=1;
		for(Attribute attr: list){
			if(propindex == i) {
				
				if(this.logger.isDebugEnabled()){
					logger.debug("属性名为["+attr.getName()+"],值为["+value+"]");
				}
				
				//选择项
				if(attr.getType() >=3){
					String[] options  = attr.getOptionAr();
					if(options==null)continue;
					int index=0;
					for(String op:options){
						if( value.equals(op) ){
							goods.put("p"+i, index);
							if(this.logger.isDebugEnabled()){
								logger.debug("找到商品属性["+propindex+"]值为["+index+"]...");
							}
							break;
						}
						index++;
					}
				}else{//输入项
					if(this.logger.isDebugEnabled()){
						logger.debug("找到商品属性["+propindex+"]值为["+value+"]...");
					}
					goods.put("p"+i, value);
				}
				break;
			}
			
			i++;
			
		}
		if(this.logger.isDebugEnabled()){
			logger.debug("导入商品属性["+propindex+"]完成");
		}
	}


}
