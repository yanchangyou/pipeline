package org.software.sphere.society.platform.omega.data.node00;

import org.software.sphere.society.platform.omega.common.JavaStringable;
import org.software.sphere.society.platform.omega.exception.data.DataGenerateException;

public class Char2 extends Node00 implements JavaStringable {

	private char _char;
	
	public void fromJavaString(java.lang.String data) throws DataGenerateException {
		// TODO Auto-generated method stub
		if (data == null || data.length() != 1) {
			throw new DataGenerateException("char2 类型只能是 一个java 字符");
		}
		_char = data.charAt(0);
	}

	public java.lang.String toJavaString() {
		return _char + "";
	}
}
