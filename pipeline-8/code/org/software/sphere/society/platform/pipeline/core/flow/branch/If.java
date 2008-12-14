package org.software.sphere.society.platform.pipeline.core.flow.branch;

import java.net.ConnectException;

import org.software.sphere.society.platform.pipeline.core.core.Evale;
import org.software.sphere.society.platform.pipeline.core.core.Session;
import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.data.node1X.DefaultNode1X;

public class If extends Branch {
	/**
	 * 逻辑判断的条件
	 */
	protected java.lang.String condition;

	public java.lang.String getCondition() {
		return condition;
	}

	public void setCondition(java.lang.String condition) {
		this.condition = condition;
	}

	public void execute(Session clientSession) throws ConnectException, Exception {
		if (Evale.evaleToBoolean(condition, this)) { //条件满足就执行
			super.defaultExecute(clientSession);
		}
	}
	
	public DefaultNode1X getDataNodeMap(Session clientSession) {
		return null;
	}

	public DataNode getPreNodeByName(String nodeName) {
		// TODO Auto-generated method stub
		return null;
	}
}
