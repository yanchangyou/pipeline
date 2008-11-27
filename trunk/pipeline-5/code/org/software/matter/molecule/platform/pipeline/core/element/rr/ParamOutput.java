package org.software.matter.molecule.platform.pipeline.core.element.rr;

import java.util.Iterator;
import java.util.Set;

import org.software.matter.molecule.platform.pipeline.core.context.Context;
import org.software.matter.molecule.platform.pipeline.core.element.pipeline.pr.Result;

public class ParamOutput extends Output {

	protected String data;

	protected Result result;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		result.dealDefineResult(); //方法用于解决 digest 执行顺序带来的问题(先设置属性)
		this.result = result;
	}

	public String getData() {
		return data;
	}

	public void tuneResultToContext(Context context) {

		Set set = result.getResultMap().keySet();
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			context.smartPut(name, result.getResult(name));
		}
//		context.put(result.getResultMap());
	}

	public void setData(String responseData) {
		data = responseData;
		result.dealReferResult(responseData);
	}

	public void tuneContextToResult(Context context) {
		// TODO Auto-generated method stub
		
	}
}
