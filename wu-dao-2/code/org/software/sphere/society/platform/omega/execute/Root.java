package org.software.sphere.society.platform.omega.execute;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.software.sphere.society.platform.omega.data.node01.DefaultNode01;
import org.software.sphere.society.platform.omega.real.Global;
import org.xml.sax.SAXException;

public class Root extends DefaultNode01 {
	
	final static String VALIDATOR_RULES = "org/software/sphere/society/platform/omega/core/execute/omega-digester-rules.xml";

	public static Root load(final String PATH) throws IOException,
			SAXException {

		URL rulesUrl = Root.class.getClassLoader().getResource(VALIDATOR_RULES);

		URL xmlUrl = Root.class.getClassLoader().getResource(PATH);
		Digester digester = DigesterLoader.createDigester(rulesUrl);

		return (Root) digester.parse(new File(xmlUrl.getFile()));
	}
	
	public void execute() throws Exception {
//		Execute.execute(this);
		System.out.println("execute!!!!");
	}
	
	public Global getGlobal() {
		return (Global) this.getNext();
	}

	public void setGlobal(Global global) {
		this.setNext(global);
	}
}
