package org.software.matter.molecule.platform.pipeline.demo;

import org.software.matter.molecule.platform.pipeline.core.element.Root;


public class Demo {

	public static void main(String[] args) throws Exception {
		demo();
	}
	
	public static void demo() throws Exception {
		final String PIPELINE_FILE_PATH = "org/software/matter/molecule/platform/pipeline/demo/integration.pipeline.xml";
		
		Root aRoot = Root.load(PIPELINE_FILE_PATH);
		
		aRoot.execute();
		
		System.out.println("over");
	}
	
}
