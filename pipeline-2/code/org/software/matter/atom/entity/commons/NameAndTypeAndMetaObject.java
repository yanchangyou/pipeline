package org.software.matter.atom.entity.commons;

import org.software.matter.molecule.platform.pipeline.core.element.common.Meta;

public class NameAndTypeAndMetaObject implements Metable, Namable, Typable {

	private String type;

	private String name;

	private Meta meta;

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
