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
 * @date : 2008-11-7 ����03:29:57
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

		//��ʼ����
		this.globalVariable.put("inputData", ware);
		
		//��ʼ��ˮ�ߴ���
		System.out.println("��ʼ������ˮ��:" + this.getName() + "\n");
		for (int i = 0; i < stepList.size(); i++) {
			Step step = (Step) stepList.get(i);
			System.out.println("��ʼ�����" + (i+1) + "��(��" + stepList.size() +"��) : " + step.getName());
			
			ware = step.deal(this.globalVariable);
			if (step.getNeedStore() != null
					&& step.getNeedStore().booleanValue()) {
				addGlobalVariable(step.getGlobalName(), ware);
			}
			System.out.println("�����" + (i+1) + "������\n");
		}
		System.out.println("��ˮ��(" + this.getName() + ")�������\n");
		
		//������Ϻ�,���տռ�
		globalVariable.clear();
		globalVariable = new HashMap();
		//���ش�������ս��
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
