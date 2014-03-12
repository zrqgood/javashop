package com.enation.app.base.widget.adv.jadadvance;

import java.util.List;

import com.enation.app.base.core.model.AdColumn;
import com.enation.app.base.core.model.Adv;
import com.enation.app.base.widget.abstractadv.AbstractAdvWidget;

public class JqueryAdAdvanceWidget extends AbstractAdvWidget {

	
	protected void execute(AdColumn adColumn, List<Adv> advList) {
		this.putData("adColumn", adColumn);
		this.putData("advList", advList);
		this.putData("width", adColumn.getWidth());
		this.putData("height", adColumn.getHeight());

	}

}
