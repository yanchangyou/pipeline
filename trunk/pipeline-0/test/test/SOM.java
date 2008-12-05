package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.software.matter.molecule.platform.pipeline.core.Pipeline;
import org.software.matter.molecule.platform.pipeline.core.PipelineConfig;

public class SOM {

	public static HSSFWorkbook readExcel(String excelFilePath)
			throws FileNotFoundException, IOException {
		HSSFWorkbook wb = null;
		
		excelFilePath = SOM.class.getClassLoader().getResource(excelFilePath).getFile();
		System.out.println("excel 文件路径是:" + excelFilePath);
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
				excelFilePath));
		wb = new HSSFWorkbook(fs);
		return wb;
	}

	public static HSSFSheet readSheet(HSSFWorkbook wb, String sheetName) {
		return wb.getSheet(sheetName);
	}

	public static Table sheet2table(HSSFSheet sheet) {
		Table table = new Table();
		HSSFRow row;
		HSSFCell cell;

		int rows; // No of rows
		rows = sheet.getPhysicalNumberOfRows();

		int cols = 0; // No of columns
		int tmp = 0;
		// log.info("读取excel完毕");
		// This trick ensures that we get the data properly even if it
		// doesn't start from first few rows
		for (int i = 0; i < 10 || i < rows; i++) {
			row = sheet.getRow(i);
			if (row != null) {
				tmp = sheet.getRow(i).getPhysicalNumberOfCells();
				if (tmp > cols)
					cols = tmp;
			}
		}

		int noSpaceRow = 0;
		int noSpaceCol = 0;
		// read field title
		// String[] field = new String[cols];
		for (int r = 0; r < 1; r++) {
			row = sheet.getRow(r);
			if (row != null) {
				for (int c = 0; c < cols; c++) {
					cell = row.getCell((short) c);
					if (cell != null && !cell.toString().trim().equals("")) {
						noSpaceCol++;
					}
				}
			}
		}

		String[] field = new String[noSpaceCol];
		for (int r = 0; r < 1; r++) {
			row = sheet.getRow(r);
			if (row != null) {
				for (int c = 0, innerC = 0; c < cols; c++) {
					cell = row.getCell((short) c);
					if (cell != null && !cell.toString().trim().equals("")) {
						field[innerC++] = cell.toString();
					}
				}
			}
		}
		table.setField(field);

		// log.info("开始遍历sheet，获取具体的行数");

		for (int r = 1; r < rows; r++) {
			row = sheet.getRow(r);
			if (row != null) {

				boolean isSpaceRow = true;
				for (int c = 0; c < cols; c++) {
					cell = row.getCell((short) c);
					boolean isSpaceCell = cell == null
							|| (cell.getCellType() == HSSFCell.CELL_TYPE_STRING && cell
									.toString().trim().equals(""));

					isSpaceRow &= isSpaceCell;

				}
				if (isSpaceRow) {

				} else {
					noSpaceRow++;
				}
			}
		}

		// read value of each field
		String[][] value = new String[noSpaceRow][noSpaceCol];

		// System.out.println("rows:" + rows);
		// System.out.println("cols:" + cols);
		// log.info("遍历sheet，转换为Table");
		// System.in.read();

		for (int r = 1, innerR = 0; r < rows; r++) {
			row = sheet.getRow(r);
			if (row != null) {
				for (int c = 0, innerC = 0; c < cols; c++) {
					cell = row.getCell((short) c);
					// if (cell != null
					// && !(cell.getCellType() == HSSFCell.CELL_TYPE_STRING &&
					// cell
					// .toString().trim().equals(""))) {
					value[innerR][innerC++] = ExcelUtil.cell2string(cell, null);// cell.toString();
					// }
				}
				innerR++;
			}
		}
		table.setValue(value);

		// log.info("sheet to table 处理完毕");
		return table;

	}

	public static List table2list(Table table) {

		List list = new ArrayList();
		Map map = new HashMap();
		for (int i = 0; i < table.getValue().length; i++) {
			Student student = new Student();
			for (int j = 0; j < table.getField().length; j++) {
				map.put(table.getField()[j], table.getValue()[i][j]);
			}

			try {
				BeanUtils.populate(student, map);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			list.add(student);
		}
		return list;
	}

	public static void printList(List list) {
		System.out.println(list);
	}

	public static void main(String[] args) throws Exception {
		String cofigFilePath = "test/sheet2list.pipeline.xml";
		PipelineConfig aPipelineConfig = new PipelineConfig(cofigFilePath);

		Pipeline aPipeline = aPipelineConfig.getPipeline("sheet2list");

		aPipeline.deal();
	}

	public static List heightAdd(List list, Double addHeight) {


		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Student student = (Student) iter.next();
			student.setHeight(student.getHeight().doubleValue() + addHeight.doubleValue());
		}
		return list;
	}

}
