package com.enation.app.base.widget.counter;

import java.io.File;
import java.util.Map;

import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.widget.AbstractWidget;
import com.enation.framework.util.FileUtil;
import com.enation.framework.util.StringUtil;

/**
 * 计数器挂件
 * @author kingapex
 * 2010-8-15下午07:00:35
 */
public class CounterWidget extends AbstractWidget {

	@Override
	protected void config(Map<String, String> params) {

	}

	@Override
	protected void display(Map<String, String> params) {
		String counterPath  =EopSetting.EOP_PATH+ EopContext.getContext().getContextPath() +"/counter.txt";
		File file  = new File(counterPath);
		try{
			if(!file.exists()) file.createNewFile();
		}catch(Exception e){
			e.printStackTrace();
		}
		String count =  FileUtil.read(counterPath, "UTF-8");
		if(StringUtil.isEmpty(count)) count="0";
		count = count.replaceAll("\n", "");
		FileUtil.write(counterPath, ""+ (Integer.valueOf(count) +1)) ;
		this.putData("count", (Integer.valueOf(count) +1));
	}

}
