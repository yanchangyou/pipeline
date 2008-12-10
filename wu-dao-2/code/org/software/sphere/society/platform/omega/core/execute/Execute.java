package org.software.sphere.society.platform.omega.core.execute;

import org.software.sphere.society.platform.omega.common.Logable;
import org.software.sphere.society.platform.omega.core.flow.unit.Unit;
import org.software.sphere.society.platform.omega.core.real.Service;

public class Execute implements Logable {
	public static void execute(Root root) throws Exception {

		Unit mainUnit = root.getMainUnit();

		log.info("开始加载[main unit]服务");

		log.info("加载服务成功");
		
		Service mainService = new Service();
		mainService.setName("main");
		mainService.setUnit(mainUnit);
		 String port = "11001";
//		 try {
//			 port = 11001;//mainUnit.getIntPort();
//			 
//		 } catch (Exception e) {
//		 log.error("严重错误:没有指定端口号, 在服务[[main]]处");
//		 }
		 mainService.setPort(port);
		 
		 log.info("开始在" + port + "端口监听请求");
		 mainService.start();

		log.info("所有服务结束");
		// log.info("关闭在" + mainService.getPort() + "端口监听请求");
	}
}
