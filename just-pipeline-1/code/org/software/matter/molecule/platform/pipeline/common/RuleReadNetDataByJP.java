package org.software.matter.molecule.platform.pipeline.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


/**
 * 按JP规则读取网络数据
 * 规则就是 :  & 续行, && 换行
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-11-29 下午11:04:59
 * @file : RuleReadNetDataByJP.java
 * @version : 0.1
 */

public class RuleReadNetDataByJP {

	
	public static String readData(Socket socket) throws IOException { 
		BufferedReader is = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		StringBuffer requestDatabuf = new StringBuffer();
		String requestData = is.readLine();
		while (isAppend(requestData)) {
			requestDatabuf.append(getJPLineString(requestData));
			requestData = is.readLine();
		}
		requestDatabuf.append(requestData);
		return requestDatabuf.toString();
	}
	
	public static boolean isAppend(String line) {
		boolean isAppend = false;
		if (line == null || line.length() == 0) {
			isAppend = false;
		} else if (line.charAt(line.length()-1) == '&') {
			isAppend = true;
		}  else {
			isAppend = false;
		}
		return isAppend;
	}
	
	public static boolean isLineAppend(String line) {
		boolean isLineAppend = false;
		if (line == null || line.length() < 2) {
			isLineAppend = false;
		} else if (line.charAt(line.length()-1) == '&' && line.charAt(line.length()-2) != '&') {
			isLineAppend = true;
		}  else {
			isLineAppend = false;
		}
		return isLineAppend;
	}
	
	/**
	 * 对null自动处理
	 * @param line
	 * @return
	 */
	public static String getJPLineString(String line) {
		if (isAppend(line)) {
			if (isLineAppend(line)) {
				line = line.substring(0, line.length()-2) + System.getProperty("line.separator");
			} else {
				line = line.substring(0, line.length()-1);
			}
		}		
		return line == null ? "" : line;
	}
}
