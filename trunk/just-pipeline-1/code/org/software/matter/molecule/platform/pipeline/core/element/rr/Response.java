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
//	 * ������Ӧ������
//	 * 
//	 * ����Ҫ���� 
//	 * @param responseData
//	 * @param type
//	 */
//	//TODO ������Ӧ������ ������
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
