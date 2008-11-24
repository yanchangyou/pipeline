package org.software.matter.molecule.platform.pipeline.core;

import org.software.matter.molecule.platform.pipeline.core.context.PipelineContext;
import org.software.matter.molecule.platform.pipeline.core.element.Root;
import org.software.matter.molecule.platform.pipeline.core.element.pipeline.Pipeline;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Request;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Response;
import org.software.matter.molecule.platform.pipeline.core.element.rr.StringOutput;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Service;

public class Execute {

	
	public static void execute(Root root) throws Exception {
		System.out.println("EXECUTE begin |--->");
//		System.out.println(root);
		
		
		String mainPath = "main.main.main.main.main";
		
		
		Service mainService = (Service) Locator.locate(root, mainPath);
		
		
		Request request = mainService.getRequest();
		Response response = mainService.getResponse();
		PipelineContext pipelineContext = new PipelineContext();
		
		if (request != null && request.getInput() != null) {
			pipelineContext.put(Const.REQUEST_INPUT, request.getInput().getData());
		}
		
		Pipeline mainPipeline = mainService.getPipeline();
		mainPipeline.setRoot(root);
		mainPipeline.setPipelineContext(pipelineContext);
		mainPipeline.deal(request, response);
		
		if (response == null) {
			response = new Response();
			response.setOutput(new StringOutput());
		}
		
		System.out.println("<---|EXECUTE end");
	}
}
