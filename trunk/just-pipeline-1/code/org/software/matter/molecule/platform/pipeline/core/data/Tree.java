package org.software.matter.molecule.platform.pipeline.core.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.software.matter.atom.entity.commons.Logable;
import org.software.matter.atom.entity.commons.NameAndTypeAndMetaObject;
import org.software.matter.molecule.platform.pipeline.core.data.tree0D.String;
import org.software.matter.molecule.platform.pipeline.core.data.tree3D.DefaultTree3D;
import org.software.matter.molecule.platform.pipeline.core.element.common.Meta;
import org.software.matter.molecule.platform.pipeline.core.lang.Context;
import org.software.matter.molecule.platform.pipeline.exception.data.NonTreeClassException;
import org.software.matter.molecule.platform.pipeline.exception.data.PathLevelAndTreeClassArrayLengthNotMatchException;
import org.software.matter.molecule.platform.pipeline.exception.data.TreeNotFoundException;
import org.software.matter.molecule.platform.pipeline.exception.lang.ExcuteException;


/**
 * 一个遍历树形结构的操作接口类<br>
 * 
 * 拥有的操作有 : <br>
 * 1, 按指定位置查找  find(path)<br>
 * 2, 附加一个数据到指导位置 append(ath, data);<br>
 * 3, 删除指定路径数据 delete(path);<br>
 * 4, 替换指定位置的数据  replace(path, data);<br>
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-1 下午02:26:40
 * @file : Tree.java
 * @version : 0.1
 */
public abstract class Tree extends NameAndTypeAndMetaObject implements Logable {

	protected Tree parent; //向上导航
	
	protected Map children; //向下导航
	
	protected java.lang.String originalCode;
	
	protected int index;
	
	public static final int DEFAULT_MAP_LENGTH = 5;
	
	protected Tree() {
		this.children = new HashMap(DEFAULT_MAP_LENGTH);
		this.parent = null;
		this.index = 0;
	}
	
	public Map getChildren() {
		return children;
	}
	
	public Tree getChild(java.lang.String childName) {
		return (Tree) children.get(childName);
	}
	
	public void addChild(Tree tree) {
		if (tree.getName() == null) {
			tree.setName("" + index);
			index ++;
		}
		this.children.put(tree.getName(), tree);
	}
	
	public void addChild(java.lang.String name, Tree tree) {
		tree.setName(name);
		this.addChild(tree);
	}
	
	public java.lang.String getOriginalCode() {
		return originalCode;
	}
	
	public void setOriginalCode(java.lang.String originalCode) {
		this.originalCode = originalCode;
	}
	
	public Tree getParent() {
		return parent;
	}

	public void setParent(Tree parent) {
		this.parent = parent;
	}

	public void setMeta(Meta meta){
		Tree metaTree = new DefaultTree3D();
		Set set = meta.getPropertyMap().keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			java.lang.String name = (java.lang.String) iter.next();
			java.lang.String value = meta.getProperty(name);
			String data = new String(name, value);
			metaTree.append(data);
		}
		metaTree.setName("meta");
		merge(metaTree);
	}

	public abstract Tree find(java.lang.String path);
	
	public abstract boolean cteate(java.lang.String path, Class treeClass) throws NonTreeClassException;
	
	public abstract boolean cteate(java.lang.String path, Class[] treeClass) throws NonTreeClassException, PathLevelAndTreeClassArrayLengthNotMatchException;
	
	public abstract void append(java.lang.String path, Tree data) throws TreeNotFoundException;
	
	public abstract void append(Tree data);
	
	public abstract boolean delete(java.lang.String path) throws TreeNotFoundException;
	
	public abstract boolean replace(java.lang.String path, Tree data) throws TreeNotFoundException;
	
	public abstract boolean merge(java.lang.String path, Tree data) ;
	
	public void merge(Tree data) {
		append(data);
	}
	
	public abstract java.lang.String getResult();
	
	public abstract void execute(Context context) throws ExcuteException;
}
