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
			throw new Exception("请在设置方法名之前先设置类名");
		}
	}

	public Object deal(Map param) throws Exception {
		if (clazz == null) {
			throw new Exception("请设置类名");
		}
		if (methodName == null) {
			throw new Exception("请设置方法名");
		}
		Object ware = null;
		this.method = this.clazz.getDeclaredMethod(methodName,
				this.methodParamClasses);
		if (method == null) {
			System.out.println("此方法不存在:" + this);
		}
		int paramsLength = param == null ? 0 : param.size();
		if (methodParamClasses == null) {

		} else if (paramsLength != this.methodParamClasses.length) {
			String msg = "";
			if (paramsLength == 0) {
				msg = "错误！没有设置参数";
			} else {
				msg = "方法参数个数错误! 方法的参数个数是:" + methodParamClasses.length
						+ ", 传递参个个数是:" + paramsLength + ", 原有是没有设置参数或传递参数个数不正确";
			}
			throw new Exception(msg);
		}

		if (methodParamClasses != null) {
			for (int i = 0; i < methodParamClasses.length; i++) {
				if (!methodParamClasses[i].isInstance(param.get((i+1) + ""))) { // 程序自动转换,前提
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
