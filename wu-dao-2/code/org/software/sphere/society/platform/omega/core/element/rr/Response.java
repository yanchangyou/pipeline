package org.software.sphere.society.platform.omega.core.element.rr;


public class Response extends RR {
	
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
//		Tree responseTree = OmegaTreeCompiler.compile(responseData);
//		responseTree.execute();
//	}
//
//	public void tuneResponseDataToParentContext(Context context) {
//		context.put(this.getName(), box);
//	}
}
