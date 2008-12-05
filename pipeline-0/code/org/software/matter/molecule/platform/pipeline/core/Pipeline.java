package org.software.matter.molecule.platform.pipeline.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.software.matter.atom.entity.commons.NamadObject;

/**
 * 
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-11-7 下午03:29:57
 * @file : Pipeline.java
 * @version : 0.1
 */
public class Pipeline extends NamadObject {

	/**
	 * 
	 */
	String comment;

	/**
	 * 
	 */
	private List stepList = new ArrayList();

	Map globalVariable = new HashMap();

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Object getGlobalVariable(String name) {
		return globalVariable.get(name);
	}

	public void addGlobalVariable(String name, Object value) {
		globalVariable.put(name, value);
	}

	public List getStepList() {
		return stepList;
	}

	public void setStepList(List stepList) {
		this.stepList = stepList;
	}

	public void addStep(Step step) {
		stepList.add(step);
	}

	public void insertStep(Step step, int index) {
		stepList.add(index, step);
	}

	public void removeStep(int index) {
		stepList.remove(index);
	}

	/**
	 * 
	 * @param ware
	 * @return
	 * @throws Exception
	 */
	public Object deal(Object ware) throws Exception {

		//初始数据
		this.globalVariable.put("inputData", ware);
		
		//开始流水线处理
		System.out.println("开始处理流水线:" + this.getName() + "\n");
		for (int i = 0; i < stepList.size(); i++) {
			Step step = (Step) stepList.get(i);
			System.out.println("开始处理第" + (i+1) + "步(共" + stepList.size() +"步) : " + step.getName());
			
			ware = step.deal(this.globalVariable);
			if (step.getNeedStore() != null
					&& step.getNeedStore().booleanValue()) {
				addGlobalVariable(step.getGlobalName(), ware);
			}
			System.out.println("处理第" + (i+1) + "步结束\n");
		}
		System.out.println("流水线(" + this.getName() + ")处理完毕\n");
		
		//处理完毕后,回收空间
		globalVariable.clear();
		globalVariable = new HashMap();
		//返回处理的最终结果
		return ware;
	}
	
	
	public Object deal() throws Exception {
		return deal(null);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
