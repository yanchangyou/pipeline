package org.software.matter.molecule.platform.pipeline.core;

import org.software.matter.molecule.platform.pipeline.core.element.Root;
import org.software.matter.molecule.platform.pipeline.core.element.pipeline.Pipeline;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Request;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Response;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Service;

public class Execute {

	
	public static void execute(Root root) throws Exception {
		System.out.println("EXECUTE begin |--->");
		System.out.println(root);
		
		
		String mainPath = "main.main.main.main.main";
		
		
		Service mainService = (Service) Locator.locate(root, mainPath);
		
		
		Request aRequest = mainService.getRequest();
		Response aResponse = mainService.getResponse();
		Pipeline mainPipeline = mainService.getPipeline();
		mainPipeline.setRoot(root);
		mainPipeline.deal(aRequest, aResponse);
		
		
		System.out.println("<---|EXECUTE end");
	}
}
