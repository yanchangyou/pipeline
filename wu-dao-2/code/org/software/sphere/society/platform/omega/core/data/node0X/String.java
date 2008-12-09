package org.software.sphere.society.platform.omega.core.data.node0X;

import org.software.sphere.society.platform.omega.core.common.JavaStringable;
import org.software.sphere.society.platform.omega.core.common.NodeDealer;
import org.software.sphere.society.platform.omega.core.data.node00.Char2;
import org.software.sphere.society.platform.omega.exception.data.DataGenerateException;

public class String extends Node0X implements JavaStringable {

	private java.lang.String string;

	public void generateByString(java.lang.String string) {
		this.string = string;
	}

	public java.lang.String toJavaString() {
		return string;
	}

	public void dealChildNode(NodeDealer nodeDealer) {
		// TODO Auto-generated method stub
		for (int i = 0; i < string.length(); i++) {
			Char2 char2 = new Char2();
			try {
				char2.generateByString(string.charAt(i)+"");
			} catch (DataGenerateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nodeDealer.deal(char2);
		}
	}
}
