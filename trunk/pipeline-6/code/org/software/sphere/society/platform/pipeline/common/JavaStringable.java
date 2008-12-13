package org.software.sphere.society.platform.pipeline.common;

import org.software.sphere.society.platform.pipeline.exception.data.DataGenerateException;

public interface JavaStringable {

	public java.lang.String toJavaString();
	
	public void fromJavaString(java.lang.String data) throws DataGenerateException;
}
