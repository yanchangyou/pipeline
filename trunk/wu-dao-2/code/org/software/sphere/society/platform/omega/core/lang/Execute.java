package org.software.sphere.society.platform.omega.core.lang;

import org.software.sphere.atom.entity.commons.Logable;
import org.software.sphere.society.platform.omega.core.element.Root;
import org.software.sphere.society.platform.omega.core.element.esoa.Service;

public class Execute implements Logable {

	public static void execute(Root root) throws Exception {

		String mainPath = "main.main.main.main.main.main";
		log.info("��ʼ����[[" + mainPath + "]]����");
		Service mainService = (Service) root.getAbsolutePathElement(mainPath);

		log.info("���ط���ɹ�");
		int port = 0;
		try {
			port = mainService.getIntPort();
		} catch (Exception e) {
			log.error("���ش���:û��ָ���˿ں�, �ڷ���[[" + mainPath + "]]��");
		}
		
		log.info("��ʼ��" + port + "�˿ڼ�������");
		mainService.start();

		log.info("���з������");
		log.info("�ر���" + mainService.getPort() + "�˿ڼ�������");
	}
}
