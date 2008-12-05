package org.software.matter.molecule.platform.pipeline.core.element.pipeline;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.molecule.platform.pipeline.core.session.Session;

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

	public String toString() {
		return new ToStringBuilder(this).toString();
	}

	public abstract void deal(Session clientSession)
			throws ConnectException, Exception;
}
