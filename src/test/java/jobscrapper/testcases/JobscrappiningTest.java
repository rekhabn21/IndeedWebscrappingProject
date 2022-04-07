package jobscrapper.testcases;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVWriter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JobscrappiningTest {

	public static WebDriver driver;

	@Test(dataProvider = "jobsearchcriteria")

	public void searchcriteria(String keyWord, String where) throws InterruptedException, IOException {

		// ArrayList<Object[]> jobdatarows = new ArrayList<Object[]>();
		FileWriter outputfile = new FileWriter(new File("indeedJobs" + keyWord + ".csv"));
		CSVWriter writer = new CSVWriter(outputfile);
		// adding header to csv
		String[] header = { "Company Name", "Job Title", "Job Location", "Job Category", "Posted date",
				"TimeofScrapping", "Job URL" };
		writer.writeNext(header);

		String jobCategory = null, jobTitle = null, jobLocation = null, jobPostedDate = null, jobDescription = null,
				jobLink = null, companyName = null, jobType = null, timeofscrapping = null;

		WebElement whatsearchbox = driver.findElement(By.xpath("//input[@id='text-input-what']"));
		whatsearchbox.sendKeys(Keys.CONTROL + "a");
		whatsearchbox.sendKeys(Keys.DELETE);
		whatsearchbox.sendKeys(keyWord);

		/*
		 * String keyword = new String();
		 * 
		 * switch(keyword) { case "API testing": whatsearchbox.sendKeys("API testing");
		 * break; case "Rest Assured": whatsearchbox.sendKeys("Rest Assured"); break;
		 * case "Postman": whatsearchbox.sendKeys("Postman") ; break; }
		 */
		WebElement wheresearchbox = driver.findElement(By.xpath("//input[@id='text-input-where']"));
		wheresearchbox.sendKeys(Keys.CONTROL + "a");
		wheresearchbox.sendKeys(Keys.DELETE);
		wheresearchbox.sendKeys(where);

		WebElement findjobsbtn = driver.findElement(By.className("yosegi-InlineWhatWhere-primaryButton"));
		findjobsbtn.click();

		List<WebElement> pagination = driver.findElements(By.xpath("//ul[@class='pagination-list']/li"));
		int pgSize = pagination.size();

		driver.findElement(By.id("filter-dateposted")).click();
		// yosegi-FilterPill-dropdownListItemLink
		driver.findElement(By.linkText("Last 24 hours")).click();
		try {
			if (driver.findElement(By.id("popover-foreground")).isDisplayed()) {
				driver.findElement(By.id("popover-x")).click();
			}
		} catch (Exception e) {
			System.out.println("\nNo pop up");
		}
		System.out.println("**************" + keyWord + "*******************" + where + "**********");

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		timeofscrapping = (dtf.format(now));

		int pageno = 1;
		int totalpages = 1;

		try {
			for (int j = 0; j < pgSize; j++) {
				System.out.println("----- Page # " + (j + 1) + "---------");

				List<WebElement> jobcards = driver.findElements(By.xpath("//div[@class='job_seen_beacon']"));
				int jobsperpage = jobcards.size();
				System.out.println("Total jobs per page : " + jobsperpage);

				for (int i = 0; i < jobsperpage; i++) {
					Thread.sleep(1000);

					jobCategory = driver.findElement(By.xpath("//h1[@id='jobsInLocation']")).getText();
					jobTitle = driver.findElements(By.xpath("//h2[@class='jobTitle jobTitle-newJob']/span")).get(i)
							.getText();
					// System.out.println(jobTitle);

					companyName = driver.findElements(By.xpath("//span[@class='companyName']")).get(i).getText();
					jobLocation = driver.findElements(By.xpath("//div[@class='companyLocation']")).get(i).getText();
					jobLink = driver.findElements(By.xpath("//span[@class='companyName']/ancestor::a")).get(i)
							.getAttribute("href").toString();
					jobPostedDate = driver.findElements(By.xpath("//span[@class='date']")).get(i).getText();

					String[] data = { companyName, jobTitle, jobCategory, jobLocation };// ,jobPostedDate,timeofscrapping,jobLink
																						// };

					// switch to iframe
					/*
					 * jobcards.get(i).click(); Thread.sleep(2000); WebDriverWait wait = new
					 * WebDriverWait(driver, 10);
					 * wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
					 * "vjs-container-iframe")); System.out.println(" in the iframe");
					 */
					/*
					 * try { jobDescription=
					 * driver.findElement(By.cssSelector("#jobDescriptionText")).getText();
					 * Thread.sleep(1000); } catch(Exception e) { }
					 * 
					 * try { jobPostedDate=driver.findElement(By.
					 * cssSelector("p[class='jobsearch-HiringInsights-entry--bullet'] " +
					 * "span[class='jobsearch-HiringInsights-entry--text']")).getText();
					 * }catch(Exception e) { }
					 */
					// jobdatarows.add(new Object[]
					// {jobCategory,jobTitle,jobLocation,jobPostedDate,jobDescription,jobLink,companyName,
					// jobType,timeofscrapping});
					// Thread.sleep(2000);
					// driver.switchTo().defaultContent();

				}

				try {

					WebElement nextpagebtn = driver.findElement(By.xpath("//a[@aria-label='Next']"));
					// WebDriverWait waiting = new WebDriverWait(driver,10);
					// waiting.until(ExpectedConditions.elementToBeClickable(nextpagebtn));
					if (nextpagebtn.isDisplayed() == true) {
						nextpagebtn.click();
						System.out.println("next page");
						// System.out.println(totalpages++);
						// Thread.sleep(1000);
					} else {
						System.out.println("no more pages");
					}
				}

				catch (Exception es) {
					System.out.println("end of pagination loop");
				}

			}
			// end of for pgSize
			writer.flush();
			writer.close();
		} finally {

			Thread.sleep(2000);
			System.out.println("webscrapping completed");
		}
	}

	@DataProvider(name = "jobsearchcriteria")
	public Object[][] jobsearchcriteria() {

		/*
		 * Object[][] searchDataoption = { { "APITesting", "Remote" }, { "Rest Assured",
		 * "Remote" }, { "Postman", "Remote" } };
		 */

		Object[][] searchDataoption = { { "APITesting", "Remote" } };
		return searchDataoption;
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("Before Test begins====>>> Open new chrome Browser");
		try {
			 WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get("https://www.indeed.com/jobs");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	@AfterTest
	public void afterTest1() throws IOException {
		driver.close();
		driver.quit();
		System.out.println("After Test ends====>>> Close chrome Browser");
	}

}
