package org.software.sphere.society.platform.omega.core.real;

import org.software.sphere.society.platform.omega.core.data.node0X.String;

public class Market extends RealNode {

	/**
	 * ��ȡsupplier
	 * 
	 */
	public Supplier getSupplier(String supplierName) {
		return (Supplier) this.getNextRealNode(supplierName);
	}
}
