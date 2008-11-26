package org.software.matter.molecule.platform.pipeline.core.element.soa;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Global extends SOA {

	private Map economyMap = new HashMap();

	/**
	 * ���economy
	 * 
	 * @param economy
	 */
	public void addEconomy(Economy economy) {
		economyMap.put(economy.getName(), economy);
	}

	/**
	 * ��ȡeconomy
	 * 
	 */
	public Economy getEconomy(String economyName) {
		return (Economy) economyMap.get(economyName);
	}
	
	public String toString() {
		return new ToStringBuilder(this).toString() + economyMap;
	}
}
