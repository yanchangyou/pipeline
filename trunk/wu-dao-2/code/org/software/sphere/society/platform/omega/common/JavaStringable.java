package org.software.sphere.society.platform.omega.common;

import org.software.sphere.society.platform.omega.exception.data.DataGenerateException;

public interface JavaStringable {

	public java.lang.String toJavaString();
	
	public void fromJavaString(java.lang.String data) throws DataGenerateException;
}
