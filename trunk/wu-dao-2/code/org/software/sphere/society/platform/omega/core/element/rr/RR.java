package org.software.sphere.society.platform.omega.core.element.rr;

import org.software.sphere.society.platform.omega.core.common.Data;
import org.software.sphere.society.platform.omega.core.element.Element;

public class RR extends Element {

	protected Data data;

	public RR() {
		super();
	}
	
	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
	public java.lang.String toString() {
		if (data == null) {
			return null;
		}
		return super.toString() + ", data:" + data.getData();
	}
}
