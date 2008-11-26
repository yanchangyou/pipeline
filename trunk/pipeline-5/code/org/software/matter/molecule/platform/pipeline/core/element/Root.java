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
import org.software.matter.molecule.platform.pipeline.core.element.soa.Global;
import org.xml.sax.SAXException;

public class Root extends NameAndTypeAndMetaObject {

	private Map globalMap = new HashMap();

	/**
	 * ÃÌº”global
	 * 
	 * @param global
	 */
	public void addGlobal(Global global) {
		globalMap.put(global.getName(), global);
	}

	/**
	 * ªÒ»°global
	 * 
	 */
	public Global getGlobal(String globalName) {
		return (Global) globalMap.get(globalName);
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
		return super.toString() + globalMap;
	}
}
