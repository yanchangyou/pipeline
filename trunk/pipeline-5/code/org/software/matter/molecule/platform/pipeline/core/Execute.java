package org.software.matter.molecule.platform.pipeline.core;

import org.software.matter.molecule.platform.pipeline.core.element.Root;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Service;

public class Execute {

	public static void execute(Root root) throws Exception {
		

		String mainPath = "main.main.main.main.main.main";
		System.out.println("开始加载[[" + mainPath + "]]服务");
		Service mainService = (Service) Locator.locate(root, mainPath);

		mainService.setRoot(root);
		
		System.out.println("加载服务成功");
		
		System.out.println("开始在" + mainService.getPort() + "端口监听请求");
		mainService.start();

		System.out.println("所有服务结束");
		System.out.println("关闭在" + mainService.getPort() + "端口监听请求");
	}
}
