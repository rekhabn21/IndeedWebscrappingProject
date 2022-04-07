package jobscrapper.testcases;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pom.jobscrapper.searchpage;
import utils.Jobscrapper.TestBaseJobScrapper;
import utils.Jobscrapper.Testutils;

public class newtest  extends TestBaseJobScrapper{

	public newtest() throws IOException {
		super();
	}
	
	searchpage search= new searchpage();
	
	//@Test(priority=1)
	 public void apiJobSearch() throws IOException {
		
		 search= new searchpage();
			System.out.println("\n**************" +" API Testing" + "*******************" +" Remote"+ "**********");
		
		try {
			search.apisearchfunction();
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
		
		try {
		search.Datepostedselection();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}	
		
		try {	
			search.datacollectiontocsv(Testutils.sheetname1, Testutils.apitesting);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}	
	System.out.println("API job search completed");	
	}
	
	
	@Test(priority=2)
	 public void restassuredJobSearch() throws IOException {
	 
	  search= new searchpage();
		System.out.println("\n**************" +"Rest assured search" + "*******************" +" Remote"+ "**********");

		try {
			search.restassuredsearchfunction();
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
		
		try {
		search.Datepostedselection();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}	
		
		try {	
			search.jobsdatatoexcel(Testutils.sheetname2, Testutils.restassuredtesting);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}	
	System.out.println("Rest assured job search completed");	
	}
		
	//@Test(priority=3)
	 public void postmanJobSearch() throws IOException { 
		search= new searchpage();
		System.out.println("\n**************" +" Postman search" + "*******************" +" Remote"+ "**********");

		try {
			search.postmansearchfunction();
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
		
		try {
		search.Datepostedselection();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}	
		
		try {	
			search.jobsdatatoexcel(Testutils.sheetname3, Testutils.postmantesting);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}	
	System.out.println("Postman job search completed");	
	}
		@BeforeTest
		public void beforeTest() {
			System.out.println("Before Test begins====>>> Open new chrome Browser");
			try {
				TestBaseJobScrapper.Intialization();
				driver.get(Testutils.Base_URL);
			} catch (NullPointerException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Indeed.com     " + driver.getTitle());
			}
	
@AfterTest
public void afterTest() throws IOException {
	driver.close();
	driver.quit();
	System.out.println("After Test ends====>>> Close chrome Browser");
	}

	
	
	
}
