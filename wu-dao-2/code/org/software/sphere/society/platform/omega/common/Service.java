package org.software.sphere.society.platform.omega.common;

import java.util.Iterator;

import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node0X.String;
import org.software.sphere.society.platform.omega.core.data.node1X.DefaultNode1X;
import org.software.sphere.society.platform.omega.exception.data.MiddleNodeNotFountException;
import org.software.sphere.society.platform.omega.exception.execute.NoAvailableGodException;

public class Service extends DefaultNode1X {

	public Service() {
		super();
	}

	public Service(Node node) {
		super(node);
	}

	public void addCompetitor(Competitor competitor) {
		this.addNextNode(competitor);
		competitor.setPreNode(this);
	}

	public Competitor getCompetitor(java.lang.String name) {
		return (Competitor) this.getNextNodeByName(new String(name));
	}

	public Object getAvailableGod() throws MiddleNodeNotFountException, NoAvailableGodException {

		Object god = null;
//		Set set = this.getNextNodesMap().keySet();
		for (Iterator iter = this.getNextNodesMap().values().iterator(); iter.hasNext();) {
			Competitor competitor = (Competitor) iter.next();
			try {
				god = competitor.getGod();
				log.info("获取到一个可用的服务 :" + competitor.getName() + ", " + competitor.getRealService());
				break; //获得了一个可以的God就退出
			} catch (MiddleNodeNotFountException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				log.info("获取服务失败, 自动寻找下一个服务, 失败的服务是:" + competitor.getName() + ", " + competitor.getRealService());
//				e.printStackTrace();
			}
		}
		if (god == null) {
			throw new NoAvailableGodException("错误:请求的服务没有启动, 测试请求的服务有:" + this.toString());
		}
		return god;
	}

	public java.lang.String toString() {
		return super.toString() + ", competitor : {" + this.getNextNodesMap()
				+ "}]";
	}
}
