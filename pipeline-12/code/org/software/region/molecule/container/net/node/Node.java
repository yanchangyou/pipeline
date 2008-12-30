package org.software.region.molecule.container.net.node;

import org.apache.commons.lang.ClassUtils;
import org.software.region.molecule.container.net.nodable.Nodable;

/**
 * ��pipeline��, �������ݶ�������net��һ����<br>
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
 * 1.1, addPrecede(Net node);<br>
 * 1.2, addPrecede(String name, Net node);<br>
 * 1.3, addPrecedeByPath(String path, Net node);<br>
 * 1.4, addPrecedeByPath(String path, String name, Net node);<br>
 * 1.5, getPrecede(String name);<br>
 * 1.6, getPrecedeByPath(String path);<br>
 * 1.7, getPathInPrecede(String name); <br>
 * 1.8, getPathInPrecede(Net node); <br>
 * 1.9, containNetInPrecede(String name);<br>
 * 1.10, containPathInPrecede(String path);<br>
 * 1.11, contanLoopNetInPrecede();<br>
 * <br>
 * 2, ����<br>
 * 2.1, addNext(Net node);<br>
 * 2.2, addNext(String name, Net node);<br>
 * 2.3, addNextByPath(String path, Net node);<br>
 * 2.4, addNextByPath(String path, String name, Net node);<br>
 * 2.5, getNext(String name);<br>
 * 2.6, getNextByPath(String path);<br>
 * 2.7, getPathInNext(String name); <br>
 * 2.8, getPathInNext(Net node); <br>
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
public abstract class Node implements Nodable {

	public Node() {
		
	}
	public java.lang.String toString() {
		return "[" + ClassUtils.getShortClassName(this.getClass()) + " ";
	}
	
}
