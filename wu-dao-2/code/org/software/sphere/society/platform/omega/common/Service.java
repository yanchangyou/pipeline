package org.software.sphere.society.platform.omega.common;

import org.software.sphere.society.platform.omega.core.data.node0X.DefaultNode0X;

public class Service extends DefaultNode0X {

	public void addCompetitor(Competitor competitor) {
		this.addNextNode(competitor);
	}

	public Competitor getCompetitor(java.lang.String name) {
		return (Competitor) this.getNextNode(name);
	}
}
