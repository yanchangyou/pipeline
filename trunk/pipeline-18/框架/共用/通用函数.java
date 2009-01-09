package 共用;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class 通用函数 {

	public static String 转换为本地编码(String 字符串) {
		byte[] 字节数组 = null;
		String 本地字符串 = null;
		try {
			字节数组 = 字符串.getBytes(System.getProperty("file.encoding"));
			本地字符串 = new String(字节数组, (System.getProperty("sun.jnu.encoding")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return 本地字符串;
	}
	
	public static void main(String[] args) {
//		Locale l = Locale.;
		System.out.println(System.getProperties().toString().replace(',', '\n'));;
		System.out.println(Charset.defaultCharset());;
	}
}
