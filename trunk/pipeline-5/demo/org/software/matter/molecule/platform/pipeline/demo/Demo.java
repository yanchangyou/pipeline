package org.software.matter.molecule.platform.pipeline.demo;

import java.io.File;
import java.net.ConnectException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.software.matter.molecule.platform.pipeline.core.element.Root;

public class Demo implements Runnable {

	public static void main(String[] args) throws Exception {

		for (Iterator iter = demoList.iterator(); iter.hasNext();) {
			Demo demo = (Demo) iter.next();
			new Thread(demo).start();
		}
	}	
	
	public void run() {

		Object[] param = new Object[]{this.getName(), this.getVersion(), this.getFunction(), this.getInnerFlow(), this.getOuterFlow()};
		
		System.out.println(msgFormat.format(param));

		final String PIPELINE_FILE_PATH = this.getPath();

		Root root = null;

		try {
			root = Root.load(PIPELINE_FILE_PATH);
		} catch (Exception e) {
			System.out.println("����[" + PIPELINE_FILE_PATH
					+ "]�ļ�ʧ��, �����pipeline�ļ����Ƿ��д���ĵط�");
			System.out.println("������Ϣ : " + e.getMessage());
			return;
		}

		try {
			System.out.println(this.getName() + "��ʼ��ʾ");
			root.execute();
			System.out.println(this.getName() + "��ʾ����");
		} catch (ConnectException e) {
			System.out.println("���Ӵ��� ��Զ�̷���û������, �������д�[" + PIPELINE_FILE_PATH
					+ "]pipeline�ļ�����Ҫ�ķ���");
			System.out.println("������Ϣ : " + e.getMessage());
			return;
		} catch (Exception e) {
			System.out.println("δ֪����");
			System.out.println("������Ϣ : " + e.getMessage());
			return;
		}
	}
	
	static {
		String VALIDATOR_RULES = "demo-digester-rules.xml";
		String PATH = "demo-list.xml";
		URL rulesUrl = Demo.class.getResource(VALIDATOR_RULES);

		URL xmlUrl = Demo.class.getResource(PATH);
		Digester digester = DigesterLoader.createDigester(rulesUrl);

		try {
			digester.parse(new File(xmlUrl.getFile()));
		} catch (Exception e) {
			System.out.println("demoϵͳ�ڲ�����");
			e.printStackTrace();
		}
	}

	static StringBuffer msgPattern;
	static MessageFormat msgFormat;
	
	static {
		msgPattern = new StringBuffer();
		
		msgPattern.append("\n\n=========��ʼ��ʾ{0} (version:{1})============\n");
		msgPattern.append("\n===��������===\n");
		msgPattern.append("{2}\n");
		msgPattern.append("\n===�ڲ�����===\n");
		msgPattern.append("{3}\n");
		msgPattern.append("\n===�ⲿ����===\n");
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

	public static void addDemo(Demo demo) {
		demoList.add(demo);
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

	static List demoList = new ArrayList();
}
