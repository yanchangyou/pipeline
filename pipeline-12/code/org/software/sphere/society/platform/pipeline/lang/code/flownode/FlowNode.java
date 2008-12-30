package org.software.sphere.society.platform.pipeline.lang.code.flownode;

import java.net.ConnectException;
import java.util.Iterator;

import org.software.sphere.society.platform.pipeline.common.Logable;
import org.software.sphere.society.platform.pipeline.common.Namable;
import org.software.sphere.society.platform.pipeline.exception.lang.core.CoreException;
import org.software.sphere.society.platform.pipeline.lang.code.Code;
import org.software.sphere.society.platform.pipeline.lang.core.Skillable;

public abstract class FlowNode extends Code implements Logable, Namable, Skillable {

	/**
	 * ���̵�����
	 */
	protected java.lang.String name;
	
//	/**
//	 * ���ļ����������̹���
//	 */
//	protected List subFlowList = new ArrayList();

	
	/**
	 * һ��ȱʡ��ִ��ģʽ:˳��ִ��
	 * @param clientSession
	 * @throws ConnectException
	 * @throws Exception
	 */
	public void defaultJustDoIt(Skillable skill) throws CoreException {
		for (Iterator iter = nextNodeList.iterator(); iter.hasNext();) {
			FlowNode flow = (FlowNode) iter.next();
			flow.justDoIt(skill);
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
