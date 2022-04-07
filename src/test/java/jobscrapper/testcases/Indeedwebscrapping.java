package jobscrapper.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.jobscrapper.Indeedhomepage;
import utils.Jobscrapper.TestBaseJobScrapper;
import utils.Jobscrapper.Testutils;

public class Indeedwebscrapping extends TestBaseJobScrapper {

	public Indeedwebscrapping() throws IOException {
		super();
	}
	Indeedhomepage homepage;

	@Test(dataProvider = "jobsearchcriteria")
		public void jobssearchcriteria(String jobcategory, String where) throws Throwable {
		
		homepage = new Indeedhomepage();
		try {
			homepage.jobsearchfunction(jobcategory,where);
			System.out.print(jobcategory);
			System.out.print("|" +where);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			homepage.Datepostedselection();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
		homepage.jobdatacollection();
		//homepage.jobscrapping(Testutils.sheetname);
			//homepage.jobdata();
		//	homepage.pagination();
			//homepage.listJobs();
		} catch (Exception e) {
			e.printStackTrace();}
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
	@DataProvider(name = "jobsearchcriteria")
	public Object[][] jobsearchcriteria() {
		
		  /*Object[][] searchDataoption = { { "APITesting", "Remote" },
		   { "Rest Assured", "Remote" }, 
		   { "Postman", "Remote" } };*/
		 
		Object[][] searchDataoption = { { "APITesting", "Remote" } };
		return searchDataoption;
		}

}