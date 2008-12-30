package org.software.sphere.society.platform.pipeline.lang.code.resourcenode;

import java.util.Iterator;

import org.software.sphere.society.platform.pipeline.exception.lang.core.CoreException;
import org.software.sphere.society.platform.pipeline.exception.lang.core.actionnode.session.RequestException;
import org.software.sphere.society.platform.pipeline.lang.code.actionnode.Speak;
import org.software.sphere.society.platform.pipeline.lang.code.mediumnode.program.unit.String;
import org.software.sphere.society.platform.pipeline.lang.core.Contentable;
import org.software.sphere.society.platform.pipeline.lang.core.Skillable;
import org.software.sphere.society.platform.pipeline.lang.core.Speakable;

/**
 * 实现控制台<br>
 * 
 * java的system.in & system.out
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-25 下午11:58:48
 * @file : Console.java
 * @version : 0.1
 */
public class Console extends ResourceNode implements Speakable {

	/**
	 * 做自己该做的
	 */
	public void justDoIt(Skillable skill) throws CoreException {
		for (Iterator iter = nextNodeList.iterator(); iter.hasNext();) {
			Skillable subSkill = (Skillable) iter.next();
			subSkill.justDoIt(this);
		}
	}
	
	public void speak(Speak speak) throws CoreException {
		Contentable content = speak.getSpeakContent();
		if (!String.class.isInstance(content)) {
			throw new RequestException("数据类型错误! 控制台只能处理字符串数据类型");
		}
		String string = (String)content;
		
		System.out.println(string.getJavaString());
		
	}
}
