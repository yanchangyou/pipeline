package org.software.sphere.society.platform.pipeline.lang.core;

import org.software.sphere.society.platform.pipeline.exception.lang.core.CoreException;
import org.software.sphere.society.platform.pipeline.lang.code.actionnode.Speak;

/**
 * 使之具备说的能力
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-26 下午02:43:48
 * @file : Speakable.java
 * @version : 0.1
 */
public interface Speakable extends Skillable {

	/**
	 * 说
	 * @param speak
	 * @throws CoreException
	 */
	public void speak(Speak speak) throws CoreException;
	
}
