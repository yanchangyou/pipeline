package org.software.sphere.society.platform.omega.core.real;


public class Economy extends RealNode {

	/**
	 * ��ȡmarket
	 * 
	 */
	public Market getMarket(String marketName) {
		return (Market) this.getChildRealNode(marketName);
	}
}
