package com.enation.javashop.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.eop.sdk.utils.UploadUtil;
import com.enation.framework.util.StringUtil;
import com.enation.javashop.core.model.SpecValue;
import com.enation.javashop.core.model.Specification;
import com.enation.javashop.core.service.ISpecManager;
import com.enation.javashop.core.service.ISpecValueManager;

/**
 * 规格管理
 * @author kingapex
 *2010-3-7上午11:19:20
 */
public class SpecManager extends BaseSupport<Specification>  implements ISpecManager {
	private ISpecValueManager specValueManager;
	
	/**
	 * 此版本未实现规格值排序功能
	 */
	@Transactional(propagation = Propagation.REQUIRED)  
	
	public void add(Specification spec, List<SpecValue> valueList) {
		
		this.baseDaoSupport.insert("specification", spec);
		Integer specId= this.baseDaoSupport.getLastId("specification");
		for(SpecValue value : valueList){
			value.setSpec_id(specId);
			value.setSpec_type(spec.getSpec_type());
			String path  = value.getSpec_image();
			if(path!=null){
				path = path.replaceAll(EopSetting.IMG_SERVER_DOMAIN+ EopContext.getContext().getContextPath(), EopSetting.FILE_STORE_PREFIX);
			}
			value.setSpec_image(path);
			specValueManager.add(value);
		}
		
	}

	
	
	/**
	 * 
	 */
	public boolean checkUsed(Integer[] idArray){
		if(idArray==null) return false;
		
		String idStr = StringUtil.arrayToString( idArray,",");
		String sql  ="select count(0)  from  goods_spec where spec_id in (-1,"+idStr+")";
		
		int count  = this.baseDaoSupport.queryForInt(sql);
		if(count>0)
			return true;
		else
			return false;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)  
	public void delete(Integer[] idArray) {
		
		String idStr = StringUtil.arrayToString( idArray,",");
		String sql ="delete from specification where spec_id in (-1,"+idStr+")";
		this.baseDaoSupport.execute(sql);
		
		sql="delete from spec_values where spec_id in (-1,"+idStr+")";
		this.baseDaoSupport.execute(sql);
		
		sql="delete from goods_spec where spec_id in (-1,"+idStr+")";
		this.baseDaoSupport.execute(sql);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)  
	public void edit(Specification spec, List<SpecValue> valueList) {
		String sql ="delete from spec_values where spec_id=?";
		this.baseDaoSupport.execute(sql, spec.getSpec_id());
		this.baseDaoSupport.update("specification", spec, "spec_id="+spec.getSpec_id());
		for(SpecValue value : valueList){
			value.setSpec_id(spec.getSpec_id());
			value.setSpec_type(spec.getSpec_type());
			String path  = value.getSpec_image();
			if(path!=null){
				path = path.replaceAll(EopSetting.IMG_SERVER_DOMAIN+ EopContext.getContext().getContextPath(), EopSetting.FILE_STORE_PREFIX);
			}
			value.setSpec_image(path);			
			specValueManager.add(value);
		}		
	}

	@Transactional(propagation = Propagation.REQUIRED)  
	public List list() {
		String sql ="select * from specification";
		return this.baseDaoSupport.queryForList(sql);
	}

	public Map get(Integer spec_id){
		String sql ="select * from specification where spec_id=?";
		return this.baseDaoSupport.queryForMap(sql, spec_id);
	}
	
	public ISpecValueManager getSpecValueManager() {
		return specValueManager;
	}

	public void setSpecValueManager(ISpecValueManager specValueManager) {
		this.specValueManager = specValueManager;
	}

}
