package com.enation.javashop.core.model.support;

import java.util.ArrayList;
import java.util.List;

import com.enation.javashop.core.model.AdjunctItem;

/**
 * 商品的一组配件
 * @author apexking
 *
 */
public class AdjunctGroup {
	private Integer couptype;  //优惠类型  
	private Integer minnum;  //最小购买量
	private Integer maxnum;  // 最大购买量
    private Double  coup;   //优惠额度
 

	public Double getCoup() {
		return coup;
	}

	public void setCoup(Double coup) {
		this.coup = coup;
	}

	public Integer getCouptype() {
		return couptype;
	}

	public void setCouptype(Integer couptype) {
		this.couptype = couptype;
	}

	public Integer getMaxnum() {
		return maxnum;
	}

	public void setMaxnum(Integer maxnum) {
		this.maxnum = maxnum;
	}

	public Integer getMinnum() {
		return minnum;
	}

	public void setMinnum(Integer minnum) {
		this.minnum = minnum;
	}
    
    
    
}
