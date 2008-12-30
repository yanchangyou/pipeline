package org.software.sphere.society.platform.pipeline.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class InputServer {
	public static void main(String args[]) {
		
		System.out.println("===========InputServer�������̨=============");
		
		ServerSocket server = null;
		try {
			server = new ServerSocket(10002);
			// ����һ��ServerSocket�ڶ˿�10001�����ͻ�����
		} catch (Exception e) {
			System.out.println("can not listen to:" + e);
			// ������ӡ������Ϣ
		}
		Socket socket = null;
		try {
			do {
				socket = server.accept();

				new Thread(new ServerThread(socket)).start();
				// ʹ��accept()�����ȴ��ͻ������пͻ�
				// �����������һ��Socket���󣬲�����ִ��

				while (!socket.isClosed()) {
					Thread.sleep(100);
				}
				
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error." + e);
			// ������ӡ������Ϣ
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
				PrintWriter os = new PrintWriter(socket.getOutputStream());
				// ��Socket����õ��������������PrintWriter����
				BufferedReader sin = new BufferedReader(new InputStreamReader(
						System.in));

				System.out.println("\nnew send BEGIN");

				// System.out.print("to "
				// + socket.getRemoteSocketAddress().toString().substring(
				// 1) + " :");
				// line = sin.readLine();
				line = "";
//				while (line != null && !line.equalsIgnoreCase("end")) {

					 System.out.print("to "
					 + socket.getRemoteSocketAddress().toString()
					 .substring(1) + " :");
					line = sin.readLine();
					os.println(line);
					os.flush();
					// ��ʼ��һ��

//				}
				System.out.println("send OVER");
				os.close(); // �ر�Socket������
				socket.close(); // �ر�Socket
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
