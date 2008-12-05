package org.software.sphere.society.platform.omega.core.lang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.software.sphere.society.platform.omega.common.Commons;
import org.software.sphere.society.platform.omega.core.data.Tree;
import org.software.sphere.society.platform.omega.core.data.tree0D.String;
import org.software.sphere.society.platform.omega.exception.lang.CompileException;

public class OmegaTreeCompiler {

	public static final java.lang.String TREE_DATA_MODEL_PATTERN = "\\s*tree\\s*\\{\\s*(meta\\s*:\\s*\\{\\s*.+\\s*,\\s*)?data\\s*:\\s*\\{\\s*.+\\s*\\}\\s*";

	public static final java.lang.String TREE_TYPE_DATA_PATTERN = "\\s*<omega>\\s*<meta>\\s*<property name=\"type\">\\s*(.+?)\\s*</property>";
	
	public static final java.lang.String TREE_DATA_PATTERN = "^\\s*<omega>(?:\\s*<meta>.+?</meta>)?\\s*<data>\\s*(.+?)</data>\\s*</omega>\\s*$";
	
	public static final java.lang.String TREE_VARIABLE_PATTERN = "OMEGA\\{\\s*(([a-zA-Z\\$\\_\\u0100-\\uffff][\\w\\$\\-\\u0100-\\uffff]*(\\.[a-zA-Z\\$\\_\\u0100-\\uffff][\\w\\$\\-\\u0100-\\uffff]*)*)?(@[a-zA-Z\\$\\_\\u0100-\\uffff][\\w\\-\\u0100-\\uffff]*)?)\\s*\\}";

	public static Tree compile(java.lang.String originalCode)
			throws CompileException {

		Tree tree = getTreeTypeInstance(originalCode);
//		java.lang.String expressData = tuneToOuterExpressMode(getTreeData(originalCode));
		tree.setOriginalCode(originalCode);
//		tree.setMeta(meta);
		tree.compile();
		
		return tree;
	}
	/*
	 * 整理成外部表达式模式, 即是  "asdfsadf" + 1 类型
	 * 
	 * OMEGA{express} 是这种模式的就认为是变量
	 * 不考虑 outer模式不允许使用引号
	 */
	public static java.lang.String tuneToOuterExpressMode(java.lang.String data) {
		if (data.matches(".+OMEGA\\{(.+?)\\}.+")) { 

			StringBuffer buf = new StringBuffer();
			
			Pattern pattern = Pattern.compile("OMEGA\\{(.+?)\\}", Pattern.DOTALL);

			Matcher matcher = pattern.matcher(data);
			while (matcher.find()) {
				java.lang.String expressCode = matcher.group();
				java.lang.String express = matcher.group(1); 
				buf.append("\"").append(data.substring(0, data.indexOf(expressCode))).append("\"");
				buf.append(" + ");
				buf.append(express);
				buf.append(" + ");
			}
			buf.append("\"\"");
			data = buf.toString();
		} 
			
		return data;
	}

	public static boolean isMatchTreeDataModel(java.lang.String originalCode) {
		return originalCode.matches(TREE_DATA_MODEL_PATTERN);
	}
	
	public static java.lang.String getTreeData(java.lang.String originalCode) {

		Pattern pattern = Pattern.compile(TREE_DATA_PATTERN, Pattern.DOTALL);

		Matcher matcher = pattern.matcher(originalCode);
		java.lang.String data = null;
		if (matcher.find()) {
			data = matcher.group(1); 
		} else {
			data = "";
		}
		return data;
	}
	
	public static java.lang.String getTreeTypeClassName(java.lang.String type) {
		java.lang.String demesion = type.substring(type.indexOf('-')+1, type.indexOf(':'));
		java.lang.String subType = type.substring(type.indexOf(':')+1);
		
		java.lang.String treeClassName = "org.software.sphere.society.platform.omega.core.data.tree" + demesion + "." + Commons.upperFirstLetter(subType);
		return treeClassName;
	}
	
	public static java.lang.String getTreeType(java.lang.String originalCode) {

		Pattern pattern = Pattern.compile(TREE_TYPE_DATA_PATTERN, Pattern.DOTALL);

		Matcher matcher = pattern.matcher(originalCode);
		java.lang.String type = null;
		if (matcher.find()) {
			type = matcher.group(1); 
		} else {
			type = "tree-0D:string";
		}
		return type;
	}

	public static void getTreeMeta(java.lang.String originalCode) {
		
	}
	
	public static Tree getTreeTypeInstance(java.lang.String originalCode) {

		java.lang.String type = getTreeType(originalCode);
		
		java.lang.String treeClassName = getTreeTypeClassName(type);
		
		Class clazz = null;
		Tree tree = null;
		try {
			clazz = Class.forName(treeClassName);
			tree = (Tree) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tree;
	}
	
	public static boolean checkTreeType(java.lang.String type) {
		
		return true;
	}

	public static void main(String[] args) {
		test();
	}

	public static void test() {

		System.out.println();

	}

}
