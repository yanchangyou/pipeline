package org.software.sphere.society.platform.omega.core.element.pipeline;

import java.net.ConnectException;
import java.util.Iterator;

import org.software.sphere.society.platform.omega.core.session.Session;


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
