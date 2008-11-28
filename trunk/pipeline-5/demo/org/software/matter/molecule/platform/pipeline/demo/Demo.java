package org.software.matter.molecule.platform.pipeline.demo;

import java.net.ConnectException;
import java.text.MessageFormat;

import org.software.matter.molecule.platform.pipeline.core.element.Root;

public class Demo implements Runnable {
	
	public void run() {

		Object[] param = new Object[]{this.getName(), this.getVersion(), this.getFunction(), this.getInnerFlow(), this.getOuterFlow()};
		
		System.out.println(msgFormat.format(param));

		final String PIPELINE_FILE_PATH = this.getPath();

		Root root = null;

		try {
			root = Root.load(PIPELINE_FILE_PATH);
		} catch (Exception e) {
			System.out.println("加载[" + PIPELINE_FILE_PATH
					+ "]文件失败, 请检查此pipeline文件中是否有错误的地方");
			System.out.println("错误信息 : " + e.getMessage());
			return;
		}

		try {
			System.out.println(this.getName() + "开始演示");
			root.execute();
			System.out.println(this.getName() + "演示结束");
		} catch (ConnectException e) {
			System.out.println("连接错误 ：远程服务没有启动, 请先运行此[" + PIPELINE_FILE_PATH
					+ "]pipeline文件中需要的服务");
			System.out.println("错误信息 : " + e.getMessage());
			return;
		} catch (Exception e) {
			System.out.println("未知错误");
			System.out.println("错误信息 : " + e.getMessage());
			return;
		}
	}

	static StringBuffer msgPattern;
	static MessageFormat msgFormat;
	
	static {
		msgPattern = new StringBuffer();
		
		msgPattern.append("\n\n=========开始演示{0} (version:{1})============\n");
		msgPattern.append("\n===功能如下===\n");
		msgPattern.append("{2}\n");
		msgPattern.append("\n===内部流程===\n");
		msgPattern.append("{3}\n");
		msgPattern.append("\n===外部流程===\n");
		msgPattern.append("{4}\n");
		
		msgFormat = new MessageFormat(msgPattern.toString());
	}
	
	public static final String LINE_PREFIX = "    ";
	
	public static final String LINE_SUBFIX = "\n";

	public Demo() {
		function = new StringBuffer();
		innerFlow = new StringBuffer();
		outerFlow = new StringBuffer();
	}

	public String getInnerFlow() {
		return innerFlow.toString();
	}

	public void addInnerFlowLine(String line) {
		this.innerFlow.append(LINE_PREFIX).append(line).append(LINE_SUBFIX);
	}

	public String getOuterFlow() {
		return outerFlow.toString();
	}

	public void addOuterFlowLine(String line) {
		this.outerFlow.append(LINE_PREFIX).append(line).append(LINE_SUBFIX);
	}

	public String getFunction() {
		return function.toString();
	}

	public void addFunctionLine(String line) {
		this.function.append(LINE_PREFIX).append(line).append(LINE_SUBFIX);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String demoName) {
		this.name = demoName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	private String name;

	private String version;

	private String path;

	private StringBuffer function;

	private StringBuffer innerFlow;

	private StringBuffer outerFlow;
}
