package org.software.sphere.society.platform.pipeline.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class InputServer {
	public static void main(String args[]) {
		
		System.out.println("===========InputServer输入控制台=============");
		
		ServerSocket server = null;
		try {
			server = new ServerSocket(10002);
			// 创建一个ServerSocket在端口10001监听客户请求
		} catch (Exception e) {
			System.out.println("can not listen to:" + e);
			// 出错，打印出错信息
		}
		Socket socket = null;
		try {
			do {
				socket = server.accept();

				new Thread(new ServerThread(socket)).start();
				// 使用accept()阻塞等待客户请求，有客户
				// 请求到来则产生一个Socket对象，并继续执行

				while (!socket.isClosed()) {
					Thread.sleep(100);
				}
				
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error." + e);
			// 出错，打印出错信息
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
				// 由Socket对象得到输出流，并构造PrintWriter对象
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
					// 开始下一行

//				}
				System.out.println("send OVER");
				os.close(); // 关闭Socket输入流
				socket.close(); // 关闭Socket
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
