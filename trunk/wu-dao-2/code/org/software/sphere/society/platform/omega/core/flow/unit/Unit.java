package org.software.sphere.society.platform.omega.core.flow.unit;

import org.software.sphere.society.platform.omega.core.flow.Flow;

public abstract class Unit extends Flow {

	
	public String toString() {
		return super.toString() + ", property define : " + this.getPropertyDefine() + ", request : " + this.request + ", response : " + this.response;
	}

}
