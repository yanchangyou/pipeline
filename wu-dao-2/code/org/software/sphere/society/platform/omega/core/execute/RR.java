package org.software.sphere.society.platform.omega.core.execute;

import org.software.sphere.society.platform.omega.common.Data;

public class RR  {

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
