package org.software.sphere.society.platform.omega.core.element.pipeline;

import java.net.ConnectException;
import java.util.Iterator;

import org.software.sphere.society.platform.omega.core.session.Session;

public class Pipeline extends Unit {

	public Pipeline() {
		super();
	}
	
	public void deal(Session clientSession) throws ConnectException, Exception {
		log.info("��ʼ---ִ��Pipeline:" + this.getName());
		
		if (clientSession.getRequest() != null) {
//			clientSession.getRequest().tuneContextToRequestData(context);
		}
		
		for (Iterator iter = unitList.iterator(); iter.hasNext();) {
			Unit unit = (Unit) iter.next();
			this.addChildElement(unit);
			unit.deal(clientSession);
			
		}
		if (clientSession.getResponse() != null) {
//			clientSession.getResponse().tuneResponseData(context);
		}
		log.info("����---ִ��Pipeline:" + this.getName());
	}
}
