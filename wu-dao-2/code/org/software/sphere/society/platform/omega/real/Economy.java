package org.software.sphere.society.platform.omega.real;

import org.software.sphere.society.platform.omega.data.node0X.String;

public class Economy extends RealNode {

	/**
	 * ��ȡmarket
	 * 
	 */
	public Market getMarket(String marketName) {
		return (Market) this.getChildRealNode(marketName);
	}
}
