package org.software.sphere.society.platform.pipeline.lang.code.actionnode;

import org.software.sphere.society.platform.pipeline.exception.lang.core.actionnode.session.ResponseException;
import org.software.sphere.society.platform.pipeline.lang.code.datanode.DataNode;
import org.software.sphere.society.platform.pipeline.lang.core.Datable;
import org.software.sphere.society.platform.pipeline.lang.core.Sessionable;

/**
 * ��Ӧ��<br>
 * 
 * ������Ӧ�����ݹ���
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-26 ����12:26:16
 * @file : Response.java
 * @version : 0.1
 */
public class Response extends ActionNode implements Datable {
	
	/**
	 * ��Ӧ������
	 * 
	 * @return DataNode
	 */

	private DataNode responseData;

	public void setResponseData(DataNode responseData) {
		this.responseData = responseData;
	}

	public DataNode getResponseData() {
		return responseData;
	}

	public void justDoIt(Sessionable session) throws ResponseException {
		session.dealResponse(this);
	}

	public DataNode getData() {
		return this.getResponseData();
	}

	public void setData(DataNode data) {
		this.setResponseData(data);
	}
	
}
