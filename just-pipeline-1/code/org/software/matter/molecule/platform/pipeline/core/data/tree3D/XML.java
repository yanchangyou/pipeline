package org.software.matter.molecule.platform.pipeline.core.data.tree3D;

import org.software.matter.molecule.platform.pipeline.core.data.Tree;
import org.software.matter.molecule.platform.pipeline.core.lang.Context;
import org.software.matter.molecule.platform.pipeline.exception.data.NonTreeClassException;
import org.software.matter.molecule.platform.pipeline.exception.data.PathLevelAndTreeClassArrayLengthNotMatchException;
import org.software.matter.molecule.platform.pipeline.exception.data.TreeNotFoundException;
import org.software.matter.molecule.platform.pipeline.exception.lang.ExcuteException;


public class XML extends Tree3D {

	public void append(String path, Tree data) throws TreeNotFoundException {
	}

	public boolean delete(String path) throws TreeNotFoundException {
		return false;		
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
		return "XML";
	}

	public boolean merge(String path, Tree data) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean cteate(String path, Class treeClass) throws NonTreeClassException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean cteate(String path, Class[] treeClass) throws NonTreeClassException, PathLevelAndTreeClassArrayLengthNotMatchException {
		// TODO Auto-generated method stub
		return false;
	}

	public void append(Tree data) {
		// TODO Auto-generated method stub
	}

}
