package org.software.sphere.society.platform.omega.core.real;


public class Economy extends ESOA {

	public Economy() {
	}

	/**
	 * ªÒ»°market
	 * 
	 */
	public Market getMarket(String marketName) {
		return (Market) this.context.getChild(marketName);
	}
}
