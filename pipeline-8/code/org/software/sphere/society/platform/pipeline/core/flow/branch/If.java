package org.software.sphere.society.platform.pipeline.core.flow.branch;

import java.net.ConnectException;

import org.software.sphere.society.platform.pipeline.core.data.Node;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.data.node1X.DefaultNode1X;
import org.software.sphere.society.platform.pipeline.core.lang.execute.Session;
import org.software.sphere.society.platform.pipeline.core.lang.parse.Evale;

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
	
	public DefaultNode1X getVarMap(Session clientSession) {
		return null;
	}

	public Node getPreNodeByName(String nodeName) {
		// TODO Auto-generated method stub
		return null;
	}
}
