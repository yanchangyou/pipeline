package org.software.sphere.society.platform.pipeline.core.flow.sequence;

import java.net.ConnectException;

import org.software.sphere.society.platform.pipeline.core.flow.FlowNode;
import org.software.sphere.society.platform.pipeline.core.lang.execute.Session;

public class Sequence extends FlowNode {

	public void execute(Session clientSession) throws ConnectException, Exception {
		super.defaultExecute(clientSession);
	}

}
