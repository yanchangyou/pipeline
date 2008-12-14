package org.software.sphere.society.platform.pipeline.core.flow.sequence;

import java.net.ConnectException;

import org.software.sphere.society.platform.pipeline.core.core.Session;
import org.software.sphere.society.platform.pipeline.core.flow.FlowNode;

public class Sequence extends FlowNode {

	public void execute(Session clientSession) throws ConnectException, Exception {
		super.defaultExecute(clientSession);
	}

}
