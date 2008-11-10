package org.software.matter.molecule.platform.pipeline.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.software.matter.atom.entity.commons.NamedObject;

public class Pipeline extends NamedObject {

	
	String share;

	private List stepList = new ArrayList();

	Map globalVariable = new HashMap();

	private Map sharedStepMap = new HashMap();

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
		if (step.getShare() != null && step.getShare().trim().length() != 0) {
			this.addSharedStep(step.getShare(), step);
		}
	}

	public void insertStep(Step step, int index) {
		stepList.add(index, step);
	}

	public void removeStep(int index) {
		stepList.remove(index);
	}

	public Object deal() throws Exception {
		return deal(null);
	}
	
	public Object deal(Object obj) throws Exception {
		Map map = new HashMap();
		map.put("input-data", obj);
		return deal(map);
	}
	
	public Object deal(Map globalVariable) throws Exception {
		if (globalVariable != null) {
			this.globalVariable.putAll(globalVariable);	
		}
		
		Object ware = null;
		System.out.println("��ʼ������ˮ��(" + this.getName() + ")\n");
		for (int i = 0; i < stepList.size(); i++) {
			Step step = (Step) stepList.get(i);
			System.out.println("��ʼ�����" + (i + 1) + "��(��" + stepList.size()
					+ "��) : " + step.getName());

			if (step.getPipelineReference() != null) {// �����pipeline������
				Pipeline aPipeline = PipelineConfig.getSharedPipeline(step
						.getPipelineReference().getName());
				if (aPipeline == null) {
					throw new Exception("û���ҵ�������pipeline, ������:"
							+ step.getPipelineReference().getName());
				}
				ware = aPipeline.deal(this.globalVariable);
			} else if (step.getStepReference() != null) { // �����step������
				Step referencedStep = getSharedStep(step.getStepReference()
						.getName());
				if (referencedStep == null) {
					throw new Exception("û���ҵ�������shared step, ������:"
							+ step.getStepReference().getName());
				}
				ware = referencedStep.deal(this.globalVariable);
			} else {
				ware = step.deal(this.globalVariable);
			}

			if (step.getOutput() != null && !step.getOutput().trim().equals("")) {
				addGlobalVariable(step.getOutput(), ware);
			}
			System.out.println("�����" + (i + 1) + "������\n");
		}
		System.out.println("��ˮ��(" + this.getName() + ")�������\n");

		return ware;
	}
	
	public void addSharedStep(String name, Step step) {
		this.sharedStepMap.put(name, step);

	}

	public Step getSharedStep(String name) {
		return (Step) this.sharedStepMap.get(name);
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}
}
