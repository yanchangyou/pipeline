package 太极;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import org.xml.sax.SAXException;

import 核心.接口.可写的;
import 核心.类.内容;
import 核心.类.系统;

public class 太极系统 extends 系统 implements 可写的 {

	public 太极系统() throws IOException, SAXException {
		super();
	}

	public void 写出(核心.类.内容 内容) {
		System.out.println(内容);
	}

	public void 写出(内容 内容, Socket socket) {
		OutputStream out = null;
		try {
			out = socket.getOutputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			System.out.println(内容);
			System.out.println(System.getProperty("sun.jnu.encoding"));
			out.write(内容.toString().getBytes(System.getProperty("sun.jnu.encoding")));
			out.write('\r');
			out.write('\n');
			out.flush();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
