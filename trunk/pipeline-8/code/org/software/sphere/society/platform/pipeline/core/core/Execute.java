package org.software.sphere.society.platform.pipeline.core.core;

import org.software.sphere.society.platform.pipeline.common.Logable;
import org.software.sphere.society.platform.pipeline.core.core.unit.Unit;
import org.software.sphere.society.platform.pipeline.core.real.Service;

public class Execute implements Logable {
	public static void execute(Root root) throws Exception {

		Unit selfUnit = null;//root.getselfUnit();

		log.info("��ʼ����[self unit]����");

		log.info("���ط���ɹ�");
		
		Service selfService = new Service();
		selfService.setName("self");
		selfService.setFlow(selfUnit);
		 String port = "11001";
//		 try {
//			 port = 11001;//selfUnit.getIntPort();
//			 
//		 } catch (Exception e) {
//		 log.error("���ش���:û��ָ���˿ں�, �ڷ���[[self]]��");
//		 }
		 selfService.setPort(port);
		 
		 log.info("��ʼ��" + port + "�˿ڼ�������");
		 selfService.start();

		log.info("���з������");
		// log.info("�ر���" + selfService.getPort() + "�˿ڼ�������");
	}
}
