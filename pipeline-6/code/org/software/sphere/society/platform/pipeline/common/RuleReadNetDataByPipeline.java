package org.software.sphere.society.platform.pipeline.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * ��Pipeline�����ȡ��������
 * ������� :  & ����, && ����
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-11-29 ����11:04:59
 * @file : RuleReadNetDataByPipeline.java
 * @version : 0.1
 */

public class RuleReadNetDataByPipeline {

	
	public static String readData(InputStream inputStream) throws IOException { 
		BufferedReader is = new BufferedReader(new InputStreamReader(inputStream));
		StringBuffer requestDatabuf = new StringBuffer();
		String requestData = is.readLine();
		while (isAppend(requestData)) {
			requestDatabuf.append(getPipelineLineString(requestData));
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
	 * ��null�Զ�����
	 * @param line
	 * @return
	 */
	public static String getPipelineLineString(String line) {
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
