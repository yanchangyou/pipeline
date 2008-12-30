package org.software.sphere.society.platform.pipeline.common;



public class Commons {
	
//	public static Tree getTreeXD(String type) throws TypeException {
//		
//		type = type.replaceAll(":.*", ""); //去掉子类型
//		type = type.replaceAll("-", ""); // 去掉横杠
//		Class clazz = null;
//		try {
//			clazz = Class.forName("org.software.sphere.society.platform.pipeline.core.type.tree." + upperFirstLetter(type));
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new TypeException("不支持此类型的tree, tree类型只有[tree-0D], [tree-1D], [tree-2D], [tree-3D], [tree-4D]");
//		}
//		Tree treeXD = null;
//		try {
//			treeXD = (Tree) clazz.newInstance();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return treeXD;
//	}
	
	public static java.lang.String upperFirstLetter(java.lang.String data) {
		if (data == null) {
			return null;
		}
		return data.substring(0, 1).toUpperCase() + data.substring(1);
	}
	
	
	public static String[] getPathNameArray(String pathNames) {
		java.lang.String path_javaString = null;//pathNames.toJavaString();
		java.lang.String[] pathArray = path_javaString.split("\\.");
		String[] pathNameArray = new String[pathArray.length];
		for (int i = 0; i < pathNameArray.length; i++) {
			pathNameArray[i] = new String(pathArray[i]);
		}
		return pathNameArray;
	}
	
	public static String[] convertToStringArray(java.lang.String[] javaStringArray) {
		String[] _array = new String[javaStringArray.length]; 
		for (int i = 0; i < javaStringArray.length; i++) {
			_array[i] = new String(javaStringArray[i]);
		}
		return _array;
	}
}
