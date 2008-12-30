package org.software.sphere.society.platform.pipeline.lang.core;

import org.software.sphere.society.platform.pipeline.exception.lang.core.CoreException;



/**
 * ӵ�м��ܵ�
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-26 ����02:54:37
 * @file : Skillable.java
 * @version : 0.1
 */
public interface Skillable {
	
	/**
	 * ����
	 * @param skill
	 * @throws CoreException
	 */
	public void justDoIt(Skillable skill) throws CoreException;
	
}
