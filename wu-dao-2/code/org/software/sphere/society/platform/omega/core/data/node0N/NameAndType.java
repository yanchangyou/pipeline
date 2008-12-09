package org.software.sphere.society.platform.omega.core.data.node0N;

import org.software.sphere.society.platform.omega.exception.data.DataGenerateException;
import org.software.sphere.society.platform.omega.core.data.node0X.String;


public class NameAndType extends Node0N {

	final private int INDEX_OF_NAME = 0;
	
	final private int INDEX_OF_TYPE = 1;
	
	public NameAndType() {
		this.childNodes = new String[2];
	}

	public void generateByString(java.lang.String data) throws DataGenerateException {
		// TODO Auto-generated method stub
		
	}
	
//	public String getName() {
//		return this.getChildAt(INDEX_OF_NAME);
//	}
	
}
