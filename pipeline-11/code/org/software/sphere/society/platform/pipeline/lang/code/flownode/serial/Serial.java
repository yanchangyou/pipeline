package org.software.sphere.society.platform.pipeline.lang.code.flownode.serial;

import java.net.ConnectException;

import org.software.sphere.society.platform.pipeline.lang.Session;
import org.software.sphere.society.platform.pipeline.lang.code.flownode.FlowNode;

/**
 * 串行执行, 与并行执行相对
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-18 上午03:16:53
 * @file : Serial.java
 * @version : 0.1
 */
public class Serial extends FlowNode {

	public void execute(Session clientSession) throws ConnectException, Exception {
		super.defaultExecute(clientSession);
	}

}
