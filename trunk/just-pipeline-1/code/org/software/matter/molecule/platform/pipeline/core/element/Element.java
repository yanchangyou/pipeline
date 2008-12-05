package org.software.matter.molecule.platform.pipeline.core.element;

import org.software.matter.atom.entity.commons.Logable;
import org.software.matter.molecule.platform.pipeline.core.data.Tree;
import org.software.matter.molecule.platform.pipeline.core.data.tree0D.Date;
import org.software.matter.molecule.platform.pipeline.core.data.tree0D.Number;
import org.software.matter.molecule.platform.pipeline.core.data.tree0D.String;
import org.software.matter.molecule.platform.pipeline.core.data.tree0D.Tree0D;
import org.software.matter.molecule.platform.pipeline.core.element.common.Meta;
import org.software.matter.molecule.platform.pipeline.core.lang.Context;
import org.software.matter.molecule.platform.pipeline.exception.data.NonTreeClassException;
import org.software.matter.molecule.platform.pipeline.exception.data.PathLevelAndTreeClassArrayLengthNotMatchException;
import org.software.matter.molecule.platform.pipeline.exception.data.TreeNotFoundException;
import org.software.matter.molecule.platform.pipeline.exception.data.tree0D.Tree0DConvertException;
import org.software.matter.molecule.platform.pipeline.exception.lang.ExcuteException;

public class Element extends Tree0D implements Logable {
	
	protected Context context;

	public Element() { 
		context = new Context(this);
	}

	public Context getContext() {
		return context;
	}
	
	public void setMeta(Meta meta) {
		super.setMeta(meta);
		this.context.setMeta(meta);
	}

	public void addChildElement(Element element) {
		this.getContext().addChild(element);
		element.getContext().setParent(this);
	}

	public Date converToDate() throws Tree0DConvertException {
		// TODO Auto-generated method stub
		return null;
	}

	public Number converToNumber() throws Tree0DConvertException {
		// TODO Auto-generated method stub
		return null;
	}

	public String converToString() {
		// TODO Auto-generated method stub
		return null;
	}

	public void append(java.lang.String path, Tree data) throws TreeNotFoundException {
		// TODO Auto-generated method stub
		
	}

	public void append(Tree data) {
		// TODO Auto-generated method stub
		
	}

	public boolean cteate(java.lang.String path, Class treeClass) throws NonTreeClassException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean cteate(java.lang.String path, Class[] treeClass) throws NonTreeClassException, PathLevelAndTreeClassArrayLengthNotMatchException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(java.lang.String path) throws TreeNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	public void execute(Context context) throws ExcuteException {
		// TODO Auto-generated method stub
		
	}

	public Tree find(java.lang.String path) {
		// TODO Auto-generated method stub
		return null;
	}

	public java.lang.String getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean merge(java.lang.String path, Tree data) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean replace(java.lang.String path, Tree data) throws TreeNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}
}
