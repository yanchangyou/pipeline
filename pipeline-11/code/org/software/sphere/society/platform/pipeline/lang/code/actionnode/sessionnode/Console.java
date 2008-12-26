package org.software.sphere.society.platform.pipeline.lang.code.actionnode.sessionnode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.software.sphere.society.platform.pipeline.exception.lang.core.CoreException;
import org.software.sphere.society.platform.pipeline.exception.lang.core.actionnode.session.RequestException;
import org.software.sphere.society.platform.pipeline.exception.lang.core.actionnode.session.ResponseException;
import org.software.sphere.society.platform.pipeline.lang.code.actionnode.Request;
import org.software.sphere.society.platform.pipeline.lang.code.actionnode.Response;
import org.software.sphere.society.platform.pipeline.lang.code.datanode.DataNode;
import org.software.sphere.society.platform.pipeline.lang.code.datanode.program.unit.String;
import org.software.sphere.society.platform.pipeline.lang.core.JustDoItable;
import org.software.sphere.society.platform.pipeline.lang.core.Sessionable;

/**
 * 实现控制台<br>
 * 
 * java的system.in & system.out
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-25 下午11:58:48
 * @file : Console.java
 * @version : 0.1
 */
public class Console extends SessionNode implements Sessionable {

	public void dealResponse(Response response) throws ResponseException {
		BufferedReader bos = new BufferedReader(new InputStreamReader(System.in));
		java.lang.String data = null;
		try {
			data = bos.readLine();
		} catch (IOException e) {
			throw new ResponseException(e.getMessage());
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
		String responseData = new String(data);
//		list.add(responseData);
		response.setResponseData(responseData);
	}

	public void sendRequest(Request request) throws RequestException {
		DataNode dataNode = request.getRequestData();
		
		if (!String.class.isInstance(dataNode)) {
			throw new RequestException("数据类型错误! 控制台只能处理字符串数据类型");
		}
		String string = (String)dataNode;
		
		System.out.println(string.getJavaString());
	}

	/**
	 * 开始 just do it
	 */
	public void justDoIt(Sessionable session) throws CoreException {

		for (Iterator iter = justDoItList.iterator(); iter.hasNext();) {
			JustDoItable justDoIt = (JustDoItable) iter.next();
			justDoIt.justDoIt(this);
		}
	}
}
