package 基本;


import java.util.HashMap;
import java.util.Map;


public final class 常量 {

	public final static class 活动状态常量 {
		public static String 正常 = "正常";
		public static String 暂停 = "暂停";
		public static String 废弃 = "废弃";
		
		static Map map = new HashMap();
		static {
			map.put(new Integer(1), "正常");
			map.put(new Integer(0), "暂停");
			map.put(new Integer(-1), "废弃");
			map.put("正常", new Integer(1));
			map.put("暂停", new Integer(0));
			map.put("废弃", new Integer(-1));
		}

		public static String 获取状态名字(int 状态ID) {
			return (String) map.get(new Integer(状态ID));
		}

		public static int 获取状态ID(String 状态名字) {
			return ((Integer) map.get(new Integer(状态名字))).intValue();
		}

	}
	/**
	 * 自动|手动|禁止
	 * 
	 * @author yanchangyou@gmail.com
	 * @date : 2009-1-9 下午06:59:16
	 * @file : 常量.java
	 * @version : 0.1
	 */
	public final static class 系统启动类型 {
		public static String 自动启动 = "自动";
		
	}
	
	public final static class 节点状态 {
		public static String 使用 = "使用";
		public static String 删除 = "删除";
		public static String 初始化 = "初始化";
		public static String 已初始化 = "已初始化";
		public static String 被引用 = "被引用";
		public static String 被删除 = "被删除";
	} 
}
