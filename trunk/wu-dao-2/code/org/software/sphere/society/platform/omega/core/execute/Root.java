package org.software.sphere.society.platform.omega.core.execute;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.software.sphere.society.platform.omega.core.element.Element;
import org.software.sphere.society.platform.omega.core.element.esoa.Global;
import org.xml.sax.SAXException;

public class Root extends Element {
	
	public Root() {
	}

	/**
	 * ªÒ»°global
	 * 
	 */
	public Global getGlobal(String globalName) {
		return (Global) this.context.getChild(globalName);
	}

	final static String VALIDATOR_RULES = "org/software/sphere/society/platform/omega/core/omega-digester-rules.xml";

	public static Root load(final String PATH) throws IOException,
			SAXException {

		URL rulesUrl = Root.class.getClassLoader().getResource(VALIDATOR_RULES);

		URL xmlUrl = Root.class.getClassLoader().getResource(PATH);
		Digester digester = DigesterLoader.createDigester(rulesUrl);

		return (Root) digester.parse(new File(xmlUrl.getFile()));
	}
	
	public void execute() throws Exception {
//		Execute.execute(this);
	}
}
