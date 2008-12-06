package org.software.sphere.society.platform.omega.core.data.tree1D;

import org.mvel.CompileException;
import org.software.sphere.society.platform.omega.core.data.Tree;
import org.software.sphere.society.platform.omega.core.lang.Context;
import org.software.sphere.society.platform.omega.exception.data.NonTreeClassException;
import org.software.sphere.society.platform.omega.exception.data.NotValidTreePath;
import org.software.sphere.society.platform.omega.exception.data.PathLevelAndTreeClassArrayLengthNotMatchException;
import org.software.sphere.society.platform.omega.exception.data.TreeNotFoundException;
import org.software.sphere.society.platform.omega.exception.lang.ExcuteException;

public class Array extends Tree1D {

	private int index = 0;
	
	public void append(String path, Tree data) {
		// TODO Auto-generated method stub
		this.children.put(path, data);
		
	}

	public void append(Tree data) {
		// TODO Auto-generated method stub
		if (data.getName() == null) {
			data.setName("" + index++);
		}
		this.children.put(data.getName(), data);
	}

	public void compile() throws CompileException {
		// TODO Auto-generated method stub
		
	}

	public boolean create(String path, Class treeClass) throws NotValidTreePath, NonTreeClassException {
		// TODO Auto-generated method stub
		try {
			this.create(path, new Class[]{treeClass});
		} catch (PathLevelAndTreeClassArrayLengthNotMatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean create(String path, Class[] treeClass) throws NotValidTreePath, NonTreeClassException, PathLevelAndTreeClassArrayLengthNotMatchException {
		// TODO Auto-generated method stub
		try {
			this.children.put(path, treeClass[0].newInstance());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Tree delete(String path) throws NotValidTreePath, TreeNotFoundException {
		// TODO Auto-generated method stub
		return (Tree) this.children.remove(path);
	}

	public void execute(Context context) throws ExcuteException {
		// TODO Auto-generated method stub
		
	}

	public Tree find(String path) throws NotValidTreePath {
		// TODO Auto-generated method stub
		return (Tree) this.children.get(path);
	}

	public String getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean merge(String path, Tree data) throws NotValidTreePath {
		// TODO Auto-generated method stub
		this.append(path, data);
		return true;
	}

	public boolean replace(String path, Tree data) throws NotValidTreePath, TreeNotFoundException {
		// TODO Auto-generated method stub
		this.append(path, data);
		return true;
	}

	
}
