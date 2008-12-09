package org.software.sphere.society.platform.omega.test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.software.sphere.society.platform.omega.common.Logable;

public class TestList implements Runnable, Logable {
	
	 private List testList = new ArrayList();
	 
	 public void addTest(Test test) {
		testList.add(test);
	 }

	public void run() {
		
		for (Iterator iter = testList.iterator(); iter.hasNext();) {
			Test test = (Test) iter.next();
			new Thread(test).start();
		}
	}
	
	public static void main(String[] args) {
		String VALIDATOR_RULES = "test-digester-rules.xml";
		String PATH = "test-list.xml";
		URL rulesUrl = TestList.class.getResource(VALIDATOR_RULES);

		URL xmlUrl = TestList.class.getResource(PATH);
		Digester digester = DigesterLoader.createDigester(rulesUrl);

		try {
			log.info("开始运行整个test集合");
			TestList aTestList = (TestList) digester.parse(new File(xmlUrl.getFile()));
			new Thread(aTestList).start();
		} catch (Exception e) {
			System.out.println("test系统内部错误");
			e.printStackTrace();
		}
	}
}
