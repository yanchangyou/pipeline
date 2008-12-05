package org.software.matter.molecule.platform.pipeline.core.data.tree0D;

import org.software.matter.molecule.platform.pipeline.core.data.Tree;
import org.software.matter.molecule.platform.pipeline.core.lang.Context;
import org.software.matter.molecule.platform.pipeline.exception.data.NonTreeClassException;
import org.software.matter.molecule.platform.pipeline.exception.data.PathLevelAndTreeClassArrayLengthNotMatchException;
import org.software.matter.molecule.platform.pipeline.exception.data.TreeNotFoundException;
import org.software.matter.molecule.platform.pipeline.exception.data.tree0D.Tree0DConvertException;
import org.software.matter.molecule.platform.pipeline.exception.lang.ExcuteException;

public class Date extends Tree0D {
	
	protected java.util.Date javaDate;

	public Date(java.util.Date javaDate) {
		this.javaDate = javaDate;
	}
	
	public Date() {
		this(null);
	}
	
	public Date converToDate() throws Tree0DConvertException {
		return this;
	}

	public Number converToNumber() throws Tree0DConvertException {
		return new Number(new Long(this.javaDate.getTime()));
	}

	public String converToString() {
		return new String(this.javaDate.toString());
	}

	public void append(java.lang.String path, Tree data) throws TreeNotFoundException {
	}

	public boolean delete(java.lang.String path) throws TreeNotFoundException {
		return false;
	}

	public Tree find(java.lang.String path) {
		return null;
	}

	public boolean replace(java.lang.String path, Tree data) throws TreeNotFoundException {
		return false;
	}

	public java.util.Date getJavaDate() {
		return javaDate;
	}

	public void setJavaDate(java.util.Date javaDate) {
		this.javaDate = javaDate;
	}

	public void execute(Context context) throws ExcuteException {
		// TODO Auto-generated method stub
		
	}

	public java.lang.String getResult() {
		// TODO Auto-generated method stub
		return javaDate.toString();
	}

	public boolean merge(java.lang.String path, Tree data) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean cteate(java.lang.String path, Class treeClass) throws NonTreeClassException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean cteate(java.lang.String path, Class[] treeClass) throws NonTreeClassException, PathLevelAndTreeClassArrayLengthNotMatchException {
		// TODO Auto-generated method stub
		return false;
	}

	public void append(Tree data){
	}
}
