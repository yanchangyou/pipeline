package org.software.sphere.society.platform.omega.common;


public class LineString {

	private String prefix; //ǰ׺

	private String subfix; //��׺
	
	private String line;

	public String getLine() {
		prefix = prefix == null ? "" : prefix;
		subfix = subfix == null ? "\n" : subfix;
		line = line == null ? "" : line;
		return prefix + line + subfix;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSubfix() {
		return subfix;
	}

	public void setSubfix(String subfix) {
		this.subfix = subfix;
		this.subfix = this.subfix.replaceAll("\\\\r\\\\n|\\\\n|\\\\r", System.getProperty("line.separator"));
		//�����ĸ�б��, java���� ����,  ������ʽ �������ĸ�---һ��С�ľʹ���
	}
}
