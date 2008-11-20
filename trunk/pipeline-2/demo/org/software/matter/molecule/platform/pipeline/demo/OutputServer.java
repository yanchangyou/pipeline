package org.software.matter.molecule.platform.pipeline.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class OutputServer {
	public static void main(String args[]) {
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
				System.out.println("Error." + e);
				// ������ӡ������Ϣ
			}
		}
	}
}

class ServerThread implements Runnable {
	Socket socket;

	ServerThread(Socket socket) {
		this.socket = socket;
	}

	ServerThread() {

	}

	public void run() {

		String line = null;
		BufferedReader is = null;
		try {
			is = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));

			// ��Socket����õ�����������������Ӧ��BufferedReader����
			// PrintWriter os = new PrintWriter(socket.getOutputStream());
			// ��Socket����õ��������������PrintWriter����
			BufferedReader sin = new BufferedReader(new InputStreamReader(
					System.in));
			// ��ϵͳ��׼�����豸����BufferedReader����
			try {
				System.out.println(socket.getRemoteSocketAddress().toString()
						.substring(1)
						+ " speak : " + is.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// �ڱ�׼����ϴ�ӡ�ӿͻ��˶�����ַ���
			line = sin.readLine();
			// �ӱ�׼�������һ�ַ���r
			while (!line.equals("bye")) {
				// ������ַ���Ϊ "bye"����ֹͣѭ��
				// os.println(line);
				// ��ͻ���������ַ���
				// os.flush();
				// ˢ���������ʹClient�����յ����ַ���
				System.out.println("Server:" + line);
				// ��ϵͳ��׼����ϴ�ӡ������ַ���
				System.out.println("Client:" + is.readLine());
				// ��Client����һ�ַ���������ӡ����׼�����
				line = sin.readLine();
				// ��ϵͳ��׼�������һ�ַ���
			} // ����ѭ��
			// os.close(); // �ر�Socket�����
			is.close(); // �ر�Socket������
			socket.close(); // �ر�Socket
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
