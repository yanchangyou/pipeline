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
	 * ��Դ�����е�boxת��Ϊ���ݽṹ��box
	 * ���ı�Ԫ��ת��Ϊ����Ԫ��
	 * @return
	 */
	public Box convertToDataBox() {
		
		return null;
	}

}
