package org.software.sphere.society.platform.pipeline.lang.code.actionnode;

import java.util.Iterator;

import org.software.sphere.society.platform.pipeline.exception.lang.core.CoreException;
import org.software.sphere.society.platform.pipeline.lang.code.Code;
import org.software.sphere.society.platform.pipeline.lang.core.Skillable;

/**
 * 行为节点<br>
 * 表现出一系列的行为
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-26 下午04:50:30
 * @file : ActionNode.java
 * @version : 0.1
 */
public abstract class ActionNode extends Code implements Skillable {
	
	

	/**
	 * 做自己该做的
	 */
	public void justDoIt(Skillable skill) throws CoreException {
		for (Iterator iter = nextNodeList.iterator(); iter.hasNext();) {
			Skillable subSkill = (Skillable) iter.next();
			subSkill.justDoIt(subSkill);
		}
	}
}
