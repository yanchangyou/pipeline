package org.software.sphere.society.platform.pipeline.lang.core;

import org.software.sphere.society.platform.pipeline.exception.lang.core.CoreException;

/**
 * ���п��Դ���,ִ�е���
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-26 ����12:33:09
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
	 * ��Ӻ���� just do it
	 * @param justDoIt
	 * @throws CoreException
	 */
	public void addJustDoIt(JustDoItable justDoIt) throws CoreException;
	
}
