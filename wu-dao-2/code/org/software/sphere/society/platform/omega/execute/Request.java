package org.software.sphere.society.platform.omega.execute;


public class Request extends RR {

	public void setRequestData(String requestData) {
		;//box.setData(requestData);
	}
	
	public String getRequestData() {
		if (data == null) {
			return null;
		}
		return data.getData();
	}
}
