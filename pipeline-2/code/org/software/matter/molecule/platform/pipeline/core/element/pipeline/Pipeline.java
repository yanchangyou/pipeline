package org.software.matter.molecule.platform.pipeline.core.element.pipeline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.molecule.platform.pipeline.core.element.Root;
import org.software.matter.molecule.platform.pipeline.core.element.common.NameAndTypeAndDeclareObject;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Request;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Response;

public class Pipeline extends NameAndTypeAndDeclareObject {

	
	private List unitList = new ArrayList();

	public void addUnit(Unit unit) {
		unitList.add(unit);
	}

	public String toString() {
		return new ToStringBuilder(this).toString() + "\nunitList : "
				+ unitList;
	}

	public void deal(Request request, Response response) throws Exception {

		for (Iterator iter = unitList.iterator(); iter.hasNext();) {
			Unit unit = (Unit) iter.next();
			unit.setRoot(root);
			unit.deal(request, response);
		}
	}

	private Root root;

	public void setRoot(Root root) {
		this.root = root;
	}

	public Root getRoot() {
		return root;
	}

}
