package org.software.sphere.society.platform.omega.core.execute;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.software.sphere.society.platform.omega.OmegaConst;
import org.software.sphere.society.platform.omega.core.data.node01.DefaultNode01;
import org.software.sphere.society.platform.omega.core.data.node0X.String;
import org.software.sphere.society.platform.omega.core.flow.FlowNode;
import org.software.sphere.society.platform.omega.core.real.Global;
import org.software.sphere.society.platform.omega.exception.data.MiddleNodeNotFountException;
import org.xml.sax.SAXException;

public class Root extends DefaultNode01 {
	
	final static java.lang.String VALIDATOR_RULES = "org/software/sphere/society/platform/omega/core/execute/digestor/omega-digester-rules.xml";

	public static Root load(final java.lang.String PATH) throws IOException,
			SAXException {

		URL rulesUrl = Root.class.getClassLoader().getResource(VALIDATOR_RULES);

		URL xmlUrl = Root.class.getClassLoader().getResource(PATH);
		Digester digester = DigesterLoader.createDigester(rulesUrl);

		return (Root) digester.parse(new File(xmlUrl.getFile()));
	}
	
	public void execute() throws Exception {
		log.info("初始化第一个flow, 此flow的socket为null, 后面注意");
		FlowNode sefFlow = this.getSelfFlow();
		Session clientSession = new Session(sefFlow.getRequest(), sefFlow.getResponse(), null);
		log.info("执行第一个 flow");
		sefFlow.execute(clientSession);
	}
	
	public Global getGlobal() {
		return (Global) this.getNext();
	}

	public void setGlobal(Global global) {
		this.setNext(global);
		global.setPreNode(this);
	}
	
	public FlowNode getSelfFlow() throws MiddleNodeNotFountException {
		final String SELF_UNIT_PATH = new String(OmegaConst.CORE.EXECUTE.SELF_UNIT_PATH);
		return (FlowNode) this.getGlobal().getNextNodeByPath(SELF_UNIT_PATH);
	}
}
