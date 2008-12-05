package org.software.matter.molecule.platform.pipeline.core.lang;

import org.software.matter.atom.entity.commons.Logable;
import org.software.matter.molecule.platform.pipeline.core.element.Root;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Service;

public class Execute implements Logable {

	public static void execute(Root root) throws Exception {

		String mainPath = "main.main.main.main.main.main";
		log.info("��ʼ����[[" + mainPath + "]]����");
		Service mainService = (Service) root.getContext().getAbsolutePathElement(mainPath);

		mainService.setRoot(root);
		
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
