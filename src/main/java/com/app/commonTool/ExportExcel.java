package com.app.commonTool;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExportExcel {

	
	/**
	 * 
	 * @param filename 文件名/sheet名
	 * @param titles  表头
	 * @param contentList 内容
	 * @param reponse 响应
	 * @return 导出结果（反馈成功或失败）
	 */
	public final static String exportExcel(String filename,String[] titles, List<Object[]> contentList,HttpServletResponse  response){
		
		//创建一个excel
		HSSFWorkbook wb = new HSSFWorkbook();
		
		//新建一个sheet
		HSSFSheet sheet = wb.createSheet(filename);
		
		//创建标题行
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		
		//定义sheet样式
		HSSFCellStyle columnTopStyle = getColumnTopStyle(wb);
		HSSFCellStyle style = getStyle(wb);
		
		sheet.addMergedRegion(new CellRangeAddress(0,1,0,(titles.length-1)));
		
		cell.setCellStyle(columnTopStyle);
		cell.setCellValue(filename);
		
		//写入列头
		int columnNum = titles.length;
		HSSFRow rowName = sheet.createRow(2);
		for(int i = 0;i < columnNum;i++){
			HSSFCell cellName = rowName.createCell(i);
			cellName.setCellType(HSSFCell.CELL_TYPE_STRING);
			HSSFRichTextString text = new HSSFRichTextString(titles[i]);
			cellName.setCellValue(text);
			cellName.setCellStyle(columnTopStyle);
		}
		
		//写入数据
		for(int i = 0;i < contentList.size(); i++){
			Object[] obj = contentList.get(i);
			HSSFRow objRow = sheet.createRow(i+3);
			
			for(int j = 0;j < obj.length; j++){
				HSSFCell objCell = null;
				if(j == 0){
					objCell = objRow.createCell(j,HSSFCell.CELL_TYPE_NUMERIC);
					objCell.setCellValue(i+1);
				}else{
					objCell = objRow.createCell(j,HSSFCell.CELL_TYPE_STRING);
					if(!"".equals(obj[j]) && obj[j] != null){
						objCell.setCellValue(obj[j].toString());
					}
				}
				
				objCell.setCellStyle(style);
			}
		}
		
		//调节单元格宽度
		for (int colNum = 0; colNum < columnNum; colNum++) {
            int columnWidth = sheet.getColumnWidth(colNum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                HSSFRow currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }
                if (currentRow.getCell(colNum) != null) {
                    HSSFCell currentCell = currentRow.getCell(colNum);
                    if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            if(colNum == 0){
            	sheet.setColumnWidth(colNum, (columnWidth-2) * 256);
            }else{
            	sheet.setColumnWidth(colNum, (columnWidth+4) * 256);
            }
        }
		
		
		//Download
		if(wb != null){
			
			// 设置response参数，可以打开下载页面
			String fileSaveName = "Excel-" + 
						filename + String.valueOf(System.currentTimeMillis()).substring(4, 13) + 
						".xls"; 
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileSaveName+".xls");
            ServletOutputStream os;
			try {
				os = response.getOutputStream();
				wb.write(os);
	        	os.flush();
	        	os.close();
	        	wb.close();
			} catch (IOException e) {
				e.printStackTrace();
				return "error";
			}
        	
		}
		
		return "success";
	}
	
	/* 
	 * 列头单元格样式
	 */    
  	public static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
  		
  		  // 设置字体
    	  HSSFFont font = workbook.createFont();
    	  //设置字体大小
    	  font.setFontHeightInPoints((short)11);
    	  //字体加粗
    	  font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    	  //设置字体名字 
    	  font.setFontName("Courier New");
    	  //设置样式; 
    	  HSSFCellStyle style = workbook.createCellStyle();
    	  //设置底边框; 
    	  style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    	  //设置底边框颜色;  
    	  style.setBottomBorderColor(HSSFColor.BLACK.index);
    	  //设置左边框;   
    	  style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    	  //设置左边框颜色; 
    	  style.setLeftBorderColor(HSSFColor.BLACK.index);
    	  //设置右边框; 
    	  style.setBorderRight(HSSFCellStyle.BORDER_THIN);
    	  //设置右边框颜色; 
    	  style.setRightBorderColor(HSSFColor.BLACK.index);
    	  //设置顶边框; 
    	  style.setBorderTop(HSSFCellStyle.BORDER_THIN);
    	  //设置顶边框颜色;  
    	  style.setTopBorderColor(HSSFColor.BLACK.index);
    	  //在样式用应用设置的字体;  
    	  style.setFont(font);
    	  //设置自动换行; 
    	  style.setWrapText(false);
    	  //设置水平对齐的样式为居中对齐;  
    	  style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    	  //设置垂直对齐的样式为居中对齐; 
    	  style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    	  
    	  return style;
    	  
  	}
  	
  	/*  
	 * 列数据信息单元格样式
	 */  
  	public static HSSFCellStyle getStyle(HSSFWorkbook workbook) {
	  	  // 设置字体
	  	  HSSFFont font = workbook.createFont();
	  	  //设置字体大小
	  	  //font.setFontHeightInPoints((short)10);
	  	  //字体加粗
	  	  //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	  	  //设置字体名字 
	  	  font.setFontName("Courier New");
	  	  //设置样式; 
	  	  HSSFCellStyle style = workbook.createCellStyle();
	  	  //设置底边框; 
	  	  style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	  	  //设置底边框颜色;  
	  	  style.setBottomBorderColor(HSSFColor.BLACK.index);
	  	  //设置左边框;   
	  	  style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	  	  //设置左边框颜色; 
	  	  style.setLeftBorderColor(HSSFColor.BLACK.index);
	  	  //设置右边框; 
	  	  style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	  	  //设置右边框颜色; 
	  	  style.setRightBorderColor(HSSFColor.BLACK.index);
	  	  //设置顶边框; 
	  	  style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	  	  //设置顶边框颜色;  
	  	  style.setTopBorderColor(HSSFColor.BLACK.index);
	  	  //在样式用应用设置的字体;  
	  	  style.setFont(font);
	  	  //设置自动换行; 
	  	  style.setWrapText(false);
	  	  //设置水平对齐的样式为居中对齐;  
	  	  style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	  	  //设置垂直对齐的样式为居中对齐; 
	  	  style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	  	 
	  	  return style;
  	
  	}
}
