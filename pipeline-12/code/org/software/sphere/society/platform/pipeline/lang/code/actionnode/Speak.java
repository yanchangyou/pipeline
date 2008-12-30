package org.software.sphere.society.platform.pipeline.lang.code.actionnode;

import org.software.sphere.society.platform.pipeline.exception.lang.core.CoreException;
import org.software.sphere.society.platform.pipeline.lang.code.mediumnode.MediumNode;
import org.software.sphere.society.platform.pipeline.lang.core.Contentable;
import org.software.sphere.society.platform.pipeline.lang.core.Skillable;
import org.software.sphere.society.platform.pipeline.lang.core.Speakable;

/**
 * 说<br>
 * 包括内容---data
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-26 下午02:50:04
 * @file : Speak.java
 * @version : 0.1
 */
public class Speak extends ActionNode implements Contentable {
	/**
	 * 说的内容
	 */
	private MediumNode medium;
	
	/**
	 * 具体的行为
	 * @param speaker
	 * @throws CoreException
	 */
	public void justDoIt(Speakable speaker) throws CoreException {
		speaker.speak(this);
	}

	/**
	 * 抽象的行为
	 */
	public void justDoIt(Skillable skill) throws CoreException {
		justDoIt((Speakable)skill);	
	}
	
	public Contentable getSpeakContent() {
		return getMedium();
	}

	public MediumNode getMedium() {
		return medium;
	}

	public void setMedium(MediumNode medium) {
		this.medium = medium;
	}

	
}
