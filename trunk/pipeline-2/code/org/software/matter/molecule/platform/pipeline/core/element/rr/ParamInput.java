package org.software.matter.molecule.platform.pipeline.core.element.rr;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.software.matter.molecule.platform.pipeline.core.context.PipelineContext;
import org.software.matter.molecule.platform.pipeline.core.element.pipeline.Declare;

public class ParamInput extends Input {

	private Declare declare;

	public Declare getDeclare() {
		return declare;
	}

	public void setDeclare(Declare declare) {
		this.declare = declare;
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
		Set set = declare.getParamMap().keySet();
		
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			buf.append(declare.getParam(name));
		}
//		declare.getParamMap()..values();
		return buf.toString();
	}

	public void setData(String data) {
		// TODO Auto-generated method stub
		
	}

	public void tunePipelineContextToParam(PipelineContext pipelineContext) {

		List list = declare.getReferParamList();
		
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			declare.addParam(name, pipelineContext.get(name));
		}		
	}

}
