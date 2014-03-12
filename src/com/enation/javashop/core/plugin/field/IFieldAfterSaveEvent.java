package com.enation.javashop.core.plugin.field;

import java.util.Map;

/**
 * 字段保存后事件
 * @author kingapex
 *
 */
public interface IFieldAfterSaveEvent {
	
	public void onAfterSave(Map goods,GoodsField field );
	
}
