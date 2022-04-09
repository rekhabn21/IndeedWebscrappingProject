package com.qa.jobscrapperpageobjects;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencsv.CSVWriter;
import com.qa.jobscrapperutils.TestBaseJobScrapper;
import com.qa.jobscrapperutils.Testutils;
import com.qa.jobscrapperutils.XLutils;
import com.relevantcodes.extentreports.utils.Writer;

public class searchpage extends TestBaseJobScrapper {

/**
 * constructor- used to initiate webelements from Pagefactory
 * @throws IOException
 */
	public searchpage() throws IOException {
		super();
		try {
			PageFactory.initElements(driver, this);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//XLutils excelutils;
	
	// Webelements from Indeed page
	@FindBy(xpath = "//input[@id='text-input-what']")	@CacheLookup
	WebElement whatsearchbox;

	@FindBy(xpath = "//input[@id='text-input-where']")	@CacheLookup
	WebElement wheresearchbox;

	@FindBy(css = "button[type='submit']")	@CacheLookup
	WebElement findjobsbtn;
	
	@FindBy(xpath = "//*[@id='popover-x']/button")	@CacheLookup
	WebElement popupbutton;

	@FindBy(css = "button[aria-controls='filter-dateposted-menu']")	@CacheLookup
	WebElement datepostedbtn;

	@FindBy(xpath = "//ul[@id='filter-dateposted-menu']/li")	@CacheLookup
	List<WebElement> datepostedmenu;

	@FindBy(xpath = "//ul[@id='filter-dateposted-menu']/li/a[contains(text(),'Last 24 hours')]")
	WebElement datepostedoption1;

	@FindBy(xpath = "//ul[@id='filter-dateposted-menu']/li/a[contains(text(),'Last 3 days')]")
	WebElement datepostedoption2;

	@FindBy(xpath = "//ul[@id='filter-dateposted-menu']/li/a[contains(text(),'Last 7 days')]")
	WebElement datepostedoption3;

	@FindBy(xpath = "//ul[@id='filter-dateposted-menu']/li/a[contains(text(),'Last 14 days')]")
	WebElement datepostedoption4;

	@FindBy(xpath = "//ul[@id='filter-dateposted-menu']/li/a[contains(text(),'since you lastvisited')]")
	WebElement datepostedoption5;

	// pagination elements
	@FindBy(xpath = "//a[@aria-label='Previous']//span//span//*[name()='svg']")
	WebElement previouspagebtn;

	@FindBy(xpath = "//ul[@class='pagination-list']/li")
	List<WebElement> pagination;

	@FindBy(xpath = "//div//ul[@class='pagination-list']//li/b")
	WebElement pageonebtn;

	@FindBy(xpath = "//a[@aria-label='Next']//span//span//*[name()='svg']")
	WebElement nextpagebtn;

	@FindBy(xpath = "//a[@aria-label='Next']")
	List<WebElement> nextpage;

	// pop-up alert // popupwindow
	@FindBy(id = "popover-foreground")
	WebElement popupwindow;

	@FindBy(id = "popover-x")
	WebElement popupcancelbtn;

	// job cards info
	@FindBy(xpath = "//div[@class='job_seen_beacon']")
	List<WebElement> jobcards;

	@FindBy(xpath = "//h1[@id='jobsInLocation']")
	WebElement jobCategory;

	@FindBy(xpath = "//h2[@class='jobTitle jobTitle-newJob']/span")
	List<WebElement> jobTitle;

	@FindBy(xpath = "//span[@class='companyName']")
	List<WebElement> companyName;

	@FindBy(xpath = "//div[@class='companyLocation']")
	List<WebElement> jobLocation;

	@FindBy(xpath = "//span[@class='companyName']/ancestor::a")
	List<WebElement> jobLink;

	@FindBy(xpath = "//span[@class='date']")
	List<WebElement> jobPostedDate;

	// iframe
	@FindBy(xpath = "//iframe[@id='vjs-container-iframe']")
	WebElement iframe;

	@FindBy(css = "#jobDescriptionText")
	WebElement jobDescription;

	@FindBy(css = "p[class='jobsearch-HiringInsights-entry--bullet'] span[class='jobsearch-HiringInsights-entry--text']")
	WebElement postedtime;

	/*
	 * @FindBy(xpath = "//div[@class='icl-u-lg-mr--sm icl-u-xs-mr--xs']/a")
	 * WebElement jobUrl;
	 * 
	 * @FindBy(xpath = "//div[@id='salaryInfoAndJobType']") WebElement
	 * jobTypedetails; //@FindBy(xpath =
	 * "//div[@id='jobDescriptionText']")WebElement jobDescription;
	 * 
	 * @FindBy(xpath =
	 * "//div[@id='hiringInsightsSectionRoot']//p/span[@class='jobsearch-HiringInsights-entry--text']")
	 * WebElement jobPostedtime;
	 */

	// ACTIONS--------------------------------------------------------------------
	
	public void whatsearchfunction() {
		whatsearchbox.sendKeys("");
		return;
	}

	public void wheresearchfunction() {
		wheresearchbox.sendKeys("");
		return;
	}

	public void findjobsfunction() {
		findjobsbtn.click();
	}

	public void datepostedbtnfunction() {
		datepostedbtn.click();
	}

	public void previouspagebtnfunction() {
		previouspagebtn.click();
	}

	public void paginationfunction() {
		pagination.size();
		return;
	}

	public void nextpagebtnfunction() {
		nextpagebtn.click();
	}

	public void popupfuction() {
		popupbutton.click();
	}

	public void iframeswitch() {
		driver.switchTo().frame(iframe);
	}

// methods
// --------------------------------------------------------------------------------------------
/**
 * TO  Use "Remote" as search criteria 
 * @throws InterruptedException
 */
	public void WhereBox() throws InterruptedException {
		wheresearchbox.sendKeys(Keys.CONTROL + "a");
		wheresearchbox.sendKeys(Keys.DELETE);
		wheresearchbox.sendKeys(Testutils.wheresearch);
	}

	// search criteria// whattextbox
	/**
	 * Methods to search with  different keywords for job type
	 * @throws InterruptedException
	 */

	//Api Testing job searchfunction
	public void apisearchfunction() throws InterruptedException {
		whatsearchbox.sendKeys(Keys.CONTROL + "a");
		whatsearchbox.sendKeys(Keys.DELETE);
		whatsearchbox.sendKeys(Testutils.apitesting);
		WhereBox();
		findjobsbtn.click();
	}
// restassured job search function
	
	public void restassuredsearchfunction() throws InterruptedException {
		whatsearchbox.sendKeys(Keys.CONTROL + "a");
		whatsearchbox.sendKeys(Keys.DELETE);
		whatsearchbox.sendKeys(Testutils.restassuredtesting);
		WhereBox();
		findjobsbtn.click();
	}
// Postman search function
	public void postmansearchfunction() throws InterruptedException {
		whatsearchbox.sendKeys(Keys.CONTROL + "a");
		whatsearchbox.sendKeys(Keys.DELETE);
		whatsearchbox.sendKeys(Testutils.postmantesting);
		WhereBox();
		findjobsbtn.click();
	}

//----------------------------------------------------------------------------------------------------	
	
// popuphandling
	public void popuphandling() {
		try {
			if (popupwindow.isDisplayed() == true) {
				popupcancelbtn.click();
			}
		} catch (Exception ne) {
		}
	}
//-----------------------------------------------------------------------------------------------------------	
	// date posted selection dropdown
	/**
	 * Select from dropdown for jobs posted time
	 * @throws InterruptedException
	 */
	public void Datepostedselection() throws InterruptedException {
		datepostedbtn.click();
		Actions Act = new Actions(driver);
		Act.moveToElement(datepostedoption1).click().build().perform();
		// Act.moveToElement(datepostedbtn).moveToElement(datepostedoption2).click().build().perform();
		// Act.moveToElement(datepostedbtn).moveToElement(datepostedoption3).click().build().perform();
		// Act.moveToElement(datepostedbtn).moveToElement(datepostedoption4).click().build().perform();
		// Act.moveToElement(datepostedbtn).moveToElement(datepostedoption5).click().build().perform();
		// Thread.sleep(3000);
		popuphandling();
	}

// ------------------------------------------------------------------------------------------------------------------

	// pagination
	/**
	 * loop thru pages while next-page button is clicked and return the total pages of data available for this search
	 * @return noofPages
	 */
	
	public int pagination() {
		int noofPages = 1;
		int pagecounter = 0;

		try {
			while (nextpagebtn.isDisplayed() == true) {
				nextpagebtn.click();
				pagecounter++;
				noofPages++;
			}
		} catch (Exception e) {
			System.out.println(" End of page counter  " + pagecounter);
			//System.out.println("total pages   " + noofPages);
		}
		System.out.println("total pages   " + noofPages);
		return noofPages;
	}

	// go to page1
	/**
	 * after pagination, driver is on last page..... it has to start from page 1
	 * @throws InterruptedException
	 */
	public void startonfirstpage() throws InterruptedException {

		try {
			
			while (previouspagebtn.isDisplayed() == true) {
				previouspagebtn.click();
			}
		} catch (Exception e) {
	        WebDriverWait wait = new WebDriverWait(driver, 15);
	        wait.until(ExpectedConditions.elementToBeClickable(pageonebtn)).click();
			System.out.println(" Starting from page 1");// go to page one
			//Thread.sleep(1000);
		}
	}

// -----------------------------------------------------------------------------------------------------------------------------

//currentdate to record webscrapping time
	
/**
 * 
 * @return time of scrapping from system
 */
	
	public String currentdate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
	//	System.out.println(dtf.format(now));
		return dtf.format(now);
	}

	
	
	
	//to get the number of jobcards in each page
	/**
	 * 
	 * @return noofcardsperpage
	 * @throws InterruptedException
	 */
	public int JobCards() throws InterruptedException {
		int totcount = jobcards.size();
		System.out.println( "Job cards in this page " +totcount);
		return totcount;
	}

	public String JobDescription() {
		return jobDescription.getText();
	}

	public String JobPostedTime() {
		return postedtime.getText();
	}

//-------------------------------------------------------------------------------------------------------		
//data to csv files ---> added csv dependency in POM.xml

	public void datacollectiontocsv(String SheetName, String keyword) throws IOException, InterruptedException {
		System.out.println("=== Entering datacollectiontocsv() ===");
		FileWriter outputfile = new FileWriter(new File("indeedJobs" + keyword + ".csv"));
		CSVWriter writer = new CSVWriter(outputfile);

		// adding header to csv file
		
		String[] header = { "Job Category", " Company name", "Job Title", "Job Location", "Job URL", "Job posted Time",
				"Job Scraped Time", "Job Description" };
		writer.writeNext(header);
		
		// pagination
		int totalpages = pagination();
		System.out.println("Total searchpages =  :" + totalpages);

		
		// go to  firstpage--- if condition-->when noof pages is 1,avoids error throws
			
		if (totalpages > 1) {
			startonfirstpage();
		}
		
		
		int rownum = 1;
		for (int i = 1; i <= totalpages; i++) {
			System.out.println("----- Page # " + (i) + "---------");

			// data from jobcards
			int jobsperpage = JobCards();
			String JobCategory = jobCategory.getText();
			
			// looping thru all jobcards
			try {
				for (int j = 0; j < jobsperpage; j++) {
					Thread.sleep(1000);
					// jobcards.get(j);
					String JobTitle = jobTitle.get(j).getText();
					// System.out.println( JobTitle);
					String CompanyName = companyName.get(j).getText();
					String JobLocation = jobLocation.get(j).getText();
					String JobURL = jobLink.get(j).getAttribute("href").toString();
					//String JobPostedDate = jobPostedDate.get(j).getAttribute("text");
					String timeofdatacollection = currentdate();

					
					 jobcards.get(j).click(); 
					 iframeswitch();
					 // Thread.sleep(2000); 
					 String Jobdesc=JobDescription();
					 String jobPostedate = JobPostedTime();
					  //dataRows.add(JobDescription());
					 //System.out.println("iframe data not completed"); 
				

					String[] dataRows = { JobCategory, JobTitle, CompanyName, JobLocation, JobURL, jobPostedate,
							timeofdatacollection, Jobdesc };
					writer.writeNext(dataRows);
					driver.switchTo().defaultContent();
					//Thread.sleep(1000);
					
				} // end of for loop for jobcards
			} catch (Exception n) {
			}
		
		// writer.flush();
		
		try {
			if (nextpagebtn.isDisplayed() == true)		{
				nextpagebtn.click();
				Thread.sleep(1000);
				System.out.println("go to nextpage");
			}
		} catch (Exception e) {
		}
	}
		writer.close();
		Thread.sleep(1000);
		System.out.println("Enter all data to excel from CSV");

		System.out.println("Job Search completed");
		System.out.println("=== Leaving datacollectiontocsv() ===");
	}

//=====================================================================================================================================================================
///>>>>>>>>>>>>>>. data to excel method 1>>>>>>>>>>>>>>>>>>>>>>

	public void jobsdatatoexcel(String SheetName,String keyword) throws IOException, InterruptedException {

		ArrayList<Object[]> jobdata = new ArrayList<Object[]>();

//pagination
		int totalpages = pagination();
		System.out.println("Total searchpages =  :" + totalpages);

//go to page
		if (totalpages > 1) {
			startonfirstpage();
		}

		
// loop thru pagination pages
				
		int rownum = 1;
		
		for (int i = 1; i <= totalpages; i++) {
			System.out.println("----- Page # " + (i) + "---------");// print page number

			// data from jobcards
			int jobsperpage = JobCards();
			String JobCategory = jobCategory.getText();
		// looping thru all jobcards 
			try {
				for (int j = 0; j < jobsperpage; j++) {
					//Thread.sleep(1000);
					// jobcards.get(j);
					
					String JobTitle = jobTitle.get(j).getText();
					// System.out.println( JobTitle);

					String CompanyName = companyName.get(j).getText();
					String JobLocation = jobLocation.get(j).getText();
					String JobURL = jobLink.get(j).getAttribute("href").toString();
					String JobPostedDate = jobPostedDate.get(j).getText();
					String timeofdatacollection = currentdate();

					try {
						jobcards.get(j).click();
						iframeswitch();
					//	Thread.sleep(1000);
					} catch (Exception f) { 
						 System.out.println("iframe data not completed");
					}
					String Jobdesc = JobDescription();
					String jobPostedat = JobPostedTime();
					
					jobdata.add(new Object[] { JobCategory, JobTitle, CompanyName, JobLocation, JobURL, JobPostedDate,	timeofdatacollection, Jobdesc });
					Thread.sleep(1000);
					driver.switchTo().defaultContent();

				} // end of for loop for jobcards }
				
				} catch (Exception n) {
				System.out.println(n);
			}
			
			try {
				if (nextpagebtn.isDisplayed() == true)			{
				nextpagebtn.click();
				Thread.sleep(1000);
				System.out.println("go to nextpage");
			}
		} catch (Exception e) {
			System.out.println("End of pagination loop");
		}
		}// end of pagination for loop
		
		try {
			System.out.println("---------Save to Excel-----------------");
			// System.out.println(" jobdata" + jobdata.get(0));
			saveDataToExcel(jobdata, keyword);

		} catch (IOException e) {
			System.out.println("Having issue while exporting data to excel " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("Job Search completed");
			}
	// end of method
	
					private void saveDataToExcel(ArrayList<Object[]> jobdatabase, String keyword) throws IOException {
						XSSFWorkbook workbook = new XSSFWorkbook();
						String[] columnheadings = { "Job Category", " Company name", "Job Title", "Job Location", "Job URL",
								"Job posted Time", "Job Scraped Time", "Job Description" };
						XSSFSheet sheet = workbook.createSheet(keyword);
				
						System.out.println("---------Start to export into Excel-----------------");
						CellStyle style = workbook.createCellStyle();
						style.setFillBackgroundColor(IndexedColors.BLUE.getIndex());
				
						Row headerRow = sheet.createRow(0);
				
						for (int i = 0; i < columnheadings.length; i++) {
				
							Cell cell = headerRow.createCell(i);
							cell.setCellValue((columnheadings[i]));
							cell.setCellStyle(style);
						}
				
						int rownum = 1;
						for (Object[] jlist : jobdatabase) {
				
							XSSFRow row = sheet.createRow(rownum++);
							int cellnum = 0;
							for (Object value : jlist) {
				
								XSSFCell cell = row.createCell(cellnum++);
								cell.setCellValue((String) value);
				
							}
						}
						File availableFile = new File("JobDetails.xlsx");
						boolean exists = availableFile.exists();
				
						if (exists) { // Checking file already exist,if yes then delete first and create again
							availableFile.delete();
						}
						FileOutputStream fos = new FileOutputStream(availableFile);
						workbook.write(fos);
						fos.close();
						workbook.close();
				
					}
	//==================================================================================================================================================================
	/*
	 * /// >>>>>>>>>>>>>>. data to excel method 2>>>>>>>>>>>>>>>>>>>>>>
	 * 
	 * public void jobsdatascrapping(String SheetName, String keyword) throws
	 * IOException, InterruptedException {
	 * 
	 * XLutils exceldata = new XLutils(Testutils.excelFilepath);
	 * 
	 * ArrayList<String> dataRows = new ArrayList<String>();
	 * dataRows.add("Job Category"); dataRows.add("Job Company name");
	 * dataRows.add("Job Title"); dataRows.add("Job Location");
	 * dataRows.add("Job URL"); dataRows.add("Job posted Time");
	 * dataRows.add("Job Scraped Time"); dataRows.add("Job Description");
	 * 
	 * exceldata.writeToExcel(dataRows, SheetName, 0);
	 * 
	 * // pagination int totalpages = pagination();
	 * System.out.println("Total searchpages =  :" + totalpages);
	 * 
	 * // go to page if (totalpages > 1) { startonfirstpage(); }
	 * 
	 * int rownum = 1; for (int i = 1; i <= totalpages; i++) {
	 * System.out.println("----- Page # " + (i) + "---------");
	 * 
	 * // data from jobcards int jobsperpage = JobCards();
	 * 
	 * // looping thru all jobcards try { for (int j = 0; j < jobsperpage; j++) { //
	 * .sleep(2000); // jobcards.get(j); String JobCategory = jobCategory.getText();
	 * String JobTitle = jobTitle.get(j).getText(); // System.out.println(
	 * JobTitle);
	 * 
	 * String CompanyName = companyName.get(j).getText(); String JobLocation =
	 * jobLocation.get(j).getText(); String JobURL =
	 * jobLink.get(j).getAttribute("href").toString(); String JobPostedDate =
	 * jobPostedDate.get(j).getAttribute("text"); String timeofdatacollection =
	 * currentdate();
	 * 
	 * dataRows.add(JobCategory); dataRows.add(CompanyName); dataRows.add(JobTitle);
	 * dataRows.add(JobLocation); dataRows.add(JobURL); dataRows.add(JobPostedDate);
	 * dataRows.add(timeofdatacollection);
	 * 
	 * try { jobcards.get(j).click(); iframeswitch(); Thread.sleep(2000); String
	 * Jobdesc = JobDescription(); String jobPostedat = JobPostedTime(); //
	 * dataRows.add(JobDescription()); // } catch (Exception f) { //
	 * System.out.println("iframe data not completed"); }
	 * 
	 * // String[] dataRows = { JobCategory, JobTitle, CompanyName,
	 * JobLocation,JobURL, // JobPostedDate,timeofdatacollection,"Jobdesc "}; //
	 * exceldata.writeToExcel(dataRows, SheetName, rownum); // rownum++;
	 * Thread.sleep(1000); driver.switchTo().defaultContent(); Thread.sleep(1000);
	 * 
	 * } // end of for loop for jobcards } } catch (Exception n) {
	 * System.out.println("Data collected from jobcards"); } }
	 * 
	 * // writer.flush(); try { if (nextpagebtn.isDisplayed() == true) {
	 * nextpagebtn.click(); Thread.sleep(1000);
	 * System.out.println("go to nextpage"); } } catch (Exception e) {
	 * 
	 * }
	 * 
	 * System.out.println("Job Search completed"); }
	 */

}
