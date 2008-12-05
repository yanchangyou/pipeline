package org.software.sphere.atom.entity.commons;

import org.software.sphere.society.platform.omega.core.element.common.Meta;

public class NameAndTypeAndMetaObject implements Metable, Namable, Typable {

	protected String type;

	protected String name;

	protected Meta meta = new Meta();

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
