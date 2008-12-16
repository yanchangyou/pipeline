package org.software.sphere.society.platform.pipeline.core.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mvel.MVEL;
import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.flow.FlowNode;
import org.software.sphere.society.platform.pipeline.exception.core.core.VarNotFountException;
import org.software.sphere.society.platform.pipeline.exception.core.data.NextNodeNotFountException;

public class Evale {

	public static final java.lang.String PIPELINE_VARIABLE_NAME_PATTERN = "\\b[a-zA-Z\\$\\_\\u0100-\\uffff][\\w\\$\\u0100-\\uffff]*\\b(?!\\()";

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
			throws NextNodeNotFountException, VarNotFountException {

//		express = express.replace('\r', ' ').replace('\n', ' '); //���д���
//		express = express.replaceAll("[\\+\\-\\*/%]|==", " $0 ");//�����ǰ��ӿո�, ���ں������ݽڵ��滻
		
		java.lang.String[] part = (express).split("\"");

		Map name_value_map = new HashMap();
		
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < part.length; i++) {
			if (i % 2 == 0) { //��ż�� ��ʾ�Ƿ��ַ���
				java.lang.String[] var_names = substractNames(part[i]);

				for (int j = 0; j < var_names.length; j++) {
					FlowNode originalFlow = flow;
					java.lang.String value = null;
					DataNode node = originalFlow.getDataNode(new String(var_names[j]));
					if (String.class.isInstance(node)) {
						value = ((String) node).toJavaString();
					}
					if (value == null) {
						throw new VarNotFountException("���������̽ڵ����ʵ�ڵ���������, �����ڴ����ݽڵ�, ���ȶ���, ��ʹ�����ݽڵ�:" + var_names[j]);
					}
					name_value_map.put(var_names[j], value);
//					part[i] = part[i].replaceAll(" " +  + " ", value);
				}
				buf.append(part[i]);

			} else { //��ʾ���ַ���
				buf.append("\"").append(part[i]).append("\"");
			}
		}

		Object result = MVEL.eval(buf.toString(), name_value_map);
		return result;
	}

	public static boolean evaleToBoolean(java.lang.String express, FlowNode flow)
			throws NextNodeNotFountException, VarNotFountException {
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
