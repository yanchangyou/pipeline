package org.software.sphere.society.platform.pipeline.common;

import org.software.region.molecule.container.net.node.node10.Node10;
import org.software.sphere.society.platform.pipeline.exception.core.data.DataGenerateException;
import org.software.sphere.society.platform.pipeline.exception.core.data.NextNodeNotFountException;

public class Competitor extends Node10 {

	private java.lang.String priority;
	
	private java.lang.String realService;
	
	public Object getGod() throws NextNodeNotFountException, Exception {
//		RealNode realNode = (RealNode) this.getFirstNodeInSequencePre1ableNodes();
//		log.info("开始获取真实的服务 :" + this.realService);
//		Connect unit = (Connect) Root.getFlowNode(new String(realService));
//		log.info("获取到真实的执行单元 :" + unit);
		return null;//unit.getGod();
	}
	
	public java.lang.String getPriority() {
		return priority;
	}

	public void setPriority(java.lang.String priority) {
		this.priority = priority;
	}
	
//	public java.lang.String toString() {
//		return "[" + this.getName() + ", " + this.priority + "]";
//	}

	public void generateByString(java.lang.String data) throws DataGenerateException {
		// TODO Auto-generated method stub
		
	}

	public void fromJavaString(String data) throws DataGenerateException {
		// TODO Auto-generated method stub
		
	}

	public String toJavaString() {
		// TODO Auto-generated method stub
		return null;
	}

	public java.lang.String getRealService() {
		return realService;
	}

	public void setRealService(java.lang.String realService) {
		this.realService = realService;
	}
}
