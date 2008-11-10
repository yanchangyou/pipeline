package org.software.matter.molecule.platform.pipeline.core;

import java.util.Map;

import org.software.matter.atom.entity.commons.NamedObject;

public class Step extends NamedObject {

	private PipelineReference pipelineReference;
	
	private StepReference stepReference;
	
	private String share;

	private String output;

	private Algorithm algorithm;

	private Input input = new Input();

	public Object deal(Map globalVariable) throws Exception {
		if (algorithm == null) {
			throw new Exception("«Î…Ë÷√À„∑®");
		}
		
		return algorithm.deal(input.getParam(globalVariable));
	}

	public Algorithm getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}


	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	public Step(String name) {
		super(name);
	}

	public Step() {
		super();
	}

	public PipelineReference getPipelineReference() {
		return pipelineReference;
	}

	public void setPipelineReference(PipelineReference pipelineReference) {
		this.pipelineReference = pipelineReference;
	}

	public StepReference getStepReference() {
		return stepReference;
	}

	public void setStepReference(StepReference stepReference) {
		this.stepReference = stepReference;
	}	
}
