package org.software.sphere.society.platform.pipeline;

public class PipelineConst {

	public final static String REQUEST_INPUT = "request-input";

	public final static String RESPONSE_OUTPUT = "response-output";

	// public final String REQUEST_INPUT = "request-input";

	public static class CORE {
		public static class EXECUTE {
			public final static String SELF_UNIT_PATH = "self.self.self.self.self";//ִ�����
		}
	}
	
	public static class OuterDataStructure {

		public static class Raw { // ԭ�����ݽṹ
			public final static String TOKEN = "raw";
		}

		public static class Sentence { // �������ݽṹ
			public final static String TOKEN = "sentence";

			public static class SpaceSymbol {

				public final static String BLANK_REGEXP = "\\s+"; // �ո�����������ʽ

				public final static String COMMA_REGEXP = ","; // ���ż����������ʽ

				public final static String SEMICOLON_REGEXP = ";"; // �ֺż����������ʽ

				public final static String PIONT_REGEXP = "\\."; // ���,
				// ��ż����������ʽ
			}
		}

		public static class Tree { //�������ݽṹ
			public final static String TOKEN = "tree";

			public static class Symbol { //����

				public static class Enclose { //Χ�·���
					public final static String small_bracket_left = "("; // ��С����, Բ����
					
					public final static String small_bracket_right = ")"; // ��С����, Բ����

					public final static String middle_bracket_left = "["; // ��������, ������
					
					public final static String middle_bracket_right = "]"; // ��������, ������
					
					public final static String big_bracket_left = "{"; // �������, ������

					public final static String big_bracket_right = "}"; // �Ҵ�����, ������
					
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
		
		public static class Object { //�������ݽṹ
			public final static String TOKEN = "object";


			public static class Symbol { //����

				public static class Enclose { //Χ�·���
					public final static String small_bracket_left = "("; // ��С����, Բ����
					
					public final static String small_bracket_right = ")"; // ��С����, Բ����

					public final static String middle_bracket_left = "["; // ��������, ������
					
					public final static String middle_bracket_right = "]"; // ��������, ������
					
					public final static String big_bracket_left = "{"; // �������, ������

					public final static String big_bracket_right = "}"; // �Ҵ�����, ������
					
					public final static String SINGLE_QUOTATION = "'"; // ����

					public final static String DOUBLE_QUOTATION = "\""; // �ֺ�
					

				}
				
				public static class Join { //���ӷ���, �����ƺ�ֵ����(��ϵ)�����ķ���
					public final static String EQUAL = "="; // �Ⱥ�
					
					public final static String COLON = ":"; // ð��

					public final static String SPACE = " "; // �Ҵ�����, ������
				}
				
				public static class Separate { //�ָ����, ���ڷָ�ͬ��Ķ���

					public final static String COMMA = ","; // ����

					public final static String SEMICOLON = ";"; // �ֺ�
				}
				
				public static class Quotation { //����

					public final static String SINGLE_QUOTATION = "'"; // ����

					public final static String DOUBLE_QUOTATION = "\""; // �ֺ�
					
				}
			}
		}
		
		public static class XML { //xml���ݽṹ
			
			public final static String TOKEN = "xml";

			public static class Symbol { //ʡ��

			}
		}
	}

	public static class Symbol {

		public final static String BLANK = " "; // �ո�

		public final static String COMMA = ","; // ����

		public final static String SEMICOLON = ";"; // �ֺ�

		public final static String POINT = "."; // ���, ���

	}

	public static class SymbolSpaceRegExp { // ���ż����������ʽ

		public final static String BLANK_REGEXP = "\\s+"; // �ո�����������ʽ

		public final static String COMMA_REGEXP = ","; // ���ż����������ʽ

		public final static String SEMICOLON_REGEXP = ";"; // �ֺż����������ʽ

		public final static String PIONT_REGEXP = "\\."; // ���, ��ż����������ʽ

	}

	public static class SentenceDataStructureSpaceSymbol { // �������ݽṹ�ָ���

	}
	
	public static class Tree {
		public static class TreeType {
			public final static String TREE_0D = "tree-0D"; // ��ά��
			
			public final static String TREE_1D = "tree-1D	"; // һά��
			
			public final static String TREE_2D = "tree-2D"; // ��ά��
			
			public final static String TREE_3D = "tree-3D"; // ��ά��
			
			public final static String TREE_4D = "tree-0D"; // ��ά��
			
			public final static String TREE_3D_JSON = "tree-3D:json"; // json����
			
			public final static String TREE_3D_XML = "tree-3D:xml"; // xml����
			
		}
	}

}
