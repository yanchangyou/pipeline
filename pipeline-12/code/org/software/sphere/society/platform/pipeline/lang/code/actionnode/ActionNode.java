package org.software.sphere.society.platform.pipeline.lang.code.actionnode;

import java.util.Iterator;

import org.software.sphere.society.platform.pipeline.exception.lang.core.CoreException;
import org.software.sphere.society.platform.pipeline.lang.code.Code;
import org.software.sphere.society.platform.pipeline.lang.core.Skillable;

/**
 * ��Ϊ�ڵ�<br>
 * ���ֳ�һϵ�е���Ϊ
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-26 ����04:50:30
 * @file : ActionNode.java
 * @version : 0.1
 */
public abstract class ActionNode extends Code implements Skillable {
	
	

	/**
	 * ���Լ�������
	 */
	public void justDoIt(Skillable skill) throws CoreException {
		for (Iterator iter = nextNodeList.iterator(); iter.hasNext();) {
			Skillable subSkill = (Skillable) iter.next();
			subSkill.justDoIt(subSkill);
		}
	}
}
