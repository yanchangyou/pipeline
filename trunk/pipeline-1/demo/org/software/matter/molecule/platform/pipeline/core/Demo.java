package org.software.matter.molecule.platform.pipeline.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.software.matter.molecule.platform.pipeline.core.Pipeline;
import org.software.matter.molecule.platform.pipeline.core.PipelineConfig;

public class Demo {

	public static void main(String[] args) throws Exception {
		
		inputProcessOutput();
	}
	
	public static void inputProcessOutput() throws Exception {
		
		PipelineConfig pipelineConfig = new PipelineConfig("org/software/matter/molecule/platform/pipeline/core/input-process-output.pipeline.xml");
		String pipelineName = "" +
				"input-process-output";
		Pipeline aPipeline = pipelineConfig.getPipeline(pipelineName);
		
		aPipeline.deal();
	}
	
	public static void println(String message) {
		System.out.println(message);
	}
	
	public static void println(Object obj) {
		println(obj.toString());
	}
	
	public static Double calculate(Integer a, Integer b, Double c) {
		return new Double((a.intValue() + b.intValue()) * c.doubleValue());
	}
	
	public static Integer readInt() {
		 InputStreamReader inR = new InputStreamReader( System.in );
		   BufferedReader buf = new BufferedReader( inR );
		String str= null;
		try {
			str = buf.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Integer(str);
	}
}
