package org.software.sphere.society.platform.pipeline.core.lang.execute;


import org.software.sphere.society.platform.pipeline.core.data.node0X.String;

/**
 * 这个类列出所有的语言中使用的关键字
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-11-28 下午06:46:27
 * @file : KeyWord.java
 * @version : 0.1
 */
public class KeyWords {

	/**
	 * 客户端请求的socket, 将它放在第一个上下文中
	 */
	public final static String CLIENT_IN_FIRST_CONTEXT_KEY_WORD = new String("client");
	
	public final static String THIS_KEY_WORLD = new String("this");
	
	public final static String SUPER_KEY_WORLD = new String("super");
	
	public final static String ROOT_KEY_WORLD = new String("root");
	
	public final static String DATA_KEY_WORLD = new String("data");
}
