package com.app.commonTool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadExcel {

	@SuppressWarnings("resource")
	public static List<List<String>> ReadXlsx(String path) throws IOException{
		InputStream Fis = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(Fis);
		List<List<String>> result = new ArrayList<>();
		for(int numSheet = 0;numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++){
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if(hssfSheet == null){
				continue;
			}
			for (int rowNum = 0; rowNum < hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				List<String> rowList = new ArrayList<>();
				for(int colIx = hssfRow.getFirstCellNum(); colIx < hssfRow.getLastCellNum(); colIx++){
					HSSFCell cell = hssfRow.getCell(colIx);
					if(cell == null){
						continue;
					}
					rowList.add(getCellValue(cell));
				}
				result.add(rowList);
			}
		}
		
		return result;
		
	}
	
	public static String getCellValue(HSSFCell cell){
		switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue()?"ture":"false";
			case HSSFCell.CELL_TYPE_FORMULA:
				return cell.getCellFormula();
			case HSSFCell.CELL_TYPE_NUMERIC:
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				return cell.getStringCellValue();
			case HSSFCell.CELL_TYPE_STRING:
				return cell.getStringCellValue();
			default:
				return "";
		}
	}
}
