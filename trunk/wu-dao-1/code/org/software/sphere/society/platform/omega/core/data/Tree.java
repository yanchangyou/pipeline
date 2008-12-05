package org.software.sphere.society.platform.omega.core.data;

import java.util.HashMap;
import java.util.Map;

import org.mvel.CompileException;
import org.software.sphere.atom.entity.commons.Logable;
import org.software.sphere.atom.entity.commons.Metable;
import org.software.sphere.atom.entity.commons.NameAndTypeObject;
import org.software.sphere.society.platform.omega.core.data.tree3D.DefaultTree3D;
import org.software.sphere.society.platform.omega.core.element.common.Meta;
import org.software.sphere.society.platform.omega.core.lang.Context;
import org.software.sphere.society.platform.omega.core.lang.KeyWords;
import org.software.sphere.society.platform.omega.exception.data.NonTreeClassException;
import org.software.sphere.society.platform.omega.exception.data.NotValidTreePath;
import org.software.sphere.society.platform.omega.exception.data.PathLevelAndTreeClassArrayLengthNotMatchException;
import org.software.sphere.society.platform.omega.exception.data.TreeNotFoundException;
import org.software.sphere.society.platform.omega.exception.lang.ExcuteException;


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
public abstract class Tree extends NameAndTypeObject implements Metable, Logable {

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
	
	
	public Tree getSuitablePathTree(java.lang.String path) throws NotValidTreePath {
		Tree tree = null;
		if (path.startsWith(KeyWords.THIS_KEY_WORLD + ".")) {
			java.lang.String realPath = path.substring(KeyWords.THIS_KEY_WORLD.length() + 1);
			tree = this.find(realPath);
		} else {
			tree = getAbsolutePathTree(path);
		}
		return tree;
	}
	
	public Tree getAbsolutePathTree(java.lang.String path) throws NotValidTreePath {
		Tree root = this.getRootTree();
		return root.find(path);
	}
	
	public Tree getRootTree() {
		Tree tree = this;
		while (tree.getParent() != null) {
			tree = tree.getParent();
		}
		
		return tree;
	}
	
	public void checkPath(String path) throws NotValidTreePath {
		if (path == null || path.trim().length() == 0 || !path.trim().matches("^([a-zA-Z\\$\\_\\u0100-\\uffff][\\w\\$\\-\\u0100-\\uffff]*(\\.[a-zA-Z\\$\\_\\u0100-\\uffff][\\w\\$\\-\\u0100-\\uffff]*)*)?(@[a-zA-Z\\$\\_\\u0100-\\uffff][\\w\\-\\u0100-\\uffff]*)?$")) {
			throw new NotValidTreePath("错误 : 无效路径, 此路径是:" + path);
		}
	}
	
	public abstract Tree find(java.lang.String path) throws NotValidTreePath ;
	
	public abstract boolean cteate(java.lang.String path, Class treeClass) throws NotValidTreePath, NonTreeClassException;
	
	public abstract boolean cteate(java.lang.String path, Class[] treeClass) throws NotValidTreePath, NonTreeClassException, PathLevelAndTreeClassArrayLengthNotMatchException;
	
	public abstract void append(java.lang.String path, Tree data) throws NotValidTreePath, TreeNotFoundException;
	
	public abstract void append(Tree data);
	
	public abstract boolean delete(java.lang.String path) throws NotValidTreePath, TreeNotFoundException;
	
	public abstract boolean replace(java.lang.String path, Tree data) throws NotValidTreePath, TreeNotFoundException;
	
	public abstract boolean merge(java.lang.String path, Tree data) throws NotValidTreePath;
	
	public void merge(Tree data) {
		append(data);
	}
	
	public abstract java.lang.String getResult();
	
	public abstract void execute(Context context) throws ExcuteException;
	
	public abstract void compile() throws CompileException ;
	
	public static void main(String[] args) throws NotValidTreePath {
		
		Tree tree = new DefaultTree3D();
		
		//
		String[] javaRightPathArray = new String[]{"@property1", "object1@property1", "this.object1", "this.object_1@property_1", "this.object$1@property-1", "this.object$1@property-1"};
		for (int i = 0; i < javaRightPathArray.length; i++) {
			tree.checkPath(javaRightPathArray[i]);
		}
		
		String[] xmlRightPathArray = new String[]{"this.object1@property1", "this.object-1@property-1", "this.object$1@property-1", "this.object$1@property-1"};
		for (int i = 0; i < xmlRightPathArray.length; i++) {
			tree.checkPath(xmlRightPathArray[i]);
		}
		
		String[] 汉字Array = new String[]{"@属性1", "对象1@属性1", "本身.对象1", "本身.对象_1@对象_1", "本身.对象$1@对象-1", "本身.对象$1@对象-1"};
		for (int i = 0; i < 汉字Array.length; i++) {
			tree.checkPath(汉字Array[i]);
		}
		
		String[] errotRightPathArray = new String[]{".@property1", ".object1@property1", "this..object1", "this.object_1.@property_1", "this", "this.object$1@property-1"};
		for (int i = 0; i < errotRightPathArray.length; i++) {
			try {
				tree.checkPath(errotRightPathArray[i]);	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		System.out.println(errotRightPathArray.length);
	}
	
	protected Meta meta;
	
	public Meta getMeta() {
		return meta;
	}
	
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	
	public String toString() {
		return "tree : {" + this.getName() + ":" + this.children + "}";
	}
}
