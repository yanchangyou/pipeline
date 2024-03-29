package org.software.matter.molecule.platform.pipeline.core.element;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.software.matter.atom.entity.commons.NameAndTypeAndMetaObject;
import org.software.matter.molecule.platform.pipeline.core.Execute;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Economy;
import org.xml.sax.SAXException;

public class Root extends NameAndTypeAndMetaObject {

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

	final static String VALIDATOR_RULES = "org/software/matter/molecule/platform/pipeline/core/pipeline-digester-rules.xml";

	public static Root load(final String PATH) throws IOException,
			SAXException {

		URL rulesUrl = Root.class.getClassLoader().getResource(VALIDATOR_RULES);

		URL xmlUrl = Root.class.getClassLoader().getResource(PATH);
		Digester digester = DigesterLoader.createDigester(rulesUrl);

		return (Root) digester.parse(new File(xmlUrl.getFile()));
	}
	
	public void execute() throws Exception {
		Execute.execute(this);
	}
	
	public String toString() {
		return super.toString() + economyMap;
	}
}
