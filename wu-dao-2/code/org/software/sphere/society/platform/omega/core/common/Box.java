package org.software.sphere.society.platform.omega.core.common;

import java.util.ArrayList;
import java.util.List;

public class Box {

	protected List lineList = new ArrayList();

	protected StringBuffer lineBuffer = new StringBuffer();

	public void addLine(LineString line) {
		lineList.add(line);
	}
	
	/**
	 * 将源代码中的box转换为数据结构的box
	 * 将文本元素转换为程序元素
	 * @return
	 */
	public Box convertToDataBox() {
		
		return null;
	}

}
