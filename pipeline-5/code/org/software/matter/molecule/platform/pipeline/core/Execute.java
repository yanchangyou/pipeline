package org.software.matter.molecule.platform.pipeline.core;

import org.software.matter.molecule.platform.pipeline.core.element.Root;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Service;

public class Execute {

	public static void execute(Root root) throws Exception {
		

		String mainPath = "main.main.main.main.main.main";
		System.out.println("��ʼ����[[" + mainPath + "]]����");
		Service mainService = (Service) Locator.locate(root, mainPath);

		mainService.setRoot(root);
		
		System.out.println("���ط���ɹ�");
		
		System.out.println("��ʼ��" + mainService.getPort() + "�˿ڼ�������");
		mainService.start();

		System.out.println("���з������");
		System.out.println("�ر���" + mainService.getPort() + "�˿ڼ�������");
	}
}
