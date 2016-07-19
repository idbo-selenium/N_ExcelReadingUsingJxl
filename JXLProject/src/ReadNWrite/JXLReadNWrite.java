package ReadNWrite;

import java.io.File;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class JXLReadNWrite {

	public static void main(String [] args) throws Exception{
		WritableWorkbook wb;
		wb=Workbook.createWorkbook(new File("C:\\workspaces\\webdriver\\spicejet\\JXLProject\\Xl_Files\\mybook.xls"));
		WritableSheet wsheet=wb.createSheet("mysheet", 0);
		Label l=new Label (0, 2, "A label record");
		wsheet.addCell(l);
		Number num=new Number(3, 4, 3.1459);
		wsheet.addCell(num);
		wb.write();
		wb.close();
		Workbook workbook = Workbook.getWorkbook(new File("C:\\workspaces\\webdriver\\spicejet\\JXLProject\\Xl_Files\\mybook.xls"));
	      Sheet sheet = workbook.getSheet(0);
	      Cell cell1 = sheet.getCell(0, 2);
	      System.out.println(cell1.getContents());
	      Cell cell2 = sheet.getCell(3, 4);
	      System.out.println(cell2.getContents());
	      workbook.close();
	}	
}