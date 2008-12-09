package org.software.sphere.society.platform.omega.common;


public class LineString {

	private String prefix; //前缀

	private String subfix; //后缀
	
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
		//这里四个斜杠, java语言 两个,  正则表达式 两个变四个---一不小心就错了
	}
}
