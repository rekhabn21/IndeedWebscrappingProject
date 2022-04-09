package utils.Jobscrapper;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


	public class XLutils{
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public CellStyle style;   
	static String path=null;

	public XLutils(String path) {
		XLutils.path=path;
	}

	public int getRowCount(String sheetName) throws IOException 
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}


	public int getCellCount(String sheetName,int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}


	public String getCellData(String sheetName,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);

		DataFormatter formatter = new DataFormatter();
		String data;
		try{
		data = formatter.formatCellValue(cell); 
		//Returns the formatted value of a cell as a String regardless of the cell type.
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}
	  
	
	
	public Object[][] getExcelData(String fileName, String sheetName){
        	
        Object[][] data = null;   	
 	   	
	   			
        try {
        	
  	    fi = new FileInputStream(path);
  	    workbook=new XSSFWorkbook(fi);
  		sheet=workbook.getSheet(sheetName);
   	   	XSSFRow row = sheet.getRow(0);
   	   	
   	   	int totalrows = sheet.getLastRowNum(); // no. of rows
   	   	System.out.println("Total no. of rows is  "  +totalrows );
  	 
   	   	int totalcols= sheet.getRow(0).getLastCellNum();// number of cells in a row =no. of columns in the table
   	   	System.out.println("Total no. of cols is  "  +totalcols); 
  	  	
  	   	//Cell cell;
  	   data = new Object[totalrows][totalcols];
  	   	
  	   	for(int i =1; i<=totalrows;i++){
			 row = sheet.getRow(i);// reading rows 

  	   		for(int j=0;j<totalcols;j++){
  		    	   				 	
  		   // cell= row.getCell(j);
  		   		    		    		
  		   DataFormatter formatter = new DataFormatter();
  		data[i-1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j)); 
	  			
  		 // data[i-1][j] = cell.getStringCellValue();
 		//Returns the formatted value of a cell as a String regardless of the cell type.
  	   					}
  	   			}
  	   	    }
  	   			
        	catch (Exception e) {
	     	   
        		System.out.println("The exception is: " + e.getMessage());
  	   			
        		}
     				return data;
		 				
				}
	
	public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
	{
		File xlfile=new File(path);
		if(!xlfile.exists())    // If file not exists then create new file
		{
		workbook=new XSSFWorkbook();
		fo=new FileOutputStream(path);
		workbook.write(fo);
		}
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
				if(workbook.getSheetIndex(sheetName)==-1) // If sheet not exists then create new Sheet
					workbook.createSheet(sheetName);
		
		sheet=workbook.getSheet(sheetName);
					
		if(sheet.getRow(rownum)==null)   // If row not exists then create new Row
				sheet.createRow(rownum);
		row=sheet.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		workbook.write(fo);		
		workbook.close();
		fi.close();
		fo.close();
	}

	public void writeToExcel(ArrayList<String> dataRows , String SheetName,int rownum) throws IOException {

		File excelfile=new File(path);
		if(!excelfile.exists())
		{
			workbook=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			workbook.write(fo);
		}

		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);

		////If sheet not exists create one 
		if(workbook.getSheetIndex(SheetName)==-1) 
			workbook.createSheet(SheetName);
		sheet=workbook.getSheet(SheetName);
	//	int rowNum=0;
		////If row not exists create one	
		//		if(sheet.getRow(rowNum)==null)
		//			sheet.createRow(rowNum);
		//		row=sheet.getRow(rowNum);

		int colNum =0;
		//		for(Map.Entry entry: data.entrySet()) {
		//			XSSFRow row = sheet.createRow(rowNum++);
		//			colNum =0;
		//			row.createCell(colNum).setCellValue((String)entry.getKey());
		//			row.createCell(++colNum).setCellValue((String)entry.getValue());
		//		}
		//System.out.println("out"+rownum);

		if(sheet.getRow(rownum)==null) 
		{
			sheet.createRow(rownum);
			System.out.println("Created");
		}
		row=sheet.getRow(rownum);

		//System.out.println("just get "+sheet.getPhysicalNumberOfRows());
		XSSFRow row = sheet.createRow(rownum++);
		for (int p = 0; p < dataRows.size(); p++) {

			//colNum =0;
			row.createCell(p).setCellValue(dataRows.get(p));
			
			//row.createCell(++colNum).setCellValue(data.get());
			//System.out.println("inside"+sheet.getLastRowNum());
		}
		fo=new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
	}
		
	}

	
	
	
	
							
		 		

