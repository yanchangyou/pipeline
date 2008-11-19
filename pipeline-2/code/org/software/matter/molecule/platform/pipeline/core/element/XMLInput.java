package org.software.matter.molecule.platform.pipeline.core.element;

import org.apache.commons.lang.builder.ToStringBuilder;

public class XMLInput extends Input{

	private String xml;

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}
	

	public String toString() {
		return new ToStringBuilder(this).toString();
	}
}
