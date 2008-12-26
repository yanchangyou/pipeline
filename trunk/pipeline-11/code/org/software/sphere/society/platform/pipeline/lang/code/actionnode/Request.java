package org.software.sphere.society.platform.pipeline.lang.code.actionnode;

import org.software.sphere.society.platform.pipeline.exception.lang.core.actionnode.session.RequestException;
import org.software.sphere.society.platform.pipeline.lang.code.datanode.DataNode;
import org.software.sphere.society.platform.pipeline.lang.core.Datable;
import org.software.sphere.society.platform.pipeline.lang.core.Sessionable;

/**
 * 请求类
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-26 上午12:08:54
 * @file : Request.java
 * @version : 0.1
 */
public class Request extends ActionNode implements Datable {
	/**
	 * 获取请求的数据, 准备方式到资源处
	 * 
	 * @return DataNode
	 */

	private DataNode requestData;

	public DataNode getRequestData() {
		return requestData;
	}

	public void justDoIt(Sessionable session) throws RequestException {
		session.sendRequest(this);
	}

	public DataNode getData() {
		return this.getRequestData();
	}

	public void setData(DataNode data) {
		requestData = data; 
	}
}
