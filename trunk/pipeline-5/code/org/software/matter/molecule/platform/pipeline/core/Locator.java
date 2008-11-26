package org.software.matter.molecule.platform.pipeline.core;

import org.software.matter.molecule.platform.pipeline.core.element.Root;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Economy;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Global;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Market;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Server;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Service;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Supplier;

public class Locator {

	public static Object locate(Root root, String path) {

		Object obj = root;
		String[] node = path.split("\\.");

		for (int i = 0; i < node.length; i++) {
			switch (i) {
			case 0:
				obj = ((Root) obj).getGlobal(node[i]);
				break;
			case 1:
				obj = ((Global) obj).getEconomy(node[i]);
				break;
			case 2:
				obj = ((Economy) obj).getMarket(node[i]);
				break;
			case 3:
				obj = ((Market) obj).getSupplier(node[i]);
				break;
			case 4:
				obj = ((Supplier) obj).getServer(node[i]);
				break;
			case 5:
				obj = ((Server) obj).getService(node[i]);
				break;
			}
		}
		return obj;
	}
	
	
	public static Economy locateGlobal(Root root, String path) {
		path = path.substring(0, path.indexOf('.'));
		return (Economy) locate(root, path);
	}
	
	public static Economy locateEconomy(Root root, String path) {
		path = path.substring(0, path.indexOf('.', path.indexOf('.')+1));
		return (Economy) locate(root, path);
	}
	
	public static Market locateMarket(Root root, String path) {
		path = path.substring(0, path.indexOf('.', path.indexOf('.', path.indexOf('.')+1)+1));
		return (Market) locate(root, path);
	}
	
	public static Supplier locateSupplier(Root root, String path) {
		path = path.substring(0, path.indexOf('.', path.indexOf('.', path.indexOf('.', path.indexOf('.')+1)+1)+1));
		return (Supplier) locate(root, path);
	}
	
	public static Server locateServer(Root root, String path) {
		path = path.substring(0, path.indexOf('.', path.indexOf('.', path.indexOf('.', path.indexOf('.', path.indexOf('.')+1)+1)+1)+1));
		return (Server) locate(root, path);
	}	
	
	public static Service locateService(Root root, String path) {
		return (Service) locate(root, path);
	}
}
