package org.software.sphere.society.platform.omega.core.real;

import org.software.sphere.society.platform.omega.core.data.node0X.String;

public class Market extends RealNode {

	/**
	 * ªÒ»°supplier
	 * 
	 */
	public Supplier getSupplier(String supplierName) {
		return (Supplier) this.getNextRealNode(supplierName);
	}
}
