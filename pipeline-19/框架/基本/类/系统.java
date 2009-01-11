package 基本.类;


import java.io.IOException;

import org.xml.sax.SAXException;

import 基本.常量;
import 异常.异常;
import 核心.接口.可活动化的;
import 核心.接口.可系统化的;
import 通用.类.乾坤包;
import 通用.类.名单;

public class 系统 extends 基本的实体 implements 可系统化的 {
	
	protected 乾坤包 乾坤包 = new 乾坤包();
	
	protected 名单 活动名单 = new 名单();
	
	public 系统() throws IOException, SAXException {
		super();
	}

	public void 停止() throws 异常{
		for (int i=0; i<乾坤包.获取下一个编号().intValue(); i++) {
			final 可活动化的 活动 = (可活动化的) 乾坤包.查看(new Integer(i));
			活动.去欢送(系统.this);
		}
	}

	public 活动 删除活动(活动 活动) {
		return (活动) 乾坤包.拿出(活动.获取名字());
	}

	public void 启动() throws 异常{
		for (int i=0; i<乾坤包.获取下一个编号().intValue(); i++) {
			final 可活动化的 活动 = (可活动化的) 乾坤包.查看(new Integer(i));
			if (活动.获取活动状态().equals(常量.活动状态常量.正常)) {
				new Thread () {
					public void run() {
						try {
							活动.去接待(系统.this);
						} catch (异常 e) {
							// TODO Auto-generated catch block
							
							e.printStackTrace();
//							this.suspend();//.stop();
							return ;
						}
					}
				}.start();
			}
		}
	}

	public void 添加活动(活动 活动) {
		乾坤包.放入(活动);
	}

	public 活动 获取活动(String 活动名字) {
		return (活动) 乾坤包.查看(活动名字);
	}
	
	public String toString() {
		return super.toString() + ", 乾坤包 : \n" + this.乾坤包 + ", 活动名单 : \n" + this.活动名单;
	}

	public 名单 获取活动名单() {
		return 活动名单;
	}

	public void 设置名单(名单 活动名单) {
		this.活动名单 = 活动名单;
	}
}
