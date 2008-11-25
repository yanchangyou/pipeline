package org.software.matter.molecule.platform.pipeline.core;

import org.software.matter.molecule.platform.pipeline.core.element.Root;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Service;

public class Execute {

	public static void execute(Root root) throws Exception {
		System.out.println("EXECUTE begin |--->");

		String mainPath = "main.main.main.main.main";

		Service mainService = (Service) Locator.locate(root, mainPath);

		mainService.setRoot(root);

		mainService.start();

		System.out.println("<---|EXECUTE end");
	}
}
