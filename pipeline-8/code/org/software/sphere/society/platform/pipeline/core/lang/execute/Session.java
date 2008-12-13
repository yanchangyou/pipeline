package org.software.sphere.society.platform.pipeline.core.lang.execute;

import java.net.Socket;

public class Session {

	public Session() {
		
	}

	public Session(Request request, Response response) {
		this.request = request;
		this.response = response;
	}

	public Session(Request request, Response response, Socket clientSocket) {
		this.request = request;
		this.response = response;
		this.clientSocket = clientSocket;
	}
	
	protected Socket clientSocket;
	
	protected Request request;

	protected Response response;

//	protected Context context;
//	
//	public Context getContext() {
//		return context;
//	}
//
//	public void setContext(Context context) {
//		this.context = context;
//	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
}
