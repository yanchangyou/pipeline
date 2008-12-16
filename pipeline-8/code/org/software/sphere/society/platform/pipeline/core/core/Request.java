package org.software.sphere.society.platform.pipeline.core.core;

import java.io.PrintWriter;
import java.net.ConnectException;

import org.software.sphere.society.platform.pipeline.common.JavaStringable;
import org.software.sphere.society.platform.pipeline.core.flow.FlowNode;
import org.software.sphere.society.platform.pipeline.exception.core.data.DataGenerateException;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;

public class Request extends FlowNode implements JavaStringable {

	private java.lang.String data;

	public void execute(Session clientSession) throws ConnectException, Exception {
		if (data != null) {
			PrintWriter os = new PrintWriter(clientSession.clientSocket.getOutputStream());
			log.info("开始向外输出数据 : " + data);
			java.lang.String result = Evale.eval(data, this).toString();
			os.println(result);
			os.flush();
			this.getFlowNodeContext().addNextNode(this.getNodeName(), new String(result));
			log.info("完毕向外输出数据");
		}
		
	}

	public void fromJavaString(java.lang.String data) throws DataGenerateException {
		this.data = data;
	}

	public java.lang.String toJavaString() {
		return data;
	}
}
