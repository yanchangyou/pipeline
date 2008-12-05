package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.software.matter.molecule.platform.pipeline.core.Algorithm;
import org.software.matter.molecule.platform.pipeline.core.ParamList;
import org.software.matter.molecule.platform.pipeline.core.Pipeline;
import org.software.matter.molecule.platform.pipeline.core.PipelineConfig;
import org.software.matter.molecule.platform.pipeline.core.Step;

public class Test {

	public static void main(String[] args) throws Exception {
//		helloWorldTest();
//		printlnTest();
//		addTest();
//		readAddPrintTest();
		
		inputProcessOutputTest();
		
//		somTest();
	}
	
	public static void inputProcessOutputTest() throws Exception {
		
		PipelineConfig pipelineConfig = new PipelineConfig("pipeline.xml");
		String pipelineName = "input-process-output";
		Pipeline aPipeline = pipelineConfig.getPipeline(pipelineName);
		
		aPipeline.deal();
	}
	
	public static void somTest() throws Exception {
		String cofigFilePath = "test/sheet2list.pipeline.xml";
		PipelineConfig aPipelineConfig = new PipelineConfig(cofigFilePath);

		Pipeline aPipeline = aPipelineConfig.getPipeline("sheet2list");

		aPipeline.deal();
	}
	
	public static void helloWorld() {
		System.out.println("hello world!");
	}
	
	
	public static void println(String message) {
		System.out.println(message);
	}
	
	public static void println(Object obj) {
		println(obj.toString());
	}
	
	public static Integer add(Integer a, Integer b) {
		return new Integer(a.intValue() + b.intValue());
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
	
	
	public static void helloWorldTest() throws Exception {
		Pipeline aPipeline = new Pipeline();
		
		Step step = new Step();
		
		Algorithm algorithm = new Algorithm();
		
		algorithm.setClassName("test.TestPipeline");
		algorithm.setMethodName("helloWorld");
		
		step.setAlgorithm(algorithm);
		
		aPipeline.addStep(step);
		
		aPipeline.deal();
		
	}
	
	
	public static void printlnTest() throws Exception {
		Pipeline aPipeline = new Pipeline();
		
		Step step = new Step();
		
		Algorithm algorithm = new Algorithm();
		
		ParamList paramList = new ParamList();
		
		algorithm.setClassName("test.TestPipeline");
		algorithm.setMethodName("println");
		algorithm.setMethodParams("java.lang.String");
		
		paramList.addParam("0", "just a hello world!");
		
		step.setParamList(paramList);
		
		step.setAlgorithm(algorithm);
		
		aPipeline.addStep(step);
		
		aPipeline.deal();
		
	}
	
	public static void addTest() throws Exception {
		Pipeline aPipeline = new Pipeline();
		
		Step step = new Step();
		
		Algorithm algorithm = new Algorithm();
		
		ParamList paramList = new ParamList();
		
		algorithm.setClassName("test.TestPipeline");
		algorithm.setMethodName("add");
		algorithm.setMethodParams("java.lang.Integer,java.lang.Integer");
		step.setAlgorithm(algorithm);
		
		
		paramList.addParam("0", new Integer("123"));
		paramList.addParam("1", new Integer("456"));
		step.setParamList(paramList);
		
		
		step.setNeedStore("true");
		step.setGlobalName("result");
		
		aPipeline.addStep(step);
		
		
		aPipeline.deal();

		Object result = aPipeline.getGlobalVariable("result");
		
		System.out.println(result);
	}
	
	
	public static void readAddPrintTest() throws Exception {
		Pipeline aPipeline = new Pipeline();
		
		Step readA = new Step();
		Step readB = new Step();
		
		Step add_A_B = new Step();
		
		Step print = new Step();
		
		aPipeline.addStep(readA);
		aPipeline.addStep(readB);
		aPipeline.addStep(add_A_B);
		aPipeline.addStep(print);

		ParamList paramList = new ParamList();
		Algorithm algorithm = new Algorithm();
		
		algorithm.setClassName("test.TestPipeline");
		algorithm.setMethodName("readInt");
		
		readA.setAlgorithm(algorithm);		
		readA.setNeedStore("true");
		readA.setGlobalName("a");
		
		algorithm = new Algorithm();
		
		algorithm.setClassName("test.TestPipeline");
		algorithm.setMethodName("readInt");
		
		readB.setAlgorithm(algorithm);
		readB.setNeedStore("true");
		readB.setGlobalName("b");

		algorithm = new Algorithm();
		
		algorithm.setClassName("test.TestPipeline");
		algorithm.setMethodName("add");
		algorithm.setMethodParams("java.lang.Integer,java.lang.Integer");
		
		paramList.setGlobalVariableNameIndexMap("a", "0");
		paramList.setGlobalVariableNameIndexMap("b", "1");
		
		add_A_B.setAlgorithm(algorithm);
		
		add_A_B.setParamList(paramList);
		add_A_B.setNeedStore("true");
		add_A_B.setGlobalName("result");

		
		algorithm = new Algorithm();
		algorithm.setClassName("test.TestPipeline");
		algorithm.setMethodName("println");
		algorithm.setMethodParams("java.lang.Object");
		
		paramList = new ParamList();
		
		paramList.setGlobalVariableNameIndexMap("result", "0");
		
		print.setParamList(paramList);
		print.setAlgorithm(algorithm);
		
		
		aPipeline.deal();
		
	}
	
}
