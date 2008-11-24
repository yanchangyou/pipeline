package org.software.matter.molecule.platform.pipeline.demo;

import java.net.ConnectException;

import org.software.matter.molecule.platform.pipeline.core.element.Root;

public class Demo {

	public static void main(String[] args) throws Exception {

		helloWorld();			//ʵ��

		echo();					//ʵ��
		
		// add(); 				//�ƻ���
		//		
		// simpleExpress();		//�ƻ���
		//		
		// complexExpress();	//�ƻ���

	}

	public static void helloWorld() {
		final String PIPELINE_FILE_PATH = "org/software/matter/molecule/platform/pipeline/demo/demo/hello-world.pipeline.xml";

		Root aRoot = null;
		try {
			aRoot = Root.load(PIPELINE_FILE_PATH);
		} catch (Exception e1) {
			System.out.println("�ڲ�����");
			e1.printStackTrace();
			return;
		} 

		System.out.println("BEGIN ALL");

		try {
			aRoot.execute();
		} catch (ConnectException e) {
			System.out.println("���� ��Զ�̷���û������, ��������demo�е�OutputServer����, ����Զ�̷���");
//			e.printStackTrace();
			return;
		} catch (Exception e) {
			System.out.println("δ֪����");
			e.printStackTrace();
			return;
		}

		System.out.println("OVER ALL");
		
		System.out.println();
		System.out.println("�� OutputServer �Ŀ���̨������Ľ��");
	}

	public static void echo() throws Exception {
		final String PIPELINE_FILE_PATH = "org/software/matter/molecule/platform/pipeline/demo/demo/echo.pipeline.xml";

		Root aRoot = Root.load(PIPELINE_FILE_PATH);

		try {
			aRoot = Root.load(PIPELINE_FILE_PATH);
		} catch (Exception e1) {
			System.out.println("�ڲ�����");
			e1.printStackTrace();
			return;
		} 

		System.out.println("BEGIN ALL");

		try {
			aRoot.execute();
		} catch (ConnectException e) {
			System.out.println("���� ��Զ�̷���û������, ��������demo�е�InputServer��OutputServer����, ����Զ�̷���");
//			e.printStackTrace();
			return;
		} catch (Exception e) {
			System.out.println("δ֪����");
			e.printStackTrace();
			return;
		}

		System.out.println("OVER ALL");
		
		System.out.println();
		System.out.println("�� OutputServer �Ŀ���̨������Ľ��");
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
