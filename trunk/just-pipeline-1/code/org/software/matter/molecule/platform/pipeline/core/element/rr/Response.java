package org.software.matter.molecule.platform.pipeline.core.element.rr;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Response extends RR {

//	protected Box responseBox;
	
	public java.lang.String toString() {
		return new ToStringBuilder(this).toString();
	}
	
	public java.lang.String getResponseData() {
		if (data == null) {
			return null;
		}
		return data.getData();
	}
//	/**
//	 * 处理响应的数据
//	 * 
//	 * 这里要完善 
//	 * @param responseData
//	 * @param type
//	 */
//	//TODO 处理响应的数据 待完善
//	public void dealResponseData(java.lang.String responseData) {
//		
//		Tree responseTree = JPTreeCompiler.compile(responseData);
//		responseTree.execute();
//	}
//
//	public void tuneResponseDataToParentContext(Context context) {
//		context.put(this.getName(), box);
//	}
}
