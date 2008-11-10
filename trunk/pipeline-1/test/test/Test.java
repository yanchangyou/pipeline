package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.software.matter.molecule.platform.pipeline.core.Pipeline;
import org.software.matter.molecule.platform.pipeline.core.PipelineConfig;

public class Test {

	public static void main(String[] args) throws Exception {
		
		inputProcessOutputTest();
//		
//		somTest();
	}
	
	public static void inputProcessOutputTest() throws Exception {
		
		PipelineConfig pipelineConfig = new PipelineConfig("pipeline.xml");
		String pipelineName = "" +
				"input-process-output";
		Pipeline aPipeline = pipelineConfig.getPipeline(pipelineName);
		
		aPipeline.deal();
	}
	
	public static void somTest() throws Exception {
		String cofigFilePath = "test/sheet2list.pipeline.xml";
		PipelineConfig aPipelineConfig = new PipelineConfig(cofigFilePath);

		Pipeline aPipeline = aPipelineConfig.getPipeline("sheet2list");

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
