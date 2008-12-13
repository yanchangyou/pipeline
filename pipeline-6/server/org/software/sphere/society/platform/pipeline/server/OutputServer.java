package org.software.sphere.society.platform.pipeline.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.software.sphere.society.platform.pipeline.common.RuleReadNetDataByPipeline;

public class OutputServer {
	public static void main(String args[]) {
		
		System.out.println("===========OutputServer输出控制台=============");
		
		ServerSocket server = null;
		try {
			server = new ServerSocket(10001);
			// 创建一个ServerSocket在端口10001监听客户请求
		} catch (Exception e) {
			System.out.println("can not listen to:" + e);
			// 出错，打印出错信息
		}
		Socket socket = null;

		while (true) {
			try {
				socket = server.accept();
				new Thread(new ServerThread(socket)).start();
				// 使用accept()阻塞等待客户请求，有客户
				// 请求到来则产生一个Socket对象，并继续执行
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error." + e);
				// 出错，打印出错信息
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

			try {
				BufferedReader is = new BufferedReader(new InputStreamReader(socket
						.getInputStream()));
					String data = RuleReadNetDataByPipeline.readData(socket.getInputStream());
					System.out.println(socket.getRemoteSocketAddress().toString()
							.substring(1)
							+ " speak : " + data);
				is.close(); // 关闭Socket输入流
				socket.close(); // 关闭Socket
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

