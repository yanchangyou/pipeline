package org.software.matter.molecule.platform.pipeline.core.element.pipeline;

import java.net.ConnectException;
import java.util.Iterator;

import org.software.matter.molecule.platform.pipeline.core.element.rr.Request;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Response;

public class Pipeline extends Unit {

	public void deal(Request request, Response response) throws ConnectException, Exception {
		tuneParamToContext();
		if (request != null) {
			request.tuneRequestData(context);
		}
		for (Iterator iter = unitList.iterator(); iter.hasNext();) {
			Unit unit = (Unit) iter.next();
			unit.setRoot(root);
			unit.setParentContext(context);
			unit.deal(request, response);
		}
		if (response != null) {
			response.tuneResponseData(context);
		}
	}
}
