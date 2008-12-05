package org.software.sphere.society.platform.omega.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class AddCalculator {
	public static void main(String args[]) {

		System.out.println("===========AddCalculator�������̨=============");

		ServerSocket server = null;
		try {
			server = new ServerSocket(11001);
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
				BufferedReader is = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				// ��Socket����õ��������������PrintWriter����

				System.out.println("\nnew calculate BEGIN");

				line = is.readLine();
				System.out.println("input :��" + line);
				if (line == null) {
					os.println("");
				} else {
					String[] params = line.split("\\s*[,; :]\\s*");

					double result = Tool.add(params[0], params[1]);

					os.println(result);
					System.out.println("result is : " + result);
				}
				
				os.flush();
				System.out.println("calculate OVER");
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // �ر�Socket
			}
		}

	}

	static class Tool {

		public static double add(String a, String b) {
			return Double.parseDouble(a) + Double.parseDouble(b);
		}

	}

}
