package org.software.matter.molecule.platform.pipeline.core.element.soa;

import java.util.HashMap;
import java.util.Map;

public class Market extends SOA {


	private Map supplierMap = new HashMap();
	/**
	 * Ìí¼Ósupplier
	 * @param supplier
	 */
	public void addSupplier(Supplier supplier) {
		supplierMap.put(supplier.getName(), supplier);
	}
	

	/**
	 * »ñÈ¡supplier
	 * 
	 */
	public Supplier getSupplier(String supplierName) {
		return (Supplier) supplierMap.get(supplierName);
	}
	
	public String toString() {
		return this.getName() + " : " + supplierMap.toString();
	}

}
