package org.software.sphere.society.platform.omega.core.real;

import org.software.sphere.society.platform.omega.core.data.node0X.String;

public class Economy extends RealNode {

	/**
	 * ªÒ»°market
	 * 
	 */
	public Market getMarket(String marketName) {
		return (Market) this.getChildRealNode(marketName);
	}
}
