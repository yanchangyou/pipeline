package org.software.sphere.society.platform.omega.real;

import org.software.sphere.society.platform.omega.data.node0X.String;

public class Global extends RealNode {

	/**
	 * ��ȡeconomy
	 * 
	 */
	public Economy getEconomy(String economyName) {
		return (Economy) this.getChildRealNode(economyName);
	}
}
