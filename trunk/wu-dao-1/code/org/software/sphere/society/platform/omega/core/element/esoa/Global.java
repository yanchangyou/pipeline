package org.software.sphere.society.platform.omega.core.element.esoa;


public class Global extends ESOA {

	public Global() {
	}

	/**
	 * ªÒ»°economy
	 * 
	 */
	public Economy getEconomy(String economyName) {
		return (Economy) this.context.getChild(economyName);
	}
}
