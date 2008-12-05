package org.software.sphere.society.platform.omega.core.data.tree0D;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mvel.CompileException;
import org.mvel.MVEL;
import org.software.sphere.society.platform.omega.core.data.Tree;
import org.software.sphere.society.platform.omega.core.lang.Context;
import org.software.sphere.society.platform.omega.core.lang.OmegaTreeCompiler;
import org.software.sphere.society.platform.omega.exception.data.NonTreeClassException;
import org.software.sphere.society.platform.omega.exception.data.NotValidTreePath;
import org.software.sphere.society.platform.omega.exception.data.PathLevelAndTreeClassArrayLengthNotMatchException;
import org.software.sphere.society.platform.omega.exception.data.TreeNotFoundException;
import org.software.sphere.society.platform.omega.exception.data.tree0D.Tree0DConvertException;
import org.software.sphere.society.platform.omega.exception.lang.ExcuteException;

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

	public void compile() throws CompileException {
		if (originalCode.indexOf("OMEGA{") != -1) { 

			StringBuffer buf = new StringBuffer();
			
			Pattern pattern = Pattern.compile(OmegaTreeCompiler.TREE_VARIABLE_PATTERN, Pattern.DOTALL);

			int beforeIndex = 0;
			Matcher matcher = pattern.matcher(originalCode);
			while (matcher.find()) {
				java.lang.String expressCode = matcher.group();
				java.lang.String express = matcher.group(1); 
				buf.append("\"").append(originalCode.substring(beforeIndex, originalCode.indexOf(expressCode))).append("\"");
				buf.append(" + ");
				buf.append(express);
				buf.append(" + ");
				
				beforeIndex = originalCode.indexOf(expressCode) + expressCode.length();
			}
			buf.append("\"\"");
			originalCode = buf.toString(); // TODO UPPER
		} 
	}

	public void execute(Context context) throws ExcuteException {

		StringBuffer buf = new StringBuffer();
		
		java.lang.String[] unitArray = this.originalCode.split("\\s*\\+\\s*");
		String data = new String("name", "cyyan");
		context.append(data);
		
		for (int i = 0; i < unitArray.length; i++) {
			if (unitArray[i] == null || unitArray[i].trim().length() == 0) {
				continue;
			}
			if (Character.isJavaIdentifierPart(unitArray[i].charAt(0))) {
				java.lang.String value = null;
				try {
//					value
					Tree tree = context.getSuitablePathTree(unitArray[i]);
					while (tree == null && context.getParentElement() != null) { //依次向上遍历
						context = context.getParentElement().getContext();
						tree = context.getSuitablePathTree(unitArray[i]);
					}
					if (tree == null) {
						throw new ExcuteException("错误:变量没有定义:" +  unitArray[i]);
					} else {
						value = tree.getResult();
					}
				} catch (NotValidTreePath e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new ExcuteException(e.getMessage());
				}
//				buf.append(value);
				buf.append(" + ");
				buf.append("\"" + value + "\"");
				buf.append(" + ");
			} else {
				buf.append(unitArray[i]);
			}
		}
		this.javaString = MVEL.eval(buf.toString()).toString(); 
	}
	
	public java.lang.String toString() {
		return "[tree-0D:string] " + this.getName() + " : " + this.javaString;
	}
}
