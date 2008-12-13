package org.software.sphere.society.platform.omega.core.flow.sequence;

import java.net.ConnectException;

import org.software.sphere.society.platform.omega.core.flow.FlowNode;
import org.software.sphere.society.platform.omega.core.lang.execute.Session;

public class Sequence extends FlowNode {

	public void execute(Session clientSession) throws ConnectException, Exception {
		super.defaultExecute(clientSession);
	}

}
