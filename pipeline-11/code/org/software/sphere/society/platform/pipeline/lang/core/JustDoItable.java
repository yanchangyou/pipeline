package org.software.sphere.society.platform.pipeline.lang.core;

import org.software.sphere.society.platform.pipeline.exception.lang.core.CoreException;

/**
 * 所有可以处理,执行的类
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-26 上午12:33:09
 * @file : JustDoItable.java
 * @version : 0.1
 */
public interface JustDoItable {

	/**
	 * just do it
	 * @param session
	 */
	public void justDoIt(Sessionable session) throws CoreException;
	
	/**
	 * 添加后面的 just do it
	 * @param justDoIt
	 * @throws CoreException
	 */
	public void addJustDoIt(JustDoItable justDoIt) throws CoreException;
	
}
