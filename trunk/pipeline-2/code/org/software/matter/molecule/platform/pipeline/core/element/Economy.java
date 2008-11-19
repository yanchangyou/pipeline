package org.software.matter.molecule.platform.pipeline.core.element;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.atom.entity.commons.NamedObject;
import org.xml.sax.SAXException;

public class Economy extends NamedObject {

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

	private static Economy economy;

	final static String VALIDATOR_RULES = "pipeline-digester-rules.xml";

	final static String VALIDATOR_XML = "input-process-output.pipeline.xml";

	/**
	 * 
	 * @throws SAXException
	 * @throws IOException
	 * @throws Exception
	 */

	public static Economy configEconomy(final String PATH) throws IOException,
			SAXException {

		URL rulesUrl = Economy.class.getResource(VALIDATOR_RULES);

		URL xmlUrl = Economy.class.getClassLoader().getResource(PATH);
		Digester digester = DigesterLoader.createDigester(rulesUrl);

		economy = (Economy) digester.parse(new File(xmlUrl.getFile()));

		return economy;
	}

	
	public String toString() {
		return new ToStringBuilder(this).toString() + marketMap;
	}
	
	public static void main(String[] args) throws Exception {
		final String VALIDATOR_RULES = "pipeline-digester-rules.xml";
		final String VALIDATOR_XML = "input-process-output.pipeline.xml";

		URL rulesUrl = Economy.class.getResource(VALIDATOR_RULES);

		URL xmlUrl = Economy.class.getResource(VALIDATOR_XML);
		Digester digester = DigesterLoader.createDigester(rulesUrl);

		Economy aEconomy = (Economy) digester.parse(new File(xmlUrl.getFile()));

		System.out.println(aEconomy);
	}

	

}
