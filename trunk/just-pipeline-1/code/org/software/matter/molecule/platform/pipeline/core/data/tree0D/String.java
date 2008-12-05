package org.software.matter.molecule.platform.pipeline.core.data.tree0D;

import org.software.matter.molecule.platform.pipeline.core.data.Tree;
import org.software.matter.molecule.platform.pipeline.core.lang.Context;
import org.software.matter.molecule.platform.pipeline.exception.data.NonTreeClassException;
import org.software.matter.molecule.platform.pipeline.exception.data.PathLevelAndTreeClassArrayLengthNotMatchException;
import org.software.matter.molecule.platform.pipeline.exception.data.TreeNotFoundException;
import org.software.matter.molecule.platform.pipeline.exception.data.tree0D.Tree0DConvertException;
import org.software.matter.molecule.platform.pipeline.exception.lang.ExcuteException;

public class String extends Tree0D {

	public String(java.lang.String name, java.lang.String javaString) {
		this.name = name;
		this.javaString = javaString;
	}

	public String(java.lang.String javaString) {
		this.javaString = javaString;
	}

	public String() {
		this(null);
	}

	protected java.lang.String javaString;

	public java.lang.String getJavaString() {
		return javaString;
	}

	public void setJavaString(java.lang.String javaString) {
		this.javaString = javaString;
	}

	public Date converToDate() throws Tree0DConvertException {

		return null;// new Date(javaString);
	}

	public Number converToNumber() throws Tree0DConvertException {
		// TODO Auto-generated method stub
		return null;
	}

	public String converToString() {
		return this;
	}

	public void append(java.lang.String path, Tree data)
			throws TreeNotFoundException {
	}

	public boolean delete(java.lang.String path) throws TreeNotFoundException {
		return false;
	}

	public Tree find(java.lang.String path) {
		return this;
	}

	public boolean replace(java.lang.String path, Tree data)
			throws TreeNotFoundException {
		return false;
	}

	public void execute(Context context) throws ExcuteException {
		// TODO Auto-generated method stub

	}

	public java.lang.String getResult() {
		return this.javaString;
	}

	public boolean merge(java.lang.String path, Tree data) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean cteate(java.lang.String path, Class treeClass)
			throws NonTreeClassException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean cteate(java.lang.String path, Class[] treeClass)
			throws NonTreeClassException,
			PathLevelAndTreeClassArrayLengthNotMatchException {
		// TODO Auto-generated method stub
		return false;
	}

	public void append(Tree data) {
		// TODO Auto-generated method stub
	}
}
