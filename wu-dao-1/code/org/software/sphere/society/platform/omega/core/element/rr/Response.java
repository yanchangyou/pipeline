package org.software.sphere.society.platform.omega.core.element.rr;


public class Response extends RR {
	
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
//		Tree responseTree = OmegaTreeCompiler.compile(responseData);
//		responseTree.execute();
//	}
//
//	public void tuneResponseDataToParentContext(Context context) {
//		context.put(this.getName(), box);
//	}
}
