package org.software.sphere.society.platform.omega.core.flow.sequence;

import java.net.ConnectException;
import java.util.Iterator;

import org.software.sphere.society.platform.omega.core.execute.Session;
import org.software.sphere.society.platform.omega.core.flow.Flow;

public class Sequence extends Flow {

	public void execute(Session clientSession) throws ConnectException, Exception {
		for (Iterator iter = flowList.iterator(); iter.hasNext();) {
			Flow flow = (Flow) iter.next();
			flow.execute(clientSession);
		}
	}

}
