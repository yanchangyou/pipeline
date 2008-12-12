package org.software.sphere.society.platform.omega.common;

import org.software.sphere.society.platform.omega.core.data.node0X.String;
import org.software.sphere.society.platform.omega.core.data.node10.Node10;
import org.software.sphere.society.platform.omega.core.flow.unit.Unit;
import org.software.sphere.society.platform.omega.core.real.Global;
import org.software.sphere.society.platform.omega.exception.data.DataGenerateException;
import org.software.sphere.society.platform.omega.exception.data.MiddleNodeNotFountException;

public class Competitor extends Node10 {

	private java.lang.String priority;
	
	private java.lang.String realService;
	
	public Object getGod() throws MiddleNodeNotFountException, Exception {
		Global global = (Global) this.getFirstNodeInSequencePre1ableNodes();
		if (!realService.trim().startsWith("self")) {
			throw new RuntimeException("抱歉!现在只支持[self]星球的查找");
		}
		log.info("开始获取真实的服务 :" + this.realService);
		Unit unit = (Unit) global.getNextNodeByPath(new String(realService.substring(realService.indexOf('.')+1)));
		log.info("获取到真实的执行单元 :" + unit);
		return unit.getGod();
	}
	
	public java.lang.String getPriority() {
		return priority;
	}

	public void setPriority(java.lang.String priority) {
		this.priority = priority;
	}
	
	public java.lang.String toString() {
		return "[" + this.getName() + ", " + this.priority + "]";
	}

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
