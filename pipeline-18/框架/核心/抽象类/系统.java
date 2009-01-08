package 核心.抽象类;



import java.io.IOException;

import org.xml.sax.SAXException;

import 核心.常量;
import 神.神;


public abstract class 系统 extends 神 {

	public 系统() throws IOException, SAXException {
		super();
	}

	public 服务 获取服务(int 服务的ID) {
		return (服务) this.拿出(服务的ID);
	}
	
	public 服务 获取服务(String 服务的名称) {
		return (服务) this.拿出(服务的名称);
	}

	
	public void 启动主动服务() {
		for (int i = 0; i < count; i++) {
			服务 服务项 = (服务) 乾坤包.get("" + i);
			if (服务项.get运行类型().equals(常量.服务运行类型.主动运行)) {
				服务项.运行(this);
			}
		}
	}
}
