package org.software.sphere.society.platform.omega.core.flow.unit;

import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import org.software.sphere.society.platform.omega.core.execute.Session;

public class TELNET extends Unit {

	public void execute(Session clientSession) throws ConnectException, Exception {
		// TODO Auto-generated method stub
		System.out.println("run telnet");
		if (clientSession != null) {
			
			Socket socket = clientSession.getClientSocket();
			
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			os.println("这是一个telnet服务, 目前还没有实现");
			os.flush();
		}
//		socket.close();
	}

}
