package org.software.sphere.society.platform.pipeline.lang.code.flownode.pick;

import java.net.ConnectException;

import org.software.region.molecule.container.net.node.node1X.DefaultNode1X;
import org.software.sphere.society.platform.pipeline.lang.Evale;
import org.software.sphere.society.platform.pipeline.lang.Session;
import org.software.sphere.society.platform.pipeline.lang.code.datanode.DataNode;

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
