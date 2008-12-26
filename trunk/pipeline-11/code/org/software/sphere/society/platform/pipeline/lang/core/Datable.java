package org.software.sphere.society.platform.pipeline.lang.core;

import org.software.sphere.society.platform.pipeline.lang.code.datanode.DataNode;

/**
 * 可以容纳数据的
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-26 上午01:14:14
 * @file : Datable.java
 * @version : 0.1
 */
public interface Datable {

	public DataNode getData();
	
	public void setData(DataNode data);
}
