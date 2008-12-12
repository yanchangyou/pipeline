package org.software.sphere.society.platform.omega.core.real;

import org.software.sphere.society.platform.omega.common.Service;
import org.software.sphere.society.platform.omega.core.data.node0X.String;
import org.software.sphere.society.platform.omega.exception.data.MiddleNodeNotFountException;
import org.software.sphere.society.platform.omega.exception.execute.NoAvailableGodException;
import org.software.sphere.society.platform.omega.exception.execute.NoFoundDefineServiceException;

public class Global extends RealNode {

	/**
	 * 获取economy
	 * 
	 */
	public Economy getEconomy(String economyName) {
		return (Economy) this.getNextRealNode(economyName);
	}
	
	public Object getGod(String godHome) throws MiddleNodeNotFountException, NoAvailableGodException, NoFoundDefineServiceException {
		java.lang.String[] part = godHome.toJavaString().split("@");
		java.lang.String defineServiceName = part[0];
		RealNode realNode = null;
		if (part.length == 1) {
			realNode = this;
		} else {
			java.lang.String realPath = part[1];
			if (!realPath.trim().startsWith("self")) {
				throw new RuntimeException("抱歉!现在只支持[self]星球的查找");
			}
			if (realPath.equals("self")) {
				realNode = this;
			} else {
				realNode = (RealNode) this.getNextNodeByPath(new String(realPath.substring(realPath.indexOf('.')+1)));	
			}
		}
		
		Service defineService = realNode.getService(new String(defineServiceName));
		
		if (defineService == null) {
			throw new NoFoundDefineServiceException("没有找到你定义的服务:" + godHome + ", 请检查名字是书写错误");
		}
		
		return defineService.getAvailableGod();
	}
}
