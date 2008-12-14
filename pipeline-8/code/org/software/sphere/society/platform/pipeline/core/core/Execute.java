package org.software.sphere.society.platform.pipeline.core.core;

import org.software.sphere.society.platform.pipeline.common.Logable;
import org.software.sphere.society.platform.pipeline.core.core.unit.Unit;
import org.software.sphere.society.platform.pipeline.core.real.Service;

public class Execute implements Logable {
	public static void execute(Root root) throws Exception {

		Unit selfUnit = null;//root.getselfUnit();

		log.info("开始加载[self unit]服务");

		log.info("加载服务成功");
		
		Service selfService = new Service();
		selfService.setName("self");
		selfService.setFlow(selfUnit);
		 String port = "11001";
//		 try {
//			 port = 11001;//selfUnit.getIntPort();
//			 
//		 } catch (Exception e) {
//		 log.error("严重错误:没有指定端口号, 在服务[[self]]处");
//		 }
		 selfService.setPort(port);
		 
		 log.info("开始在" + port + "端口监听请求");
		 selfService.start();

		log.info("所有服务结束");
		// log.info("关闭在" + selfService.getPort() + "端口监听请求");
	}
}
