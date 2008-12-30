package org.software.sphere.society.platform.pipeline.lang.code.flownode.serial;

import org.software.sphere.society.platform.pipeline.exception.lang.core.CoreException;
import org.software.sphere.society.platform.pipeline.lang.code.flownode.FlowNode;
import org.software.sphere.society.platform.pipeline.lang.core.Skillable;

/**
 * 串行执行, 与并行执行相对
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-18 上午03:16:53
 * @file : Serial.java
 * @version : 0.1
 */
public class Serial extends FlowNode {
	/**
	 * 做自己该做的
	 */
	public void justDoIt(Skillable skill) throws CoreException {
		super.defaultJustDoIt(skill);
	}
}
