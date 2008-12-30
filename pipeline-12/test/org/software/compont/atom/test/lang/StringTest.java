package org.software.compont.atom.test.lang;

import org.apache.commons.lang.ArrayUtils;

public class StringTest {

	public static void main(String[] args) {
		
		
		String[] array = new String[]{"\"123\" + ", "a \"123\""};
		
		
		
		for (int i = 0; i < array.length; i++) {
			System.out.println(ArrayUtils.toString(array[i].split("\"")));
		}
		
	}
}
