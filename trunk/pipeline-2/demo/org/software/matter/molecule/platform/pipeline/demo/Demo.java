package org.software.matter.molecule.platform.pipeline.demo;

import org.software.matter.molecule.platform.pipeline.core.element.Economy;


public class Demo {

	public static void main(String[] args) throws Exception {
		
//		inputProcessOutput();
		
		simpleTest();
	}
	
	public static void inputProcessOutput() throws Exception {
//		String configFilePath = "org/software/matter/molecule/platform/pipeline/demo/input-process-output.pipeline.xml";
//		PipelineConfig pipelineConfig = new PipelineConfig(configFilePath);
//		String pipelineName = "input-process-output";
//		Pipeline aPipeline = pipelineConfig.getPipeline(pipelineName);
//		
//		aPipeline.deal();
	}
	
	
	public static void simpleTest() throws Exception {
		final String VALIDATOR_XML = "org/software/matter/molecule/platform/pipeline/demo/request-response.pipeline.xml";
		
		Economy aEconomy = Economy.configEconomy(VALIDATOR_XML);
		
		System.out.println(aEconomy);
	}
	
}
