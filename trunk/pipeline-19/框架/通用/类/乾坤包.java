package 通用.类;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import 基本.接口.基本的;
import 通用.接口.有名字的;

/**
 * 乾坤包节点<br>
 * <br>
 * <br>
 * @author yanchangyou@gmail.com
 * @date : 2009-1-8 下午09:18:49
 * @file : 节点.java
 * @version : 0.1
 */
public class 乾坤包 {

	
	public Map 乾坤包 = new HashMap();
	
	/**
	 * id从0开始编号
	 */
	private Integer 下一个编号 = new Integer(0);
	
	public Map get乾坤包() {
		return 乾坤包;
	}

	public void set乾坤包(Map 乾坤包) {
		this.乾坤包 = 乾坤包;
	}

	public Integer get下一个编号() {
		return 下一个编号;
	}
	
	public Integer 获取下一个编号() {
		return this.get下一个编号();
	}

	public void 放入(有名字的 对象) {
		
		乾坤包.put(对象.获取名字(), 对象);
		
		乾坤包.put(this.下一个编号 , 对象);
		
//		对象.设置被引用次数(对象.获取被引用次数() + 1);
		
//		对象.设置父节点(this);
		
		下一个编号 = new Integer(下一个编号.intValue() + 1);
	}
	
	public 基本的 拿出(Integer 对象的编号) {
		
		基本的 对象 = (基本的) 乾坤包.remove(对象的编号);
		乾坤包.remove(对象.获取名字());
		
//		对象.设置被引用次数(对象.获取被引用次数() - 1);
		对象.设置父节点(null);
		
		return 对象;
	}
	
	public 有名字的 拿出(String 对象的名字) {

		有名字的 对象 = (有名字的) 乾坤包.remove(对象的名字);
		Integer 对象的编号 = 获取对象编号(对象);
		乾坤包.remove(对象的编号);
		
//		对象.设置被引用次数(对象.获取被引用次数() - 1);
		
		return 对象;
	}

	public 有名字的 查看(Integer 对象的编号) {
		return (有名字的) 乾坤包.get(对象的编号);
	}
	
	public 有名字的 查看(String 对象的名字) {
		return (有名字的) 乾坤包.get(对象的名字);
	}

	public void 替换(Integer 对象的编号, 有名字的 对象) {
		
		乾坤包.put(对象.获取名字(), 对象);
		
		乾坤包.put(对象的编号 , 对象);
	}
	
	public void 替换(String 对象的名字, 有名字的 对象) {
		
		Integer 对象的编号 = 获取对象编号(对象);

		乾坤包.put(对象.获取名字(), 对象);
		
		乾坤包.put(对象的编号 , 对象);
		
	}
	
	public Integer 获取对象编号(有名字的 对象) {
		Integer 对象的编号 = null;
		for (Iterator iterator = 乾坤包.keySet().iterator(); iterator.hasNext();) {
			Object 遍历的编号 = iterator.next();
			if (Integer.class.isInstance(遍历的编号)) {
				有名字的 遍历的对象 = (有名字的) 乾坤包.get(遍历的编号);
				if (遍历的对象.equals(对象)) {
					对象的编号 = (Integer) 遍历的编号;
					break;
				}
			}
		}
		return 对象的编号;
	}
	
	public String toString() {
		return super.toString() + ", " + this.乾坤包;
	}
	
	/**
	 * 初始化节点
	 */
	public 乾坤包() {
		super();
	}
}