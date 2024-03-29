package org.software.sphere.society.platform.omega.core.element.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Data {

	protected java.lang.String data;

	protected List lineList = new ArrayList();

	public void addLine(LineString line) {
		lineList.add(line);
	}

	/**
	 * 将omega文件中的数据转换成字符串, 按照omega行规则 & 续行, && 换行
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
}
