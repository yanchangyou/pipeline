package org.software.sphere.society.platform.omega.core.element.pipeline;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.software.sphere.society.platform.omega.core.session.Session;

public abstract class Unit extends PP {
	
	public Unit() {
		super();
	}

	public Unit(String name) {
	}

	protected List unitList = new ArrayList();

	public void addUnit(Unit unit) {
		unitList.add(unit);
		this.addChildElement(unit);
	}

	public List getUnitList() {
		return unitList;
	}

	public void setUnitList(List unitList) {
		this.unitList = unitList;
	}

	public abstract void deal(Session clientSession)
			throws ConnectException, Exception;
}
