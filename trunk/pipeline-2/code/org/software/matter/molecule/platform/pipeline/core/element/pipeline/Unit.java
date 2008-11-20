package org.software.matter.molecule.platform.pipeline.core.element.pipeline;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.molecule.platform.pipeline.core.element.Root;
import org.software.matter.molecule.platform.pipeline.core.element.common.NameAndTypeAndDeclareObject;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Request;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Response;

public abstract class Unit extends NameAndTypeAndDeclareObject {

	private Root root;
	
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


	public String toString() {
		return new ToStringBuilder(this).toString();
	}
	
	public abstract void deal(Request request, Response response) throws Exception ;
}
