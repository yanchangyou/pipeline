package org.software.matter.molecule.platform.pipeline.core.element.rr;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.software.matter.molecule.platform.pipeline.core.context.Context;
import org.software.matter.molecule.platform.pipeline.core.element.pipeline.pr.Param;

public class ParamInput extends Input {

	private Param param;

	protected String separtor = "";
	
	public Param getParam() {
		return param;
	}

	public void setParam(Param param) {
		param.dealDefineParam();
		this.param = param;
	}

	public byte[] toByteType() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toJSONType() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toStringType() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toXMLType() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getData() {
		StringBuffer buf = new StringBuffer();
		Set set = param.getParamMap().keySet();
		
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			buf.append(param.getParam(name));
			buf.append(separtor);
		}
//		param.getParamMap()..values();
		return buf.substring(0, buf.length() - separtor.length()).toString();
	}

	public void setData(String data) {
		// TODO Auto-generated method stub
		
	}

	public void tuneContextToParam(Context context) {

		List list = param.getReferParamList();
		
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			param.addParam(name, context.get(name));
		}		
	}

	public String getSepartor() {
		return separtor;
	}

	public void setSepartor(String separtor) {
		this.separtor = separtor;
	}

	public void tuneParamToContext(Context context) {
		// TODO Auto-generated method stub
		
	}

}
