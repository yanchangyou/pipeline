package org.software.sphere.society.platform.omega.real;

import org.software.sphere.society.platform.omega.data.node0X.String;

public class Market extends RealNode {

	/**
	 * ��ȡsupplier
	 * 
	 */
	public Supplier getSupplier(String supplierName) {
		return (Supplier) this.getChildRealNode(supplierName);
	}
}
