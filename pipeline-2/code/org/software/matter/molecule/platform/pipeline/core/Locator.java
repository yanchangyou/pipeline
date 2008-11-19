package org.software.matter.molecule.platform.pipeline.core;

import org.software.matter.molecule.platform.pipeline.core.element.Economy;
import org.software.matter.molecule.platform.pipeline.core.element.Market;
import org.software.matter.molecule.platform.pipeline.core.element.Root;
import org.software.matter.molecule.platform.pipeline.core.element.Server;
import org.software.matter.molecule.platform.pipeline.core.element.Service;
import org.software.matter.molecule.platform.pipeline.core.element.Supplier;

public class Locator {

	public static Object locate(Root root, String path) {

		Object obj = root;
		String[] node = path.split("\\.");

		for (int i = 0; i < node.length; i++) {
			switch (i) {
			case 0:
				obj = ((Root) obj).getEconomy(node[i]);
				break;
			case 1:
				obj = ((Economy) obj).getMarket(node[i]);
				break;
			case 2:
				obj = ((Market) obj).getSupplier(node[i]);
				break;
			case 3:
				obj = ((Supplier) obj).getServer(node[i]);
				break;
			case 4:
				obj = ((Server) obj).getService(node[i]);
				break;
			}
		}
		return obj;
	}
	
	
	public static Economy locateEconomy(Root root, String path) {
		path = path.substring(0, path.indexOf('.'));
		return (Economy) locate(root, path);
	}
	
	public static Market locateMarket(Root root, String path) {
		path = path.substring(0, path.indexOf('.', path.indexOf('.')+1));
		return (Market) locate(root, path);
	}
	
	public static Supplier locateSupplier(Root root, String path) {
		path = path.substring(0, path.indexOf('.', path.indexOf('.', path.indexOf('.')+1)+1));
		return (Supplier) locate(root, path);
	}
	
	public static Server locateServer(Root root, String path) {
		path = path.substring(0, path.indexOf('.', path.indexOf('.', path.indexOf('.', path.indexOf('.')+1)+1)+1));
		return (Server) locate(root, path);
	}	
	
	public static Service locateService(Root root, String path) {
		return (Service) locate(root, path);
	}
}
