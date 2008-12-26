package org.software.sphere.society.platform.pipeline.lang.core;

import org.software.sphere.society.platform.pipeline.exception.lang.core.actionnode.session.RequestException;
import org.software.sphere.society.platform.pipeline.exception.lang.core.actionnode.session.ResponseException;
import org.software.sphere.society.platform.pipeline.exception.lang.core.actionnode.session.SessionException;
import org.software.sphere.society.platform.pipeline.lang.code.actionnode.Request;
import org.software.sphere.society.platform.pipeline.lang.code.actionnode.Response;

/**
 * 资源的会话接口<br>
 * 
 * 接口功能<br>
 * 1, 发送请求<br>
 * 2, 处理响应<br>
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-25 下午11:46:19
 * @file : Sessionable.java
 * @version : 0.1
 */
public interface Sessionable {

	/**
	 * 发送请求
	 * 
	 * @throws SessionException 抛出请求过程中的异常
	 */
	public void sendRequest(Request request) throws RequestException;
	
	/**
	 * 处理响应
	 * 
	 * @throws SessionException 
	 */
	public void dealResponse(Response response) throws  ResponseException;
	
}
