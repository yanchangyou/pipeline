package org.software.matter.molecule.platform.pipeline.demo;

import java.net.ConnectException;

import org.software.matter.molecule.platform.pipeline.core.element.Root;

public class Demo {

	public static void main(String[] args) throws Exception {

		// System.out.println("==============演示hello world程序开始============");
		// helloWorld(); // 实现
		// System.out.println("==============演示hello
		// world程序结束=============\n\n");
		//
		// System.out.println("==============演示echo程序开始==============");
		// echo(); // 实现
		// System.out.println("==============演示echo程序结束==============\n\n");

//		System.out.println("==============演示add程序开始============");
//		add(); // 实现
//		System.out.println("==============演示add程序结束============");

		System.out.println("==============演示express程序开始============");
		express(); // 实现
		System.out.println("==============演示express程序结束============");

	}

	public static void helloWorld() {
		final String PIPELINE_FILE_PATH = "org/software/matter/molecule/platform/pipeline/demo/demo/hello-world.pipeline.xml";

		Root root = null;
		try {
			root = Root.load(PIPELINE_FILE_PATH);
		} catch (Exception e1) {
			System.out.println("内部错误");
			e1.printStackTrace();
			return;
		}

		// System.out.println("BEGIN ALL");

		try {
			root.execute();
		} catch (ConnectException e) {
			System.out
					.println("错误 ：远程服务没有启动, 请先运行demo中的OutputServer程序, 启动远程服务");
			// e.printStackTrace();
			return;
		} catch (Exception e) {
			System.out.println("未知错误");
			e.printStackTrace();
			return;
		}

		// System.out.println("OVER ALL");

		System.out.println();
		System.out.println("到 OutputServer 的控制台看输出的结果");
	}

	public static void echo() throws Exception {
		final String PIPELINE_FILE_PATH = "org/software/matter/molecule/platform/pipeline/demo/demo/echo.pipeline.xml";

		Root root = Root.load(PIPELINE_FILE_PATH);

		try {
			root = Root.load(PIPELINE_FILE_PATH);
		} catch (Exception e1) {
			System.out.println("内部错误");
			e1.printStackTrace();
			return;
		}

		// System.out.println("BEGIN ALL");
		System.out.println("准备到InputServer控制台, 输入内容:");
		try {
			root.execute();
		} catch (ConnectException e) {
			System.out
					.println("错误 ：远程服务没有启动, 请先运行demo中的InputServer和OutputServer程序, 启动远程服务");
			// e.printStackTrace();
			return;
		} catch (Exception e) {
			System.out.println("未知错误");
			e.printStackTrace();
			return;
		}

		// System.out.println("OVER ALL");

		System.out.println();
		System.out.println("到 OutputServer 的控制台看输出的结果");
	}

	public static void add() throws Exception {
		final String PIPELINE_FILE_PATH = "org/software/matter/molecule/platform/pipeline/demo/demo/add.pipeline.xml";

		Root root = Root.load(PIPELINE_FILE_PATH);

		try {
			root = Root.load(PIPELINE_FILE_PATH);
		} catch (Exception e1) {
			System.out.println("内部错误");
			e1.printStackTrace();
			return;
		}

		// System.out.println("BEGIN ALL");
		System.out.println("准备到InputServer控制台, 输入内容:");
		try {
			root.execute();
		} catch (ConnectException e) {
			System.out
					.println("错误 ：远程服务没有启动, 请先运行demo中的InputServer和OutputServer程序, 启动远程服务");
			// e.printStackTrace();
			return;
		} catch (Exception e) {
			System.out.println("未知错误");
			e.printStackTrace();
			return;
		}

		// System.out.println("OVER ALL");

		System.out.println();
		System.out.println("到 OutputServer 的控制台看输出的结果");
	}

	public static void express() throws Exception {
		final String PIPELINE_FILE_PATH = "org/software/matter/molecule/platform/pipeline/demo/demo/express.pipeline.xml";

		Root root = Root.load(PIPELINE_FILE_PATH);

		try {
			root = Root.load(PIPELINE_FILE_PATH);
		} catch (Exception e1) {
			System.out.println("内部错误");
			e1.printStackTrace();
			return;
		}

		// System.out.println("BEGIN ALL");
		System.out.println("准备到InputServer控制台, 输入内容:");
		try {
			root.execute();
		} catch (ConnectException e) {
			System.out
					.println("错误 ：远程服务没有启动, 请先运行demo中的InputServer和OutputServer程序, 启动远程服务");
			// e.printStackTrace();
			return;
		} catch (Exception e) {
			System.out.println("未知错误");
			e.printStackTrace();
			return;
		}

		// System.out.println("OVER ALL");

		System.out.println();
		System.out.println("到 OutputServer 的控制台看输出的结果");
	}
}
