package com.enation.javashop.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.enation.javashop.core.model.support.Adjunct;
import com.enation.javashop.core.model.support.AdjunctGroup;
import com.enation.javashop.core.model.support.SpecJson;

public abstract class GoodsUtils {
	
	
	public static List getSpecList(String specString){
		if(specString==null || specString.equals("[]") ||specString.equals("") ){
			return new ArrayList();
		} 
		JSONArray j1 = JSONArray.fromObject(specString);
		List<SpecJson> list =(List) JSONArray.toCollection(j1, SpecJson.class);		
		return list;
	}
	
	
	/**
	 * 将一个
	 * 
	 * @param adjString
	 * @return
	 */
	public static AdjunctGroup converAdjFormString(String adjString) {
		if (adjString == null) {
			return null;
		}
		Map classMap = new HashMap();

		classMap.put("adjList", Adjunct.class);
		JSONObject j1 = JSONObject.fromObject(adjString);
		AdjunctGroup adjunct = (AdjunctGroup) JSONObject.toBean(j1,
				AdjunctGroup.class, classMap);
		return adjunct;
	}


 
		
}
