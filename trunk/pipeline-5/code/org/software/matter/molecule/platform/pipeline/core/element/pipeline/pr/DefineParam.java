package org.software.matter.molecule.platform.pipeline.core.element.pipeline.pr;

import java.util.HashSet;
import java.util.Set;

public class DefineParam extends DRPR {

	public final static String STRING_TYPE = "string";

	public final static String NUMBER_TYPE = "number";

	public final static String DATE_TYPE = "date";

	public final static String BOX_TYPE = "box";
	
	public final static Set ALL_TYPE_SET = new HashSet(4);
	
	static {
		ALL_TYPE_SET.add(STRING_TYPE);
		ALL_TYPE_SET.add(NUMBER_TYPE);
		ALL_TYPE_SET.add(DATE_TYPE);
		ALL_TYPE_SET.add(BOX_TYPE);
	}

	protected String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public boolean containType(String type) {
		return ALL_TYPE_SET.contains(type);
	}

}
