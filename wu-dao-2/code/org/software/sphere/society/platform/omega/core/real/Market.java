package org.software.sphere.society.platform.omega.core.real;


public class Market extends ESOA {

	public Market() {
	}

	/**
	 * ��ȡsupplier
	 * 
	 */
	public Supplier getSupplier(String supplierName) {
		return (Supplier) this.context.getChild(supplierName);
	}
}
