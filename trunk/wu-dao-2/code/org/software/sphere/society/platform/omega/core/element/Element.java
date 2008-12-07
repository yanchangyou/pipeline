package org.software.sphere.society.platform.omega.core.element;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.software.matter.atom.entity.commons.Logable;
import org.software.sphere.society.platform.omega.core.data.tree0D.String;
import org.software.sphere.society.platform.omega.core.data.tree1D.Array;
import org.software.sphere.society.platform.omega.core.data.tree3D.DefaultTree3D;
import org.software.sphere.society.platform.omega.core.element.common.Define;
import org.software.sphere.society.platform.omega.core.lang.Context;
import org.software.sphere.society.platform.omega.core.lang.KeyWords;
import org.software.sphere.society.platform.omega.exception.data.NotValidTreePath;

public class Element extends Array implements Logable {

	protected Context context;

	protected Define define;
	
	public Define getDefine() {
		return define;
	}

	public void setDefine(Define define) {
		this.define = define;
	}

	public Element() {
		context = new Context(this);
	}

	public Context getContext() {
		return context;
	}

	public void addChildElement(Element element) {
//		this.getContext().addChild(element);
		super.append(element);
		element.setParent(this);
		element.tuneVarToContext();
	}

	public void tuneVarToContext() {
		if (define != null) {
			Set set = this.define.getVarMap().keySet();
			for (Iterator iter = set.iterator(); iter.hasNext();) {
				java.lang.String name = (java.lang.String) iter.next();
				java.lang.String value = this.define.getVar(name);

				String data = new String(name, value);
				try {
					context.merge(name, data, DefaultTree3D.class);
				} catch (NotValidTreePath e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
	}
	
	public java.lang.String toString() {
		int elementLevel = getElementLevel();
		java.lang.String leftPad = StringUtils.leftPad(" ", elementLevel*4); 
		return "\n" + leftPad + "<" + this.getName() + "@" + ClassUtils.getShortClassName(this.getClass()) + ">"
				+ "{ \n" + leftPad + "define:{" + this.getDefine() + "}, \n" + 
				leftPad +  "meta:{ " + this.getMeta() + "}, \n" +
				leftPad + 	"context:{" + this.context + "}, \n" +
				leftPad + 	"children:{" 
				+ this.getChildren() + "}";
	}

	public int getElementLevel() {
		int elementLevel = 0;
		Element element = this;
		while (element.parent != null) {
			elementLevel ++;
			element = (Element) element.parent;
		}
		return elementLevel;
	}
	
	public Element getSuitablePathElement(java.lang.String path) {
		Element element = null;
		if (path.startsWith(KeyWords.THIS_KEY_WORLD + ".")) {
			element = getRelativePathElement(path.substring(KeyWords.THIS_KEY_WORLD.length() + 1));
		} else {
			element = getAbsolutePathElement(path);
		}
		return element;
	}
	
	public Element getRelativePathElement(java.lang.String path) {
		Element element = this;

		java.lang.String[] pathArray = path.split("\\.");

		for (int i = 0; i < pathArray.length; i++) {
			if (pathArray[i].equals(KeyWords.SUPER_KEY_WORLD)) {
				element = (Element) element.getParent();
			} else {
				element = (Element) element.getChild(pathArray[i]);
			}
		}
		return element;
	}

	public Element getAbsolutePathElement(java.lang.String path) {
		Element element = this.getRootElement();
		java.lang.String[] pathArray = path.split("\\.");
		for (int i = 0; i < pathArray.length; i++) {
			element = (Element) element.getChild(pathArray[i]);
		}
		return element;
	}
		
	public Element getRootElement() {
		Element element = this;
		while (element.getParent() != null) {
			element = (Element) element.getParent();
		}
		return element;
	}
	
	public Context getRootContext() {
		return getRootElement().getContext();
	}

}
