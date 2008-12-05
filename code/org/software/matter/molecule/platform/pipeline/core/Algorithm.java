package org.software.matter.molecule.platform.pipeline.core;

import java.lang.reflect.Method;

import org.software.matter.atom.entity.commons.NamadObject;

public class Algorithm extends NamadObject {

	String className;

	String methodName;

	String methodParams;

	Class clazz;

	Method method;

	Class[] methodParamClasses;

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

	public Object deal(ParamList paramList) throws Exception {
		if (clazz == null) {
			throw new Exception("请设置类名");
		}
		if (methodName == null) {
			throw new Exception("请设置方法名");
		}
		Object ware = null;
		this.method = this.clazz.getDeclaredMethod(methodName,
				this.methodParamClasses);
		int paramsLength = paramList == null ? 0
				: paramList.paramMap == null ? 0 : paramList.paramMap.size();
		if (methodParamClasses == null) {

		} else if (paramsLength != this.methodParamClasses.length) {
			String msg = "";
			if (paramList == null || paramList.paramMap == null) {
				msg = "错误！没有设置参数";
			} else {
				msg = "方法参数个数错误! 方法的参数个数是:" + methodParamClasses.length
						+ ", 传递参个个数是:" + paramsLength + ", 原有是没有设置参数或传递参数个数不正确";
			}
			throw new Exception(msg);
		}

		if (methodParamClasses == null) {
			// nothing
		} else {
			for (int i = 0; i < methodParamClasses.length; i++) {
				if (!methodParamClasses[i].isInstance(paramList.paramMap.get(i
						+ ""))) { // 程序自动转换,前提 改类提过了 valueOf 转换方法
					Object value = paramList.paramMap.get(i + "");
					value = methodParamClasses[i].getDeclaredMethod("valueOf",
							new Class[] { String.class }).invoke(null,
							new Object[] { value });
					paramList.paramMap.put(i + "", value);
				}
			}
		}

		Object[] params = new Object[paramsLength];

		for (int i = 0; i < params.length; i++) {
			params[i] = paramList.paramMap.get("" + i);
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

}
