package org.software.sphere.society.platform.omega.core.real;


public class Global extends RealNode {

	/**
	 * ªÒ»°economy
	 * 
	 */
	public Economy getEconomy(String economyName) {
		return (Economy) this.getChildRealNode(economyName);
	}
}
