package org.software.sphere.society.platform.pipeline.exception.core.data;


/**
 * ����Ľڵ�û�з��ֵ��쳣<br>
 * 
 * �ڱ���һ��·��ʱ, �������Ľڵ㲻���ھ��׳����쳣<BR>
 * 
 * ������������ǰһ���ڵ�û���ҵ�, ���ǲ��������Ľڵ�û��, ������ǰ,�������������׳����쳣
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-10 ����08:07:02
 * @file : MiddleNodeNotFountException.java
 * @version : 0.1
 */
public class NextNodeNotFountException extends DataException {
	private static final long serialVersionUID = 3227892196505005719L;

	public NextNodeNotFountException() {
		super();
	}

	public NextNodeNotFountException(String message) {
		super(message);
	}
}
