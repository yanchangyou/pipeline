package org.software.sphere.society.platform.omega.core.execute;

import org.software.sphere.society.platform.omega.common.Logable;
import org.software.sphere.society.platform.omega.core.flow.unit.Unit;
import org.software.sphere.society.platform.omega.core.real.Service;

public class Execute implements Logable {
	public static void execute(Root root) throws Exception {

		Unit mainUnit = root.getMainUnit();

		log.info("��ʼ����[main unit]����");

		log.info("���ط���ɹ�");
		
		Service mainService = new Service();
		mainService.setName("main");
		mainService.setUnit(mainUnit);
		 String port = "11001";
//		 try {
//			 port = 11001;//mainUnit.getIntPort();
//			 
//		 } catch (Exception e) {
//		 log.error("���ش���:û��ָ���˿ں�, �ڷ���[[main]]��");
//		 }
		 mainService.setPort(port);
		 
		 log.info("��ʼ��" + port + "�˿ڼ�������");
		 mainService.start();

		log.info("���з������");
		// log.info("�ر���" + mainService.getPort() + "�˿ڼ�������");
	}
}
