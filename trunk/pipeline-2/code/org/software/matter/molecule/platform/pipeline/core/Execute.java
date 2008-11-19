package org.software.matter.molecule.platform.pipeline.core;

import org.software.matter.molecule.platform.pipeline.core.element.Root;

public class Execute {

	
	public static void execute(Root root) {
		System.out.println("EXECUTE begin |--->");
		System.out.println(root);
		System.out.println("<---|EXECUTE end");
	}
}
