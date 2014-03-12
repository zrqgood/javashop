package com.enation.app.base.widget.adv.ad;

import java.util.List;

import com.enation.app.base.core.model.AdColumn;
import com.enation.app.base.core.model.Adv;
import com.enation.app.base.widget.abstractadv.AbstractAdvWidget;

public class AdWidget extends AbstractAdvWidget {

	
	protected void execute(AdColumn adColumn, List<Adv> advList) {
		String adwidth = adColumn.getWidth();
		String adheight = adColumn.getHeight();
		int atype = adColumn.getAtype();
		int a = (int)((advList.size()) * Math.random());
		this.putData("adv", advList.get(a));
		this.putData("adwidth", adwidth);
		this.putData("adheight", adheight);
		this.putData("atype", atype);

	}

}
