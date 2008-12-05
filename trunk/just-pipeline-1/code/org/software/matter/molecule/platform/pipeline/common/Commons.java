package org.software.matter.molecule.platform.pipeline.common;

import org.software.matter.molecule.platform.pipeline.core.data.Tree;
import org.software.matter.molecule.platform.pipeline.exception.type.TypeException;

public class Commons {
	
	public static Tree getTreeXD(String type) throws TypeException {
		
		type = type.replaceAll(":.*", ""); //ȥ��������
		type = type.replaceAll("-", ""); // ȥ�����
		Class clazz = null;
		try {
			clazz = Class.forName("org.software.matter.molecule.platform.pipeline.core.type.tree." + upperFirstLetter(type));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new TypeException("��֧�ִ����͵�tree, tree����ֻ��[tree-0D], [tree-1D], [tree-2D], [tree-3D], [tree-4D]");
		}
		Tree treeXD = null;
		try {
			treeXD = (Tree) clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return treeXD;
	}
	
	public static String upperFirstLetter(String data) {
		if (data == null) {
			return null;
		}
		return data.substring(0, 1).toUpperCase() + data.substring(1);
	}
}
