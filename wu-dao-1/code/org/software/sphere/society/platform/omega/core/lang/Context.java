package org.software.sphere.society.platform.omega.core.lang;

import java.util.Map;

import org.software.sphere.society.platform.omega.core.data.tree3D.DefaultTree3D;
import org.software.sphere.society.platform.omega.core.element.Element;


/**
 * 上下文<br>
 * 目标 1, 元素导航---向上向下导航<br>
 * 目标 2, 变量存储<br>
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-2 下午09:30:51
 * @file : Context.java
 * @version : 0.1
 */
public class Context extends DefaultTree3D {

	protected Element thisElement;
	
	public Context(Element thisElement) {
		super();
		this.thisElement = thisElement;
		this.parent = null;
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
	
	public Context getSuitablePathContext(java.lang.String path) {
		return getSuitablePathElement(path).getContext();
	}

	public Element getRelativePathElement(java.lang.String path) {
		Context context = this;
		Element element = null;

		java.lang.String[] pathArray = path.split("\\.");

		for (int i = 0; i < pathArray.length; i++) {
			if (pathArray[i].equals(KeyWords.SUPER_KEY_WORLD)) {
				element = context.getParentElement();
				context = element.getContext();
			} else {
				element = context.getChildElement(pathArray[i]);
				context = element.getContext();
			}
		}
		return element;
	}
	
	public Context getRelativePathContext(java.lang.String path) {
		return getRelativePathElement(path).getContext();
	}

	public Element getAbsolutePathElement(java.lang.String path) {
		Element element = this.getRootElement();
		Context context = element.getContext();
		java.lang.String[] pathArray = path.split("\\.");
		for (int i = 0; i < pathArray.length; i++) {
			element = context.getChildElement(pathArray[i]);
			context = element.getContext();
		}
		return element;
	}
	
	public Context getAbsolutePathContext(java.lang.String path) {
		return getAbsolutePathElement(path).getContext();
	}
		
	public Element getThisElement() {
		return thisElement;
	}

	public Element getRootElement() {
		Element element = this.getThisElement();
		Context context = element.getContext();
		while (context.getParent() != null) {
			element = context.getParentElement();
			context = element.getContext();
		}
		
		return element;
	}
	
	public Context getRootContext() {
		return getRootElement().getContext();
	}
	
	public Map getChildren() {
		return children;
	}
	
	public Element getChildElement(java.lang.String childName) {
		return (Element) children.get(childName);
	}
	
	public void addChild(Element element) {
		if (element == null) {
			throw new RuntimeException("添加的元素为空, 请检查");
		}
		if (element.getName() == null) {
			element.setName("" + index);
			index ++;
		}
		this.children.put(element.getName(), element);
	}
	
	public void addChild(java.lang.String name, Element element) {
		element.setName(name);
		this.addChild(element);
	}

	public void setChildren(Map children) {
		this.children = children;
	}

	public void setParentElement(Element parent) {
		this.setParent(parent);
	}

	public Element getParentElement() {
		return (Element) this.getParent();
	}
	
	public String toString(){ 
		return this.children.toString() + "\n";
	}
}
