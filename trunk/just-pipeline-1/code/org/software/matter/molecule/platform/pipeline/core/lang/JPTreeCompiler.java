package org.software.matter.molecule.platform.pipeline.core.lang;

import org.software.matter.molecule.platform.pipeline.core.data.Tree;
import org.software.matter.molecule.platform.pipeline.exception.lang.CompileException;
import org.software.matter.molecule.platform.pipeline.core.data.tree0D.String;

;
public class JPTreeCompiler {

	public static final java.lang.String TREE_DATA_MODEL_PATTERN = "\\s*tree\\s*\\{\\s*(meta\\s*:\\s*\\{\\s*.+\\s*,\\s*)?data\\s*:\\s*\\{\\s*.+\\s*\\}\\s*";

	public static Tree compile(java.lang.String originalCode)
			throws CompileException {

//		if (originalCode == null
//				|| !originalCode.matches(TREE_DATA_MODEL_PATTERN)) {
//			throw new CompileException("语法错误: 正确的语法是 : \n"
//					+ TREE_DATA_MODEL_PATTERN);
//		}

		return new String(originalCode);
	}
	
	public static boolean isMatchTreeDataModel(java.lang.String originalCode) {
		return originalCode.matches(TREE_DATA_MODEL_PATTERN);
	}

	public static void main(String[] args) {
		test();
	}

	public static void test() {
		
		
		
		System.out.println();
		
		
		
	}

}
