package org.software.sphere.society.platform.pipeline;

public class PipelineConst {

	public final static String REQUEST_INPUT = "request-input";

	public final static String RESPONSE_OUTPUT = "response-output";

	// public final String REQUEST_INPUT = "request-input";

	public static class CORE {
		public static class EXECUTE {
			public final static String SELF_UNIT_PATH = "self.self.self.self.self";//执行入口
		}
	}
	
	public static class OuterDataStructure {

		public static class Raw { // 原生数据结构
			public final static String TOKEN = "raw";
		}

		public static class Sentence { // 句子数据结构
			public final static String TOKEN = "sentence";

			public static class SpaceSymbol {

				public final static String BLANK_REGEXP = "\\s+"; // 空格间隔的正则表达式

				public final static String COMMA_REGEXP = ","; // 逗号间隔的正则表达式

				public final static String SEMICOLON_REGEXP = ";"; // 分号间隔的正则表达式

				public final static String PIONT_REGEXP = "\\."; // 句号,
				// 点号间隔的正则表达式
			}
		}

		public static class Tree { //树形数据结构
			public final static String TOKEN = "tree";

			public static class Symbol { //符号

				public static class Enclose { //围堵符号
					public final static String small_bracket_left = "("; // 左小括号, 圆括号
					
					public final static String small_bracket_right = ")"; // 右小括号, 圆括号

					public final static String middle_bracket_left = "["; // 左中括号, 方括号
					
					public final static String middle_bracket_right = "]"; // 左中括号, 方括号
					
					public final static String big_bracket_left = "{"; // 左大括号, 花括号

					public final static String big_bracket_right = "}"; // 右大括号, 花括号
					
				}
				
				public static String getOpposite(String symbol) {
					String opposite = "";
					
					if (symbol.equals(Enclose.small_bracket_left)) {
						opposite = Enclose.small_bracket_right;
					} else if (symbol.equals(Enclose.small_bracket_right)) {
						opposite = Enclose.small_bracket_left;
					}  else if (symbol.equals(Enclose.middle_bracket_left)) {
						opposite = Enclose.middle_bracket_right;
					}  else if (symbol.equals(Enclose.middle_bracket_right)) {
						opposite = Enclose.middle_bracket_left;
					}  else if (symbol.equals(Enclose.small_bracket_left)) {
						opposite = Enclose.middle_bracket_right;
					}  else if (symbol.equals(Enclose.big_bracket_left)) {
						opposite = Enclose.big_bracket_right;
					}  else if (symbol.equals(Enclose.big_bracket_right)) {
						opposite = Enclose.big_bracket_left;
					} 
					
					return opposite;
				}
			}
			
			
		}
		
//		public final static String OBJECT_TYPE = "object";
		
		public static class Object { //对象数据结构
			public final static String TOKEN = "object";


			public static class Symbol { //符号

				public static class Enclose { //围堵符号
					public final static String small_bracket_left = "("; // 左小括号, 圆括号
					
					public final static String small_bracket_right = ")"; // 右小括号, 圆括号

					public final static String middle_bracket_left = "["; // 左中括号, 方括号
					
					public final static String middle_bracket_right = "]"; // 左中括号, 方括号
					
					public final static String big_bracket_left = "{"; // 左大括号, 花括号

					public final static String big_bracket_right = "}"; // 右大括号, 花括号
					
					public final static String SINGLE_QUOTATION = "'"; // 逗号

					public final static String DOUBLE_QUOTATION = "\""; // 分号
					

				}
				
				public static class Join { //连接符号, 把名称和值连接(联系)起来的符号
					public final static String EQUAL = "="; // 等号
					
					public final static String COLON = ":"; // 冒号

					public final static String SPACE = " "; // 右大括号, 花括号
				}
				
				public static class Separate { //分割符号, 用于分割同类的对象

					public final static String COMMA = ","; // 逗号

					public final static String SEMICOLON = ";"; // 分号
				}
				
				public static class Quotation { //引号

					public final static String SINGLE_QUOTATION = "'"; // 逗号

					public final static String DOUBLE_QUOTATION = "\""; // 分号
					
				}
			}
		}
		
		public static class XML { //xml数据结构
			
			public final static String TOKEN = "xml";

			public static class Symbol { //省略

			}
		}
	}

	public static class Symbol {

		public final static String BLANK = " "; // 空格

		public final static String COMMA = ","; // 逗号

		public final static String SEMICOLON = ";"; // 分号

		public final static String POINT = "."; // 句号, 点号

	}

	public static class SymbolSpaceRegExp { // 符号间隔的正则表达式

		public final static String BLANK_REGEXP = "\\s+"; // 空格间隔的正则表达式

		public final static String COMMA_REGEXP = ","; // 逗号间隔的正则表达式

		public final static String SEMICOLON_REGEXP = ";"; // 分号间隔的正则表达式

		public final static String PIONT_REGEXP = "\\."; // 句号, 点号间隔的正则表达式

	}

	public static class SentenceDataStructureSpaceSymbol { // 句子数据结构分隔符

	}
	
	public static class Tree {
		public static class TreeType {
			public final static String TREE_0D = "tree-0D"; // 零维树
			
			public final static String TREE_1D = "tree-1D	"; // 一维树
			
			public final static String TREE_2D = "tree-2D"; // 二维树
			
			public final static String TREE_3D = "tree-3D"; // 三维树
			
			public final static String TREE_4D = "tree-0D"; // 四维树
			
			public final static String TREE_3D_JSON = "tree-3D:json"; // json数据
			
			public final static String TREE_3D_XML = "tree-3D:xml"; // xml数据
			
		}
	}

}
