package org.software.matter.molecule.platform.pipeline.core.data.tree3D;

import org.software.matter.molecule.platform.pipeline.core.data.Tree;
import org.software.matter.molecule.platform.pipeline.core.lang.Context;
import org.software.matter.molecule.platform.pipeline.exception.data.NonTreeClassException;
import org.software.matter.molecule.platform.pipeline.exception.data.PathLevelAndTreeClassArrayLengthNotMatchException;
import org.software.matter.molecule.platform.pipeline.exception.data.TreeNotFoundException;
import org.software.matter.molecule.platform.pipeline.exception.lang.ExcuteException;

public class MapTree3D extends Tree3D {

	public MapTree3D() {

	}

	public MapTree3D(String name, Tree data) {
		this.children.put(name, data);
	}

	public void append(Tree data) {
		this.children.put(data.getName(), data);
	}

	public void append(String path, Tree data) throws TreeNotFoundException {
		Tree tree = find(path);
		if (tree == null) {
			throw new TreeNotFoundException("路径没有找到");
		}
		tree.append(data);
	}

	public boolean delete(String path) throws TreeNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	public void execute(Context context) throws ExcuteException {
		// TODO Auto-generated method stub

	}

	public Tree find(String path) {
		String[] pathArray = path.split("\\.|@");
		Tree data = (Tree) children.get(pathArray[0]); //只知道本层的find方法, 其它层的调用标准接口
		if (data == null) {
			return null;
		}
		for (int i = 1; i < pathArray.length; i++) {
			data = data.find(pathArray[i]);
			if (data == null) {
				break;
			}
		}
		return data;
	}

	public String getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean merge(String path, Tree data, Class clazz) {
		Tree tree = find(path);
		if (tree == null) {
			try {
				this.cteate(path, clazz);
			} catch (NonTreeClassException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		
		try {
			replace(path, data);
		} catch (TreeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean merge(String path, Tree data) {
		return this.merge(path, data, this.getClass());
	}

	public boolean replace(String path, Tree data) throws TreeNotFoundException {
		Tree tree = find(path);
		if (tree == null) {
			throw new TreeNotFoundException("路径没有找到, 此路径是" + path);
		}
		String[] pathArray = path.split("\\.|@");
		StringBuffer pathBuf = new StringBuffer();
		for (int i = 0; i < pathArray.length; i++) {
			pathBuf.append(pathArray[i]);
			if (i != pathArray.length-1) {
				pathBuf.append('.');
			}
		}
		data.setName(pathArray[pathArray.length-1]);
		append(pathBuf.toString(), data);
		return false;
	}

	public boolean cteate(String path, Class treeClass)
			throws NonTreeClassException {

		String[] pathArray = path.split("\\.|@");
		Class[] treeClassArray = new Class[pathArray.length];
		for (int i = 0; i < treeClassArray.length; i++) {
			treeClassArray[i] = treeClass;
		}
		try {
			cteate(path, treeClassArray);
		} catch (PathLevelAndTreeClassArrayLengthNotMatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean cteate(String path, Class[] treeClassArray)
			throws NonTreeClassException,
			PathLevelAndTreeClassArrayLengthNotMatchException {
		Tree tree = this;
		Tree parent = this;
		String[] pathArray = path.split("\\.|@");
		for (int i = 0; i < pathArray.length; i++) {
			Class treeClass = treeClassArray[i];
			if (!Tree.class.isAssignableFrom(treeClass)) {
				throw new NonTreeClassException();
			}
			tree = tree.find(pathArray[i]);
			if (tree == null) {
				try {
					tree = (Tree) treeClass.newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					log.error("实例化树失败, 此树是:" + treeClass + ", 内部错误信息 :\n" + e.getMessage());
					e.printStackTrace();
					return false;
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				tree.setName(pathArray[i]);
				parent.append(tree);
				tree.setParent(parent);
			}

			parent = tree;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(Tree.class.isAssignableFrom(Tree.class));
	}
}
