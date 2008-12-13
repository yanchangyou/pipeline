package org.software.sphere.society.platform.pipeline.core.real;

import org.software.sphere.society.platform.pipeline.core.data.node0X.String;

public class Economy extends RealNode {

	/**
	 * ªÒ»°market
	 * 
	 */
	public Market getMarket(String marketName) {
		return (Market) this.getNextRealNode(marketName);
	}
}
