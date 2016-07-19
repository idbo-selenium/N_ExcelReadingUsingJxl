package ReadNWrite;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Trello_Login {
	
	static Workbook wbook;
    static WritableWorkbook wwb;
    static String ExecutedTestCasesSheet;
    static WritableSheet write_sheet;
    static WebDriver driver;
    static Label label;
    
    public static void main(String[] args) throws Exception{	
		File file = new File("TrelloLogin.xls");
		Workbook wb_get = Workbook.getWorkbook(file);
		Sheet sheet = wb_get.getSheet("Login");
		int rows_count = sheet.getRows();
		System.out.println("Rows count : "+rows_count);
		
		wbook = Workbook.getWorkbook(file);
		wwb = Workbook.createWorkbook(file,wbook);
		write_sheet = wwb.getSheet("Login");		
		
		for(int i = 1;i < rows_count;i++){
			String username = sheet.getCell(0, i).getContents();
			String password = sheet.getCell(1, i).getContents();
			System.out.println(username + " , "+password);
			driver =  new FirefoxDriver();
			driver.get("https://trello.com/login");
			Thread.sleep(8000);
			driver.findElement(By.id("user")).sendKeys(username);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("login")).click();
			
			String loginPage_title = driver.getTitle();
			System.out.println("Login Page title is : "+loginPage_title);
			
			//WaitMethod(driver,"//div[@id='header']//span[@class='header-btn-text js-member-name']");
			Thread.sleep(4000);
			String title = driver.getTitle();
			
			System.out.println("title is : "+title);
			if(title.equals("Boards | Trello")){				
				label = new Label(2,i,"Success");
				write_sheet.addCell(label);
				System.out.println("Success");
			}
			else {
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