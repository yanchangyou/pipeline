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
 * һ���������νṹ�Ĳ����ӿ���<br>
 * 
 * ӵ�еĲ����� : <br>
 * 1, ��ָ��λ�ò���  find(path)<br>
 * 2, ����һ�����ݵ�ָ��λ�� append(ath, data);<br>
 * 3, ɾ��ָ��·������ delete(path);<br>
 * 4, �滻ָ��λ�õ�����  replace(path, data);<br>
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-1 ����02:26:40
 * @file : Tree.java
 * @version : 0.1
 */
public abstract class Tree extends NameAndTypeObject implements Metable, Logable {

	protected Tree parent; //���ϵ���
	
	protected Map children; //���µ���
	
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
			throw new NotValidTreePath("���� : ��Ч·��, ��·����:" + path);
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
		
		String[] ����Array = new String[]{"@����1", "����1@����1", "����.����1", "����.����_1@����_1", "����.����$1@����-1", "����.����$1@����-1"};
		for (int i = 0; i < ����Array.length; i++) {
			tree.checkPath(����Array[i]);
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
