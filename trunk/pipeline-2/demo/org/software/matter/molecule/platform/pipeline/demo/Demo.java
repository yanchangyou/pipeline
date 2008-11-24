package org.software.matter.molecule.platform.pipeline.demo;

import java.net.ConnectException;

import org.software.matter.molecule.platform.pipeline.core.element.Root;

public class Demo {

	public static void main(String[] args) throws Exception {

		helloWorld();			//实现

		echo();					//实现
		
		// add(); 				//计划中
		//		
		// simpleExpress();		//计划中
		//		
		// complexExpress();	//计划中

	}

	public static void helloWorld() {
		final String PIPELINE_FILE_PATH = "org/software/matter/molecule/platform/pipeline/demo/demo/hello-world.pipeline.xml";

		Root aRoot = null;
		try {
			aRoot = Root.load(PIPELINE_FILE_PATH);
		} catch (Exception e1) {
			System.out.println("内部错误");
			e1.printStackTrace();
			return;
		} 

		System.out.println("BEGIN ALL");

		try {
			aRoot.execute();
		} catch (ConnectException e) {
			System.out.println("错误 ：远程服务没有启动, 请先运行demo中的OutputServer程序, 启动远程服务");
//			e.printStackTrace();
			return;
		} catch (Exception e) {
			System.out.println("未知错误");
			e.printStackTrace();
			return;
		}

		System.out.println("OVER ALL");
		
		System.out.println();
		System.out.println("到 OutputServer 的控制台看输出的结果");
	}

	public static void echo() throws Exception {
		final String PIPELINE_FILE_PATH = "org/software/matter/molecule/platform/pipeline/demo/demo/echo.pipeline.xml";

		Root aRoot = Root.load(PIPELINE_FILE_PATH);

		try {
			aRoot = Root.load(PIPELINE_FILE_PATH);
		} catch (Exception e1) {
			System.out.println("内部错误");
			e1.printStackTrace();
			return;
		} 

		System.out.println("BEGIN ALL");

		try {
			aRoot.execute();
		} catch (ConnectException e) {
			System.out.println("错误 ：远程服务没有启动, 请先运行demo中的InputServer和OutputServer程序, 启动远程服务");
//			e.printStackTrace();
			return;
		} catch (Exception e) {
			System.out.println("未知错误");
			e.printStackTrace();
			return;
		}

		System.out.println("OVER ALL");
		
		System.out.println();
		System.out.println("到 OutputServer 的控制台看输出的结果");
	}

	public static void add() throws Exception {
		final String PIPELINE_FILE_PATH = "org/software/matter/molecule/platform/pipeline/demo/demo/add.pipeline.xml";

		Root aRoot = Root.load(PIPELINE_FILE_PATH);

		System.out.println("BEGIN ALL");

		aRoot.execute();

		System.out.println("OVER ALL");
	}

	public static void simpleExpress() throws Exception {
		final String PIPELINE_FILE_PATH = "org/software/matter/molecule/platform/pipeline/demo/demo/simple-express.pipeline.xml";

		Root aRoot = Root.load(PIPELINE_FILE_PATH);

		System.out.println("BEGIN ALL");

		aRoot.execute();

		System.out.println("OVER ALL");
	}

	public static void complexExpress() throws Exception {
		final String PIPELINE_FILE_PATH = "org/software/matter/molecule/platform/pipeline/demo/demo/complex-express.pipeline.xml";

		Root aRoot = Root.load(PIPELINE_FILE_PATH);

		System.out.println("BEGIN ALL");

		aRoot.execute();

		System.out.println("OVER ALL");
	}
}
