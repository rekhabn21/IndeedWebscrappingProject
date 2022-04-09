# IndeedWebscrappingProject
Indeed.com Web-scrapping project
UI Automation : Selenium java 
Framework: PageObjectmodel, Testng
Cross browser Testing
Reports - Extent Reports, Allure reports ,Testng Reports

Project structure

SRC/JAVA/ Main  has packages
			Packages					                class files
**1.SRC/MAIN/JAVA	**
1.com.qa.config--------------------->	Config.java Readerfile, config.properties file
2.com.qa.Jobscrapperpageopbjects ---->	Searchpage - has all webelements , pagefactory used
3.com.qa.Jobscrapperutils ------->	TestUtils- with constants and file paths
	                           ----> 	TestbaseJobscrapper java- driver initialization 
		                         ---->  Xlutils -excelfunctions
4.Extent– Extent report listener package  	


**2.SRC/JAVA/TEST **–Jobscrapper.testcases package  with newtest testng testfile

3. **SRC/JAVA/TEST**/resources – Driver folder to store driver files

Project flow

1.TestbaseJobscrapper page- driver initialization  This class initiates the properties file
Webdriver Manager used to initiate driver in chromebrowser

2. searchpage – pageobjects page is a directory of all webelements found by inspecting Indeed.com website in a realtime work environment. 
•	It extends to TestBasejobscrapper from .com.qa.Jobscrapperutils package for driver Initialization
•	PageFactory is used to initiate these elements. 
•	Popuphandling, search criteria , dateposted options, are handled by defining methods in searchpage
•	Pagination method in searchpage is used to get the total number of pages in the search. Methods are also defined to start from the firstpage  and looping thru the pages  to scrape all the required data.
•	Datascrapped was saved to 
              1.csv file in datacollectiontocsv() method . the csv file can be opened using excel or notepad
              2. to excel file in jobsdatatoexcel() using excelfunction

3. Testngtest page – newtest – this class extends to has before test method to initialize driver 
•	Beforetest method used to initialize driver and launch “indeed.com” webpage
•	Different tests used defined to run searches with different searchcriteria
•	All tests are run by calling methods from searchpage
•	Enables parallel testing to save time
•	afterTestmethod used to teardown and close driver.
 
Reports generated

Test output generated in .csv and .xlsx  file format

Testngreports, Junit, jsonreports
Running test in testNg with extent listener generates Extentreports.
After test completion, allure results can be viewed by running the allure folder path on command prompt with command” allure serve - //allure results folder path”







