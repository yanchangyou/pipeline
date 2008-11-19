package org.software.matter.molecule.platform.pipeline.core.element;

import java.util.Iterator;



public class Line extends Unit {

	public void deal(Request request, Response response) throws Exception {

		for (Iterator iter = this.getUnitList().iterator(); iter.hasNext();) {
			Unit unit = (Unit) iter.next();
			unit.setRoot(this.getRoot());
			unit.deal(request, response);
		}
	}

}
