
package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //log4j2
import org.apache.logging.log4j.Logger; //log4j2
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
  
public class BaseClass {
	public static WebDriver driver;
	public Logger logger; // log4j2
	public Properties properties;

	@BeforeClass(alwaysRun =true,groups = {"Sanity", "Regression", "Master", "Datadriven"})
	@Parameters({ "os", "browser" })
	public void setUp(String os, String br) throws IOException {
		//Loading config.properties file
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		properties = new Properties();
		properties.load(file);
		logger = LogManager.getLogger(this.getClass()); // this.getClass() will dynamically getting the class name
		switch (br.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		/*case "firefox":
			driver = new FirefoxDriver();
			break;
			*/
		default:
			System.out.println("Invalid browser name");
			return;
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(properties.getProperty("appURL"));// reading URL from properties file
	}

	@AfterClass(alwaysRun = true, groups = {"Sanity", "Regression", "Master"})
	public void tearDown() {
		driver.quit();
	}
	
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public String randomAlphaNumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return (generatedString + "#" + generatedNumber);
	}
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//screenshots//" + testCaseName + "_" +timeStamp+ ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//screenshots//" + testCaseName+".png";
	}
}
