package automation.core.testng;




import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import automation.core.utils.data.ProjectConfig;
import io.github.bonigarcia.wdm.WebDriverManager;

//@Listeners({DriverListener.class})
public class BaseTest {
	public static String reportFolder;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports  report;
	public static ExtentTest test;
	protected static WebDriver driver = null;
	public  static String testID;
	public  static String sheetName;


	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		String datetimestamp =new SimpleDateFormat("yyyyMMddHHmmss" ).format(new Date());
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/Test-Output"+datetimestamp+".html");
		report = new ExtentReports();
		report.attachReporter(htmlReporter);


	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {

	}

	@BeforeTest(alwaysRun = true)
	@Parameters({ "testCaseID", "dataSheet" })
	public void launchBrowser(String testCaseID, String dataSheet) throws Exception {

		testID=testCaseID;
		sheetName=dataSheet;
		test = report.createTest("TestCase ID : "+testID);
		if(!testCaseID.equalsIgnoreCase("TC002"))
		{
			driverManager();
			driver.navigate().to(ProjectConfig.getPropertyValue("SauceDemo_URL"));
		}

	}

	@AfterTest(alwaysRun = true)
	public void closeBrowser() {

		if(!testID.equalsIgnoreCase("TC002"))
		{
			driver.quit();
		}
		report.flush();
	}


	public void driverManager()
	{
		if(ProjectConfig.getPropertyValue("BrowserType").equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup(); // Can be used to manage browser and Driver versions
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		if(ProjectConfig.getPropertyValue("BrowserType").equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup(); // Can be used to manage browser and Driver versions
			driver = new  FirefoxDriver();
			driver.manage().window().maximize();
		}
		if(ProjectConfig.getPropertyValue("BrowserType").equalsIgnoreCase("ie"))
		{
			WebDriverManager.firefoxdriver().setup(); // Can be used to manage browser and Driver versions
			driver = new  InternetExplorerDriver();
			driver.manage().window().maximize();
		}
		
	}


}
