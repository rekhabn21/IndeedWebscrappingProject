package pom.jobscrapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.net.UrlChecker.TimeoutException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.opencsv.CSVWriter;

import utils.Jobscrapper.TestBaseJobScrapper;
import utils.Jobscrapper.Testutils;
import utils.Jobscrapper.XLutils;

public class Indeedhomepage extends TestBaseJobScrapper {

	public Indeedhomepage() throws IOException {
		super();
		try {
			PageFactory.initElements(driver, this);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	XLutils excelutils;

	@FindBy(xpath = "//input[@id='text-input-what']")
	@CacheLookup
	WebElement whatsearchbox;

	@FindBy(xpath = "//input[@id='text-input-where']")
	@CacheLookup
	WebElement wheresearchbox;

	@FindBy(css = "button[type='submit']")
	@CacheLookup
	WebElement findjobsbtn;

	// popupwindow
	@FindBy(xpath = "//*[@id='popover-x']/button")
	@CacheLookup
	WebElement popupbutton;

	@FindBy(css = "button[aria-controls='filter-dateposted-menu']")
	@CacheLookup
	WebElement datepostedbtn;

	@FindBy(xpath = "//ul[@id='filter-dateposted-menu']/li")
	@CacheLookup
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

	@FindBy(xpath = "//h1[@id='jobsInLocation']")
	WebElement jobCategory;

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

	// jobcard elements
	// @FindBy(xpath = "" ) WebElement ;

	// @FindBy(xpath = "//div[@id='mosaic-provider-jobcards']//h2")
	@FindBy(id = "mosaic-provider-jobcards")
	// List<WebElement> jobCard;
	WebElement jobCard;
	// @FindBy(xpath = ".//h2/span")

	@FindBy(xpath = "//h2[@class='jobTitle jobTitle-newJob']/span")
	List<WebElement> jobTitlefromjobcard;

	@FindBy(xpath = "//span[@class='companyName']")
	List<WebElement> jobCompanyName;

	@FindBy(xpath = "//div[@class='companyLocation']")
	List<WebElement> companyLocation;
	// @FindBy(xpath=
	// "//div[@id='mosaic-provider-jobcards']//a")List<WebElement>companyUrl;

	@FindBy(xpath = "//span[@class='companyName']/ancestor::a")
	List<WebElement> companyUrl;

	// @FindBy(xpath = "//div[@class='job-snippet']//ul")
	// List<WebElement> jobDescription;
	@FindBy(xpath = "//div[@class='heading6 tapItem-gutter metadataContainer noJEMChips salaryOnly']")
	List<WebElement> jobTypedetails;
	@FindBy(xpath = "//span[@class='date']")
	List<WebElement> jobPostedtime;

	// **************************************elements from
	// iframe***********************

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

//=============================================================================================================	//
	// Actions

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

//===================================================================================================================================================
	// search criteria
	public void jobsearchfunction(String jobcategory, String joblocation) throws InterruptedException {
		// whattextbox
		Actions Act = new Actions(driver);
		Act.click(whatsearchbox).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE)
				.sendKeys(jobcategory).build().perform();
		Act.click(wheresearchbox).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE)
				.sendKeys(joblocation).build().perform();

		findjobsbtn.click();
	}

//===================================================================================================================================================
// popuphandling();
	public void popuphandling() {
		try {
			if (driver.findElement(By.id("popover-foreground")).isDisplayed()) {
				driver.findElement(By.id("popover-x")).click();
			}
		} catch (Exception e) {
			System.out.println("\nNo pop up");
		}
		/*
		 * Actions action = new Actions(driver); action.sendKeys(Keys.ESCAPE).perform();
		 * /* if (popupbutton != null) { System.out.println("\n Go to Popupwindow");
		 * driver.switchTo().activeElement(); popupbutton.click(); //
		 * .sendKeys(Keys.ESCAPE); System.out.println("popup closed");
		 * driver.switchTo().defaultContent(); }
		 */
	}

//===================================================================================================================================================
// date posted selection dropdown
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
		// Thread.sleep(3000);
		// System.out.println(datepostedoption1);
	}

//===================================================================================================================================================
//job search category
	public void Jobcategory() {
		whatsearchbox.getText();
	}

//===================================================================================================================================================
//pagination
	public int pagination() {
		int noofPages = 1;
		int pagecounter = 0;

		try {
			while (nextpagebtn.isDisplayed() == true) {

				nextpagebtn.click();
				pagecounter++;
				noofPages++;
				// System.out.println(pagecounter);
			}
		} catch (Exception e) {
			System.out.println(" End of page counter  " + pagecounter);
			System.out.println("total pages   " + noofPages);
		}
		return noofPages;
	}

//===================================================================================================================================================
//currentdate to record webscrapping time
	public String currentdate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		return dtf.format(now);
	}

//***********************************************************************************************************************************

	public void jobdatacollection() throws IOException {

		FileWriter outputfile = new FileWriter(new File("indeedJobs.csv"));
		CSVWriter writer = new CSVWriter(outputfile);

		// adding header to csv
		String[] header = { "Company Name", "Job Title", "Job Location", "Job Category", "Job Posting Date",
				"Job Type Info", "Company URL", };
		writer.writeNext(header);

		int pages = 1;
		int noOfPages = pagination();
		// System.out.println("total search pages = " + pages);
		/*
		 * try { while (previouspagebtn.isDisplayed() == true) {
		 * previouspagebtn.click(); } } catch(Exception e) { } } // go to page one
		 * WebDriverWait wait = new WebDriverWait(driver, 60);
		 * wait.until(ExpectedConditions.visibilityOf(pageonebtn)).click();
		 * Thread.sleep(2000); // looping thru pagination pages for (int p = 1; p <
		 * Pages; p++) {
		 */
		try {

			for (int j = 1; j <= noOfPages; j++) {

				List<WebElement> job_cards = driver.findElements(By.xpath("//div[@class='job_seen_beacon']"));
				int totcount = job_cards.size();
				// List<WebElement> jobCardWithh2 = jobCard.findElements(By.tagName("h2"));
				// int totcount = jobCardWithh2.size();
				// while(nextpagebtn.isDisplayed() == true){
				// System.out.println(nextpage.size());
				// for(int j=0; j<noOfPages; j++) {
				// while(nextpage.size() > 0) {
				String dateofscrapping = currentdate();
				String JobCategory = jobCategory.getText();
				// job cards in a page
				// int noofjobcards = jobCard.size();
				System.out.println(" no.of jobcards in this page is  : " + totcount);
				try {
					// collect data from a jobcard
					for (int i = 0; i < totcount - 1; i++) {
						String JobTitle = jobTitlefromjobcard.get(i).getText();
						String CompanyName = jobCompanyName.get(i).getText();
						String JobLocation = companyLocation.get(i).getText();
						String CompanyURL = companyUrl.get(i).getAttribute("href").toString();
						String Jobpostingdate = jobPostedtime.get(i).getText();
						// String JobDescription = jobDescription.get(i).getText();
						// String JobTypeinfo = jobTypedetails.get(i).getText();

						String[] data = { CompanyName, JobTitle, JobLocation, JobCategory, Jobpostingdate, "",
								CompanyURL };
						writer.writeNext(data);

					}
				} catch (Exception ie) {
					System.out.println(ie);
				}

				WebElement pagei = driver.findElement(By.xpath("(//ul[@class='pagination-list']/li)[" + j + "]"));
				pagei.click();
				pages++;
				System.out.println("Go to next page");
				System.out.println("on page" + pages);
			}
			// } // end of for pages

		} catch (Exception e) {
		}
		// end of for loop for pagination

		try {
			System.out.println("---------Save to Excel-----------------");
			// saveDataToExcel(jobdatarows);
			writer.close();

		} catch (Exception e) {
			System.out.println("Having issue while exporting data to excel " + e.getMessage());
			e.printStackTrace();
		}
	}

	// end of method

///===================================================================================================================================================

	public void jobscrapping(String sheetName) throws IOException, Throwable {

		XLutils excelutils = new XLutils(Testutils.excelFilepath);
		int Row = 0;
		ArrayList<String> datarows = new ArrayList<String>();
		datarows.add("Company Name");
		datarows.add("Job Title");
		datarows.add("Job Location");
		datarows.add("Job Category");
		datarows.add("Company URL");
		datarows.add("Job Type Info");
		//datarows.add("Job Description");
		datarows.add("Job Posting Date");

		int totalPages = pagination();
		Thread.sleep(2000);
		// go to page1
		try {
			while (previouspagebtn.isDisplayed() == true) {
				previouspagebtn.click();
			}
		} catch (Exception e) {
			pageonebtn.click();
			System.out.println("from page 1");// go to page one
			Thread.sleep(2000);
		}

		// looping thru pagination pages
		for (int p = 1; p <= totalPages; p++) {
			String dateofscrapping = currentdate();

			List<WebElement> jobCard = driver.findElements(By.xpath("//div[@class='job_seen_beacon']"));
			int totcount = jobCard.size();
			System.out.println(" no.of jobcards in this page is  : " + totcount);
			String JobCategory = jobCategory.getText();

			// loop thru all jobCards in a page
			for (int i = 1; i < totcount; i++) {

				String JobTitle = jobTitlefromjobcard.get(i).getText();
				System.out.println(JobTitle);
				String CompanyName = jobCompanyName.get(i).getText();
				String JobLocation = companyLocation.get(i).getText();
				String CompanyURL = companyUrl.get(i).getAttribute("href").toString();

				// add to datarows aaary
				datarows.add(CompanyName);
				datarows.add(JobTitle);
				datarows.add(JobLocation);
				datarows.add(JobCategory);
				datarows.add(CompanyURL);
				try {
					String JobTypeInfo = jobTypedetails.get(i).getText();
					datarows.add(JobTypeInfo);
				} catch (ElementNotFoundException ee) {

				}
				String Jobpostingdate = jobPostedtime.get(i).getAttribute("text");
				// switch to iframe
				/*
				 * jobCard.get(i).click();
				 * 
				 * WebDriverWait wait = new WebDriverWait(driver, 10);
				 * wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
				 * "vjs-container-iframe")); System.out.println(" in the iframe");
				 */
				/*
				 * try { String JobDesc =jobDescription.getText(); datarows.add(JobDesc);
				 * Thread.sleep(2000); } catch(Exception e) { } }
				 * 
				 * try { String Jobpostingdate = postedtime.getText();
				 * datarows.add(Jobpostingdate); System.out.println(Jobpostingdate); }catch(
				 * Exception e) { }
				 */

				excelutils.writeToExcel(datarows, sheetName, Row);
				Row++;
				Thread.sleep(3000);

				driver.switchTo().defaultContent();
				Thread.sleep(2000);
			} // end of for loop for job cards

			try {
				nextpagebtn.click();
				Thread.sleep(5000);
			} catch (Exception e) {

			}
			System.out.println("WebScrapping successfully completed");

		}
	}

}
