package org.software.sphere.society.platform.omega.core.flow.sequence;

import java.net.ConnectException;
import java.util.Iterator;

import org.software.sphere.society.platform.omega.core.execute.Session;
import org.software.sphere.society.platform.omega.core.flow.FlowNode;

public class Sequence extends FlowNode {

	public void execute(Session clientSession) throws ConnectException, Exception {
		for (Iterator iter = flowList.iterator(); iter.hasNext();) {
			FlowNode flow = (FlowNode) iter.next();
			flow.execute(clientSession);
		}
	}

}
