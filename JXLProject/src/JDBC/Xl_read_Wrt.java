package JDBC;
import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.Label;
import jxl.write.WriteException;

public class Xl_read_Wrt {
   	static Workbook wbook;
    static WritableWorkbook wwbCopy;
    static String ExecutedTestCasesSheet;
    static WritableSheet shSheet;
    
    public static void main(String[] args)throws BiffException, IOException, WriteException{
      WritableWorkbook wworkbook;
      wworkbook = Workbook.createWorkbook(new File("output.xls"));
      WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
      Label label = new Label(0, 2, "A label record");
      wsheet.addCell(label);
      Label label2 = new Label(3, 4, "3.1459");
      wsheet.addCell(label2);
      wworkbook.write();
      wworkbook.close();
      Workbook workbook = Workbook.getWorkbook(new File("output.xls"));
      Sheet sheet = workbook.getSheet(0);
      Cell cell1 = sheet.getCell(0, 2);
      System.out.println(cell1.getContents());
      Cell cell2 = sheet.getCell(3, 4);
      System.out.println(cell2.getContents());
      
      wbook = Workbook.getWorkbook(new File("output.xls"));
      wwbCopy = Workbook.createWorkbook(new File("output.xls"), wbook);
      shSheet = wwbCopy.getSheet(0);
      Label label3 = new Label(1, 0, "zzzzzzzzz");
      shSheet.addCell(label3);
      Label label4 = new Label(1, 1, "%%%%%%%%%%");
      shSheet.addCell(label4);
      wwbCopy.write();
      wwbCopy.close();	    
   }
}