package org.software.matter.molecule.platform.pipeline.core.element.pipeline;

import java.net.ConnectException;
import java.util.Iterator;

import org.software.matter.molecule.platform.pipeline.core.element.rr.Request;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Response;


public class Line extends Unit {

	public void deal(Request request, Response response) throws ConnectException, Exception {

		for (Iterator iter = this.getUnitList().iterator(); iter.hasNext();) {
			Unit unit = (Unit) iter.next();
			unit.setRoot(root);
			unit.setPipelineContext(pipelineContext);
			unit.deal(request, response);
		}
	}

}
