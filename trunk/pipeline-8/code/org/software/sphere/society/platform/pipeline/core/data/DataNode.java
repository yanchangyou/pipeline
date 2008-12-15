package org.software.sphere.society.platform.pipeline.core.data;

import org.apache.commons.lang.ClassUtils;
import org.software.sphere.society.platform.pipeline.common.Logable;
import org.software.sphere.society.platform.pipeline.common.Namable;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.exception.core.data.NextNodeNotFountException;
import org.software.sphere.society.platform.pipeline.exception.core.data.PreNodeNotFountException;

/**
 * 在pipeline中, 所有数据都看成是net的一部分<br>
 * <br>
 * Net是所有的父接口<br>
 * <br>
 * net中有的方法<br>
 * <br>
 * 1, 前驱<br>
 * 2, 后续<br>
 * <br>
 * <br>
 * 1, 前驱<br>
 * 1.1, addPrecede(Net net);<br>
 * 1.2, addPrecede(String name, Net net);<br>
 * 1.3, addPrecedeByPath(String path, Net net);<br>
 * 1.4, addPrecedeByPath(String path, String name, Net net);<br>
 * 1.5, getPrecede(String name);<br>
 * 1.6, getPrecedeByPath(String path);<br>
 * 1.7, getPathInPrecede(String name); <br>
 * 1.8, getPathInPrecede(Net net); <br>
 * 1.9, containNetInPrecede(String name);<br>
 * 1.10, containPathInPrecede(String path);<br>
 * 1.11, contanLoopNetInPrecede();<br>
 * <br>
 * 2, 后续<br>
 * 2.1, addNext(Net net);<br>
 * 2.2, addNext(String name, Net net);<br>
 * 2.3, addNextByPath(String path, Net net);<br>
 * 2.4, addNextByPath(String path, String name, Net net);<br>
 * 2.5, getNext(String name);<br>
 * 2.6, getNextByPath(String path);<br>
 * 2.7, getPathInNext(String name); <br>
 * 2.8, getPathInNext(Net net); <br>
 * 2.9, containNetInNext(String name);<br>
 * 2.10, containPathInNext(String path);<br>
 * 2.11, contanLoopNetInNext();<br>
 * <br>
 * 前驱和后续 
 * 3.1, containLoop(); 
 * 3.2, getShortestPath(Net anotherNet);
 * <br>
 * @author yanchangyou@gmail.com
 * @date : 2008-12-6 下午10:41:39
 * @file : Net.java
 * @version : 0.1
 */
public abstract class DataNode extends Data implements Namable, Logable {

	protected String nodeName;
	
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public DataNode() {
		
	}
	
	public java.lang.String getName() {
		return nodeName.toJavaString();
	}

	public void setName(java.lang.String name) {
		setNodeName(new String(name));
	}
	
	//	// * 1, 前驱
//
//	/**
//	 * 
//	 */
//	public abstract void addPrecede(Node net);
//
//	public abstract void addPrecede(String name, Node net);
//
//	public abstract void addPrecedeByPath(String path, Node net);
//
//	public abstract void addPrecedeByPath(String path, String name, Node net);
//
	public abstract DataNode getPreNodeByName(String nodeName);

	public abstract DataNode getPreNodeByPath(String pathName) throws PreNodeNotFountException;
	
	public abstract DataNode getPreNodeByPath(String[] pathNamesArray) throws PreNodeNotFountException;
//
//	public abstract void getPathInPrecede(String name);
//
//	public abstract void getPathInPrecede(Node net);
//
//	public abstract void containNetInPrecede(String name);
//
//	public abstract void containPathInPrecede(String path);
//
//	public abstract void contanLoopNetInPrecede();
//
//	// * 2, 后续
//	public abstract void addNext(Node net);
//
//	public abstract void addNext(String name, Node net);
//
//	public abstract void addNextByPath(String path, Node net);
//
//	public abstract void addNextByPath(String path, String name, Node net);
//
	public abstract DataNode getNextNodeByName(String nodeName);

	public abstract DataNode getNextNodeByPath(String pathName) throws NextNodeNotFountException;
	
	public abstract DataNode getNextNodeByPath(String[] pathNamesArray) throws NextNodeNotFountException;
	
//
//	public abstract void getPathInNext(String name);
//
//	public abstract void getPathInNext(Node net);
//
//	public abstract void containNetInNext(String name);
//
//	public abstract void containPathInNext(String path);
//
//	public abstract void contanLoopNetInNext();
//ch
//	// * 前驱和后续
//	public abstract void containLoop();
//
//	public abstract void getShortestPath(Node anotherNet);

	
	public java.lang.String toString() {
		if (this.getNodeName() == null) {
			log.info("why this is null, make a break point at here to know");
		}
		return "[" + ClassUtils.getShortClassName(this.getClass()) + " " + this.getNodeName();
	}
	
}
