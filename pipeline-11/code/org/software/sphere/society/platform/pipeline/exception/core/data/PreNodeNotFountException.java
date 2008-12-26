package org.software.sphere.society.platform.pipeline.exception.core.data;


/**
 * 前面的节点没有发现的异常<br>
 * 
 * 在遍历一条路径时, 如果前面的节点不存在就抛出此异常<BR>
 * 
 * 遍历规则允许最后一个节点没有找到, 但是不允许前面的节点没有, 这在向前,向后遍历都可能抛出此异常
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-10 下午08:07:02
 * @file : MiddleNodeNotFountException.java
 * @version : 0.1
 */
public class PreNodeNotFountException extends DataException {
	private static final long serialVersionUID = 3227892196505005719L;

	public PreNodeNotFountException() {
		super();
	}

	public PreNodeNotFountException(String message) {
		super(message);
	}
}
