package org.software.sphere.society.platform.pipeline.lang.code.flownode.pick;

import org.software.sphere.society.platform.pipeline.exception.lang.core.CoreException;
import org.software.sphere.society.platform.pipeline.lang.core.Skillable;

public class If extends Branch {
	/**
	 * �߼��жϵ�����
	 */
	protected java.lang.String condition;

	public java.lang.String getCondition() {
		return condition;
	}

	public void setCondition(java.lang.String condition) {
		this.condition = condition;
	}

	public void justDoIt(Skillable skill) throws CoreException {
//		if (Evale.evaleToBoolean(condition, this)) { //���������ִ��
//			super.defaultJustDoIt(skill);
//		}
	}

}
