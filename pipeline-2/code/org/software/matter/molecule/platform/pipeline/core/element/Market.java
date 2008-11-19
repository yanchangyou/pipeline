package org.software.matter.molecule.platform.pipeline.core.element;

import java.util.HashMap;
import java.util.Map;

import org.software.matter.atom.entity.commons.NamedObject;

public class Market extends NamedObject {


	private Map supplierMap = new HashMap();
	/**
	 * ���supplier
	 * @param supplier
	 */
	public void addSupplier(Supplier supplier) {
		supplierMap.put(supplier.getName(), supplier);
	}
	

	/**
	 * ��ȡsupplier
	 * 
	 */
	public Supplier getSupplier(String supplierName) {
		return (Supplier) supplierMap.get(supplierName);
	}
	
	public String toString() {
		return this.getName() + " : " + supplierMap.toString();
	}

}
