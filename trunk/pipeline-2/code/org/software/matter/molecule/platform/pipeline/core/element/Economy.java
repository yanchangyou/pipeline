package org.software.matter.molecule.platform.pipeline.core.element;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.atom.entity.commons.NameAndTypeAndMetaObject;

public class Economy extends NameAndTypeAndMetaObject {

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

	final static String VALIDATOR_RULES = "pipeline-digester-rules.xml";

	final static String VALIDATOR_XML = "input-process-output.pipeline.xml";

	
	public String toString() {
		return new ToStringBuilder(this).toString() + marketMap;
	}
}
