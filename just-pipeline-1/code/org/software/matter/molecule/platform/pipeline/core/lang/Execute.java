package org.software.matter.molecule.platform.pipeline.core.lang;

import org.software.matter.atom.entity.commons.Logable;
import org.software.matter.molecule.platform.pipeline.core.element.Root;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Service;

public class Execute implements Logable {

	public static void execute(Root root) throws Exception {

		String mainPath = "main.main.main.main.main.main";
		log.info("开始加载[[" + mainPath + "]]服务");
		Service mainService = (Service) root.getContext().getAbsolutePathElement(mainPath);

		mainService.setRoot(root);
		
		log.info("加载服务成功");
		int port = 0;
		try {
			port = mainService.getIntPort();
		} catch (Exception e) {
			log.error("严重错误:没有指定端口号, 在服务[[" + mainPath + "]]处");
		}
		
		log.info("开始在" + port + "端口监听请求");
		mainService.start();

		log.info("所有服务结束");
		log.info("关闭在" + mainService.getPort() + "端口监听请求");
	}
}
