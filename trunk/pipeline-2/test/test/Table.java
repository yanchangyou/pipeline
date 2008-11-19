package test;

import org.apache.commons.lang.ArrayUtils;


public class Table {

	/**
	 * the table name
	 */
	private String name;

	/**
	 * the column title
	 */
	private String[] field;

	/**
	 * the data
	 */
	private String[][] value;

	public String[] getField() {
		return field;
	}

	public void setField(String[] field) {
		this.field = field;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[][] getValue() {
		return value;
	}

	public void setValue(String[][] value) {
		this.value = value;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(this.name);
		
		buf.append("\n");
		
		String fieldString = ArrayUtils.toString(this.field);
		buf.append(fieldString);
		
		buf.append("\n");
		
		for (int i = 0; i < this.value.length; i++) {
			String valueString = ArrayUtils.toString(this.value[i]);
			buf.append(valueString);
			buf.append("\n");
		}
		
		
		return buf.toString();
	}

}
