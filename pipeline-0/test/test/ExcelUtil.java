/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package test;

import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/**
 * ����excel����غ���
 * 
 * @author <a href="mailto:cyyan@isoftstone.com">cyyan</a>
 * @version $Id: ExcelUtil.java,v0.1 2007-12-6 ����01:46:38 cyyan Exp$
 */
public class ExcelUtil {

	final public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * ��ȡ���е�title ��poi����
	 * @param sheet
	 * @param titleRowIndex
	 * @return
	 */
	public static HSSFRow getSheetTitleRow(HSSFSheet sheet, int titleRowIndex) {
		return sheet.getRow(titleRowIndex);
	}
	
	
	/**
	 * �жϱ���һ���Ƿ�Ϊ��
	 * ���Ϊ�վͲ��ý���, �����ų�excel�пհ���
	 * @param row
	 * @return
	 */
	public static boolean isEmptyRow(HSSFRow row) {
		boolean result = true;
		if (row == null) {
			result = true;
		} else {
			for (short i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
				HSSFCell cell = row.getCell(i);
				result &= isEmptyCell(cell);
				if (!result) {
					break;
				}
			}
		}
		return result;
	}

	/**
	 * �ж��Ƿ��ǿյ�Ԫ��
	 * @param cell
	 * @return
	 */
	public static boolean isEmptyCell(HSSFCell cell) {
		String cellStr = null;
		cellStr = cell2string(cell, null);
		return cellStr == null || cellStr.trim().equals("");
	}

	/**
	 * �Ե�Ԫ�������ת�����ַ���, �Ա����ͳһ����
	 * 
	 * @param cell
	 * @return
	 */
	public static String cell2string(HSSFCell cell, HSSFFormulaEvaluator evaluator) {
		if (cell == null) {
			return null;
		}
		String str = null;
		final int cellType = cell.getCellType();
	
		switch (cellType) {
		case HSSFCell.CELL_TYPE_STRING:
			str = "" + cell.getRichStringCellValue().getString().trim();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				str = "" + dateFormat.format(cell.getDateCellValue());
			} else {
				str = String.valueOf(cell.getNumericCellValue());
				
				//modify by cyyan 2008-09-23 19:17:28 
				//excel����ֵת������ʱ����E������, ���º���Ĺ���У��ʧ��;
				//Ϊ�˽��а�E������ת����ͨ������, ����ʹ��С�����15λ, (15λ��С������󾫶�, �ܹ���֤�Ǳ�������������)
				// ʹ��15λС����, ��������ĩβ��0, ���ڵ�һ�������ȥ����Щĩβ0
//				str = "" + new BigDecimal(numberStr).setScale(15, BigDecimal.ROUND_HALF_UP);
//				
//				//modify yanchangyou 2008-09-26 18:01:43 
//				//ԭ�������ֻ��ȥ�� .0000* ����ģʽ, �޸ĺ���ȥ��ĩβ��0, ���ĩβ������
//				if (str.indexOf('.') != -1) {
//					str = str.replaceAll("(\\.)?0*$", "");
//				}				

				/*
				 * ����ȥ��С�����ĩβ����, ��С�����ȫ��������
				 */
//				if (str.indexOf('.') != -1) { //ֻ������С����
//					int index = str.length();
//					for (int i = str.length()-1; i > -1; i--) {
//						if (str.charAt(i) == '0') {
//							index = i;
//						} else if (str.charAt(i) == '.'){
//							index = i;
//							break;
//						} else {
//							break;
//						}
//					}
//					str = str.substring(0, index);
//				}
			}
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			str = "";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			str = "" + cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			str = "" + cell.getErrorCellValue();
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			if (evaluator == null) {
				str = "" + cell.getRichStringCellValue().getString();				
			} else {
				str = "" + evaluator.evaluate(cell).getNumberValue();
			}
			
			break;
		}	

		return (str == null || str.trim().equals("")) ? null : str.trim();
	}



	/**
	 * ��ȡexcel�еķ���
	 * @param sheet
	 * @param workbook
	 * @param row
	 * @return
	 */
	public static HSSFFormulaEvaluator getFormulaEvaluator(HSSFSheet sheet, HSSFWorkbook workbook, HSSFRow row) {
		HSSFFormulaEvaluator evaluator = new HSSFFormulaEvaluator(sheet, workbook);
		evaluator.setCurrentRow(row);
		return evaluator;
	}
}
