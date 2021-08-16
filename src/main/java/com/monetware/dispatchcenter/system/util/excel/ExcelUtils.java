package com.monetware.dispatchcenter.system.util.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author yangjl
 */
public class ExcelUtils {
    @SuppressWarnings("deprecation")
    public static String getCellValue(Cell cell, boolean ignoreType) {
	String cellValue = null;
	if (cell == null) {
	    return null;
	}

	if (ignoreType) {
	    cell.setCellType(Cell.CELL_TYPE_STRING);
	    cellValue = String.valueOf(cell.getStringCellValue());
	} else {
	    DecimalFormat df = new DecimalFormat("#");
	    int cellType = cell.getCellType();
	    switch (cellType) {
	    case Cell.CELL_TYPE_NUMERIC:
		if (HSSFDateUtil.isCellDateFormatted(cell)) {
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    cellValue = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
		} else {
		    cellValue = df.format(cell.getNumericCellValue());
		}
		break;
	    case Cell.CELL_TYPE_STRING:
		cellValue = String.valueOf(cell.getStringCellValue());
		break;
	    case Cell.CELL_TYPE_FORMULA:
		cellValue = String.valueOf(cell.getCellFormula());
		break;
	    case Cell.CELL_TYPE_BLANK:
		cellValue = null;
		break;
	    case Cell.CELL_TYPE_BOOLEAN:
		cellValue = String.valueOf(cell.getBooleanCellValue());
		break;
	    case Cell.CELL_TYPE_ERROR:
		cellValue = String.valueOf(cell.getErrorCellValue());
		break;
	    }
	}

	if (cellValue != null && cellValue.trim().length() <= 0) {
	    cellValue = null;
	}
	return cellValue != null ? cellValue.trim() : cellValue;
    }

	/**
	 * HashMap
	 */
	public static void exportExcel(List<HashMap> datas, String fileName, List<String> columns, List<String> secondHeadList, List<String> firstHeadList,String sheetName, HttpServletResponse response)throws Exception{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(sheetName);
		HSSFCellStyle style = workbook.createCellStyle();
		//style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//excel head 部分
		int start = 1;
		if(firstHeadList != null){
			HSSFRow headRow = sheet.createRow((int) 0);//创建第一行
			HSSFRow row = sheet.createRow((int) 1);//创建第一行
			for(int fHeadLength=0;fHeadLength<firstHeadList.size();fHeadLength++){
				headRow.createCell(fHeadLength).setCellValue(firstHeadList.get(fHeadLength));
			}
			for(int sHeadLength=0;sHeadLength<secondHeadList.size();sHeadLength++){
				row.createCell(sHeadLength).setCellValue(secondHeadList.get(sHeadLength));
			}
			start++;
		}else{
			HSSFRow row = sheet.createRow((int) 0);//创建第一行
			for(int sHeadLength=0;sHeadLength<secondHeadList.size();sHeadLength++){
				row.createCell(sHeadLength).setCellValue(secondHeadList.get(sHeadLength));
			}
		}
		//excel data 部分
		for (int i=0;i<datas.size();i++){
			HSSFRow rowBatch = sheet.createRow((int) i + start);
			HashMap nowData=datas.get(i);
			for (Object key : nowData.keySet()) {

				for(int columnLength=0;columnLength<columns.size();columnLength++){
					rowBatch.createCell(columnLength).setCellValue(nowData.get(columns.get(columnLength)) == null ? "" : nowData.get(columns.get(columnLength)).toString());
				}
			}
		}
		// 弹出保存框方式
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			workbook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".xls");

		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (bis != null){
				bis.close();
			}

			if (bos != null){
				bos.close();
			}
		}
	}
	/**
	 * HashMap
	 */
	public static void exportExcelLinked(LinkedList<LinkedHashMap<String,Object>> datas, String fileName, String[] columns, String[] columnTexts, String sheetName, HttpServletResponse response)throws Exception{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(sheetName);
		HSSFRow row = sheet.createRow((int) 0);//创建第一行
		HSSFCellStyle style = workbook.createCellStyle();
		//style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		Boolean isHasTitle=false;
		for (int i=0;i<datas.size();i++){
			HSSFRow rowBatch = sheet.createRow((int) i + 1);
			HashMap nowData=datas.get(i);
			int k=0;
			for (Object key : nowData.keySet()) {
				if(!isHasTitle){
					for(int columnTextLength=0;columnTextLength<columnTexts.length;columnTextLength++){
						row.createCell(columnTextLength).setCellValue(columnTexts[columnTextLength]);
					}
					isHasTitle=true;
				}
				for(int columnLength=0;columnLength<columns.length;columnLength++){
					rowBatch.createCell(columnLength).setCellValue(nowData.get(columns[columnLength]) == null ? "" : nowData.get(columns[columnLength]).toString());
				}
				k++;
			}
		}
		if(datas.size()==0){
			for(int columnTextLength=0;columnTextLength<columnTexts.length;columnTextLength++){
				row.createCell(columnTextLength).setCellValue(columnTexts[columnTextLength]);
			}
		}
		// 弹出保存框方式
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			workbook.write(os);

		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".xls");

		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (bis != null){
				bis.close();
			}

			if (bos != null){
				bos.close();
			}
		}
	}
	/**
	 * HashMap
	 */
	public static void exportExcel(List<HashMap> datas, String fileName, List<String> columns, List<String> columnTexts, String sheetName, HttpServletResponse response)throws Exception{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(sheetName);
		HSSFRow row = sheet.createRow((int) 0);//创建第一行
		HSSFCellStyle style = workbook.createCellStyle();
		//style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		Boolean isHasTitle=false;
		for (int i=0;i<datas.size();i++){
			HSSFRow rowBatch = sheet.createRow((int) i + 1);
			HashMap nowData=datas.get(i);
			int k=0;
			for (Object key : nowData.keySet()) {
				if(!isHasTitle){
					for(int columnTextLength=0;columnTextLength<columnTexts.size();columnTextLength++){
						row.createCell(columnTextLength).setCellValue(columnTexts.get(columnTextLength));
					}
					isHasTitle=true;
				}
				for(int columnLength=0;columnLength<columns.size();columnLength++){
					rowBatch.createCell(columnLength).setCellValue(nowData.get(columns.get(columnLength)) == null ? "" : nowData.get(columns.get(columnLength)).toString());
				}
				k++;
			}
		}
		if(datas.size()==0){
			for(int columnTextLength=0;columnTextLength<columnTexts.size();columnTextLength++){
				row.createCell(columnTextLength).setCellValue(columnTexts.get(columnTextLength));
			}
		}
		// 弹出保存框方式
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			workbook.write(os);

		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".xls");

		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (bis != null){
				bis.close();
			}

			if (bos != null){
				bos.close();
			}
		}
	}
	/**
	 * HashMap
	 */
	public static void exportMultiline(List<HashMap> datas, String fileName, String[] columns, String[] columnTexts,int[] colNumber,String[] headColumnTexts,String[] head2ColumnTexts, String sheetName, HttpServletResponse response)throws Exception{
		String fileExcelName = new SimpleDateFormat("yyyyMMddhh").format(new Date()).toString() + fileName;
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(sheetName);

		XSSFRow row = sheet.createRow((int) 0);//创建第一行
		XSSFRow row2 = sheet.createRow((int) 1);//创建第二行
		XSSFRow row3 = sheet.createRow((int) 2);//创建第三行

		XSSFCellStyle style = workbook.createCellStyle();
		//居中样式
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);

		Boolean isHasTitle=false;
		for (int i=0;i<datas.size();i++){
			XSSFRow rowBatch = sheet.createRow((int) i + 1);
			HashMap nowData=datas.get(i);
			int k=0;
			for (Object key : nowData.keySet()) {
				if(!isHasTitle){
					for(int columnTextLength=0;columnTextLength<columnTexts.length;columnTextLength++){
						row.createCell(columnTextLength).setCellValue(columnTexts[columnTextLength]);
					}
					isHasTitle=true;
				}
				for(int columnLength=0;columnLength<columns.length;columnLength++){
					rowBatch.createCell(columnLength).setCellValue(nowData.get(columns[columnLength]) == null ? "" : nowData.get(columns[columnLength]).toString());
				}
				k++;
			}
		}
		if(datas.size()==0){
			int start = 0;
			for(int columnTextLength=0;columnTextLength<columnTexts.length;columnTextLength++){
				if(start ==0||colNumber[columnTextLength]==1){
					row.createCell(start).setCellValue(columnTexts[columnTextLength]);
					start++;
				}else{
					if(colNumber[columnTextLength]!=0){
						sheet.addMergedRegion(new CellRangeAddress(0, 0, start, start+colNumber[columnTextLength]-1));
						row.createCell(start).setCellValue(columnTexts[columnTextLength]);
						row.getCell(start).setCellStyle(style);
						start = start+colNumber[columnTextLength];
					}
				}
			}
			for(int columnTextLength=0;columnTextLength<headColumnTexts.length;columnTextLength++){
				row2.createCell(columnTextLength).setCellValue(headColumnTexts[columnTextLength]);
				row3.createCell(columnTextLength).setCellValue(head2ColumnTexts[columnTextLength]);
			}
		}

		// 弹出保存框方式
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			workbook.write(os);

		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".xls");

		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (bis != null){
				bis.close();
			}

			if (bos != null){
				bos.close();
			}
		}
	}
}
