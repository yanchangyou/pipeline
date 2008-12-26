package org.software.sphere.society.platform.pipeline.lang.code.actionnode;

import java.util.ArrayList;
import java.util.List;

import org.software.sphere.society.platform.pipeline.lang.core.JustDoItable;

public abstract class ActionNode implements JustDoItable {
	protected List justDoItList = new ArrayList();

	public void addJustDoIt(JustDoItable justDoIt) {
		justDoItList.add(justDoIt);
	}
	
}
