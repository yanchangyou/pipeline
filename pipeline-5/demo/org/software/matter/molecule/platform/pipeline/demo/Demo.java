package org.software.matter.molecule.platform.pipeline.demo;

import java.net.ConnectException;

import org.software.matter.molecule.platform.pipeline.core.element.Root;

public class Demo {

	public static void main(String[] args) throws Exception {

		System.out.println("==============使用说明============");
		System.out.println("1, 运行server包下面4个server类, 开启相应的服务");
		System.out.println("2, 执行demo程序");
		System.out.println("3, 按照下面的流程说明使用程序");
		System.out.println("4, 下面程序以服务的方式提供, 使用telnet去请求服务");
		System.out.println("5, 演示了pipeline5能够整合其它的服务以提供新的服务");
		System.out.println("6, pipeline5 == 全新的定位 + 全新的目标");
		System.out.println();
		System.out.println();
		
		System.out.println("==============hello world流程说明============");
		System.out.println("1, 运行CMD.exe, 开启控制台");
		System.out.println("2, telnet 127.0.0.1 20001");
		System.out.println("3, 在OutputServer输出控制台, 看输出的 hello world!");
		System.out.println("4, 在CMD中敲任意键, 返回控制台");
		System.out.println();
		System.out.println();
		
		System.out.println("==============echo流程说明============");
		System.out.println("1, 运行CMD.exe, 开启控制台");
		System.out.println("2, telnet 127.0.0.1 20002");
		System.out.println("3, 在InputServer输入控制台, 输入任意单行字符串");
		System.out.println("4, 在OutputServer输出控制台, 查看刚才输入内容");		
		System.out.println();
		System.out.println();
		
		System.out.println("==============add流程说明============");
		System.out.println("1, 运行CMD.exe, 开启控制台");
		System.out.println("2, telnet 127.0.0.1 20003");					
		System.out.println("3, 在InputServer输入控制台, 输入数字a(一定要是数字, 没有异常处理)");
		System.out.println("4, 在InputServer输入控制台, 输入数字b(一定要是数字, 没有异常处理)");
		System.out.println("5, 在OutputServer输出控制台, 查看 a+b 的值");		
		System.out.println();
		System.out.println();
		
		System.out.println("==============express流程说明============");
		System.out.println("1, 运行CMD.exe, 开启控制台");
		System.out.println("2, telnet 127.0.0.1 20003");					
		System.out.println("3, 在InputServer输入控制台, 输入数学表达式,如:(3+2)*2;(一定要是数学表达式, 没有异常处理)");
		System.out.println("5, 在OutputServer输出控制台, 查看 表达式的值");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		new Thread() {
			public void run() {
				System.out.println("==============演示hello world!程序开始============");
				helloWorld(); // 实现
				System.out.println("==============演示hello world!程序结束=============\n\n");
			}
		}.start();
		new Thread() {
			public void run() {
				
				try {
					System.out.println("==============演示echo程序开始==============");
					echo();
					System.out.println("==============演示echo程序结束==============\n\n");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 实现
				
			}
		}.start();

		new Thread() {
			public void run() {
				
				try {
					System.out.println("==============演示add程序开始============");
					
					add();
					System.out.println("==============演示add程序结束============");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 实现
				
			}
		}.start();

		new Thread() {
			public void run() {
				
				try {
					System.out.println("==============演示express程序开始============");
					
					express();
					System.out.println("==============演示express程序结束============");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 实现
				
			}
		}.start();
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
