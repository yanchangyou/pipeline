package org.software.sphere.society.platform.pipeline.lang.core;

import org.software.sphere.society.platform.pipeline.exception.lang.core.actionnode.session.RequestException;
import org.software.sphere.society.platform.pipeline.exception.lang.core.actionnode.session.ResponseException;
import org.software.sphere.society.platform.pipeline.exception.lang.core.actionnode.session.SessionException;
import org.software.sphere.society.platform.pipeline.lang.code.actionnode.Request;
import org.software.sphere.society.platform.pipeline.lang.code.actionnode.Response;

/**
 * ��Դ�ĻỰ�ӿ�<br>
 * 
 * �ӿڹ���<br>
 * 1, ��������<br>
 * 2, ������Ӧ<br>
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-25 ����11:46:19
 * @file : Sessionable.java
 * @version : 0.1
 */
public interface Sessionable {

	/**
	 * ��������
	 * 
	 * @throws SessionException �׳���������е��쳣
	 */
	public void sendRequest(Request request) throws RequestException;
	
	/**
	 * ������Ӧ
	 * 
	 * @throws SessionException 
	 */
	public void dealResponse(Response response) throws  ResponseException;
	
}
