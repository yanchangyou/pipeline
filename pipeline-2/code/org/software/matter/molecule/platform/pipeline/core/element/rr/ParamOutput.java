package org.software.matter.molecule.platform.pipeline.core.element.rr;

import java.util.Iterator;
import java.util.Set;

import org.software.matter.molecule.platform.pipeline.core.context.PipelineContext;
import org.software.matter.molecule.platform.pipeline.core.element.pipeline.Result;

public class ParamOutput extends Output {

	protected String data;

	protected Result result;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String getData() {
		return data;
	}

	public void tuneResultToPipelineContext(PipelineContext pipelineContext) {

		Set set = result.getResultMap().keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			pipelineContext.put(name, result.getResult(name));
		}
//		pipelineContext.put(result.getResultMap());
	}

	public void setData(String responseData) {
		data = responseData;
		result.dealReferResult(responseData);
	}
}
