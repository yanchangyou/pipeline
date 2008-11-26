package org.software.matter.molecule.platform.pipeline.core.element.rr;

import org.software.matter.molecule.platform.pipeline.core.context.PipelineContext;

public abstract class Output extends Res {

	public abstract String getData();

	public abstract void setData(String responseData);

	public abstract void tuneResultToPipelineContext(PipelineContext pipelineContext);
	
	public abstract void tunePipelineContextToResult(PipelineContext pipelineContext);

}
