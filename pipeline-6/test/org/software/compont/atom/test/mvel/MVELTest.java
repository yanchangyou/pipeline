package org.software.compont.atom.test.mvel;

import java.util.HashMap;
import java.util.Map;

import org.mvel.MVEL;

public class MVELTest {

	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("a_c", "1");
		map.put("b", "2");
		
		Object result = MVEL.eval("a_c+b==3", map);
		
		
		System.out.println(result);
	}
}
