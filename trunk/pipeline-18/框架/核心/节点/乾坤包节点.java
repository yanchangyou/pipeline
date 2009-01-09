package 核心.节点;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.xml.sax.SAXException;

import 共用.可统一的;

/**
 * 乾坤包节点<br>
 * <br>
 * <br>
 * @author yanchangyou@gmail.com
 * @date : 2009-1-8 下午09:18:49
 * @file : 节点.java
 * @version : 0.1
 */
public abstract class 乾坤包节点 extends 节点 {

	
	protected Map 乾坤包 = new HashMap();
	
	/**
	 * id从0开始编号
	 */
	protected Integer 下一个编号 = new Integer(0);
	
	public Map get乾坤包() {
		return 乾坤包;
	}

	public void set乾坤包(Map 乾坤包) {
		this.乾坤包 = 乾坤包;
	}

	public Integer get下一个编号() {
		return 下一个编号;
	}

	public void set下一个编号(Integer 下一个编号) {
		this.下一个编号 = 下一个编号;
	}

	protected void 放入(可统一的 对象) {
		
		乾坤包.put(对象.获取名字(), 对象);
		
		乾坤包.put(this.下一个编号 , 对象);
		
		对象.设置被引用次数(对象.获取被引用次数() + 1);
		
		对象.设置父节点(this);
		
		下一个编号 = new Integer(下一个编号.intValue() + 1);
	}
	
	protected 可统一的 拿出(Integer 对象的编号) {
		
		可统一的 对象 = (可统一的) 乾坤包.remove(对象的编号);
		乾坤包.remove(对象.获取名字());
		
		对象.设置被引用次数(对象.获取被引用次数() - 1);
		对象.设置父节点(null);
		
		return 对象;
	}
	
	protected 可统一的 拿出(String 对象的名字) {

		可统一的 对象 = (可统一的) 乾坤包.remove(对象的名字);
		Integer 对象的编号 = 获取对象编号(对象);
		乾坤包.remove(对象的编号);
		
		对象.设置被引用次数(对象.获取被引用次数() - 1);
		
		return 对象;
	}

	protected 可统一的 查看(Integer 对象的编号) {
		return (可统一的) 乾坤包.get(对象的编号);
	}
	
	protected 可统一的 查看(String 对象的名字) {
		return (可统一的) 乾坤包.get(对象的名字);
	}

	protected void 替换(Integer 对象的编号, 可统一的 对象) {
		
		乾坤包.put(对象.获取名字(), 对象);
		
		乾坤包.put(对象的编号 , 对象);
	}
	
	protected void 替换(String 对象的名字, 可统一的 对象) {
		
		Integer 对象的编号 = 获取对象编号(对象);

		乾坤包.put(对象.获取名字(), 对象);
		
		乾坤包.put(对象的编号 , 对象);
		
	}
	
	public Integer 获取对象编号(可统一的 对象) {
		Integer 对象的编号 = null;
		for (Iterator iterator = 乾坤包.keySet().iterator(); iterator.hasNext();) {
			Object 遍历的编号 = iterator.next();
			if (Integer.class.isInstance(遍历的编号)) {
				可统一的 遍历的对象 = (可统一的) 乾坤包.get(遍历的编号);
				if (遍历的对象.equals(对象)) {
					对象的编号 = (Integer) 遍历的编号;
					break;
				}
			}
		}
		return 对象的编号;
	}
	
	public String toString() {
		return super.toString() + ", \n" + this.乾坤包;
	}
	
	/**
	 * 初始化节点
	 * @throws IOException
	 * @throws SAXException
	 */
	public 乾坤包节点() throws IOException, SAXException {
		super();
	}
}