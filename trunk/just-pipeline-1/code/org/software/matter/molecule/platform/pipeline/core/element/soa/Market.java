package org.software.matter.molecule.platform.pipeline.core.element.soa;


public class Market extends SOA {

	public Market() {
	}

	/**
	 * ªÒ»°supplier
	 * 
	 */
	public Supplier getSupplier(String supplierName) {
		return (Supplier) this.context.getChild(supplierName);
	}
	
	public String toString() {
		return this.getName();
	}

}
