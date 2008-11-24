package org.software.matter.molecule.platform.pipeline.core.element.pipeline;

import java.util.Iterator;

import org.software.matter.molecule.platform.pipeline.core.element.rr.Input;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Request;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Response;
import org.software.matter.molecule.platform.pipeline.core.element.rr.StringInput;



public class Line extends Unit {

	public void deal(Request request, Response response) throws Exception {

		for (Iterator iter = this.getUnitList().iterator(); iter.hasNext();) {
			Unit unit = (Unit) iter.next();
			unit.setRoot(this.getRoot());
			unit.deal(request, response);
			
			
			
			//上一步[响应]是下一步的[请求]			
			if (response != null && response.getOutput() != null) {
				Input input = new StringInput();
				input.setData(response.getOutput().getData());
				request.setInput(input);
			}
			
		}
	}

}
