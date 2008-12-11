package org.software.sphere.society.platform.omega.core.flow.branch;

import java.net.ConnectException;
import java.util.Iterator;

import org.mvel.MVEL;
import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node0X.String;
import org.software.sphere.society.platform.omega.core.data.node1X.DefaultNode1X;
import org.software.sphere.society.platform.omega.core.execute.Session;
import org.software.sphere.society.platform.omega.core.flow.FlowNode;

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
		DefaultNode1X varMap = getVarMap(clientSession);
		if (MVEL.evalToBoolean(condition, varMap).booleanValue() == true) { //条件满足就执行
			for (Iterator iter = flowList.iterator(); iter.hasNext();) {
				FlowNode flow = (FlowNode) iter.next();
				flow.execute(clientSession);
			}
		}
	}
	
	public DefaultNode1X getVarMap(Session clientSession) {
		return new DefaultNode1X();
	}

	public Node getPreNodeByName(String nodeName) {
		// TODO Auto-generated method stub
		return null;
	}
}
