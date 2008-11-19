package org.software.matter.molecule.platform.pipeline.core.element;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.atom.entity.commons.NamedObject;

public class Unit extends NamedObject {

	public Unit(String name) {
	}
	
	public Unit() {
		
	}
	

	private List unitList = new ArrayList();
	
	public void addUnit(Unit unit) {
		unitList.add(unit);
	}


	public String toString() {
		return new ToStringBuilder(this).toString();
	}
}
