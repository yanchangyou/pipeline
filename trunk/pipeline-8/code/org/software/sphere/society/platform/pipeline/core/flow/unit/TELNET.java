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
		// os.println("����һ��telnet����, Ŀǰ��û��ʵ��");
		// os.flush();
		// }

		Socket socket = null;

		boolean isClientSocket = false;
		/**
		 * û������ʹ����������, ��Ĭ����ͻ��˶Ի�
		 */
		if (this.service == null) {
			log.info("ֱ����ͻ��˻Ự, ������Զ�̷���");
			socket = clientSession.getClientSocket();
			isClientSocket = true;
		} else { // ʹ�������ķ���
			log.info("��ʼԶ�̷������");
			isClientSocket = false;

			Global global = (Global) this.getFirstNodeInSequencePre1ableNodes();;
			
			socket = (Socket) global.getGod(new String(service));
		}

		if (socket == null) { // ���û������, �׳��쳣
		// log.error("����ʧ��");
			java.lang.String msg = "����ʧ�� : �ڲ���[" + this.getName() + "��]���������쳣, ��ȷ������["
					+ this.service + "]�Ѿ�����";
			log.error(msg);
			throw new ConnectException(msg);
		}

		if (request != null && request.getRequestData() != null) {
			
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			java.lang.String requestData = request.getRequestData();
			log.info("��ʼ����������� : " + requestData);
			java.lang.String result = Evale.eval(requestData, this).toString();
			os.println(result);
			os.flush();
			log.info("��������������");
		}
		/**
		 * ��ȡ���� ����&����, && ���еĴ������
		 */
		if (response != null) {
			log.info("��ʼ���ڶ�ȡ����");
			java.lang.String responseData = RuleReadNetDataByPipeline.readData(socket.getInputStream());
			this.decodeResponseData(response, new String(responseData));
			log.info("������ڶ�ȡ���� : " + responseData);
		}
		if (isClientSocket) { // ����ǿͻ��˾Ͳ��ر�
			log.info("��ͻ��˻Ự����");
		} else {
			socket.close();
			log.info("Զ�̷�����ý���");
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
