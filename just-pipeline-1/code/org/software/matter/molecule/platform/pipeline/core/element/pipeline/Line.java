package org.software.matter.molecule.platform.pipeline.core.element.pipeline;

import java.net.ConnectException;
import java.util.Iterator;

import org.software.matter.molecule.platform.pipeline.core.session.Session;


public class Line extends Unit {
	
	public Line() {
		super();
	}
	
	public void deal(Session clientSession) throws ConnectException, Exception {

//		tuneParamToContext();
		
		for (Iterator iter = this.getUnitList().iterator(); iter.hasNext();) {
			Unit unit = (Unit) iter.next();
			this.addChildElement(unit);
			log.info("��ʼ---ִ�е�Ԫ:" + unit.getName());
			unit.deal(clientSession);
			log.info("����---ִ�е�Ԫ:" + unit.getName());
		}
	}

}
