package org.software.matter.molecule.platform.pipeline.core.element;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.software.matter.atom.entity.commons.NamedObject;
import org.xml.sax.SAXException;

public class Root extends NamedObject {

	private Map economyMap = new HashMap();

	/**
	 * ����economy
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

	final static String VALIDATOR_RULES = "pipeline-digester-rules.xml";

	public static Root config(final String PATH) throws IOException,
			SAXException {

		URL rulesUrl = Root.class.getResource(VALIDATOR_RULES);

		URL xmlUrl = Economy.class.getClassLoader().getResource(PATH);
		Digester digester = DigesterLoader.createDigester(rulesUrl);

		return (Root) digester.parse(new File(xmlUrl.getFile()));
	}
	
	public String toString() {
		return super.toString() + economyMap;
	}
}
