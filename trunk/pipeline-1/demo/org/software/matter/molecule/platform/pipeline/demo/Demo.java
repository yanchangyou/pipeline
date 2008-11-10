package org.software.matter.molecule.platform.pipeline.demo;

import org.software.matter.molecule.platform.pipeline.core.Pipeline;
import org.software.matter.molecule.platform.pipeline.core.PipelineConfig;

public class Demo {

	public static void main(String[] args) throws Exception {
		
		inputProcessOutput();
	}
	
	public static void inputProcessOutput() throws Exception {
		String configFilePath = "org/software/matter/molecule/platform/pipeline/demo/input-process-output.pipeline.xml";
		PipelineConfig pipelineConfig = new PipelineConfig(configFilePath);
		String pipelineName = "input-process-output";
		Pipeline aPipeline = pipelineConfig.getPipeline(pipelineName);
		
		aPipeline.deal();
	}
	
}
