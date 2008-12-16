package org.software.sphere.society.platform.pipeline.core.core;

import java.io.IOException;

import org.software.sphere.society.platform.pipeline.common.JavaStringable;
import org.software.sphere.society.platform.pipeline.common.RuleReadNetDataByPipeline;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.flow.FlowNode;
import org.software.sphere.society.platform.pipeline.exception.core.data.DataGenerateException;

public class Response extends FlowNode implements JavaStringable {

	private java.lang.String data;

	public void execute(Session clientSession) throws IOException {
		log.info("开始向内读取数据");
		java.lang.String responseData = RuleReadNetDataByPipeline
				.readData(clientSession.clientSocket.getInputStream());
		
		java.lang.String result = null;
		try {
			result = Evale.eval(responseData, this).toString();
		} catch (Exception e) {
			result = "错误:" + e.getMessage();
			execute(clientSession);
			return;
		}
		
		this.getPreFlowNode().getFlowNodeContext().addNextNode(this.getNodeName(),
				new String(result));
		log.info("完毕向内读取数据 : " + result);
	}

	public void fromJavaString(java.lang.String data) throws DataGenerateException {
		this.data = data;
	}

	public java.lang.String toJavaString() {
		return data;
	}
}
