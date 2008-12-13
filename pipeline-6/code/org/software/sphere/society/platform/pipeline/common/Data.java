package org.software.sphere.society.platform.pipeline.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.software.sphere.society.platform.pipeline.exception.data.DataGenerateException;

public class Data {

	protected java.lang.String data;

	protected List lineList = new ArrayList();

	public void addLine(LineString line) {
		lineList.add(line);
	}

	/**
	 * 将pipeline文件中的数据转换成字符串, 按照pipeline行规则 & 续行, && 换行
	 * 
	 * @return
	 */
	public java.lang.String getData() {
		if (data == null) {
			StringBuffer lineBuffer = new StringBuffer();
			for (Iterator iter = lineList.iterator(); iter.hasNext();) {
				LineString line = (LineString) iter.next();
				lineBuffer.append(line.getLine());
			}
			data = lineBuffer.toString();
		}
		return data;
	}

	public void setData(java.lang.String data) {
		this.data = data;
	}

	public void generateByString(String data) throws DataGenerateException {
		// TODO Auto-generated method stub
		
	}

	public void dealNextNode(NodeDealer nodeDealer) {
		// TODO Auto-generated method stub
		
	}
}
