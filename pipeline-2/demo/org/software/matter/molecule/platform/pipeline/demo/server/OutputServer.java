package org.software.matter.molecule.platform.pipeline.demo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class OutputServer {
	public static void main(String args[]) {
		
		System.out.println("===========�������̨=============");
		
		ServerSocket server = null;
		try {
			server = new ServerSocket(10001);
			// ����һ��ServerSocket�ڶ˿�10001�����ͻ�����
		} catch (Exception e) {
			System.out.println("can not listen to:" + e);
			// ������ӡ������Ϣ
		}
		Socket socket = null;

		while (true) {

			try {

				socket = server.accept();
				new Thread(new ServerThread(socket)).start();
				// ʹ��accept()�����ȴ��ͻ������пͻ�
				// �����������һ��Socket���󣬲�����ִ��
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error." + e);
				// ������ӡ������Ϣ
			}
		}
	}
	static class ServerThread implements Runnable {
		Socket socket;

		ServerThread(Socket socket) {
			this.socket = socket;
		}

		ServerThread() {

		}

		public void run() {

			String line = null;
			try {
				BufferedReader is = new BufferedReader(new InputStreamReader(socket
						.getInputStream()));
				do {
					line = is.readLine();
					if (line == null) {
						break;
					}
					System.out.println(socket.getRemoteSocketAddress().toString()
							.substring(1)
							+ " speak : " + line);
					
				} while (!line.equals("bye"));
				is.close(); // �ر�Socket������
				socket.close(); // �ر�Socket
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

