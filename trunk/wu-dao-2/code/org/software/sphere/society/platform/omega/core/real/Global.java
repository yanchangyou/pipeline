package org.software.sphere.society.platform.omega.core.real;


public class Global extends RealNode {

	/**
	 * ��ȡeconomy
	 * 
	 */
	public Economy getEconomy(String economyName) {
		return (Economy) this.getChildRealNode(economyName);
	}
}
