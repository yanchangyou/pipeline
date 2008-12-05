package org.software.matter.molecule.platform.pipeline.core.element.soa;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Global extends SOA {

	public Global() {
	}

	/**
	 * ªÒ»°economy
	 * 
	 */
	public Economy getEconomy(String economyName) {
		return (Economy) this.context.getChild(economyName);
	}
	
	public String toString() {
		return new ToStringBuilder(this).toString();
	}
}
