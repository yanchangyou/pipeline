package org.software.sphere.society.platform.pipeline.common;

import java.util.Iterator;

import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.data.node1X.DefaultNode1X;
import org.software.sphere.society.platform.pipeline.exception.core.core.DataDealException;
import org.software.sphere.society.platform.pipeline.exception.core.core.NoAvailableGodException;
import org.software.sphere.society.platform.pipeline.exception.core.data.NextNodeNotFountException;

public class ServiceNode extends DefaultNode1X {

	public ServiceNode() {
		super();
	}

	public ServiceNode(DataNode node) {
		super(node);
	}

	public void addCompetitor(Competitor competitor) {
		this.addNextNode(competitor);
		competitor.setPreNode(this);
	}

	public Competitor getCompetitor(java.lang.String name) {
		return (Competitor) this.getNextNodeByName(new String(name));
	}

	public Object getAvailableGod() throws NextNodeNotFountException, NoAvailableGodException, DataDealException {

		Object god = null;
		for (Iterator iter = this.getNextNodesMap().values().iterator(); iter.hasNext();) {
			Competitor competitor = (Competitor) iter.next();
			try {
				god = competitor.getGod();
				log.info("��ȡ��һ�����õķ��� :" + competitor.getName() + ", " + competitor.getRealService());
				break; //�����һ�����Ե�God���˳�
			} catch (NextNodeNotFountException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			} catch (DataDealException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				log.info("��ȡ����ʧ��, �Զ�Ѱ����һ������, ʧ�ܵķ�����:" + competitor.getName() + ", " + competitor.getRealService());
				e.printStackTrace();
			}
		}
		if (god == null) {
			throw new NoAvailableGodException("����:����ķ���û������, ��������ķ�����:" + this.toString());
		}
		return god;
	}

	public java.lang.String toString() {
		return super.toString() + ", competitor : {" + this.getNextNodesMap()
				+ "}]";
	}
}
