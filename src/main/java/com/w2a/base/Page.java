package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
//import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Page extends TopMenu {

	public static WebDriver driver;
	public static Properties or = new Properties();
	public static Properties config = new Properties();
	private static FileInputStream fis;
	public static Logger log = Logger.getLogger(Page.class);
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\com\\w2a\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.createInstance();
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	public static ExtentTest test;
	public static String browser;
	public static TopMenu menu;

	public Page() {
		if (driver == null) {
			Date d = new Date();
			System.setProperty("current.date", d.toString().replace(":", "_").replace(" ", "_"));
			PropertyConfigurator.configure("./src/test/resources/com/w2a/properties/log4j.properties");
			try {
				fis = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\com\\w2a\\properties\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.info("Config properties loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\w2a\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				or.load(fis);
				log.info("OR properties loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Jenkins Browser filter Configuration
			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser");
			} else {
				browser = config.getProperty("browser");
			}
			config.setProperty("browser", browser);

			if (config.getProperty("browser").equals("chrome")) {

				WebDriverManager.chromedriver().setup();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--remote-allow-origins=*");
				co.addArguments("--disable-extensions");
				co.addArguments("--disable-infobars");
				co.addArguments("incognito");
				driver = new ChromeDriver(co);
				log.info("Launching Chrome !!!");

			} else if (config.getProperty("browser").equals("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("Launching Firefox !!!");

			}

//			WebDriver driver = new FirefoxDriver();
			driver.get(config.getProperty("testsiteurl"));
			log.info("Navigated to : " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts()
					.implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));
			wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(config.getProperty("explicit.wait"))));
			menu = new TopMenu();

		}
	}// inside constructor

	public void click(String locatorKey) {
		if (locatorKey.endsWith("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(locatorKey))).click();
		} else if (locatorKey.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(or.getProperty(locatorKey))).click();
		} else if (locatorKey.endsWith("_ID")) {
			driver.findElement(By.id(or.getProperty(locatorKey))).click();
		}
		log.info("Clicking on an Element : " + locatorKey);
		test.log(Status.INFO, "Clicking on an Element : " + locatorKey);
	}

	public void type(String locatorKey, String value) {
		if (locatorKey.endsWith("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(locatorKey))).sendKeys(value);
		} else if (locatorKey.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(or.getProperty(locatorKey))).sendKeys(value);
		} else if (locatorKey.endsWith("_ID")) {
			driver.findElement(By.id(or.getProperty(locatorKey))).sendKeys(value);
		}
		log.info("typing in an Element : " + locatorKey + " entered the value as : " + value);
		test.log(Status.INFO, "typing in an Element : " + locatorKey + " entered the value as : " + value);
	}

	static WebElement dropdown;

	public static void select(String locatorKey, String value) {
		if (locatorKey.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(or.getProperty(locatorKey)));
		} else if (locatorKey.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(or.getProperty(locatorKey)));
		} else if (locatorKey.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(or.getProperty(locatorKey)));
		}
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		log.info("Selecting an Element : " + locatorKey + " selected the value as : " + value);
		test.log(Status.INFO, "Selecting an Element : " + locatorKey + " selected the value as : " + value);
	}

	public static boolean isElementPresent(String locatorKey) {
		try {
			if (locatorKey.endsWith("_XPATH")) {
				driver.findElement(By.xpath(or.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(or.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_ID")) {
				driver.findElement(By.id(or.getProperty(locatorKey)));
			}
			log.info("Finding an Element : " + locatorKey);
			test.log(Status.INFO, "Finding an Element : " + locatorKey);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void verifyEquals(String actual, String expected) throws IOException {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable t) {
			ExtentManager.captureScreenshot();
			// ReportNg
			Reporter.log("<br>" + "<font color=" + "red>" + "Verification Failed with exception: " + t.getMessage()
					+ "</font>" + "</b>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			Reporter.log("<a target=\"_blank\" href=" + ExtentManager.fileName + "><img src=" + ExtentManager.fileName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");

			// Extent Reports
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Verification Failed with exception: "
					+ t.getMessage() + "</font>" + "</b>");
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.fileName).build());
		}
	}
	
	public static void quit() {
		driver.quit();
	}

}// inside the Page Class
