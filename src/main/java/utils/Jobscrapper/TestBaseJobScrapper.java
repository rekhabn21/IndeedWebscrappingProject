package utils.Jobscrapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBaseJobScrapper {

	public static WebDriver driver;
	public static Properties prop; 

	public TestBaseJobScrapper() throws IOException {
		try {
			prop = new Properties();
			FileInputStream FIS = new FileInputStream(
					"C:\\Users\\rekha\\selenium-ws\\Indeedjobscrappingproject\\src\\main\\java\\Jobscrapperdemo\\config.properties");
			prop.load(FIS);
		} catch (IOException e) {
			e.getCause();
		}
	}

	public static WebDriver Intialization() {

		WebDriverManager.chromedriver().setup();
			//// headless mode
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("headless");
//			 driver=new ChromeDriver(options);
			 driver=new ChromeDriver();
			 driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().pageLoadTimeout(Testutils.Page_Load_TIMEOUT, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(Testutils.Implicity_wait, TimeUnit.SECONDS);
				System.out.println("opens new chrome Browser");
				return driver;
		/*
		String browserName = prop.getProperty("browser");
		System.out.println("Browser : " + browserName);

		if (browserName.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver", Testutils.chromepath);
			driver = new ChromeDriver();
		} else if (browserName.equals("FF")) {

			System.setProperty("webdriver.chrome.driver", Testutils.firefoxpath);
			driver = new FirefoxDriver();
		}*/
		

		
	}

}
