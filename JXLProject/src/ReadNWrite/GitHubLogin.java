package ReadNWrite;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class GitHubLogin {
	
	 public static void main(String [] args) throws BiffException, IOException, RowsExceededException, WriteException, InterruptedException{
	
		File file = new File("GitHubLogin.xls");
		Workbook wb = Workbook.getWorkbook(file);
		Sheet wb_sheet = wb.getSheet("Login");
		int rows_count = wb_sheet.getRows();
		System.out.println("Rows Count : "+rows_count);
		
		WritableWorkbook wwb = Workbook.createWorkbook(file,wb);
		WritableSheet write_sheet = wwb.getSheet("Login");
		Label label;
		
		for(int i = 1;i<rows_count;i++){
			String username = wb_sheet.getCell(0, i).getContents();
			String password = wb_sheet.getCell(1, i).getContents();
			System.out.println(username+" , "+password);
			WebDriver driver = new FirefoxDriver();
			driver.navigate().to("https://github.com/login");
			driver.findElement(By.id("login_field")).sendKeys(username);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.name("commit")).click();
			//WaitMethod(driver, "//ul[@id='user-links']//img[@class='avatar']");
			Thread.sleep(4000);
			String title = driver.getTitle();
			System.out.println("title is : "+title);
			if(title.equals("GitHub")){
				label = new Label(2,i,"Success");
				write_sheet.addCell(label);
				System.out.println("Success");
			}
			else{
				label = new Label(2,i,"Failure");
				write_sheet.addCell(label);
				System.out.println("Failure");
			}
			
		    driver.close();
		}
		wwb.write();
	    wwb.close();
	}	
}