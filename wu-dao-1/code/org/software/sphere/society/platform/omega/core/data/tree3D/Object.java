package org.software.sphere.society.platform.omega.core.data.tree3D;

import org.mvel.CompileException;
import org.software.sphere.society.platform.omega.core.data.Tree;
import org.software.sphere.society.platform.omega.core.lang.Context;
import org.software.sphere.society.platform.omega.exception.data.NonTreeClassException;
import org.software.sphere.society.platform.omega.exception.data.PathLevelAndTreeClassArrayLengthNotMatchException;
import org.software.sphere.society.platform.omega.exception.data.TreeNotFoundException;
import org.software.sphere.society.platform.omega.exception.lang.ExcuteException;

public class Object extends Tree3D {

//	public java.lang.String getVisibility();

	public void append(String path, Tree data) throws TreeNotFoundException {
	}

	public Tree delete(String path) throws TreeNotFoundException {
		return null;		
	}

	public Tree find(String path) {
		return null;
	}

	public boolean replace(String path, Tree data) throws TreeNotFoundException {
		return false;		
	}

	public void execute(Context context) throws ExcuteException {
		// TODO Auto-generated method stub
		
	}

	public String getResult() {
		// TODO Auto-generated method stub
		return "OBJECT";
	}

	public boolean merge(String path, Tree data) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean create(String path, Class treeClass) throws NonTreeClassException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean create(String path, Class[] treeClass) throws NonTreeClassException, PathLevelAndTreeClassArrayLengthNotMatchException {
		// TODO Auto-generated method stub
		return false;
	}

	public void append(Tree data) {
		// TODO Auto-generated method stub
	}

	public void compile() throws CompileException {
		// TODO Auto-generated method stub
		
	}
}
