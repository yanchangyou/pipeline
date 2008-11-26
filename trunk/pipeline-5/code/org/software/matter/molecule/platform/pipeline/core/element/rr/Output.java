package org.software.matter.molecule.platform.pipeline.core.element.rr;

import org.software.matter.molecule.platform.pipeline.core.context.Context;

public abstract class Output extends Res {

	public abstract String getData();

	public abstract void setData(String responseData);

	public abstract void tuneResultToContext(Context context);
	
	public abstract void tuneContextToResult(Context context);

}
