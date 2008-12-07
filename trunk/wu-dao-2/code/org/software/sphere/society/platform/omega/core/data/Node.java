package org.software.sphere.society.platform.omega.core.data;

/**
 * ��omega��, �������ݶ�������net��һ����<br>
 * <br>
 * Net�����еĸ��ӿ�<br>
 * <br>
 * net���еķ���<br>
 * <br>
 * 1, ǰ��<br>
 * 2, ����<br>
 * <br>
 * <br>
 * 1, ǰ��<br>
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
 * 2, ����<br>
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
 * ǰ���ͺ��� 
 * 3.1, containLoop(); 
 * 3.2, getShortestPath(Net anotherNet);
 * <br>
 * @author yanchangyou@gmail.com
 * @date : 2008-12-6 ����10:41:39
 * @file : Net.java
 * @version : 0.1
 */
public abstract class Node {

	// * 1, ǰ��

	/**
	 * 
	 */
	public abstract void addPrecede(Node net);

	public abstract void addPrecede(String name, Node net);

	public abstract void addPrecedeByPath(String path, Node net);

	public abstract void addPrecedeByPath(String path, String name, Node net);

	public abstract void getPrecede(String name);

	public abstract void getPrecedeByPath(String path);

	public abstract void getPathInPrecede(String name);

	public abstract void getPathInPrecede(Node net);

	public abstract void containNetInPrecede(String name);

	public abstract void containPathInPrecede(String path);

	public abstract void contanLoopNetInPrecede();

	// * 2, ����
	public abstract void addNext(Node net);

	public abstract void addNext(String name, Node net);

	public abstract void addNextByPath(String path, Node net);

	public abstract void addNextByPath(String path, String name, Node net);

	public abstract void getNext(String name);

	public abstract void getNextByPath(String path);

	public abstract void getPathInNext(String name);

	public abstract void getPathInNext(Node net);

	public abstract void containNetInNext(String name);

	public abstract void containPathInNext(String path);

	public abstract void contanLoopNetInNext();

	// * ǰ���ͺ���
	public abstract void containLoop();

	public abstract void getShortestPath(Node anotherNet);

}