package org.software.matter.molecule.platform.pipeline.core;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.software.matter.atom.entity.commons.NamedObject;

public class Algorithm extends NamedObject {

	private String className;

	private String methodName;

	private String methodParams;

	private Class clazz;

	private Method method;

	private Class[] methodParamClasses;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) throws ClassNotFoundException {
		this.className = className;
		clazz = Class.forName(className);
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) throws Exception {
		this.methodName = methodName;

		if (clazz == null) {
			throw new Exception("�������÷�����֮ǰ����������");
		}
	}

	public Object deal(Map param) throws Exception {
		if (clazz == null) {
			throw new Exception("����������");
		}
		if (methodName == null) {
			throw new Exception("�����÷�����");
		}
		Object ware = null;
		this.method = this.clazz.getDeclaredMethod(methodName,
				this.methodParamClasses);
		if (method == null) {
			System.out.println("�˷���������:" + this);
		}
		int paramsLength = param == null ? 0 : param.size();
		if (methodParamClasses == null) {

		} else if (paramsLength != this.methodParamClasses.length) {
			String msg = "";
			if (paramsLength == 0) {
				msg = "����û�����ò���";
			} else {
				msg = "����������������! �����Ĳ���������:" + methodParamClasses.length
						+ ", ���ݲθ�������:" + paramsLength + ", ԭ����û�����ò����򴫵ݲ�����������ȷ";
			}
			throw new Exception(msg);
		}

		if (methodParamClasses != null) {
			for (int i = 0; i < methodParamClasses.length; i++) {
				if (!methodParamClasses[i].isInstance(param.get((i+1) + ""))) { // �����Զ�ת��,ǰ��
					Object value = param.get((i+1) + "");
					value = methodParamClasses[i].getDeclaredMethod("valueOf",
							new Class[] { String.class }).invoke(null,
							new Object[] { value });
					param.put((i+1) + "", value);
				}
			}
		}

		Object[] params = new Object[paramsLength];

		for (int i = 0; i < params.length; i++) {
			params[i] = param.get("" + (i+1));
		}

		ware = this.method.invoke(this.clazz.newInstance(), params);
		return ware;
	}

	public String getMethodParams() {
		return methodParams;
	}

	public void setMethodParams(String methodParams)
			throws ClassNotFoundException {
		this.methodParams = methodParams;
		if (this.methodParams != null) {
			String[] methodParamArray = this.methodParams.split("\\s*,\\s*");
			this.methodParamClasses = new Class[methodParamArray.length];
			for (int i = 0; i < methodParamClasses.length; i++) {
				methodParamClasses[i] = Class.forName(methodParamArray[i]);
			}
		}
	}
	
	public String toString() {
		return ObjectUtils.toString(this);
	}

}
