package org.software.matter.molecule.platform.pipeline.core.element.soa;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Economy extends SOA {

	public Economy() {
	}

	/**
	 * ªÒ»°market
	 * 
	 */
	public Market getMarket(String marketName) {
		return (Market) this.context.getChild(marketName);
	}
	
	public String toString() {
		return new ToStringBuilder(this).toString();
	}
}
