package org.software.matter.molecule.platform.pipeline.core.element.soa;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Economy extends SOA {

	private Map marketMap = new HashMap();

	/**
	 * ÃÌº”market
	 * 
	 * @param market
	 */
	public void addMarket(Market market) {
		marketMap.put(market.getName(), market);
	}

	/**
	 * ªÒ»°market
	 * 
	 */
	public Market getMarket(String marketName) {
		return (Market) marketMap.get(marketName);
	}
	
	public String toString() {
		return new ToStringBuilder(this).toString() + marketMap;
	}
}
