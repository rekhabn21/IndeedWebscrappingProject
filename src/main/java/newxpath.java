import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class newxpath {
	static WebDriver d;
	public static void main(String[] args) throws InterruptedException {
	System.setProperty("webdriver.chrome.driver",
			"C:\\Users\\rekha\\selenium-ws\\DS-Algodemo\\src\\test\\drivers\\chromedriver.exe");
	d = new ChromeDriver();
	d.get("https://www.indeed.com/jobs");
	d.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	d.manage().window().maximize();

	WebElement submitBtn = d.findElement(By.className("yosegi-InlineWhatWhere-primaryButton"));
	System.out.println("Submit Button : " + submitBtn.getText());
	submitBtn.submit();

	WebElement whatsearchbox = d.findElement(By.xpath("//input[@id='text-input-what']"));
	WebElement wheresearchbox = d.findElement(By.xpath("//input[@id='text-input-where']"));
	WebElement findjobsbtn = d.findElement(By.className("yosegi-InlineWhatWhere-primaryButton"));

	// List<WebElement> pagination = d
	// .findElements(By.xpath("//tbody//nav//div[@class='pagination']//ul[@class='pagination-list']"));

	whatsearchbox.clear();
	whatsearchbox.sendKeys("API");

	Actions Act = new Actions(d);
	Act.click(wheresearchbox).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE)
			.sendKeys("remote").build().perform();

	findjobsbtn.submit();
	System.out.println("go to popup window");

	d.switchTo().activeElement().sendKeys(Keys.ESCAPE);
	System.out.println("popup closed");
	d.switchTo().defaultContent();
	System.out.println(d.getTitle());

	WebElement dateposted = d.findElement(By.cssSelector("button[aria-controls='filter-dateposted-menu']"));
	d.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	dateposted.click();

	// List<WebElement> datepostedsubmenu =// d.findElements(By.xpath("//ul[id='filter-dateposted-menu']/li/a"));
	List<WebElement> datepostedsubmenu = dateposted.findElements(By.xpath(
			"//tbody/tr/td/div[@id='MosaicProviderRichSearchDaemon']/div[@role='search']/div/div/div[2]/ul/li"));
	System.out.println("Sub menu empty ? " + datepostedsubmenu.isEmpty());
	for (WebElement valueofdateposted : datepostedsubmenu) {
		String name = valueofdateposted.getText();
		System.out.println(name);
	}
	WebElement menuitem = d.findElement(By.xpath(".//a[contains(text(),'Last 24 hours')]"));
	System.out.println(menuitem.getText());
	Act.moveToElement(menuitem).click().build().perform();
/*	
	//List<WebElement> pagination = d.findElements(By.xpath("//ul[@class='pagination-list']/li/"));
	//int noofpages = pagination.size();
	//int pgCounter = 0;
	// System.out.println("The no. of pagnationbuttons " + noofpages);
	//for(int i=0; i < noofpages; i++) {
	// pgCounter++;
	//System.out.println("Page # : " + pagination.get(i).getText());
	//System.out.println("Page # : " + pagination.get(i).getText());
	//WebElement prevBtn = d.findElement(By.xpath("//a[@aria-label='Previous']/span/*[name()='svg']"));
	// WebElement nextBtn = d.findElement(By.xpath("a[@aria-label='Next']/span/span/*[name()='svg']"));
	// System.out.println("Next button : " + nextBtn.isDisplayed());}*/
	
/*	List<WebElement> listofjobcardframes = d.findElements(By.xpath("//div[@id ='mosaic-provider-jobcards']/a"));
	int jobs = listofjobcardframes.size();
	System.out.println(" No. of job cards in this search : " + jobs);
	for (int j = 1; j < jobs; j++) {
		// get data from each jobcard
		try {
			// WebDriverWait wait = new WebDriverWait(d, 10);
			// switch to iframe for job data
			listofjobcardframes.get(1);
			Thread.sleep(1000);
			System.out.println("String jt=	" +d.findElement(By.xpath ("//h2[@class='jobTitle jobTitle-newJob']/span")).getAttribute("title"));
			System.out.println("String jc ="	+d.findElement(By.xpath ("//span[@class='companyName']/a[@data-tn-element='companyName']")).getAttribute("text"));
			System.out.println("String jurl = "	+d.findElement(By.xpath ("//span[@class='companyName']/a[@data-tn-element='companyName']")).getAttribute("href"));
			System.out.println("String jloc = "	+d.findElement(By.xpath ("//div[@class='companyLocation']")).getText());
			}catch (StaleElementReferenceException e) {
			}
		}	// int pn =pagination.size();*/
	 
	WebElement nextBtn = d.findElement(By.xpath("//a[@aria-label='Next']//span//span//*[name()='svg']"));
	int pagecounter =0;
	int pageno =01;
	

		
	System.out.println(pagecounter);
	System.out.println(pageno);
	
	while(nextBtn.isDisplayed() == true) {
			
		if(nextBtn.isDisplayed() == true) {
			try {
				WebDriverWait nw = new WebDriverWait(d, 45);
				nw.until(ExpectedConditions.elementToBeClickable(nextBtn));
				Actions Act1= new Actions(d);
				Act1.moveToElement(nextBtn).click().build().perform();
			
				// nextBtn.click();
				 pagecounter++;
				 pageno++;
				}	
				catch(StaleElementReferenceException s) {		
						System.out.println("click on next button again");
				}
		}
		}			
	
			 /*else if (nextBtn.isDisplayed()== false) {
			 System.out.println(pagecounter);
			 System.out.println("End of pagination counter");
			 System.out.println( "No. Of pages    " +pageno);
				 }*/
	}
	}
				 