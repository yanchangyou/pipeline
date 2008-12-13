package org.software.sphere.society.platform.pipeline.core.flow.unit;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.software.sphere.society.platform.pipeline.common.RuleReadNetDataByPipeline;
import org.software.sphere.society.platform.pipeline.core.data.Node;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.lang.execute.KeyWords;
import org.software.sphere.society.platform.pipeline.core.lang.execute.Request;
import org.software.sphere.society.platform.pipeline.core.lang.execute.Response;
import org.software.sphere.society.platform.pipeline.core.lang.execute.Session;
import org.software.sphere.society.platform.pipeline.core.lang.parse.Evale;
import org.software.sphere.society.platform.pipeline.core.real.Global;

public class TELNET extends Unit {

	public void execute(Session clientSession) throws ConnectException,
			Exception {

//		encodeRequestData(clientSession.getRequest(), this.getFlowNodeContext());

		// System.out.println("run telnet");
		// if (clientSession != null) {
		//			
		// Socket socket = clientSession.getClientSocket();
		//			
		// PrintWriter os = new PrintWriter(socket.getOutputStream());
		// os.println("这是一个telnet服务, 目前还没有实现");
		// os.flush();
		// }

		Socket socket = null;

		boolean isClientSocket = false;
		/**
		 * 没有声明使用其它服务, 就默认与客户端对话
		 */
		if (this.service == null) {
			log.info("直接与客户端会话, 不调用远程服务");
			socket = clientSession.getClientSocket();
			isClientSocket = true;
		} else { // 使用声明的服务
			log.info("开始远程服务调用");
			isClientSocket = false;

			Global global = (Global) this.getFirstNodeInSequencePre1ableNodes();;
			
			socket = (Socket) global.getGod(new String(service));
		}

		if (socket == null) { // 如果没有连接, 抛出异常
		// log.error("连接失败");
			java.lang.String msg = "连接失败 : 在步骤[" + this.getName() + "中]发生连接异常, 请确保服务["
					+ this.service + "]已经启动";
			log.error(msg);
			throw new ConnectException(msg);
		}

		if (request != null && request.getRequestData() != null) {
			
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			java.lang.String requestData = request.getRequestData();
			log.info("开始向外输出数据 : " + requestData);
			java.lang.String result = Evale.eval(requestData, this).toString();
			os.println(result);
			os.flush();
			log.info("完毕向外输出数据");
		}
		/**
		 * 读取数据 采用&续行, && 换行的处理规则
		 */
		if (response != null) {
			log.info("开始向内读取数据");
			java.lang.String responseData = RuleReadNetDataByPipeline.readData(socket.getInputStream());
			this.decodeResponseData(response, new String(responseData));
			log.info("完毕向内读取数据 : " + responseData);
		}
		if (isClientSocket) { // 如果是客户端就不关闭
			log.info("与客户端会话结束");
		} else {
			socket.close();
			log.info("远程服务调用结束");
		}
	}

	public Object getGod() throws UnknownHostException, IOException {
		String host = (String) this.getFlowNodeContext().getNextNodeByName(new String("host"));
		String portString = (String) this.getFlowNodeContext().getNextNodeByName(new String("port"));
		int port = Integer.parseInt(portString.toJavaString());
		return new Socket(host.toJavaString(), port);
	}

	public void decodeResponseData(Response response, String responseData) {
		response.addNextNode(KeyWords.DATA_KEY_WORLD, responseData);
	}

	public void encodeRequestData(Request request, Node paramater) {
		String requestData = (String) paramater.getNextNodeByName(new String("msg"));//new String("Hello World!");
		request.addNextNode(KeyWords.DATA_KEY_WORLD, requestData);
	}
}
