package org.software.sphere.society.platform.pipeline.core.lang.parse;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mvel.MVEL;
import org.software.sphere.society.platform.pipeline.core.data.Node;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.flow.FlowNode;
import org.software.sphere.society.platform.pipeline.exception.data.MiddleNodeNotFountException;

public class Evale {

	public static final java.lang.String PIPELINE_VARIABLE_NAME_PATTERN = "[a-zA-Z\\$\\_\\u0100-\\uffff][\\w\\$\\-\\u0100-\\uffff]*";

	public static final java.lang.String PIPELINE_VARIABLE_PATH_PATTERN = PIPELINE_VARIABLE_NAME_PATTERN
			+ "(\\s*\\.\\s*" + PIPELINE_VARIABLE_NAME_PATTERN + ")*";

	public static java.lang.String[] substractNames(java.lang.String express) {

		Set set = new HashSet();

		Pattern pattern = Pattern.compile(PIPELINE_VARIABLE_PATH_PATTERN,
				Pattern.DOTALL);

		Matcher matcher = pattern.matcher(express);
		while (matcher.find()) {
			java.lang.String var_name = matcher.group();
			set.add(var_name);
		}

		return (java.lang.String[]) set.toArray(new java.lang.String[] {});
	}

	public static Object eval(java.lang.String express, FlowNode flow)
			throws MiddleNodeNotFountException {

		express = express.replace('\r', ' ').replace('\n', ' '); //换行处理
		express = express.replaceAll("[\\+\\-\\*/%]|==", " $0 ");//运算符前后加空格, 便于后面变量替换
		
		java.lang.String[] part = (" " + express + " ").split("\"");

		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < part.length; i++) {
			if (i % 2 == 0) { //是偶数 表示是非字符串
				java.lang.String[] var_names = substractNames(part[i]);

				for (int j = 0; j < var_names.length; j++) {
					FlowNode originalFlow = flow;
					java.lang.String value = null;
					Node node = originalFlow.getVar(new String(var_names[j]));
					if (String.class.isInstance(node)) {
						value = ((String) node).toJavaString();
					}
					part[i] = part[i].replaceAll(" " + var_names[j] + " ", value);//StringUtils.replace(part[i], var_names[j], value);
				}
				buf.append(part[i]);

			} else { //表示是字符串
				buf.append("\"").append(part[i]).append("\"");
			}
		}

		Object result = MVEL.eval(buf.toString());
		return result;
	}

	public static boolean evaleToBoolean(java.lang.String express, FlowNode flow)
			throws MiddleNodeNotFountException {
		return ((Boolean) eval(express, flow)).booleanValue();
	}

	public static void main(java.lang.String[] args) {
		java.lang.String express = "public static final java.lang.String PIPELINE_VARIABLE_NAME_PATTERN = \"[a-zA-Z\\$\\_\\u0100-\\uffff][\\w\\$\\-\\u0100-\\uffff]*\";";

		java.lang.String[] var_names = substractNames(express);
		for (int i = 0; i < var_names.length; i++) {
			System.out.println(var_names[i]);
		}
	}

}
