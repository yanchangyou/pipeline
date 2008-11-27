package org.software.matter.molecule.platform.pipeline.core.element.pipeline;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.molecule.platform.pipeline.core.context.Context;
import org.software.matter.molecule.platform.pipeline.core.element.Root;
import org.software.matter.molecule.platform.pipeline.core.element.pipeline.pr.DefineParam;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Request;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Response;

public abstract class Unit extends PP {

	protected Context context = new Context();

	protected Root root;

	public void setParentContext(Context parentContext) {
		context.setParent(parentContext);
	}

	public Root getRoot() {
		return root;
	}

	public void setRoot(Root root) {
		this.root = root;
	}

	public Unit(String name) {
	}

	public Unit() {

	}

	protected List unitList = new ArrayList();

	public void addUnit(Unit unit) {
		unitList.add(unit);
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

	public abstract void deal(Request request, Response response)
			throws ConnectException, Exception;

	/**
	 * 只处理定义的参数
	 * 
	 */
	public void tuneParamToContext() {
		if (this.getParam() != null) {
			Map defineMap = this.getParam().getDefineParamMap();
			Set set = defineMap.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				DefineParam df = (DefineParam) defineMap.get(name);
				context.put(name, df.getValue());
			}
		}
	}
}
