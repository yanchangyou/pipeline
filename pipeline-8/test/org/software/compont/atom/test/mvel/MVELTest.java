package org.software.compont.atom.test.mvel;

import java.util.HashMap;
import java.util.Map;

import org.mvel.MVEL;

public class MVELTest {

	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("language_name", " pipeline ");
		map.put("b", "2");
		
		Object result = MVEL.eval("language_name.trim()", map);
		
		
		System.out.println(result);
	}
}
