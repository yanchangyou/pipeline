package org.software.sphere.society.platform.omega.core.real;


public class Market extends RealNode {

	/**
	 * ªÒ»°supplier
	 * 
	 */
	public Supplier getSupplier(String supplierName) {
		return (Supplier) this.getChildRealNode(supplierName);
	}
}
