package org.software.matter.molecule.platform.pipeline.core.element.rr;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Request extends RR {

	public Request() {
		super();
	}
	
	public String toString() {
		return new ToStringBuilder(this).toString();
	}

	public void setRequestData(String requestData) {
		;//box.setData(requestData);
	}
	
	public String getRequestData() {
		if (data == null) {
			return null;
		}
		return data.getData();
	}

//	/**
//	 * 对源代码中的变量替换处理
//	 * @param context
//	 * @throws DataNotFoundException 
//	 * @throws TypeException 
//	 */
//	public void tuneContextToRequestData(Context context) throws DataNotFoundException, TypeException {
////		box.tuneContextToBox(context);
////		Commons a;
//		Tree treeXD = Commons.getTreeXD(this.getType());
////		treeXD.setOriginalCode(box.getOriginalCode());
//		
//		try {
//			BeanUtils.populate(treeXD, this.getMeta().getPropertyMap());
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		Box newBox = treeXD.converToBox(context);
//		
////		box.setBoxMap(newBox.getBoxMap());// = treeXD.converToBox(context);
//	}
}
