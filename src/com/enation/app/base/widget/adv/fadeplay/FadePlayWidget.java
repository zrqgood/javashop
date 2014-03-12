package com.enation.app.base.widget.adv.fadeplay;

import java.util.List;

import com.enation.app.base.core.model.AdColumn;
import com.enation.app.base.core.model.Adv;
import com.enation.app.base.widget.abstractadv.AbstractAdvWidget;

public class FadePlayWidget extends AbstractAdvWidget {

	@Override
	protected void execute(AdColumn adColumn, List<Adv> advList) {
		this.putData("advlist", advList);
	}

}
