package com.qa.jobscrapperutils;

import org.openqa.selenium.support.ui.WebDriverWait;

public class Testutils {

	public static  int Page_Load_TIMEOUT =20;
	public static  int  Implicity_wait=20;

	public static String chromepath= "C:\\Users\\rekha\\selenium-ws\\Indeedjobscrappingproject\\src\\test\\resources\\drivers\\chromedriver.exe";
	public static String firefoxpath= "C:\\Users\\rekha\\selenium-ws\\Indeedjobscrappingproject\\src\\test\\resources\\drivers\\geckodriver.exe";
	
	public static String Base_URL = "https://www.indeed.com/jobs";
	
	//excel file location and sheetname
	public static String excelFilepath=	"C:\\Users\\rekha\\selenium-ws\\Indeedjobscrappingproject\\test-output\\testdatacollected\\indeedjobs.xlsx";

	public static final String apitesting ="API testing";
	public static final String restassuredtesting ="Rest Assured";
	public static final String postmantesting ="Postman";
	public static final String wheresearch ="Remote";
	
	public static String sheetname1= "Api Jobs";
	public static String sheetname2 ="Restassured Jobs";
	public static String  sheetname3 = "Postman Jobs";
	
	
	
}




