package org.software.matter.molecule.platform.pipeline.core.element.pipeline;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.molecule.platform.pipeline.core.context.PipelineContext;
import org.software.matter.molecule.platform.pipeline.core.element.Root;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Request;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Response;

public abstract class Unit extends PP {
	
	protected PipelineContext pipelineContext;
	
	protected Root root;
	
	public PipelineContext getPipelineContext() {
		return pipelineContext;
	}

	public void setPipelineContext(PipelineContext pipelineContext) {
		this.pipelineContext = pipelineContext;
	}

	public Root getRoot() {
		return root;
	}

	public void setRoot(Root root) {
		this.root = root;
	}

	public Unit(String name) {
	}
	
	public Unit() {
		
	}
	

	private List unitList = new ArrayList();
	
	public void addUnit(Unit unit) {
		unitList.add(unit);
	}

	public List getUnitList() {
		return unitList;
	}


	public void setUnitList(List unitList) {
		this.unitList = unitList;
	}

	public String toString() {
		return new ToStringBuilder(this).toString();
	}
	
	public abstract void deal(Request request, Response response) throws ConnectException, Exception;
}
