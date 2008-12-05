package org.software.sphere.society.platform.omega.core.element;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.ClassUtils;
import org.software.sphere.atom.entity.commons.Logable;
import org.software.sphere.society.platform.omega.core.data.Tree;
import org.software.sphere.society.platform.omega.core.data.tree0D.Date;
import org.software.sphere.society.platform.omega.core.data.tree0D.Number;
import org.software.sphere.society.platform.omega.core.data.tree0D.String;
import org.software.sphere.society.platform.omega.core.data.tree0D.Tree0D;
import org.software.sphere.society.platform.omega.core.data.tree3D.DefaultTree3D;
import org.software.sphere.society.platform.omega.core.lang.Context;
import org.software.sphere.society.platform.omega.exception.data.NonTreeClassException;
import org.software.sphere.society.platform.omega.exception.data.NotValidTreePath;
import org.software.sphere.society.platform.omega.exception.data.PathLevelAndTreeClassArrayLengthNotMatchException;
import org.software.sphere.society.platform.omega.exception.data.TreeNotFoundException;
import org.software.sphere.society.platform.omega.exception.data.tree0D.Tree0DConvertException;
import org.software.sphere.society.platform.omega.exception.lang.ExcuteException;

public class Element extends Tree0D implements Logable {
	
	protected Context context;

	public Element() { 
		context = new Context(this);
	}

	public Context getContext() {
		return context;
	}

	public void addChildElement(Element element) {
		this.getContext().addChild(element);
		element.getContext().setParent(this);
		tuneMetaToContext(element);
	}
	
	public void tuneMetaToContext(Element element) {
		java.lang.String metaPath = "meta";
//		context.getChildren().put(metaPath, null);
		Context context = element.getContext();
		try {
			context.cteate(metaPath, DefaultTree3D.class);
		} catch (NonTreeClassException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Set set = element.meta.getPropertyMap().keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			java.lang.String name = (java.lang.String) iter.next();
			java.lang.String value = element.meta.getProperty(name);

			String data = new String(name, value);
			try {
				context.append(metaPath, data);
			} catch (NotValidTreePath e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TreeNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
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

	public void compile() {
		// TODO Auto-generated method stub
		
	}
	
	public java.lang.String toString() {
		
		return ClassUtils.getShortClassName(this.getClass()) + ":" + this.getName() + " : " + this.getMeta() +  " : " + this.getContext();
	}
}
