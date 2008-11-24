package org.software.matter.molecule.platform.pipeline.demo;

import java.net.ConnectException;

import org.software.matter.molecule.platform.pipeline.core.element.Root;

public class Demo {

	public static void main(String[] args) throws Exception {

		// System.out.println("==============��ʾhello world����ʼ============");
		// helloWorld(); // ʵ��
		// System.out.println("==============��ʾhello
		// world�������=============\n\n");
		//
		// System.out.println("==============��ʾecho����ʼ==============");
		// echo(); // ʵ��
		// System.out.println("==============��ʾecho�������==============\n\n");

//		System.out.println("==============��ʾadd����ʼ============");
//		add(); // ʵ��
//		System.out.println("==============��ʾadd�������============");

		System.out.println("==============��ʾexpress����ʼ============");
		express(); // ʵ��
		System.out.println("==============��ʾexpress�������============");

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
