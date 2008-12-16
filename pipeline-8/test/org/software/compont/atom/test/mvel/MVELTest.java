package org.software.compont.atom.test.mvel;

import java.util.HashMap;
import java.util.Map;

import org.mvel.MVEL;

public class MVELTest {

	public static void main(String[] args) {
		simpleTest();
//		varDefineTest();
//		flowDefineTest();
	}
	
	public static void simpleTest() {
		Map map = new HashMap();
		map.put("���ı���", "���ı���ֵ");
		Object result = MVEL.eval("���ı��� + \"end\" ", map);
		System.out.println(result);
	}
	
	public static void varDefineTest() {
		Map map = new HashMap();
		map.put("a", null);
		String express = "a = 10";
		
		Object result = MVEL.eval(express, map);
		
		System.out.println(result);
	}
	
	public static void flowDefineTest() {
//		Map map = new HashMap();
//		map.put("a", null);
		String express = "for (int i =0; i < 100; i++) {  System.out.println(i); }";
		
		Object result = MVEL.eval(express);
		
		System.out.println(result);
	}
	
	
	
}
