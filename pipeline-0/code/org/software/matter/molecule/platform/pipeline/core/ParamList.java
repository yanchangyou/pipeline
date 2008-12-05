package org.software.matter.molecule.platform.pipeline.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.software.matter.atom.entity.commons.NamadObject;

public class ParamList extends NamadObject {
	
	public ParamList() {
		super();
	}

	Map paramMap = new HashMap();
	Map globalVariableNameIndexMap = new HashMap();
	
	public void addParam(String index, Object value) {
		paramMap.put(index, value);
	}
	/**
	 *  ��Ӳ���<br>
	 *  ��������;Ͱ����ͽ���ת��<br>
	 *  ���û�����;�Ĭ����String����<br>
	 * @param aParam
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void addParam(Param aParam) throws SecurityException, NoSuchMethodException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		paramMap.put(aParam.getIndex(), aParam.getValue());
	}
	
	public void setGlobalVariableNameIndexMap(String name, Object index) {
		globalVariableNameIndexMap.put(name, index);
	}
	
	public void setGlobalVariableNameIndexMap(GlobalParam aGlobalParam) {
		globalVariableNameIndexMap.put(aGlobalParam.getName(), aGlobalParam.getIndex());
	}
	
	public void addGlobalVariable(GlobalParam aGlobalParam) {
		setGlobalVariableNameIndexMap(aGlobalParam);
	}
	
	void dealGlobalVariable(Map globalVariable) throws Exception {
		
		for (Iterator iter = globalVariableNameIndexMap.keySet().iterator(); iter.hasNext();) {
			String globalName = (String) iter.next();
			
			if (paramMap.containsKey(globalVariableNameIndexMap.get(globalName))) {
				throw new Exception("����λ���ظ�, �˲�����λ�ã�" + globalVariableNameIndexMap.get(globalName) + "��ԭ�в�������" + paramMap);
			} else {
				paramMap.put(globalVariableNameIndexMap.get(globalName), globalVariable.get(globalName));
			}			
		}
	}

}
