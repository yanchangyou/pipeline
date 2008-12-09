package org.software.sphere.society.platform.omega.core.real;

import org.software.sphere.society.platform.omega.core.data.node0X.String;

public class Global extends RealNode {

	/**
	 * ªÒ»°economy
	 * 
	 */
	public Economy getEconomy(String economyName) {
		return (Economy) this.getChildRealNode(economyName);
	}
}
