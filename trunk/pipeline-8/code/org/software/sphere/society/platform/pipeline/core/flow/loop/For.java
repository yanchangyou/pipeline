package org.software.sphere.society.platform.pipeline.core.flow.loop;

import java.net.ConnectException;

import org.software.sphere.society.platform.pipeline.core.core.Session;
import org.software.sphere.society.platform.pipeline.exception.core.core.CompileException;

public class For extends Loop {

	protected java.lang.String min;
	
	protected java.lang.String max;
	
	protected java.lang.String step;

	public java.lang.String getMax() {
		return max;
	}

	public void setMax(java.lang.String max) {
		this.max = max;
	}

	public java.lang.String getMin() {
		return min;
	}

	public void setMin(java.lang.String min) {
		this.min = min;
	}

	public java.lang.String getStep() {
		return step;
	}

	public void setStep(java.lang.String step) {
		this.step = step;
	}

	public void execute(Session clientSession) throws ConnectException, Exception {
		checkForStatement();
		int min = Integer.parseInt(this.min);
		int max = Integer.parseInt(this.max);
		int step = Integer.parseInt(this.step == null ? "1" : this.step);//默认是1
		
		for (int i = min; i <= max; i+=step) {
			super.defaultExecute(clientSession);
		}
	}
	
	public void checkForStatement() throws CompileException {
		if (this.min == null || !this.min.matches("-?\\d{1,8}")) {
			throw new CompileException("for语句的min不能为空,并且不能超过8位");
		}
		
		if (this.max == null || !this.max.matches("-?\\d{1,8}")) {
			throw new CompileException("for语句的max不能为空,并且不能超过8位");
		}
		
		if (this.step != null && !this.step.matches("-?\\d{1,8}")) {
			throw new CompileException("for语句的step不能超过8位, 可以为空即是1");
		}
	}
	
	public java.lang.String toString() {
		return "[" + min + ", " + max + "](+" + step + ")" + super.toString();
	}
}
