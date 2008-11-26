package org.software.matter.molecule.platform.pipeline.demo;

import java.net.ConnectException;

import org.software.matter.molecule.platform.pipeline.core.element.Root;

public class Demo {

	public static void main(String[] args) throws Exception {

		System.out.println("==============ʹ��˵��============");
		System.out.println("1, ����server������4��server��, ������Ӧ�ķ���");
		System.out.println("2, ִ��demo����");
		System.out.println("3, �������������˵��ʹ�ó���");
		System.out.println("4, ��������Է���ķ�ʽ�ṩ, ʹ��telnetȥ�������");
		System.out.println("5, ��ʾ��pipeline5�ܹ����������ķ������ṩ�µķ���");
		System.out.println("6, pipeline5 == ȫ�µĶ�λ + ȫ�µ�Ŀ��");
		System.out.println();
		System.out.println();
		
		System.out.println("==============hello world����˵��============");
		System.out.println("1, ����CMD.exe, ��������̨");
		System.out.println("2, telnet 127.0.0.1 20001");
		System.out.println("3, ��OutputServer�������̨, ������� hello world!");
		System.out.println("4, ��CMD���������, ���ؿ���̨");
		System.out.println();
		System.out.println();
		
		System.out.println("==============echo����˵��============");
		System.out.println("1, ����CMD.exe, ��������̨");
		System.out.println("2, telnet 127.0.0.1 20002");
		System.out.println("3, ��InputServer�������̨, �������ⵥ���ַ���");
		System.out.println("4, ��OutputServer�������̨, �鿴�ղ���������");		
		System.out.println();
		System.out.println();
		
		System.out.println("==============add����˵��============");
		System.out.println("1, ����CMD.exe, ��������̨");
		System.out.println("2, telnet 127.0.0.1 20003");					
		System.out.println("3, ��InputServer�������̨, ��������a(һ��Ҫ������, û���쳣����)");
		System.out.println("4, ��InputServer�������̨, ��������b(һ��Ҫ������, û���쳣����)");
		System.out.println("5, ��OutputServer�������̨, �鿴 a+b ��ֵ");		
		System.out.println();
		System.out.println();
		
		System.out.println("==============express����˵��============");
		System.out.println("1, ����CMD.exe, ��������̨");
		System.out.println("2, telnet 127.0.0.1 20003");					
		System.out.println("3, ��InputServer�������̨, ������ѧ���ʽ,��:(3+2)*2;(һ��Ҫ����ѧ���ʽ, û���쳣����)");
		System.out.println("5, ��OutputServer�������̨, �鿴 ���ʽ��ֵ");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		new Thread() {
			public void run() {
				System.out.println("==============��ʾhello world!����ʼ============");
				helloWorld(); // ʵ��
				System.out.println("==============��ʾhello world!�������=============\n\n");
			}
		}.start();
		new Thread() {
			public void run() {
				
				try {
					System.out.println("==============��ʾecho����ʼ==============");
					echo();
					System.out.println("==============��ʾecho�������==============\n\n");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // ʵ��
				
			}
		}.start();

		new Thread() {
			public void run() {
				
				try {
					System.out.println("==============��ʾadd����ʼ============");
					
					add();
					System.out.println("==============��ʾadd�������============");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // ʵ��
				
			}
		}.start();

		new Thread() {
			public void run() {
				
				try {
					System.out.println("==============��ʾexpress����ʼ============");
					
					express();
					System.out.println("==============��ʾexpress�������============");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // ʵ��
				
			}
		}.start();
	}

	public static void helloWorld() {
		final String PIPELINE_FILE_PATH = "org/software/matter/molecule/platform/pipeline/demo/demo/hello-world.pipeline.xml";

		Root root = null;
		try {
			root = Root.load(PIPELINE_FILE_PATH);
		} catch (Exception e1) {
			System.out.println("�ڲ�����");
			e1.printStackTrace();
			return;
		}

		// System.out.println("BEGIN ALL");

		try {
			root.execute();
		} catch (ConnectException e) {
			System.out
					.println("���� ��Զ�̷���û������, ��������demo�е�OutputServer����, ����Զ�̷���");
			// e.printStackTrace();
			return;
		} catch (Exception e) {
			System.out.println("δ֪����");
			e.printStackTrace();
			return;
		}

		// System.out.println("OVER ALL");

		System.out.println();
		System.out.println("�� OutputServer �Ŀ���̨������Ľ��");
	}

	public static void echo() throws Exception {
		final String PIPELINE_FILE_PATH = "org/software/matter/molecule/platform/pipeline/demo/demo/echo.pipeline.xml";

		Root root = Root.load(PIPELINE_FILE_PATH);

		try {
			root = Root.load(PIPELINE_FILE_PATH);
		} catch (Exception e1) {
			System.out.println("�ڲ�����");
			e1.printStackTrace();
			return;
		}

		// System.out.println("BEGIN ALL");
		System.out.println("׼����InputServer����̨, ��������:");
		try {
			root.execute();
		} catch (ConnectException e) {
			System.out
					.println("���� ��Զ�̷���û������, ��������demo�е�InputServer��OutputServer����, ����Զ�̷���");
			// e.printStackTrace();
			return;
		} catch (Exception e) {
			System.out.println("δ֪����");
			e.printStackTrace();
			return;
		}

		// System.out.println("OVER ALL");

		System.out.println();
		System.out.println("�� OutputServer �Ŀ���̨������Ľ��");
	}

	public static void add() throws Exception {
		final String PIPELINE_FILE_PATH = "org/software/matter/molecule/platform/pipeline/demo/demo/add.pipeline.xml";

		Root root = Root.load(PIPELINE_FILE_PATH);

		try {
			root = Root.load(PIPELINE_FILE_PATH);
		} catch (Exception e1) {
			System.out.println("�ڲ�����");
			e1.printStackTrace();
			return;
		}

		// System.out.println("BEGIN ALL");
		System.out.println("׼����InputServer����̨, ��������:");
		try {
			root.execute();
		} catch (ConnectException e) {
			System.out
					.println("���� ��Զ�̷���û������, ��������demo�е�InputServer��OutputServer����, ����Զ�̷���");
			// e.printStackTrace();
			return;
		} catch (Exception e) {
			System.out.println("δ֪����");
			e.printStackTrace();
			return;
		}

		// System.out.println("OVER ALL");

		System.out.println();
		System.out.println("�� OutputServer �Ŀ���̨������Ľ��");
	}

	public static void express() throws Exception {
		final String PIPELINE_FILE_PATH = "org/software/matter/molecule/platform/pipeline/demo/demo/express.pipeline.xml";

		Root root = Root.load(PIPELINE_FILE_PATH);

		try {
			root = Root.load(PIPELINE_FILE_PATH);
		} catch (Exception e1) {
			System.out.println("�ڲ�����");
			e1.printStackTrace();
			return;
		}

		// System.out.println("BEGIN ALL");
		System.out.println("׼����InputServer����̨, ��������:");
		try {
			root.execute();
		} catch (ConnectException e) {
			System.out
					.println("���� ��Զ�̷���û������, ��������demo�е�InputServer��OutputServer����, ����Զ�̷���");
			// e.printStackTrace();
			return;
		} catch (Exception e) {
			System.out.println("δ֪����");
			e.printStackTrace();
			return;
		}

		// System.out.println("OVER ALL");

		System.out.println();
		System.out.println("�� OutputServer �Ŀ���̨������Ľ��");
	}
}
