package org.software.matter.molecule.platform.pipeline.core.data.tree0D;

import org.software.matter.molecule.platform.pipeline.core.data.Tree;
import org.software.matter.molecule.platform.pipeline.core.lang.Context;
import org.software.matter.molecule.platform.pipeline.exception.data.NonTreeClassException;
import org.software.matter.molecule.platform.pipeline.exception.data.PathLevelAndTreeClassArrayLengthNotMatchException;
import org.software.matter.molecule.platform.pipeline.exception.data.TreeNotFoundException;
import org.software.matter.molecule.platform.pipeline.exception.data.tree0D.Tree0DConvertException;
import org.software.matter.molecule.platform.pipeline.exception.lang.ExcuteException;

public class Number extends Tree0D {
	
	protected java.lang.Number javaNumber;
	
	public Number(java.lang.Number javaNumber) {
		this.javaNumber = javaNumber;
	}
	
	public Number() {
		this(null);
	}
	
	/**
	 * ʹ��longת��
	 */
	public Date converToDate() throws Tree0DConvertException {
		java.util.Date javaDate = null;
		javaDate = new java.util.Date(javaNumber.longValue());
		return new Date(javaDate);
	}

	public Number converToNumber() throws Tree0DConvertException {
		return this;
	}

	public String converToString() {
		return new String(javaNumber.toString());
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

	public java.lang.Number getJavaNumber() {
		return javaNumber;
	}

	public void setJavaNumber(java.lang.Number javaNumber) {
		this.javaNumber = javaNumber;
	}

	public void execute(Context context) throws ExcuteException {
		// TODO Auto-generated method stub
		
	}

	public java.lang.String getResult() {
		// TODO Auto-generated method stub
		return this.javaNumber.toString();
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

	public void append(Tree data) {
		// TODO Auto-generated method stub
		
	}
}
