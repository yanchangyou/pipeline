package org.software.sphere.society.platform.pipeline.lang.code.flownode.loop;

import org.software.sphere.society.platform.pipeline.exception.core.core.CompileException;
import org.software.sphere.society.platform.pipeline.exception.lang.core.CoreException;
import org.software.sphere.society.platform.pipeline.lang.core.Skillable;

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

	public void justDoIt(Skillable skill) throws CoreException {
		try {
			checkForStatement();
		} catch (CompileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CoreException(e.getMessage());
		}
		int min = Integer.parseInt(this.min);
		int max = Integer.parseInt(this.max);
		int step = Integer.parseInt(this.step == null ? "1" : this.step);//Ĭ����1
		
		for (int i = min; i <= max; i+=step) {
			super.defaultJustDoIt(skill);
		}
	}
	
	public void checkForStatement() throws CompileException {
		if (this.min == null || !this.min.matches("-?\\d{1,8}")) {
			throw new CompileException("for����min����Ϊ��,���Ҳ��ܳ���8λ");
		}
		
		if (this.max == null || !this.max.matches("-?\\d{1,8}")) {
			throw new CompileException("for����max����Ϊ��,���Ҳ��ܳ���8λ");
		}
		
		if (this.step != null && !this.step.matches("-?\\d{1,8}")) {
			throw new CompileException("for����step���ܳ���8λ, ����Ϊ�ռ���1");
		}
	}
	
	public java.lang.String toString() {
		return "[" + min + ", " + max + "](+" + step + ")" + super.toString();
	}

}