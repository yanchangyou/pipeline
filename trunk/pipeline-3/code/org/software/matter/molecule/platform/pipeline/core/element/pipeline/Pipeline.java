package org.software.matter.molecule.platform.pipeline.core.element.pipeline;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.molecule.platform.pipeline.core.context.PipelineContext;
import org.software.matter.molecule.platform.pipeline.core.element.Root;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Request;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Response;

public class Pipeline extends PP {

	private PipelineContext pipelineContext;
	
	private List unitList = new ArrayList();

	public void addUnit(Unit unit) {
		unitList.add(unit);
	}

	public String toString() {
		return new ToStringBuilder(this).toString() + "\nunitList : "
				+ unitList;
	}

	public void deal(Request request, Response response) throws ConnectException, Exception {
		if (request != null) {
			request.tuneRequestData(pipelineContext);
		}
		for (Iterator iter = unitList.iterator(); iter.hasNext();) {
			Unit unit = (Unit) iter.next();
			unit.setRoot(root);
			unit.setPipelineContext(pipelineContext);
			unit.deal(request, response);
		}
		if (response != null) {
			response.tuneResponseData(pipelineContext);
		}
	}

	private Root root;

	public void setRoot(Root root) {
		this.root = root;
	}

	public Root getRoot() {
		return root;
	}

	public PipelineContext getPipelineContext() {
		return pipelineContext;
	}

	public void setPipelineContext(PipelineContext pipelineContext) {
		this.pipelineContext = pipelineContext;
	}

}
