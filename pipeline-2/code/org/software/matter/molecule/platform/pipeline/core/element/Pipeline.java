package org.software.matter.molecule.platform.pipeline.core.element;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.atom.entity.commons.NameAndTypeAndMetaObject;

public class Pipeline extends NameAndTypeAndMetaObject {

	private List unitList = new ArrayList();
	
	public void addUnit(Unit unit) {
		unitList.add(unit);
	}
//	
//	public void addUnit(Line line) {
//		unitList.add(line);
//	}
	

	public String toString() {
		return new ToStringBuilder(this).toString() + "\nunitList : " + unitList;
	}
}
