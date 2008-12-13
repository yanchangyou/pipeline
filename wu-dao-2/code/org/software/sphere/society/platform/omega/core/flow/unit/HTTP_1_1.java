package org.software.sphere.society.platform.omega.core.flow.unit;

import java.io.IOException;
import java.net.ConnectException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.lang.execute.Request;
import org.software.sphere.society.platform.omega.core.lang.execute.Response;
import org.software.sphere.society.platform.omega.core.lang.execute.Session;

//import org.ap

public class HTTP_1_1 extends Unit {

	public void execute(Session clientSession) throws ConnectException, Exception {


		java.lang.String requestData = "http://www.baidu.com";
		
		
		String responseData = getResponseData(requestData);
		
//		Socket socket = clientSession.getClientSocket();
		
//		PrintStream os = new PrintStream(socket.getOutputStream());
//		
//		os.print(responseData);
		
		System.out.println(responseData);
		
	}
	 public static String getResponseData(String requestData) {
//	����HttpClient��ʵ��
		 byte[] responseBody = null;
	  HttpClient httpClient = new HttpClient();
	  //����GET������ʵ��
	  GetMethod getMethod = new GetMethod(requestData);
	  //ʹ��ϵͳ�ṩ��Ĭ�ϵĻָ�����
	  getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
	    new DefaultHttpMethodRetryHandler());
	  try {
	   //ִ��getMethod
	   int statusCode = httpClient.executeMethod(getMethod);
	   if (statusCode != HttpStatus.SC_OK) {
	    System.err.println("Method failed: "
	      + getMethod.getStatusLine());
	   }
	   //��ȡ���� 
	   responseBody = getMethod.getResponseBody();
	   //��������
	   System.out.println(new String(responseBody));
	  } catch (HttpException e) {
	   //�����������쳣��������Э�鲻�Ի��߷��ص�����������
	   System.out.println("Please check your provided http address!");
	   e.printStackTrace();
	  } catch (IOException e) {
	   //���������쳣
	   e.printStackTrace();
	  } finally {
	   //�ͷ�����
	   getMethod.releaseConnection();
	  }
	  
	  return new String(responseBody);
	 }
	public void encodeRequestData(Request request) {
		// TODO Auto-generated method stub
		
	}
	public void decodeResponseData(Response response) {
		// TODO Auto-generated method stub
		
	}
	public Object getGod() {
		// TODO Auto-generated method stub
		return null;
	}
	public void decodeResponseData(Response response, org.software.sphere.society.platform.omega.core.data.node0X.String responseData) {
		// TODO Auto-generated method stub
		
	}
	public void encodeRequestData(Request request, Node paramater) {
		// TODO Auto-generated method stub
		
	}



}
