package org.software.sphere.society.platform.omega.core.real;


public class Market extends RealNode {

	/**
	 * ��ȡsupplier
	 * 
	 */
	public Supplier getSupplier(String supplierName) {
		return (Supplier) this.getChildRealNode(supplierName);
	}
}
