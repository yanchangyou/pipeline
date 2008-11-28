package org.software.matter.molecule.platform.pipeline.demo;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;

public class DemoList implements Runnable {
	
	 private List demoList = new ArrayList();
	 
	 public void addDemo(Demo demo) {
		demoList.add(demo);
	 }

	public void run() {
		
		for (Iterator iter = demoList.iterator(); iter.hasNext();) {
			Demo demo = (Demo) iter.next();
			new Thread(demo).start();
		}
	}
	
	public static void main(String[] args) {
		String VALIDATOR_RULES = "demo-digester-rules.xml";
		String PATH = "demo-list.xml";
		URL rulesUrl = DemoList.class.getResource(VALIDATOR_RULES);

		URL xmlUrl = DemoList.class.getResource(PATH);
		Digester digester = DigesterLoader.createDigester(rulesUrl);

		try {
			DemoList aDemoList = (DemoList) digester.parse(new File(xmlUrl.getFile()));
			new Thread(aDemoList).start();
		} catch (Exception e) {
			System.out.println("demo系统内部错误");
			e.printStackTrace();
		}
	}
}
